package com.elasticsearch.test.stream.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class Producer {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Producer.class);
        UserElasticData data = new UserElasticData();
        String bootstrapServers = "127.0.0.1:9092";
        String id = "1";

        // create Producer properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // create the producer
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        while(data.getUser(id) != null){
            ProducerRecord<String, String> record =
                new ProducerRecord<String, String>("testingtopic", data.getUser(id));
            logger.info("PRODUCING DATA WITH THE ID {" + id +"}");
            producer.send(record);
            id = Integer.toString(Integer.parseInt(id)+1);
        }

        // flush data
        //producer.flush();
        // flush and close producer
        producer.close();

        System.exit(0);

    }
}
