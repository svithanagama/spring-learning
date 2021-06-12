package me.sanjayav.jms.orderservice.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class FulfillmentDetails {

  private Long orderId;
  private LocalDateTime fulfillmentDateTime;
  private LocalDate expectedDeliveryDate;
  private Address deliveryAddress;

  public FulfillmentDetails() {
  }

  public FulfillmentDetails(Long orderId, LocalDateTime fulfillmentDateTime,
      LocalDate expectedDeliveryDate, Address deliveryAddress) {
    this.orderId = orderId;
    this.fulfillmentDateTime = fulfillmentDateTime;
    this.expectedDeliveryDate = expectedDeliveryDate;
    this.deliveryAddress = deliveryAddress;
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public LocalDateTime getFulfillmentDateTime() {
    return fulfillmentDateTime;
  }

  public void setFulfillmentDateTime(LocalDateTime fulfillmentDateTime) {
    this.fulfillmentDateTime = fulfillmentDateTime;
  }

  public LocalDate getExpectedDeliveryDate() {
    return expectedDeliveryDate;
  }

  public void setExpectedDeliveryDate(LocalDate expectedDeliveryDate) {
    this.expectedDeliveryDate = expectedDeliveryDate;
  }

  public Address getDeliveryAddress() {
    return deliveryAddress;
  }

  public void setDeliveryAddress(Address deliveryAddress) {
    this.deliveryAddress = deliveryAddress;
  }

  @Override
  public String toString() {
    return "FulfillmentDetails{" +
        "orderId=" + orderId +
        ", fulfillmentDateTime=" + fulfillmentDateTime +
        ", expectedDeliveryDate=" + expectedDeliveryDate +
        ", deliveryAddress=" + deliveryAddress +
        '}';
  }
}
