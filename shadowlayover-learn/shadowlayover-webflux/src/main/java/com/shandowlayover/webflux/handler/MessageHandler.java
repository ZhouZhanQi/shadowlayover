package com.shandowlayover.webflux.handler;

import com.shandowlayover.webflux.router.MessageRouter;
import lombok.Data;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/8/11-11:45
 * @desc:
 * </pre>
 */
@Component
public class MessageHandler {
    
    public Mono<ServerResponse> listPeople(ServerRequest request) {
        List<Person> personList = new ArrayList<>();
        //
        // thread.start();
        // ...
        Flux<Person> people = Flux.fromIterable(personList);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(people, Person.class);
    }
    
    public Mono<ServerResponse> createPerson(ServerRequest request) {
        // ...
        return null;
    }
    
    public Mono<ServerResponse> getPerson(ServerRequest request) {
        // ...
        return null;
    }
    
    @Data
    public class Person {
        private String name;
        private int age;
        
        public Person() {
            this.name = "111";
            this.age = ++ this.age;
        }
    }
}
