package com.example.ltx.eshare.module.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Liutx
 * @date 2020/12/13 15:37
 * @Description
 */

@Data
@Accessors(chain = true)
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String nameZh;
}
