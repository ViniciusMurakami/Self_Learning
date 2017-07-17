import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Arrays;
import java.util.Properties;

/**
 * Created by vmurakami on 09/07/17.
 */
public class KafkaConsumerDemo {
    public static void main(String[] args) {
        Properties properties = new Properties();

        //Kafka Bootstrap Server
        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("key.deserializer",StringDeserializer.class.getName());
        properties.setProperty("value.deserializer",StringDeserializer.class.getName());

        properties.setProperty("group.id","test");
        properties.setProperty("enable.auto.commit","true");
        //properties.setProperty("auto.commit.interval.ms","1000");
        properties.setProperty("auto.offset.reset","earliest");
        //Kafka Consumer

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(properties);
        kafkaConsumer.subscribe(Arrays.asList("test_kafka_java"));

        while (true){
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(100);
            for(ConsumerRecord<String, String> consumerRecord : consumerRecords){
//                consumerRecord.key();
//                consumerRecord.value();
//                consumerRecord.offset();
//                consumerRecord.partition();
//                consumerRecord.topic();
//                consumerRecord.timestamp();

                  System.out.println("Partition: " + consumerRecord.partition() +
                                    ", Offsets: " + consumerRecord.offset() +
                                    ", Key: " + consumerRecord.key() +
                                    ", Value: " + consumerRecord.value());

            }
            kafkaConsumer.commitSync();
        }
    }
}
