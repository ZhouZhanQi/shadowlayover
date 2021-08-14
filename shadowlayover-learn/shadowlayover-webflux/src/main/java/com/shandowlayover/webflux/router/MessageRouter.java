package com.shandowlayover.webflux.router;

import com.shandowlayover.webflux.handler.MessageHandler;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.BodyExtractor;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/8/11-11:40
 * @desc: 消息路由
 * </pre>
 */
@EnableWebFlux
@Configuration
public class MessageRouter {
    
    @Autowired
    private MessageHandler handler;
    
    @Bean
    public RouterFunction<ServerResponse> restaurantRouter() {
        // return route(GET("/person/{id}", handler::getPerson)
        //         .andRoute(GET("/person", handler::listPeople)
        //         .POST("/person", handler::createPerson));
        // return router;
        
        return route(GET("/person/{id}"), handler::getPerson)
                .andRoute(GET("/person"), handler::listPeople)
                .andRoute(POST("/person"), handler::createPerson);
    }
}
