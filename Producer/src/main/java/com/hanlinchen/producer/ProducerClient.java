package com.hanlinchen.producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import lombok.Data;

import java.util.Properties;

@Data
public class ProducerClient {
    final private KafkaProducer<String, String> kafkaProducer;
    
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

    public void asyncSend(String key, String val){
 
            ProducerRecord<String, String> record = new ProducerRecord<>("country", key, val);
            kafkaProducer.send(record, new  Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e == null){
                        System.out.println(recordMetadata.toString());
                    }
                    else{
                        e.printStackTrace();
                    }
                }
            });
    }

    
}
