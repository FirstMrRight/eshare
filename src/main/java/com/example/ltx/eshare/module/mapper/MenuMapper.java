package com.example.ltx.eshare.module.mapper;

import com.example.ltx.eshare.module.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Ltx
 * @since 2020-12-16
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> getAllMenus();
}
