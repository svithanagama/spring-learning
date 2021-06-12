package me.sanjayav.jms.fulfillmentservice.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.util.HashMap;
import java.util.Map;
import javax.jms.ConnectionFactory;
import me.sanjayav.jms.fulfillmentservice.domain.FulfillmentDetails;
import me.sanjayav.jms.fulfillmentservice.domain.OrderDetails;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
public class JmsConfiguration {

  @Bean
  public ConnectionFactory connectionFactory() {
    return new ActiveMQConnectionFactory();
  }

  @Bean
  public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory,
      MessageConverter messageConverter) {
    JmsTemplate template = new JmsTemplate();
    template.setConnectionFactory(connectionFactory);
    template.setMessageConverter(messageConverter);
    return template;
  }

  @Bean // Serialize message content to json using TextMessage
  public MessageConverter jacksonJmsMessageConverter(ObjectMapper objectMapper) {
    MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
    converter.setTargetType(MessageType.TEXT);
    converter.setTypeIdPropertyName("_type");
    Map<String, Class<?>> idMappings = new HashMap<>();
    idMappings.put(FulfillmentDetails.class.getSimpleName(), FulfillmentDetails.class);
    idMappings.put(OrderDetails.class.getSimpleName(), OrderDetails.class);
    converter.setTypeIdMappings(idMappings);
    converter.setObjectMapper(objectMapper); // if you have a customised ObjectMapper
    return converter;
  }

  @Bean
  public JmsListenerContainerFactory<?> jmsListenerContainerFactory(
      ConnectionFactory connectionFactory,
      DefaultJmsListenerContainerFactoryConfigurer configurer, MessageConverter messageConverter) {
    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
    factory.setMessageConverter(messageConverter);
    configurer.configure(factory, connectionFactory);
    return factory;
  }

  @Bean
  public ObjectMapper objectMapper() {
    JavaTimeModule module = new JavaTimeModule();
    module.addSerializer(LocalDateTimeSerializer.INSTANCE);
    module.addSerializer(LocalDateSerializer.INSTANCE);
    return new ObjectMapper()
        .setSerializationInclusion(JsonInclude.Include.NON_NULL)
        .registerModule(module);
  }
}
