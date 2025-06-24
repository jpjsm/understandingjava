package com.example.restservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestServiceApplication {
    // Unregister the OpenTelemetryMeterRegistry from Metrics.globalRegistry and make it available
    // as a Spring bean instead.
    @Bean
    @ConditionalOnClass(name = "io.opentelemetry.javaagent.OpenTelemetryAgent")
    public MeterRegistry otelRegistry() {
    Optional<MeterRegistry> otelRegistry = Metrics.globalRegistry.getRegistries().stream()
    .filter(r -> r.getClass().getName().contains("OpenTelemetryMeterRegistry"))
    .findAny();
    otelRegistry.ifPresent(Metrics.globalRegistry::remove);
    return otelRegistry.orElse(null);
    }

    public static void main(String[] args) {
        SpringApplication.run(RestServiceApplication.class, args);
    }

}
