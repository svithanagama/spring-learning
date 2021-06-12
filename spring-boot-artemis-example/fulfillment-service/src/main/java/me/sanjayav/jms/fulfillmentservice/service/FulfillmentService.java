package me.sanjayav.jms.fulfillmentservice.service;

import me.sanjayav.jms.fulfillmentservice.domain.OrderDetails;

public interface FulfillmentService {

  void orderReceived(OrderDetails orderDetails);

  void fulfillOrder(OrderDetails orderDetails);

}
