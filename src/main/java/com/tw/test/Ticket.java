package com.tw.test;

public class Ticket {

  private String ticketNumber = "";

  public Ticket(String ticketNumber) {
    this.ticketNumber = ticketNumber;
  }

  public Ticket(Car car) {
    this.ticketNumber = "Crazy" + car.getLicensePlate();
  }

  public String getTicketNumber() {
    return this.ticketNumber;
  }
}
