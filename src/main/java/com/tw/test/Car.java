package com.tw.test;

public class Car {

  private String licensePlate = "";

  public Car() {
  }

  public Car(String licensePlate) {
    this.licensePlate = licensePlate;
  }

  public String getLicensePlate() {
    return this.licensePlate;
  }

  public void setLicensePlate(String licensePlate) {
    this.licensePlate = licensePlate;
  }
}
