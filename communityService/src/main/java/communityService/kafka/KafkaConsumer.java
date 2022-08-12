package communityService.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "quickstart-events", groupId = "mygroup")
    public void listen(String msg) {
        System.out.println("Consumed message: " + msg);
    }
}
