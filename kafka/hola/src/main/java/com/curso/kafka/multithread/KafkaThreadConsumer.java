package com.curso.kafka.multithread;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class KafkaThreadConsumer extends Thread{ // esto es un hilo, por lo tanto hereda de la clase thread
    private final KafkaConsumer<String, String> consumer; // inyecta consumidor

    private final AtomicBoolean close = new AtomicBoolean(false); // booleano en falso por defecto
    private static final Logger log = LoggerFactory.getLogger(KafkaThreadConsumer.class);
    public KafkaThreadConsumer(KafkaConsumer<String,String> consumer){
        this.consumer = consumer;
    }
    @Override
    public void run(){
        consumer.subscribe(Arrays.asList("kafka-topic"));
        try {
            while(!close.get()){
                ConsumerRecords<String,String> consumerRecords = consumer.poll(Duration.ofMillis(100));
                for(ConsumerRecord<String,String> consumerRecord : consumerRecords){
                    log.info("Offset = {}, Partition = {}, Key = {}, Value = {}", consumerRecord.offset(),
                            consumerRecord.partition(), consumerRecord.key(),consumerRecord.value());
                }
            }
        }
        catch (WakeupException e){
            if (!close.get()){
                throw e;
            }
        }finally {
            consumer.close();
        }

    }

    public void shutdown(){ // para interrumpir/apagar consumer en caso de poll demasiado largo
        close.set(true);
        consumer.wakeup();
    }
}
