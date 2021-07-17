package com.bdd.project.agro_notifier.configuration;

import com.bdd.project.agro_notifier.model.AgroNotificationsEvents;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

//@EnableKafka
@Configuration
public class KafkaConfiguration {
    @Autowired
    private ApiConfiguration apiConfig;

    @Bean
    public ProducerFactory<String, AgroNotificationsEvents> agroProducerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,apiConfig.getBootstrupServer());
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, AgroNotificationsEvents> agroKafkaTemplate() {
        return new KafkaTemplate<>(agroProducerFactory());
    }


    @Bean
    public ConsumerFactory<String, AgroNotificationsEvents> consumerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,apiConfig.getBootstrupServer());
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaConsumerFactory<>(
                config,
                new StringDeserializer(),
                new JsonDeserializer<>(AgroNotificationsEvents.class));
    }


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, AgroNotificationsEvents>
    AgroKafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, AgroNotificationsEvents> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }


}
