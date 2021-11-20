package com.shadowlayover.example.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.shadowlayover.common.db.model.BaseModel;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 订单信息
 * </p>
 *
 * @author zhouzhanqi
 * @since 2021-11-17
 */
@Getter
@Setter
public class Order extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单标题
     */
    private String orderTitle;

    /**
     * 订单内容
     */
    private String orderContent;

    /**
     * 通知状态;状态0正常1禁用
     */
    private Integer status;

    /**
     * 备注;备注
     */
    private String remark;


}
