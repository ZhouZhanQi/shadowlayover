package com.shadowlayover.common.core.utils;

import com.shadowlayover.common.core.context.ShadowlayoverThreadContext;
import com.shadowlayover.common.core.exceptions.BusinessException;
import com.shadowlayover.common.core.exceptions.FrameworkException;
import com.shadowlayover.common.core.model.ResponseData;
import com.shadowlayover.common.core.model.code.CommonExceptionCode;
import com.shadowlayover.common.core.model.code.CoreExceptionCode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.formula.functions.T;

import java.util.Optional;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/9/29-10:09
 * @desc: 客户端工具类
 * </pre>
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientUtils {

    public static ResponseData<T> serviceCall(ResponseData<T> responseData) {
        Optional<ResponseData<T>> optional = Optional.ofNullable(responseData);
        optional.orElseThrow(() -> new FrameworkException(CoreExceptionCode.SERVICE_CIRCUIT_BREAKER, "", ShadowlayoverThreadContext.getContext().getTraceId()));
        return optional.filter(response -> response.isSuccess()).orElseThrow(() -> {
            ResponseData<T> response = optional.get();
            return new BusinessException(response.getErrMsg(), response.getErrCode());
        });
    }

    /**
     * 不抛出一次获取服务调用结果
     * @param responseData
     * @return
     */
    public static ResponseData<T> serviceCallNoThrow(ResponseData<T> responseData) {
        return Optional.ofNullable(responseData).orElse(ResponseData.fail(CommonExceptionCode.SERVICE_ERROR));
    }
}
