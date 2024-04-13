package com.javadzone.webflux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;

@RestController
public class WebFluxController {

    Mono<String> firstMono = Mono.just("Welcome to the Spring WebFlux tutorial!");
    Mono<String> secondMono = Mono.just("Explore the powerful features of WebFlux.");
    Mono<String> thirdMono = Mono.just("Learn how to build reactive applications with ease.");

    // Endpoint for returning a greeting message
    @GetMapping("/")
    public Mono<String> get(){
        return Mono.just("Hello world");
    }

    // Endpoint for combining two Monos using zipWith
    @GetMapping("/combineTwoMonos")
    public Mono<Tuple2<String, String>> combinedMonos() {
        Mono<Tuple2<String, String>> tuple2Mono = firstMono.zipWith(secondMono);
        tuple2Mono.subscribe(data -> {
            System.out.println(data.getT1());
            System.out.println(data.getT2());
        });
        return tuple2Mono;
    }

    // Endpoint for combining multiple Monos using zip
    @GetMapping("/combineMultiple")
    public Mono<Tuple3<String, String, String>> combineMultipleMonos(){
        Mono<Tuple3<String, String, String>> zip = Mono.zip(firstMono, secondMono, thirdMono);
        zip.subscribe(data->{
            System.out.println(data.getT1());
            System.out.println(data.getT2());
            System.out.println(data.getT3());
        });

        return zip;
    }

    // Endpoint for transforming the data emitted by a Mono using map operator
    @GetMapping("/monoMap")
    public Mono<String> monoMap() {
        return firstMono.map(data->{
            System.out.println(data.toUpperCase());
            return data.toUpperCase();
        });
    }

    // Endpoint for transforming the item emitted by a Mono asynchronously using flatMap operator
    @GetMapping("/monoFlatMap")
    public Mono<String[]> monoFlatMap() {
        Mono<String[]> mono = firstMono.flatMap(data -> Mono.just(data.split(" ")));
        mono.subscribe(data -> {
           for(String s : data){
               System.out.println(s);
           }
        });
        return mono;
    }

    // Endpoint for transforming the item emitted by a Mono into a Publisher and forwarding its emissions into a Flux using flatMapMany operator
    @GetMapping("/monoFlatMapMany")
    public Flux<String> monoFlatMapMany() {
        Flux<String> stringFlux = firstMono.flatMapMany(data -> Flux.just(data.split("  ")));
        stringFlux.subscribe(System.out :: println);
        return stringFlux;
    }

    // Endpoint for concatenating two Monos using concatWith
    @GetMapping("/concatwith")
    public  Flux<String> concatwith() {
        Flux<String> stringFlux = firstMono.concatWith(secondMono);
        return stringFlux;
    }
}
