package com.example.kafka;

import static java.lang.System.*;

import java.util.*;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.*;

public final class BasicProducerSample {
  public static void main(String[] args) throws InterruptedException {
    final var topic = "hello-world";

    final Map<String, Object> config = 
        Map.of(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.0.80:9092", 
               ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName(), 
               ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName(), 
               ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);

    try (var producer = new KafkaProducer<String, String>(config)) {
      while (true) {
        final var key = UUID.randomUUID().toString();
        final var value = new Date().toString();
        out.format("                                                           sent record: '%s' ->  '%s'%n", 
        key, value);

        final Callback callback = (metadata, exception) -> {
          out.format("Published with metadata: %s, error: %s%n", 
                     metadata, exception);
        };

        // publish the record, handling the metadata in the callback
        producer.send(new ProducerRecord<>(topic, key, value), callback);

        // wait a second before publishing another
        Thread.sleep(100);
      }
    }
  }
}