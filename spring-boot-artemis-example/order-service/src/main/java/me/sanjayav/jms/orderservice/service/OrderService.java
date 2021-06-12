package me.sanjayav.jms.orderservice.service;

import me.sanjayav.jms.orderservice.domain.FulfillmentDetails;
import me.sanjayav.jms.orderservice.domain.OrderDetails;

public interface OrderService {

  void placeOrder(OrderDetails orderDetails);

  void orderFulfilled(FulfillmentDetails fulfillmentDetails);

}
