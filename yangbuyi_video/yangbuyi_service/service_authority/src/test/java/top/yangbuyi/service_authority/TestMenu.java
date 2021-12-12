///*==============================================================================
// = - Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
// =============================================================================*/
//
//package top.yangbuyi.service_authority;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.util.CollectionUtils;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import lombok.RequiredArgsConstructor;
//import top.yangbuyi.common_utils.constant.Constants;
//import top.yangbuyi.service_authority.entity.Menu;
//import top.yangbuyi.service_authority.mapper.MenuMapper;
//import top.yangbuyi.service_authority.service.MenuService;
//
//import java.awt.*;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
///**
// * @program: yangbuyi_video
// * @ClassName: TestMenu
// * @create: 2021-09-30 19:12
// * @author: Yang Shuai
// * @since： JDK1.8
// * @TestMenu: $
// **/
////@RunWith(SpringRunner.class)
////@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//// 获取启动类，加载配置，确定装载 Spring 程序的装载方法，它回去寻找 主配置启动类（被 @SpringCloudApplication 注解的）
//@SpringBootTest
//// 让 JUnit 运行 Spring 的测试环境， 获得 Spring 环境的上下文的支持
//@RunWith(SpringRunner.class)
//public class TestMenu {
//    @Autowired
//    private MenuMapper menuMapper;
//    @Autowired
//    private ConfigurableApplicationContext context;
//
//    @Test
//    public void remoteWebsocketClient(){
//        String[] beanDefinitionNames = context.getBeanDefinitionNames();
//        for (String beanDefinitionName : beanDefinitionNames) {
//            System.out.println(beanDefinitionName);
//        }
//    }
//
//    @Test
//    public void test1 () {
//        // 1. 查询出所以的菜单列表
//        final List<Menu> menus = menuMapper.selectList(new LambdaQueryWrapper<Menu>());
//        final List<Menu> menuList = menus.stream().filter(e -> "0".equals(e.getPid()))
//                                            .map(e -> {
//                                                e.setLevel(1);
//                                                e.setChildMenuList(listMenu(e, menus));
//                                                return e;
//                                            }).collect(Collectors.toList());
//        System.err.println(menuList);
//
//
//        // 最终结果
//        System.out.println(JSONObject.toJSONString(menuList));
//    }
//
//
//    @Test
//    public void t22 () {
//        List<Menu> selectMenuList = menuMapper.selectList(null);
//        //1.构建树形菜单
//        List<Menu> menuList = buildTreeMenuList(selectMenuList);
//        final List<JSONObject> jsonObjects = buildMenuWebList(menuList);
//        System.out.println(jsonObjects);
//
//
//    }
//
//    /**
//     * 构建树菜单列表
//     *
//     * @param menus 菜单
//     * @return {@link List<Menu>}
//     */
//    private List<Menu> buildTreeMenuList (List<Menu> menus) {
//        return menus.stream().filter(e -> e.getPid().equals(Constants.MENU_PID)).map(e -> {
//            e.setLevel(1);
//            e.setChildMenuList(listMenu(e, menus));
//            return e;
//        }).collect(Collectors.toList());
//    }
//
//
//    /**
//     * 构造路由菜单
//     *
//     * @param menuList 菜单列表
//     * @return {@link List<JSONObject>}
//     */
//    public List<JSONObject> buildMenuWebList (List<Menu> menuList) {
//        List<JSONObject> menus = new ArrayList<>();
//        if (menuList.size() == 1) {
//            Menu topNode = menuList.get(0);
//            //左侧一级菜单
//            List<Menu> oneMenuList = topNode.getChildMenuList();
//            for (Menu one : oneMenuList) {
//                JSONObject oneMenu = new JSONObject();
//                oneMenu.put("path", one.getPath());
//                oneMenu.put("component", one.getComponent());
//                oneMenu.put("redirect", "noredirect");
//                oneMenu.put("name", "name_" + one.getId());
//                oneMenu.put("hidden", false);
//
//                JSONObject oneMeta = new JSONObject();
//                oneMeta.put("title", one.getName());
//                oneMeta.put("icon", one.getIcon());
//                oneMenu.put("meta", oneMeta);
//
//                List<JSONObject> children = new ArrayList<>();
//                List<Menu> twoMenuList = one.getChildMenuList();
//                for (Menu two : twoMenuList) {
//                    JSONObject twoMenu = new JSONObject();
//                    twoMenu.put("path", two.getPath());
//                    twoMenu.put("component", two.getComponent());
//                    twoMenu.put("name", "name_" + two.getId());
//                    twoMenu.put("hidden", false);
//
//                    JSONObject twoMeta = new JSONObject();
//                    twoMeta.put("title", two.getName());
//                    twoMenu.put("meta", twoMeta);
//
//                    children.add(twoMenu);
//
//                    List<Menu> threeMenuList = two.getChildMenuList();
//                    for (Menu three : threeMenuList) {
//                        if (StringUtils.isEmpty(three.getPath())) continue;
//
//                        JSONObject threeMenu = new JSONObject();
//                        threeMenu.put("path", three.getPath());
//                        threeMenu.put("component", three.getComponent());
//                        threeMenu.put("name", "name_" + three.getId());
//                        threeMenu.put("hidden", true);
//
//                        JSONObject threeMeta = new JSONObject();
//                        threeMeta.put("title", three.getName());
//                        threeMenu.put("meta", threeMeta);
//
//                        children.add(threeMenu);
//                    }
//                }
//                oneMenu.put("children", children);
//                menus.add(oneMenu);
//            }
//        }
//        return menus;
//    }
//
//
//    public List<Menu> listMenu (Menu menu, List<Menu> menuList) {
//        return menuList.stream().filter(e -> e.getPid().equals(menu.getId()))
//                       .map(e -> {
//                           e.setLevel(menu.getLevel() + 1);
//                           // 递归找到本次获取到的子菜单的子菜单(无限层级菜单)
//                           e.setChildMenuList(listMenu(e, menuList));
//                           return e;
//                       }).collect(Collectors.toList());
//
//    }
//
//    @Test
//    public void t2 () {
//        System.out.println(deleteChildrenMenu("1195268474480156673"));
//    }
//
//    private List<String> deleteChildrenMenu (String id) {
//        return menuMapper.selectList(new LambdaQueryWrapper<Menu>()
//                                             .eq(Menu::getPid, id)
//                                             .select(Menu::getId))
//                       .stream().map(e -> {
//                    deleteChildrenMenu(e.getId());
//                    return e;
//                })
//                       .map(Menu::getId).collect(Collectors.toList());
//    }
//
//}
