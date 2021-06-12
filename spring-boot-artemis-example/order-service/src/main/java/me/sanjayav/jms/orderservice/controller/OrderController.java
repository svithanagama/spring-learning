package me.sanjayav.jms.orderservice.controller;

import me.sanjayav.jms.orderservice.domain.OrderDetails;
import me.sanjayav.jms.orderservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

  final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping("/placeOrder")
  public ResponseEntity<Void> placeOrder(@RequestBody OrderDetails orderDetails) {
    System.out.printf("Received order details: [%s]\n", orderDetails);
    orderService.placeOrder(orderDetails);
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }
}
