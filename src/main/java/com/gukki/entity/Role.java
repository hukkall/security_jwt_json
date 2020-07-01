package com.gukki.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author DvDxD
 * @since 2020-06-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Role extends Model<Role> {

    private static final long serialVersionUID=1L;

    @TableId(value = "ROLE_ID", type = IdType.AUTO)
    private Integer roleId;

    private String roleName;


    @Override
    protected Serializable pkVal() {
        return this.roleId;
    }

}
