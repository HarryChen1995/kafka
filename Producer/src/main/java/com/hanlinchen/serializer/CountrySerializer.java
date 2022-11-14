package com.hanlinchen.serializer;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanlinchen.model.Country;

public class CountrySerializer implements Serializer<Country> {
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String topic, Country data) {
        try{
            if(data == null) return null;
            return objectMapper.writeValueAsBytes(data);

        }catch(Exception exception){
           exception.printStackTrace();
        }
        return null;
    }

    @Override
    public void close(){

    }
}
