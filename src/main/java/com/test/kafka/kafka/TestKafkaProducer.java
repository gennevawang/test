package com.test.kafka.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class TestKafkaProducer {

    private final String TOPIC_01 = "TEST.001";
    private KafkaTemplate<String, String> messageProducer;

    @Autowired
    public TestKafkaProducer(KafkaTemplate<String, String> messageProducer) {
        this.messageProducer = messageProducer;
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void sendMessageToKafka() {
        int i =1;
        while( i<5){
            String message = String.valueOf(new Random().nextInt(50));
            messageProducer.send(TOPIC_01, message);
            log.info("KafkaProducer sent : {}", message);
            i++;
        }
    }
}
