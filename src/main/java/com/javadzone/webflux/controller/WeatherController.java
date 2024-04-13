package com.javadzone.webflux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@RestController
public class WeatherController {

    @GetMapping("/getWeatherDataAsync")
    public Mono<String> getWeatherDataAsync() {
        System.out.println("Real-time Example with Mono:");

        Mono<String> weatherMono = fetchWeatherDataAsync(); // Fetch weather data asynchronously
        weatherMono.subscribe(weather -> System.out.println("Received weather data: " + weather));

        System.out.println("Continuing with other tasks...");

        // Sleep for 6 seconds to ensure weather data retrieval completes
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return weatherMono;
    }


    @GetMapping("getWeatherDataSync")
    public void getWeatherDataSync() {
        System.out.println("Simple Example without Mono:");
        fetchWeatherDataSync(); // Fetch weather data synchronously
        System.out.println("Continuing with other tasks...");

        // Sleep for 6 seconds to ensure weather data retrieval completes
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Mono<String> fetchWeatherDataAsync() {
        System.out.println("Fetching weather data...");
        return Mono.delay(Duration.ofSeconds(5))  // Simulate API call delay of 5 seconds
                .map(delay -> "Weather data: Sunny and 30°C") // Simulated weather data
                .subscribeOn(Schedulers.boundedElastic()); // Execute on separate thread
    }

    public static void fetchWeatherDataSync() {
        System.out.println("Fetching weather data...");
        // Simulate API call delay of 5 seconds
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Weather data: Sunny and 30°C");
    }
}
