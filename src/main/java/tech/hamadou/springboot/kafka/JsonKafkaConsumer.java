package tech.hamadou.springboot.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import tech.hamadou.springboot.model.OrderEvent;

@Service
public class JsonKafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaConsumer.class);

    @KafkaListener(
            topics = "${spring.kafka.topics.orders-topic}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(final OrderEvent event) {
        LOGGER.info(String.format("Order event reçu -> %s", event.toString()));
    }
}
