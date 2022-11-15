package com.hanlinchen.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.errors.SerializationException;

import com.hanlinchen.model.Country;

import lombok.Data;

import java.util.Properties;

@Data
public class CustomSerializeProducerClient {

    private KafkaProducer<String, Country> kafkaProducer;

    public CustomSerializeProducerClient() {
        Properties kafkaProps = new Properties();
        kafkaProps.put("bootstrap.servers", "localhost:9092");
        kafkaProps.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer","com.hanlinchen.serializer.CountrySerializer");
        kafkaProps.put("partitioner.class", "com.hanlinchen.partition.CustomPartitioner");
    
        kafkaProducer = new KafkaProducer<String , Country>(kafkaProps);
    }

    public void syncSend(String key , Country val){
        try{
            ProducerRecord<String, Country> record = new ProducerRecord<>("country", key, val);
            RecordMetadata recordMetadata =  (RecordMetadata) kafkaProducer.send(record).get();
            System.out.println(recordMetadata.toString());
        }catch(Exception e){
              e.printStackTrace();
              throw new SerializationException("Error when serializing Country to byte[]");
        }
    }
}
