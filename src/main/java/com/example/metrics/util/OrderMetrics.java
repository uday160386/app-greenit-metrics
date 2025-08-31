package com.example.metrics.util;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

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
