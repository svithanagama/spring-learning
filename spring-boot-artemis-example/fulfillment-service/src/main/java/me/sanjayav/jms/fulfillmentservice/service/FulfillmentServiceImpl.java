package me.sanjayav.jms.fulfillmentservice.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import me.sanjayav.jms.fulfillmentservice.domain.FulfillmentDetails;
import me.sanjayav.jms.fulfillmentservice.domain.OrderDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class FulfillmentServiceImpl implements FulfillmentService {

  final JmsTemplate jmsTemplate;

  @Value("${jms.queue.order.fulfillment.queue}")
  String orderFulfillmentQueue;

  public FulfillmentServiceImpl(JmsTemplate jmsTemplate) {
    this.jmsTemplate = jmsTemplate;
  }

  @Override
  @JmsListener(destination = "${jms.queue.order.placement.queue}")
  public void orderReceived(OrderDetails orderDetails) {
    System.out.printf("Received new order details [%s]", orderDetails);
    fulfillOrder(orderDetails);
  }

  @Override
  public void fulfillOrder(OrderDetails orderDetails) {
    try {
      Thread.sleep(2500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    FulfillmentDetails fulfillmentDetails = new FulfillmentDetails(orderDetails.getOrderId(),
        LocalDateTime.now(), LocalDate.now().plusDays(3), orderDetails.getAddress());
    System.out.printf("Sending fulfillment details [%s]", fulfillmentDetails);
    jmsTemplate.convertAndSend(orderFulfillmentQueue, fulfillmentDetails);
  }
}
