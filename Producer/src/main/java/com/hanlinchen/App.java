package com.hanlinchen;
import java.util.HashMap;
import java.util.Map;

import com.hanlinchen.model.Country;
import com.hanlinchen.producer.CustomSerializeProducerClient;

public class App 
{
    public static void main( String[] args )
    {

        Map<String, Country> countries = new HashMap<>();
        countries.put("America", Country.builder().capital("Washington").name("America").population(10000.0).build());
        countries.put("Asia", Country.builder().capital("Beijin").name("China").population(610000.0).build());
        
        final CustomSerializeProducerClient  producerClient = new CustomSerializeProducerClient();
       
        countries.forEach((key, val)->{
            producerClient.syncSend(key, val);
        });
            
    
        producerClient.getKafkaProducer().flush();
        producerClient.getKafkaProducer().close();
   
    }
}
