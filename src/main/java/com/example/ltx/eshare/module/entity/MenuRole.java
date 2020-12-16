package com.example.ltx.eshare.module.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Liutx
 * @date 2020/12/16 22:07
 * @Description
 */
@Data
@Accessors(chain = true)
public class MenuRole {
    private static final long serialVersionUID = 1L;

    @TableId(type=IdType.AUTO)
    private Integer id;
    private Integer mid;
    private Integer rid;
}
