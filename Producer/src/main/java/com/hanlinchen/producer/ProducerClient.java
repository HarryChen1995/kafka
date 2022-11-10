package com.hanlinchen.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import java.util.Properties;

public class ProducerClient {
    final private KafkaProducer kafkaProducer;
    
    public ProducerClient(){
        Properties kafkaProps = new Properties();
        kafkaProps.put("bootstrap.servers", "localhost:9092");
        kafkaProps.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        kafkaProducer = new KafkaProducer<String, String>(kafkaProps);
    }

    public void syncSend(String key , String val){
        try{
            ProducerRecord<String, String> record = new ProducerRecord<>("country", key, val);
            RecordMetadata recordMetadata =  (RecordMetadata) kafkaProducer.send(record).get();
            System.out.println(recordMetadata.toString());
        }catch(Exception e){
              e.printStackTrace();
        }
    }
}
