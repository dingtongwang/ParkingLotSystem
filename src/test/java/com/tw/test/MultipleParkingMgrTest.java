package com.tw.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultipleParkingMgrTest {
  @Test
  void should_return_ticket_when_parking_given_multiple_parking_and_car_without_license_plate() {
    MultipleParkingMgr multipleParkingMgr = new MultipleParkingMgr();
    multipleParkingMgr.addParkingLot(new ParkingLotSystem("1", 10));
    multipleParkingMgr.addParkingLot(new ParkingLotSystem("2", 20));
    Car car = new Car();
    Ticket ticket = multipleParkingMgr.procParking(car);
    assertNull(ticket);
  }

  @Test
  void should_return_ticket_when_parking_given_multiple_parking_with_no_space_and_car_with_license_plate() {
    MultipleParkingMgr multipleParkingMgr = new MultipleParkingMgr();
    multipleParkingMgr.addParkingLot(new ParkingLotSystem("1", 0));
    multipleParkingMgr.addParkingLot(new ParkingLotSystem("2", 0));
    Car car = new Car("Y88888");
    Ticket ticket = multipleParkingMgr.procParking(car);
    assertNull(ticket);
  }

  @Test
  void should_return_ticket_when_parking_given_multiple_parking_and_exist_same_license_plate() {
    MultipleParkingMgr multipleParkingMgr = new MultipleParkingMgr();
    multipleParkingMgr.addParkingLot(new ParkingLotSystem("1", 10));
    multipleParkingMgr.addParkingLot(new ParkingLotSystem("2", 20));
    Car car1 = new Car("Y88888");
    Ticket ticket1 = multipleParkingMgr.procParking(car1);
    assertNotNull(ticket1);

    Car car2 = new Car("Y88888");
    Ticket ticket2 = multipleParkingMgr.procParking(car2);
    assertNull(ticket2);
  }

  @Test
  void should_return_ticket_when_parking_given_multiple_parking_and_car_with_license_plate() {
    MultipleParkingMgr multipleParkingMgr = new MultipleParkingMgr();
    multipleParkingMgr.addParkingLot(new ParkingLotSystem("1", 10));
    multipleParkingMgr.addParkingLot(new ParkingLotSystem("2", 20));
    Car car = new Car("Y88888");
    Ticket ticket = multipleParkingMgr.procParking(car);
    assertNotNull(ticket);
    assertTrue(ticket.getTicketNumber().startsWith("1"));
  }

  @Test
  void should_return_ticket_when_parking_given_multiple_parking_and_first_parking_without_space() {
    MultipleParkingMgr multipleParkingMgr = new MultipleParkingMgr();
    multipleParkingMgr.addParkingLot(new ParkingLotSystem("1", 0));
    multipleParkingMgr.addParkingLot(new ParkingLotSystem("2", 20));
    Car car = new Car("Y88888");
    Ticket ticket = multipleParkingMgr.procParking(car);
    assertNotNull(ticket);
    assertTrue(ticket.getTicketNumber().startsWith("2"));
  }

  @Test
  void should_return_car_when_get_car_given_valid_ticket() {
    MultipleParkingMgr multipleParkingMgr = new MultipleParkingMgr();
    multipleParkingMgr.addParkingLot(new ParkingLotSystem("1", 0));
    multipleParkingMgr.addParkingLot(new ParkingLotSystem("2", 20));
    Car car = new Car("Y88888");
    Ticket ticket = multipleParkingMgr.procParking(car);
    assertNotNull(ticket);
    assertTrue(ticket.getTicketNumber().startsWith("2"));

    Car car1 = multipleParkingMgr.getCarByTicket(ticket);
    assertNotNull(car1);
  }

  @Test
  void should_return_car_when_get_car_given_invalid_ticket() {
    MultipleParkingMgr multipleParkingMgr = new MultipleParkingMgr();
    multipleParkingMgr.addParkingLot(new ParkingLotSystem("1", 0));
    multipleParkingMgr.addParkingLot(new ParkingLotSystem("2", 20));
    Car car = new Car("Y88888");
    Ticket ticket = multipleParkingMgr.procParking(car);
    assertNotNull(ticket);
    assertTrue(ticket.getTicketNumber().startsWith("2"));

    Car car1 = multipleParkingMgr.getCarByTicket(new Ticket("123456"));
    assertNull(car1);
  }
}