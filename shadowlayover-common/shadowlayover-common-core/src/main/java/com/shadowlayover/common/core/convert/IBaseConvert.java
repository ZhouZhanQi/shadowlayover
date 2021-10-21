package com.shadowlayover.common.core.convert;

import com.shadowlayover.common.core.model.BaseBo;
import com.shadowlayover.common.core.model.BaseDomain;
import com.shadowlayover.common.core.model.BaseDto;
import com.shadowlayover.common.core.model.BaseVo;

import java.util.List;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/10/21-18:24
 * @desc: 基础转换器
 * </pre>
 */
public interface IBaseConvert<D extends BaseDomain, B extends BaseBo, DT extends BaseDto, V extends BaseVo> {
    
    /**
     * bean对象转换
     * @param source
     * @return
     */
    B do2Bo(D source);
    
    /**
     * bean对象列表转换
     * @param sourceList
     * @return
     */
    List<B> do2Bo(List<D> sourceList);
}
