package com.javadzone.webflux.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest
public class FluxServiceTest {

    @Autowired
    private FluxService fluxService;

    @Test
    void testFlux() {
        fluxService.getFlux().subscribe(data -> {
            System.out.println(data);
        });
    }

    @Test
    void testGetFluxList() {
        fluxService.getFluxList().subscribe(data -> {
            System.out.println(data);
        });
    }

    @Test
    void testFilter() {
        Disposable subscribe = fluxService.filterFlux().subscribe(System.out::println);
        System.out.println("filtered text: " + subscribe); // CCC
    }

    @Test
    void testFlatMap() {
        fluxService.flatMapExample().subscribe(data -> {
            System.out.println("FlatMap: " + data);
        });
    }

    @Test
    void tranformFluxExample() {
        fluxService.tranformFluxExample();
    }

    @Test
    void ifExample() {
        Flux<String> flux = fluxService.defaultIfEmptyExample("Third");
        flux.subscribe(data->{
            System.out.println("data: "+data);
        });
        StepVerifier.create(flux).expectNext("Third").verifyComplete();
    }

    @Test
    void getBlankFlux() {
        Flux<Object> blankFlux = fluxService.getBlankFlux();
        StepVerifier.create(blankFlux).expectNext().verifyComplete();
    }
}
