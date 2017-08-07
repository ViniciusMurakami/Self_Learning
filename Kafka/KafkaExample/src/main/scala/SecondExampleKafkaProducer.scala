import java.util.{Properties,Date}

import  scala.util.Random

object SecondExampleKafkaProducer {

  import org.apache.kafka.clients.producer.{KafkaProducer,ProducerRecord}

  def main(args: Array[String]): Unit = {
    val events = args(0).toInt
    val topic = args(1)
    val brokers = args(2)

    val random = new Random()
    val props = new Properties()


    props.put("bootstrap.server",brokers)
    props.put("acks",1)
    props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer")

    val producer = new KafkaProducer[String,String](props)
    val tempo = System.currentTimeMillis()
    for(nEventos <- Range(0, events)){
      val runtime = new Date().getTime()
      val ip = "192.168.20." + random.nextInt(255)
      val mensagem = runtime + "," + nEventos + ",www.google.com," + ip
      val data = new ProducerRecord[String,String](topic,ip,mensagem)

      producer.send(data)
    }
    producer.close()
  }
}
