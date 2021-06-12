package me.sanjayav.jms.orderservice.service;

import me.sanjayav.jms.orderservice.domain.FulfillmentDetails;
import me.sanjayav.jms.orderservice.domain.OrderDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

  final JmsTemplate jmsTemplate;

  @Value("${jms.queue.order.placement.queue}")
  String placeOrderQueue;

  public OrderServiceImpl(JmsTemplate jmsTemplate) {
    this.jmsTemplate = jmsTemplate;
  }

  @Override
  @JmsListener(destination = "${jms.queue.order.fulfillment.queue}")
  public void orderFulfilled(FulfillmentDetails fulfillmentDetails) {
    System.out.printf("Received order fulfillment details [%s]", fulfillmentDetails);
  }

  @Override
  public void placeOrder(OrderDetails orderDetails) {
    jmsTemplate.convertAndSend(placeOrderQueue, orderDetails);
  }
}
