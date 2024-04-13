package com.javadzone.webflux.test;

public class WeatherServiceSync {
    // Synchronous method simulating fetching weather data from an API
    public static void fetchWeatherData() {
        System.out.println("Fetching weather data...");
        // Simulate API call delay of 5 seconds
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Weather data: Sunny and 25°C");
    }

    public static void main(String[] args) {
        System.out.println("Simple Example without Mono:");

        fetchWeatherData(); // Fetch weather data synchronously

        System.out.println("Continuing with other tasks...");

        // Sleep for 6 seconds to ensure weather data retrieval completes
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
