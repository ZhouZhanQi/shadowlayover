package com.shadowlayover.message.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shadowlayover.common.db.model.BaseModel;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 系统通知
 * </p>
 *
 * @author zhouzhanqi
 * @since 2021-09-27
 */
@Getter
@Setter
@TableName("sys_notice")
public class SysNotice extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 通知类型;1通知 2公告
     */
    private Integer noticeType;

    /**
     * 通知标题;公告标题
     */
    private String noticeTitle;

    /**
     * 通知内容;公告内容
     */
    private String noticeContent;

    /**
     * 通知状态;状态0正常1禁用
     */
    private Integer status;

    /**
     * 备注;备注
     */
    private String remark;


}
