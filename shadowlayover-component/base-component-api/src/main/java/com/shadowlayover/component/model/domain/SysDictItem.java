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
@TableName("sys_dict_item")
public class SysDictItem extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
    private Long id;

    /**
     * 词典Id
     */
    private Long dictId;

    /**
     * 词典项编码
     */
    private String dictItemCode;

    /**
     * 词典项名称
     */
    private String dictItemName;

    /**
     * 词典项值
     */
    private String dictItemValue;

    /**
     * 词典项描述
     */
    private String dictItemDesc;

    /**
     * 是否为系统默认;0否1是
     */
    private Boolean isDefault;

    /**
     * 状态;0启用1禁用
     */
    private Integer status;

    /**
     * 排序
     */
    private Integer sort;
}
