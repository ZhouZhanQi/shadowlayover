package com.shadowlayover.oauth.mapper;

import com.shadowlayover.oauth.model.domain.SysDept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 系统租户部门 Mapper 接口
 * </p>
 *
 * @author zhouzhanqi
 * @since 2021-07-27
 */
@Mapper
public interface SysDeptMapper extends BaseMapper<SysDept> {

    /**
     * 根据用户Id查询部门信息
     * @param userId
     * @return
     */
    SysDept getByUserId(@Param("userId") Long userId);
}
