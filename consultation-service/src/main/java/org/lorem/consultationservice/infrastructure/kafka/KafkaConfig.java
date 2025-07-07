package org.lorem.consultationservice.infrastructure.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic consultationTopic() {
        return new NewTopic("consultation-topic", 1, (short) 1);
    }
}
