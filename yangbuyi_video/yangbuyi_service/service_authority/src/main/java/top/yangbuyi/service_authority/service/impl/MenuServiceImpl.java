/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_authority.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import top.yangbuyi.common_utils.constant.Constants;
import top.yangbuyi.common_utils.utils.ResponseResult;
import top.yangbuyi.service_authority.entity.Menu;
import top.yangbuyi.service_authority.entity.RoleMenu;
import top.yangbuyi.service_authority.entity.User;
import top.yangbuyi.service_authority.mapper.MenuMapper;
import top.yangbuyi.service_authority.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.yangbuyi.service_authority.service.RoleMenuService;
import top.yangbuyi.service_authority.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单权限 服务实现类
 * </p>
 *
 * @author yangbuyi
 * @since 2021-09-27
 */
@Service
@RequiredArgsConstructor
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    private final RoleMenuService roleMenuService;
    private final UserService userService;

    /**
     * 菜单列表
     *
     * @return {@link List<Menu>}
     */
    @Override
    public List<Menu> getMenuList () {
        return buildTreeMenuList(baseMapper.selectList(new LambdaQueryWrapper<Menu>()));
    }

    /**
     * 删除菜单通过id
     * 1. 根据id遍历出子级的子节点
     * 2. 批量删除
     *
     * @param id id
     * @return {@link ResponseResult}
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult removeMenuById (String id) {
        return ResponseResult.toOk(baseMapper.deleteBatchIds(deleteChildrenMenu(id)) > 0);
    }

    /**
     * 根据角色Id查询菜单
     *
     * @param roleId 角色id
     * @return {@link List<Menu>}
     */
    @Override
    public List<Menu> selectMenuByRoleId (String roleId) {
        // 1. 查询出全部的菜单
        final List<Menu> menus = baseMapper.selectList(new LambdaQueryWrapper<Menu>());
        final List<RoleMenu> roleMenus = roleMenuService.list(new LambdaQueryWrapper<RoleMenu>().eq(RoleMenu::getRoleId, roleId));
        // 2. 根据角色关联表ID查询菜单Id
        for (Menu menu : menus) {
            for (RoleMenu roleMenu : roleMenus) {
                if (menu.getId().equals(roleMenu.getPermissionId())) {
                    menu.setSelect(true);
                }
            }
        }
        return buildTreeMenuList(menus);
    }

    /**
     * 保存角色菜单关系船
     *
     * @param roleId  角色id
     * @param menuIds 菜单id
     * @return {@link ResponseResult}
     */
    @Override
    public ResponseResult saveRoleMenuRelationShip (String roleId, String[] menuIds) {
        roleMenuService.remove(new LambdaQueryWrapper<RoleMenu>().eq(RoleMenu::getRoleId, roleId));
        final List<RoleMenu> list = new ArrayList<>();
        Arrays.stream(menuIds).forEach(e -> {
            list.add(new RoleMenu().setRoleId(roleId).setPermissionId(e));
        });
        return ResponseResult.toOk(roleMenuService.saveBatch(list));
    }

    /**
     * 根据用户Id查询拥有的权限
     *
     * @param id id
     * @return {@link List<String>}
     */
    @Override
    public List<String> selectPermissionValueByUserId (String id) {
        List<String> selectPermissionValueList = null;
        if (this.isSysAdmin(id)) {
            //如果是系统管理员，获取所有权限
            selectPermissionValueList = baseMapper.selectAllMenuValue();
        } else {
            //根据用户查询菜单权限
            selectPermissionValueList = baseMapper.selectMenuValueByUserId(id);
        }
        return selectPermissionValueList;
    }

    @Override
    public List<JSONObject> selectPermissionByUserId (String id) {
        List<Menu> selectMenuList = null;
        if (this.isSysAdmin(id)) {
            //如果是超级管理员，获取所有菜单
            selectMenuList = baseMapper.selectList(null);
        } else {
            selectMenuList = baseMapper.selectMenuByUserId(id);
        }
        //1.构建树形菜单
        List<Menu> menuList = buildTreeMenuList(selectMenuList);
        //2.返回给前端json数据
        return buildMenuWebList(menuList);
    }

    /**
     * 构造路由菜单
     *
     * @param menuList 菜单列表
     * @return {@link List<JSONObject>}
     */
    public List<JSONObject> buildMenuWebList (List<Menu> menuList) {
        List<JSONObject> menus = new ArrayList<>();
        if (menuList.size() == 1) {
            Menu topNode = menuList.get(0);
            //左侧一级菜单
            List<Menu> oneMenuList = topNode.getChildMenuList();
            for (Menu one : oneMenuList) {
                JSONObject oneMenu = new JSONObject();
                oneMenu.put("path", one.getPath());
                oneMenu.put("component", one.getComponent());
                oneMenu.put("redirect", "noredirect");
                oneMenu.put("name", "name_" + one.getId());
                oneMenu.put("hidden", false);

                JSONObject oneMeta = new JSONObject();
                oneMeta.put("title", one.getName());
                oneMeta.put("icon", one.getIcon());
                oneMenu.put("meta", oneMeta);

                List<JSONObject> children = new ArrayList<>();
                List<Menu> twoMenuList = one.getChildMenuList();
                for (Menu two : twoMenuList) {
                    JSONObject twoMenu = new JSONObject();
                    twoMenu.put("path", two.getPath());
                    twoMenu.put("component", two.getComponent());
                    twoMenu.put("name", "name_" + two.getId());
                    twoMenu.put("hidden", false);

                    JSONObject twoMeta = new JSONObject();
                    twoMeta.put("title", two.getName());
                    twoMenu.put("meta", twoMeta);

                    children.add(twoMenu);

                    List<Menu> threeMenuList = two.getChildMenuList();
                    for (Menu three : threeMenuList) {
                        if (StringUtils.isEmpty(three.getPath())) continue;

                        JSONObject threeMenu = new JSONObject();
                        threeMenu.put("path", three.getPath());
                        threeMenu.put("component", three.getComponent());
                        threeMenu.put("name", "name_" + three.getId());
                        threeMenu.put("hidden", true);

                        JSONObject threeMeta = new JSONObject();
                        threeMeta.put("title", three.getName());
                        threeMenu.put("meta", threeMeta);

                        children.add(threeMenu);
                    }
                }
                oneMenu.put("children", children);
                menus.add(oneMenu);
            }
        }
        return menus;
    }

    /**
     * 构建树菜单列表
     *
     * @param menus 菜单
     * @return {@link List<Menu>}
     */
    private List<Menu> buildTreeMenuList (List<Menu> menus) {
        return menus.stream().filter(e -> e.getPid().equals(Constants.MENU_PID)).map(e -> {
            e.setLevel(1);
            e.setChildMenuList(buildMenuList(e, menus));
            return e;
        }).collect(Collectors.toList());
    }

    /**
     * 删除子节点(无线层级)菜单
     *
     * @param id id
     * @return {@link List<String>}
     */
    private List<String> deleteChildrenMenu (String id) {
        return baseMapper.selectList(new LambdaQueryWrapper<Menu>().eq(Menu::getPid, id).select(Menu::getId)).stream()
                       .map(e -> {
                           deleteChildrenMenu(e.getId());
                           return e;
                       }).map(Menu::getId).collect(Collectors.toList());
    }

    /**
     * 递归找到本次获取到的子菜单的子菜单(无限层级菜单)
     *
     * @param menu  当前循环的某个菜单(父级)
     * @param menus 全部的菜单集合
     * @return {@link List<Menu>}
     */
    private List<Menu> buildMenuList (Menu menu, List<Menu> menus) {
        return menus.stream().filter(e -> e.getPid().equals(menu.getId()))
                       .map(e -> {
                           // 用于前端树形渲染
                           e.setLevel(menu.getLevel() + 1);
                           e.setChildMenuList(buildMenuList(e, menus));
                           return e;
                       }).collect(Collectors.toList());
    }

    /**
     * 判断用户是否系统管理员
     *
     * @param userId
     * @return
     */
    private boolean isSysAdmin (String userId) {
        User user = userService.getById(userId);

        if (null != user && Constants.ADMIN.equalsIgnoreCase(user.getUsername())) {
            return true;
        }
        return false;
    }

}
