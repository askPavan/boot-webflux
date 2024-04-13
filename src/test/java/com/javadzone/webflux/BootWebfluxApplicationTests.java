package com.javadzone.webflux;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;

@SpringBootTest
class BootWebfluxApplicationTests {
    @Test
    public void test() {
        // Creating a Mono publisher with test data
        Mono<String> monoPublisher = Mono.just("Testdata");

        // Subscribing to the Mono publisher
        monoPublisher.subscribe(new CoreSubscriber<String>() {
            // Callback method invoked when subscription starts
            @Override
            public void onSubscribe(Subscription s) {
                System.out.println("on subscribe....");
                s.request(1);
            }

            // Callback method invoked when data is emitted
            @Override
            public void onNext(String data) {
                System.out.println("data: " + data);
            }

            // Callback method invoked when an error occurs
            @Override
            public void onError(Throwable t) {
                System.out.println("exception occured: " + t.getMessage());
            }

            // Callback method invoked when subscription is completed
            @Override
            public void onComplete() {
                System.out.println("completed the implementation....");
            }
        });
    }
}