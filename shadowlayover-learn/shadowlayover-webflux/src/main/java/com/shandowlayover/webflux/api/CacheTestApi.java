package com.shandowlayover.webflux.api;

import com.shadowlayover.common.core.model.ResponseData;
import com.shandowlayover.webflux.service.CacheTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/8/17-16:28
 * @desc:
 * </pre>
 */
@RestController
public class CacheTestApi {

    private final CacheTestService cacheTestService;

    @Autowired
    public CacheTestApi(CacheTestService cacheTestService) {
        this.cacheTestService = cacheTestService;
    }

    @GetMapping("/testCache")
    public ResponseData testCache() {
        return ResponseData.success(cacheTestService.getName("aaa"));
    }

}
