package com.gukki.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
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
public class PermissionRole extends Model<PermissionRole> {

    private static final long serialVersionUID=1L;

    private Integer premissionId;

    private Integer roleId;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
