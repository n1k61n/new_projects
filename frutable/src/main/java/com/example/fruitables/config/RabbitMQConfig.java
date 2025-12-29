package com.example.fruitables.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Mövcud OTP sabitləri
    public static final String QUEUE = "otp_queue";
    public static final String EXCHANGE = "otp_exchange";
    public static final String ROUTING_KEY = "otp_routingKey";

    // --- YENİ ƏLAVƏ EDİLƏNLƏR (Contact/Bildiriş üçün) ---
    public static final String CONTACT_QUEUE = "contact_queue";
    public static final String CONTACT_EXCHANGE = "contact_exchange";
    public static final String CONTACT_ROUTING_KEY = "contact_routingKey";

    // OTP üçün mövcud Bean-lər
    @Bean
    public Queue queue() {
        return new Queue(QUEUE);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    // --- YENİ Bean-lər (Contact üçün) ---

    @Bean
    public Queue contactQueue() {
        return new Queue(CONTACT_QUEUE);
    }

    @Bean
    public TopicExchange contactExchange() {
        return new TopicExchange(CONTACT_EXCHANGE);
    }

    @Bean
    public Binding contactBinding(Queue contactQueue, TopicExchange contactExchange) {
        return BindingBuilder.bind(contactQueue).to(contactExchange).with(CONTACT_ROUTING_KEY);
    }
}