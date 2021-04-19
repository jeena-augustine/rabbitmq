package com.example.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.service.RabbitMqListener;

@Configuration
public class RabbitMqConfig {

	@Value("${rabbitmq.queue}")
	String queue;

	@Value("${spring.rabbitmq.username}")
	String username;

	@Value("${spring.rabbitmq.password}")
	String password;

	@Bean
	Queue queue() {
		return new Queue(queue, false);
	}

	@Bean
	MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {

		SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
		simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
		simpleMessageListenerContainer.setQueues(queue());
		simpleMessageListenerContainer.setMessageListener(new RabbitMqListener());
		return simpleMessageListenerContainer;
	}

	/*
	 * @Bean ConnectionFactory connectionFactory() {
	 * 
	 * CachingConnectionFactory cachingConnectionFactory=new
	 * CachingConnectionFactory(); cachingConnectionFactory.setUsername(username);
	 * cachingConnectionFactory.setPassword(password); return
	 * cachingConnectionFactory; }
	 * 
	 * @Bean MessageListenerContainer messageListenerContainer() {
	 * 
	 * SimpleMessageListenerContainer container=new
	 * SimpleMessageListenerContainer();
	 * container.setConnectionFactory(connectionFactory());
	 * container.setQueues(queue()); container.setMessageListener(new
	 * RabbitMqListener()); return container; }
	 * 
	 */
}
