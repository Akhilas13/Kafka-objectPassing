package com.stackroute.kafka.config;
import com.stackroute.kafka.domain.User;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import com.stackroute.kafka.domain.KafkaReciever;
import org.springframework.kafka.support.serializer.JsonDeserializer;


import java.util.HashMap;
import java.util.Map;
//import java.util.function.KafkaReciever;


@EnableKafka
@Configuration

public class ConsumerConfigruation {

    private static String BOOTSTRAP_SERVERS_CONFIG;
    private static String KEY_DESERIALIZER_CLASS_CONFIG ;
    private static String VALUE_DESERIALIZER_CLASS_CONFIG;
    private static String GROUP_ID_CONFIG;
  //  private static String AUTO_OFFSET_RESET_CONFIG="earliest";

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;


    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "Json");

        return props;
    }
    @Bean
    public ConsumerFactory<String, User> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(),
                new JsonDeserializer<>(User.class));
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, User> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, User> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());

        return factory;
    }

    @Bean
    public KafkaReciever receiver() {
        return new KafkaReciever();
    }
}






