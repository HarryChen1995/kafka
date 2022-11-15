package com.hanlinchen.partition;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.InvalidRecordException;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.utils.Utils;

public class CustomPartitioner implements Partitioner{
    @Override
    public void configure(Map<String, ?> configs) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        
        List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
        int numPartitions = partitions.size();
        if ((keyBytes == null) || (!(key instanceof String))) throw new InvalidRecordException("key is not string or null");
        if (((String) key).equals("America")) return numPartitions - 1; 
        
        return (Math.abs(Utils.murmur2(keyBytes)) % (numPartitions-1));
        
    }


    @Override
    public void close() {
        // TODO Auto-generated method stub
        
    }
}
