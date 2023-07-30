package com.curso.kafka.producers;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class KafkaaProducer {
    public static final Logger log = LoggerFactory.getLogger(KafkaaProducer.class);

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092"); //broker server de kafka
        props.put("acks", "1");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("linger.ms", "6");

        try (Producer<String, String> producer = new KafkaProducer<>(props)) { // se esta enviando un msje con los siguientes valores, deberia verse en el consumer
            for (int i = 0; i < 90; i++) { // se esta enviando 10000 mensajes
                producer.send(new ProducerRecord<String, String>("kafka-topic","key", String.valueOf(i)));
                //el envio de este mensaje se hace de forma asincrona
                //al ser asincrono, no se asegura el orden de los mensajes, puede enviarse primero el ultimo, etc

            }
            producer.flush();
        }
        log.info("Processing time = {} ms", (System.currentTimeMillis() - startTime)); // tiempo de procesamiento
    }
}