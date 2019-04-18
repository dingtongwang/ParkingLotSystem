package com.tw.test;

import java.util.HashMap;

public class ParkingLotSystem {

  private int capacity;

  private String parkingLotNumber;

  // car number, ticket number
  private HashMap<String, String> carContainer = new HashMap<>();

  public ParkingLotSystem() {
  }

  public ParkingLotSystem(int capacity) {
    this.capacity = capacity;
  }

  public ParkingLotSystem(String parkingLotNumber, int capacity) {
    this.parkingLotNumber = parkingLotNumber;
    this.capacity = capacity;
  }

  public Ticket procParking(Car car) {
    if ("".equals(car.getLicensePlate()) || carContainer.containsKey(car.getLicensePlate())) {
      return null;
    }

    if (isAvailableParkingSpace()) {
      Ticket ticket = new Ticket(car);
      String ticketNumber = ticket.getTicketNumber();
      carContainer.put(car.getLicensePlate(), ticketNumber);
      return ticket;
    }
    return null;
  }

  public Car procGetCar(Ticket ticket) {
    if (isNormalTicket(ticket)) {
      final Car car = new Car();
      carContainer.forEach((k, v)->{
        if (ticket.getTicketNumber().equals(v)) {
          car.setLicensePlate(k);
        }
      });
      carContainer.remove(car.getLicensePlate());
      return car;
    }
    return null;
  }

  private boolean isAvailableParkingSpace() {
    return carContainer.size() < capacity;
  }

  public int getAvailableParkingSpace() {
    return capacity - carContainer.size();
  }

  private boolean isNormalTicket(Ticket ticket) {
    return ticket.getTicketNumber().startsWith("Crazy") && carContainer.containsValue(ticket.getTicketNumber());
  }

  public String getParkingLotNumber() {
    return this.parkingLotNumber;
  }

  public void setParkingLotNumber(String parkingLotNumber) {
    this.parkingLotNumber = parkingLotNumber;
  }

  public boolean isExistSameCarLicensePlate(String carLicensePlate) {
    return carContainer.containsKey(carLicensePlate);
  }
}
