package com.javadzone.webflux;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple4;

@SpringBootTest
public class MonoTest {
    @Test
    void testMono(){
        Mono<String> firstMono = Mono.just("First Mono");
        Mono<String> secondMono = Mono.just("Second Mono");
        Mono<String> thirdMono = Mono.just("Third Mono");
        Mono<String> fourthMono = Mono.just("Fourth Mono");

        // Subscribing to Monos and printing the data
        firstMono.subscribe(data -> {
            System.out.println("Subscribed to firstMono: "+data);
        });

        secondMono.subscribe(data -> {
            System.out.println("Subscribed to secondMono: "+ data);
        });

        // Combining Monos using zipWith and zip operators
        System.out.println("----------- zipWith() ------------ ");
        Mono<Tuple2<String, String>> tuple2Mono = firstMono.zipWith(secondMono);
        tuple2Mono.subscribe(data -> {
            System.out.println(data.getT1());
            System.out.println(data.getT2());
        });

        System.out.println("----------- zip() ------------ ");
        Mono<Tuple4<String, String, String, String>> zip = Mono.zip(firstMono, secondMono, thirdMono, fourthMono);
        zip.subscribe(data ->{
            System.out.println(data.getT1());
            System.out.println(data.getT2());
            System.out.println(data.getT3());
            System.out.println(data.getT4());
        });

        // Transforming Mono data using map and flatMap
        System.out.println("----------- map() ------------ ");
        Mono<String> map = firstMono.map(String::toUpperCase);
        map.subscribe(System.out:: println);

        System.out.println("----------- flatmap() ------------ ");
        //flatmap(): Transform the item emitted by this Mono asynchronously, returning the value emitted by another Mono (possibly changing the value type).
        Mono<String[]> flatMapMono = firstMono.flatMap(data -> Mono.just(data.split(" ")));
        flatMapMono.subscribe(data-> {
            for(String d: data) {
                System.out.println(d);
            }
            //or
            //Arrays.stream(data).forEach(System.out::println);
        });

        // Converting Mono into Flux using flatMapMany
        System.out.println("---------- flatMapMany() ------------- ");

        //flatMapMany(): Transform the item emitted by this Mono into a Publisher, then forward its emissions into the returned Flux.
        Flux<String> stringFlux = firstMono.flatMapMany(data -> Flux.just(data.split(" ")));
        stringFlux.subscribe(System.out::println);


        // Concatenating Monos using concatWith
        System.out.println("----------- concatwith() ------------ ");

        Flux<String> concatMono = firstMono.concatWith(secondMono);
        concatMono.subscribe(System.out::println);
    }

}
