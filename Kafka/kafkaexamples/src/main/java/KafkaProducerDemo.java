import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import java.util.Properties;

/**
 * Created by vmurakami on 09/07/17.
 */
public class KafkaProducerDemo {
    public static void main (String [] args){
        Properties properties = new Properties();

        //Kafka Bootstrap Server
        properties.setProperty("bootstrap.servers","localhost:9092");
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer",StringSerializer.class.getName());
        //producer acks
        properties.setProperty("acks","1");
        properties.setProperty("retries","3");
        properties.setProperty("linger.ms","1");
        //topic name

        Producer<String, String> producer = new org.apache.kafka.clients.producer.KafkaProducer<String, String>(properties);
        for(int key=0; key<=1000;key++){
            ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>("test_kafka_java",
                    Integer.toString(key) ,"Message number: " + Integer.toString(key));
            producer.send(producerRecord);
        }

        //producer.flush();
        producer.close();

    }
}
