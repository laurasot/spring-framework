package com.kafka.kafkacurso;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.CompletableFuture;

@SpringBootApplication
public class KafkaCursoApplication implements CommandLineRunner {
    private KafkaTemplate<String,String> kafkaTemplate;
    private static final Logger log = LoggerFactory.getLogger(KafkaCursoApplication.class);

    public KafkaCursoApplication(KafkaTemplate<String,String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }
    @KafkaListener(topics = "kafka-topic", groupId = "kafka-curso")
    public void listen(String message){
        log.info("Message receive {}", message);
    }

    public static void main(String[] args) {
        SpringApplication.run(KafkaCursoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
       CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send("kafka-topic","Sample message");
        future.thenAccept(sendResult->{
            RecordMetadata recordMetadata = sendResult.getRecordMetadata();
            log.info("Message sent to  topic= {}, partition= {}, offset= {}",
                    recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset());

        }).exceptionally(throwable -> {
            log.error("Failed to message: {}", throwable.getMessage());
            return null;
        }) ;
    }
}
