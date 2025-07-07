package org.lorem.communicationservice.infrastructure.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic communicationTopic() {
        return new NewTopic("communication-topic", 1, (short) 1);
    }
}