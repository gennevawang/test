package com.test.kafka.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class TestKafkaConsumers {

    private final String TOPIC_01 = "TEST.001";

    @KafkaListener(id = "batch-listener", topics = TOPIC_01, containerFactory = "kafkaListenerContainerFactory")
    public void messageListener(List<String> messages, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                                @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("Recieved {} messages", messages.size());
        messages.forEach(message ->
                log.info("MessageListener recieved message : {}", message));
    }
}
