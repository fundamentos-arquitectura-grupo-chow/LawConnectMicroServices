package org.lorem.legalcaseservice.infrastructure.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic legalCaseTopic() {
        return new NewTopic("legalcase-topic", 1, (short) 1);
    }
}