package com.shadowlayover.oauth.service.oauth;

import com.shadowlayover.oauth.model.constants.BaseOauthConstant;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Objects;

/**
 * @author zhouzhanqi
 * @date 2021/9/12 3:46 上午
 * @desc
 */
@Slf4j
@Setter
@Service
public class ClientDetailsServices extends JdbcClientDetailsService {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public ClientDetailsServices(DataSource dataSource) {
        super(dataSource);
    }

    @Bean
    @Primary
    public ClientDetailsServices clientDetailService() {
        ClientDetailsServices clientDetailsService = new ClientDetailsServices(dataSource);
        clientDetailsService.setRedisTemplate(redisTemplate);
        return clientDetailsService;
    }


    /**
     * 从redis里读取ClientDetails
     *
     * @param clientId 客户端ID
     * @return ClientDetails
     * @throws InvalidClientException 非法客户端异常
     */
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
        ClientDetails clientDetails = (ClientDetails) redisTemplate.opsForValue().get(clientKey(clientId));
        if (Objects.isNull(clientDetails)) {
            clientDetails = getCacheClient(clientId);
        }
        clientDetails.getAuthorizedGrantTypes().add(BaseOauthConstant.CLIENT_CREDENTIALS);
        return clientDetails;
    }

    /**
     * 自定义语句查询，并将数据同步至redis
     *
     * @param clientId 客户端ID
     * @return ClientDetails
     */
    private ClientDetails getCacheClient(String clientId) {
        ClientDetails clientDetails = null;

        try {
            clientDetails = super.loadClientByClientId(clientId);
            if (Objects.isNull(clientDetails)) {
                redisTemplate.opsForValue().set(clientKey(clientId), clientDetails);
                log.debug(">>> cache clientId: {}, clientDetails: {}", clientId, clientDetails);
            }
        } catch (Exception e) {
            log.error(">>> cache client error clientId: {}, message: {}", clientId, e.getMessage(), e);
        }
        return clientDetails;
    }

    private String clientKey(String clientId) {
        return BaseOauthConstant.CLIENT_TABLE + ":" + clientId;
    }
}
