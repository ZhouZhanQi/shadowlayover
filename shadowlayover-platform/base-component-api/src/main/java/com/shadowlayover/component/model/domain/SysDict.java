package com.shadowlayover.component.model.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.shadowlayover.common.db.model.BaseModel;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 系统词典
 * </p>
 *
 * @author zhouzhanqi
 * @since 2021-09-27
 */
@Getter
@Setter
@TableName("sys_dict")
public class SysDict extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
    private Long id;

    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 模块名称
     */
    private String moduleName;

    /**
     * 词典编码
     */
    private String dictCode;

    /**
     * 词典名称
     */
    private String dictName;

    /**
     * 词典描述
     */
    private String dictDesc;

    /**
     * 状态;0正常1禁用
     */
    private Integer status;

    /**
     * 排序
     */
    private Integer sort;


}
