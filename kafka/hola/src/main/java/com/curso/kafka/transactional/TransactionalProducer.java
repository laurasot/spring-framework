package com.curso.kafka.transactional;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class TransactionalProducer {
    private static final Logger log = LoggerFactory.getLogger(TransactionalConsumer.class);
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092"); //broker server de kafka
        props.put("acks", "all"); // para transacciones si o si, debe ser all el ack
        props.put("transactional.id", "producer-1-id");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("linger.ms", "6");

        try (Producer<String, String> producer = new KafkaProducer<>(props)) { // se esta enviando un msje con los siguientes valores, deberia verse en el consumer
            try {
                producer.initTransactions();
                producer.beginTransaction();
                for (int i = 0; i < 90; i++) { // se esta enviando 10000 mensajes
                    producer.send(new ProducerRecord<String, String>("kafka-topic", String.valueOf(i), "kafka-value"));
                    //el envio de este mensaje se hace de forma asincrona
                    //al ser asincrono, no se asegura el orden de los mensajes, puede enviarse primero el ultimo, etc
                    if (i == 50){
                        throw new Exception("Unexpected Exception");
                    }
                }
                producer.commitTransaction();// si todo sale bien, se commitea
                producer.flush();
            }catch (Exception e){
                log.error("Error ", e);
                producer.abortTransaction(); // en caso de error se aborta la transaccion
            }

        }
        log.info("Processing time = {} ms", (System.currentTimeMillis() - startTime));
    }
}
