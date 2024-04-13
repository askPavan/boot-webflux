package com.javadzone.webflux.test;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class WeatherServiceAsync {
    // Asynchronous method using Mono to fetch weather data from an API
    public static Mono<String> fetchWeatherData() {
        System.out.println("Fetching weather data...");
        return Mono.delay(Duration.ofSeconds(5))  // Simulate API call delay of 5 seconds
                .map(delay -> "Weather data: Sunny and 25°C") // Simulated weather data
                .subscribeOn(Schedulers.boundedElastic()); // Execute on separate thread
    }

    public static void main(String[] args) {
        System.out.println("Real-time Example with Mono:");

        Mono<String> weatherMono = fetchWeatherData(); // Fetch weather data asynchronously
        weatherMono.subscribe(weather -> System.out.println("Received weather data: " + weather));

        System.out.println("Continuing with other tasks...");

        // Sleep for 6 seconds to ensure weather data retrieval completes
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
