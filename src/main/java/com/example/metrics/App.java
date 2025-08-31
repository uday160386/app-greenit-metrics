package com.example.metrics;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

    @Component
    public class OrderMetrics {
    private final Counter orders;

    public OrderMetrics(MeterRegistry registry) {
        this.orders = Counter.builder("app.orders.total")
            .description("Number of orders placed")
            .register(registry);
    }
    public void increment() { orders.increment(); }
}
}