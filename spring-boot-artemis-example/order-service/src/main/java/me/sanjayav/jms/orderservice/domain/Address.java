package me.sanjayav.jms.orderservice.domain;

public class Address {

  private String street;
  private String suburb;
  private String postcode;

  public Address() {
  }

  public Address(String street, String suburb, String postcode) {
    this.street = street;
    this.suburb = suburb;
    this.postcode = postcode;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getSuburb() {
    return suburb;
  }

  public void setSuburb(String suburb) {
    this.suburb = suburb;
  }

  public String getPostcode() {
    return postcode;
  }

  public void setPostcode(String postcode) {
    this.postcode = postcode;
  }

  @Override
  public String toString() {
    return "Address{" +
        "street='" + street + '\'' +
        ", suburb='" + suburb + '\'' +
        ", postcode='" + postcode + '\'' +
        '}';
  }
}
