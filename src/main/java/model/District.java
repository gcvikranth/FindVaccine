package model;

public class District {
  private String state_id;
  private String district_id;
  private String district_name;

  public String getState_id() {
    return state_id;
  }

  public void setState_id(String state_id) {
    this.state_id = state_id;
  }

  public String getDistrict_id() {
    return district_id;
  }

  public void setDistrict_id(String district_id) {
    this.district_id = district_id;
  }

  public String getDistrict_name() {
    return district_name;
  }

  public void setDistrict_name(String district_name) {
    this.district_name = district_name;
  }
}

