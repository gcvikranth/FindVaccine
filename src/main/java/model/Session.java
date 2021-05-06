package model;

import java.util.List;

public class Session {
  private String center_id;
  private String name;
  private String address;
  private String pincode;
  private String date;
  private String min_age_limit;
  private String fee_type;
  private String vaccine;
  private Double available_capacity;
  private List<String> slots;
  private String from;
  private String to;

  public String getCenter_id() {
    return center_id;
  }

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  public String getPincode() {
    return pincode;
  }

  public String getDate() {
    return date;
  }



  public String getFee_type() {
    return fee_type;
  }

  public String getVaccine() {
    return vaccine;
  }

  public String getMin_age_limit() {
    return min_age_limit;
  }

  public void setMin_age_limit(String min_age_limit) {
    this.min_age_limit = min_age_limit;
  }

  public Double getAvailable_capacity() {
    return available_capacity;
  }

  public List<String> getSlots() {
    return slots;
  }

  public void setSlots(List<String> slots) {
    this.slots = slots;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }
}

