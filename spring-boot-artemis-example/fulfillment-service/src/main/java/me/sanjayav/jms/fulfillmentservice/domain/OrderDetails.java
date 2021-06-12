package me.sanjayav.jms.fulfillmentservice.domain;

public class OrderDetails {

  private Long orderId;
  private Integer quantity;
  private String item;
  private Address address;

  public OrderDetails() {
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public String getItem() {
    return item;
  }

  public void setItem(String item) {
    this.item = item;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return "OrderDetails{" +
        "orderId=" + orderId +
        ", quantity=" + quantity +
        ", item='" + item + '\'' +
        ", address=" + address +
        '}';
  }
}
