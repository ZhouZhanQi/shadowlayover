package com.shadowlayover.adminuser.api.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.shadowlayover.common.db.model.BaseModel;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色身份关联
 * </p>
 *
 * @author zhouzhanqi
 * @since 2021-07-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRolePost extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色Id
     */
    @TableField("role_id")
    private Long roleId;

    /**
     * 身份Id
     */
    @TableField("post_id")
    private Long postId;

    /**
     * 创建人
     */
    @TableField("created_by")
    private Integer createdBy;

    /**
     * 创建时间
     */
    @TableField("created_time")
    private LocalDateTime createdTime;

    /**
     * 更新人
     */
    @TableField("updated_by")
    private Integer updatedBy;

    /**
     * 更新时间
     */
    @TableField("updated_time")
    private LocalDateTime updatedTime;


}
