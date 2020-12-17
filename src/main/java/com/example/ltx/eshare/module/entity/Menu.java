package com.example.ltx.eshare.module.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author Liutx
 * @date 2020/12/16 22:04
 * @Description
 */

@Data
@Accessors(chain = true)
public class Menu {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String pattern;
    /**
     * 当前menu需要那些角色访问
     */
    private List<Role> roles;
}
