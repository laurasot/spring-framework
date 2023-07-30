package com.curso.kafka.consumers;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class KafkaaConsumer {
    private static final Logger log = LoggerFactory.getLogger(KafkaaConsumer.class);
    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("group.id", "kafkaa1-group"); // id del consumer group
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        // el try se utiliza para invocar el metodo close
        try(KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {
            //TopicPartition topicPartition = new TopicPartition("kafka-topic", 4);
            //consumer.assign(Arrays.asList(topicPartition));
            //consumer.seek(topicPartition,50);
            consumer.subscribe(Arrays.asList("kafka-topic")); // se puede suscribir a un topic o a muchos
            //TopicPartition topicPartition = new TopicPartition("kafka-topic",)
            //consumer.assign();
            while (true){
                 ConsumerRecords<String,String> consumerRecords = consumer.poll(Duration.ofMillis(100));
                 for(ConsumerRecord<String,String> consumerRecord : consumerRecords){
                    log.info("Offset = {}, Partition = {}, Key = {}, Value = {}", consumerRecord.offset(), consumerRecord.partition(),
                            consumerRecord.key(),consumerRecord.value());
                            consumer.commitSync();
                 }
            }
        }
    }
}
