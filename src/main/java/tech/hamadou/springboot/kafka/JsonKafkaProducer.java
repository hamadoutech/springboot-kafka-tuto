package tech.hamadou.springboot.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import tech.hamadou.springboot.model.OrderEvent;

@Service
public class JsonKafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaProducer.class);

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;
    private final String ordersTopicName;

    public JsonKafkaProducer(final KafkaTemplate<String, OrderEvent> kafkaTemplate,
                             final @Value("${spring.kafka.topics.orders-topic}") String ordersTopicName) {
        this.kafkaTemplate = kafkaTemplate;
        this.ordersTopicName = ordersTopicName;
    }

    public void envoyerMessage(final OrderEvent orderEvent) {
        LOGGER.info(String.format("Event order à envoyer : %s", orderEvent.toString()));

        Message<OrderEvent> message = MessageBuilder
                .withPayload(orderEvent)
                .setHeader(KafkaHeaders.TOPIC, ordersTopicName)
                .build();

        kafkaTemplate.send(message);
    }

}
