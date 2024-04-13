package com.javadzone.webflux.service;

import com.javadzone.webflux.beans.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Arrays;

@Service
public class FluxService {

    /**
     Useful for providing static data or examples in Flux streams.
     */
    public Flux<String> getFlux() {
        return Flux.just("First", "Second", "Third", "Fourth", "Fifth");
    }

    /**
     * Useful for converting collections into reactive streams.
     */
    public Flux<User> getFluxList() {
        return Flux.fromIterable(Arrays.asList(new User("Pavan", "pavan@123"),
                        new User("Kiran", "kiran@123")))
                .cast(User.class);
    }

    /**
     * Useful for selecting specific elements based on a condition.
     */
    public Flux<String> filterFlux() {
        return getFlux().filter(data -> data.equals("CCC"));
    }

    /**
     * Useful for asynchronous processing of each element.
     */
    public Flux<String> flatMapExample() {
        return getFlux().flatMap(data -> Flux.just(data)).delayElements(Duration.ofMillis(3000));
    }

    /*
        while you can achieve similar results without transform by chaining operators directly on the Flux,
        transform provides a cleaner, more modular approach to defining and applying Flux transformations,
        promoting code reuse, readability, and maintainability.
     */

    public void tranformFluxExample() {
        Flux<Integer> originalFlux = Flux.range(1, 10);
        //without transform method
        Flux<Integer> integerFlux = originalFlux.map(i -> i * 2).filter(i -> i % 3 != 0).publishOn(Schedulers.parallel());
        integerFlux.subscribe(data -> System.out.println(data)); //2 4 6 8 10 14 16 20

        //with transform method.
        Flux<Integer> transformedFlux = originalFlux.transform(flux -> {
            return flux.map(i -> i * 2).filter(i -> i % 3 != 0).subscribeOn(Schedulers.parallel());
        });
        transformedFlux.subscribe(data -> System.out.println(data));
    }

    /**
     * Filters elements in the Flux stream based on the provided string, returning a default value if empty.
     * Ensures graceful handling of empty streams by providing a default value.
     */
    public Flux<String> defaultIfEmptyExample(String str) {
        return getFlux().filter(data -> data.contains(str)).defaultIfEmpty("Doesn't contians: " + str);
    }

    /**
     * Returns an empty Flux stream.
     */
    public Flux<Object> getBlankFlux() {
        return Flux.empty();
    }

}
