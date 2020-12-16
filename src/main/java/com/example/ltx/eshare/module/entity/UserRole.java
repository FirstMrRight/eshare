package com.example.ltx.eshare.module.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Liutx
 * @date 2020/12/16 22:09
 * @Description
 */
@Data
@Accessors(chain = true)
public class UserRole {
    private static final long serialVersionUID = 1L;

    @TableId(type= IdType.AUTO)
    private Integer id;
    private Integer uid;
    private Integer rid;
}
