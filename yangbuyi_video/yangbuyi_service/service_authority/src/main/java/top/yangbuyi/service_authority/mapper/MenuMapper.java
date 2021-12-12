/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_authority.mapper;

import top.yangbuyi.service_authority.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 菜单权限 Mapper 接口
 * </p>
 *
 * @author yangbuyi
 * @since 2021-09-27
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据用户Id查询用户用于的菜单
     *
     * @param id id
     * @return {@link List<Menu>}
     */
    List<Menu> selectMenuByUserId (String id);

    /**
     * 管理员所有菜单
     *
     * @return {@link List<String>}
     */
    List<String> selectAllMenuValue ();

    /**
     * 根据用户Id查询菜单权限信息
     *
     * @param id id
     * @return {@link List<String>}
     */
    List<String> selectMenuValueByUserId (String id);
}
