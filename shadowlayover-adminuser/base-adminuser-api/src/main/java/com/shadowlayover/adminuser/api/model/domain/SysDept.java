package com.shadowlayover.adminuser.api.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.shadowlayover.common.db.model.BaseModel;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统租户部门
 * </p>
 *
 * @author zhouzhanqi
 * @since 2021-07-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDept extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
    private Long id;

    /**
     * 部门名称
     */
    @TableField("dept_name")
    private String deptName;

    /**
     * 部门负责人
     */
    private String leader;

    /**
     * 部门电话
     */
    private String phone;

    /**
     * 部门邮件
     */
    private String email;

    /**
     * 部门状态
     */
    private Integer status;

    /**
     * 部门排序
     */
    @TableField("order_num")
    private Integer orderNum;

    /**
     * 父部门Id
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 部门所属租户
     */
    @TableField("tenant_id")
    private Long tenantId;

    /**
     * 是否删除
     */
    @TableField("is_deleted")
    private Boolean isDeleted;

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
