package com.hanlinchen;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.Properties;
import java.util.HashMap;
import java.util.Map;;
public class App 
{
    public static void main( String[] args )
    {
        
        Properties kafkaProps = new Properties();
        kafkaProps.put("bootstrap.servers", "localhost:9092");
        kafkaProps.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer producer = new KafkaProducer<String, String>(kafkaProps);
        Map<String, String> countries = new HashMap<>();
        countries.put("america", "Washinton");
        countries.put("china", "Beijin");

        try {
            countries.forEach((key, val)->{
                ProducerRecord<String, String> record = new ProducerRecord<>("country", key, val);
                producer.send(record);
            });
            
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
