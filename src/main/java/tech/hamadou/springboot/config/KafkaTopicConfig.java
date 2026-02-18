package tech.hamadou.springboot.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    private final String ordersTopicName;
    private final String messagesTopicName;

    public KafkaTopicConfig(final @Value("${spring.kafka.topics.orders-topic}") String ordersTopicName,
                            final @Value("${spring.kafka.topics.messages-topic}") String messagesTopicName) {
        this.ordersTopicName = ordersTopicName;
        this.messagesTopicName = messagesTopicName;
    }

    @Bean
    public NewTopic hamatechTopic() {
        return TopicBuilder.name(messagesTopicName)
                .build();
    }

    @Bean
    public NewTopic ordersTopic() {
        return TopicBuilder.name(ordersTopicName)
                .build();
    }

}
