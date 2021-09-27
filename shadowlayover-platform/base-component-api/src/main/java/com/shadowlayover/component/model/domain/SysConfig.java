package com.shadowlayover.component.model.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.shadowlayover.common.db.model.BaseModel;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 系统配置
 * </p>
 *
 * @author zhouzhanqi
 * @since 2021-09-27
 */
@Getter
@Setter
@TableName("sys_config")
public class SysConfig extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
    private Long id;

    /**
     * 配置类型
     */
    private Integer configType;

    /**
     * 配置key
     */
    private String configKey;

    /**
     * 配置value
     */
    private String configValue;

    /**
     * 是否为系统默认
     */
    private Boolean isDefault;

    private String remark;
}
