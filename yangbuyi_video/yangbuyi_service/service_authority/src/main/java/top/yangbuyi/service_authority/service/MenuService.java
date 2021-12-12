/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_authority.service;

import com.alibaba.fastjson.JSONObject;
import top.yangbuyi.common_utils.utils.ResponseResult;
import top.yangbuyi.service_authority.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单权限 服务类
 * </p>
 *
 * @author yangbuyi
 * @since 2021-09-27
 */
public interface MenuService extends IService<Menu> {

    /**
     * 把菜单列表
     *
     * @return {@link List<Menu>}
     */
    List<Menu> getMenuList ();


    /**
     * 删除菜单通过id
     *
     * @param id id
     * @return {@link ResponseResult}
     */
    ResponseResult removeMenuById (String id);


    /**
     * 根据角色Id查询菜单
     *
     * @param roleId 角色id
     * @return {@link List<Menu>}
     */
    List<Menu> selectMenuByRoleId (String roleId);

    /**
     * 保存角色菜单关系船
     *  @param roleId  角色id
     * @param menuIds 菜单id
     * @return
     */
    ResponseResult saveRoleMenuRelationShip (String roleId, String[] menuIds);


    /**
     * 根据用户Id查询拥有的权限
     *
     * @param id id
     * @return {@link List<String>}
     */
    List<String> selectPermissionValueByUserId (String id);

    /**
     * 根据用户ID查询用户菜单权限
     * @param id
     * @return
     */
    List<JSONObject> selectPermissionByUserId (String id);
}
