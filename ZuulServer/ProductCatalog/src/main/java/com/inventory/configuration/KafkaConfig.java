package com.inventory.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.google.gson.Gson;
import com.inventory.domain.Product;

@Configuration
public class KafkaConfig {

	@Bean
	public ProducerFactory<String, Product>  producerFactory(){
		
		Map<String,Object> config=new HashMap();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		
		return new DefaultKafkaProducerFactory<String, Product> (config);
		
	}
	
	
	@Bean
	public KafkaTemplate<String, Product> kafkaTemplate(){
		return new KafkaTemplate<String, Product>(producerFactory());
	}
	
	   @Bean
	    public ConsumerFactory<String, String> consumerFactory(){
	        Map<String, Object> config = new HashMap<>();

	        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	        config.put(ConsumerConfig.GROUP_ID_CONFIG, "myGroupId");

	        return new  DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new StringDeserializer());
	    }

	    @Bean
	    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(){
	        ConcurrentKafkaListenerContainerFactory<String, String> concurrentKafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();

	        concurrentKafkaListenerContainerFactory.setConsumerFactory(consumerFactory());

	        return concurrentKafkaListenerContainerFactory;
	    }
	    

	
	  @Bean public Gson jsonConverter(){ return new Gson(); }
	 
	
}
