package communityService.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    public static final String topic = "quickstart-events";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void publishToTopic(String msg) {
        System.out.println("Publishing to topic");
        kafkaTemplate.send(topic, msg);
    }
}
