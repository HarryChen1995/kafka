package com.hanlinchen;
import java.util.Properties;
import java.util.HashMap;
import java.util.Map;
import com.hanlinchen.producer.ProducerClient;
public class App 
{
    public static void main( String[] args )
    {

        Map<String, String> countries = new HashMap<>();
        countries.put("america", "Washinton");
        countries.put("china", "Beijin");
        
        final ProducerClient producerClient = new ProducerClient();
       
        countries.forEach((key, val)->{
            producerClient.syncSend(key, val);
        });
            
   
    }
}
