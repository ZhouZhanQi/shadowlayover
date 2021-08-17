package com.shandowlayover.webflux.api;

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
    
    @Autowired
    private CacheTestService cacheTestService;
    
    @GetMapping("/test")
    public String testCache() {
        return cacheTestService.getName();
    }
}
