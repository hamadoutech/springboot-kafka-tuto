package tech.hamadou.springboot.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String messagesTopicName;

    public KafkaProducer(final KafkaTemplate<String, String> kafkaTemplate,
                         final @Value("${spring.kafka.topics.messages-topic}") String messagesTopicName) {
        this.kafkaTemplate = kafkaTemplate;
        this.messagesTopicName = messagesTopicName;
    }

    public void envoyerMessage(final String message) {
        kafkaTemplate.send(messagesTopicName, message);
        LOGGER.info(String.format("Message envoyé : %s", message));
    }
}
