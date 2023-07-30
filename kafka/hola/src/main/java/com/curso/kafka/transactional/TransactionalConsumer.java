package com.curso.kafka.transactional;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class TransactionalConsumer {
    private static final Logger log = LoggerFactory.getLogger(TransactionalConsumer.class);
    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers","localhost:9092");
        props.setProperty("group.id","kafkaa1-group"); // id del consumer group
        props.setProperty("enable.auto.commit","true");
        props.setProperty("isolation.level","read_committed"); //trae todos los mensajes a los que se le hicieron commit
        props.setProperty("auto.commit.interval.ms","1000");
        props.setProperty("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        // el try se utiliza para invocar el metodo close
        try(KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {
            consumer.subscribe(Arrays.asList("kafka-topic")); // se puede suscribir a un topic o a muchos
            while (true){
                ConsumerRecords<String,String> consumerRecords = consumer.poll(Duration.ofMillis(100));
                for(ConsumerRecord<String,String> consumerRecord : consumerRecords){
                    log.info("Offset = {}, Partition = {}, Key = {}, Value = {}", consumerRecord.offset(), consumerRecord.partition(),
                            consumerRecord.key(),consumerRecord.value());
                }
            }
        }
    }
}
