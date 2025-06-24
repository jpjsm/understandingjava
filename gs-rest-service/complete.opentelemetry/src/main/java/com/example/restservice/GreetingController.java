package com.example.restservice;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Custom metrics must be registered with a MeterRegistry provided by Spring Boot (aka micrometer)
import io.micrometer.core.instrument.MeterRegistry;


@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	// for custom metrics
	private final MeterRegistry registry;

	// Use constructor injection to get the MeterRegistry
	public GreetingController(MeterRegistry registry) {
		this.registry = registry;
	  }

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		// Add a counter tracking the greeting calls by name
		registry.counter("greetings.total", "name", name).increment();

		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
}
