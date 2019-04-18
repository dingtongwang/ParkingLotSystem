package com.tw.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotSystemTest {

  @Test
  void should_return_normal_ticket_when_parking_given_available_parking_space_and_no_license_plate() {
    ParkingLotSystem parkingLotSystem = new ParkingLotSystem(10);
    Car car = new Car();
    Ticket ticket = parkingLotSystem.procParking(car);
    assertNull(ticket);
  }

  @Test
  void should_return_normal_ticket_when_parking_given_available_parking_space_and_license_plate() {
    ParkingLotSystem parkingLotSystem = new ParkingLotSystem(10);
    Car car = new Car("Y88888");
    Ticket ticket = parkingLotSystem.procParking(car);
    assertNotNull(ticket);
    assertEquals(9, parkingLotSystem.getAvailableParkingSpace());
  }

  @Test
  void should_return_normal_ticket_when_parking_given_available_parking_space_and_same_license_plate() {
    ParkingLotSystem parkingLotSystem = new ParkingLotSystem(10);
    Car car1 = new Car("Y88888");
    Ticket ticket1 = parkingLotSystem.procParking(car1);
    assertNotNull(ticket1);

    Car car2 = new Car("Y88888");
    Ticket ticket2 = parkingLotSystem.procParking(car2);
    assertNull(ticket2);
  }

  @Test
  void should_return_normal_ticket_when_parking_given_no_parking_space() {
    ParkingLotSystem parkingLotSystem = new ParkingLotSystem(0);
    Car car = new Car("Y88888");
    Ticket ticket = parkingLotSystem.procParking(car);
    assertNull(ticket);
  }

  @Test
  void should_return_car_when_get_car_given_illegal_ticket_number() {
    ParkingLotSystem parkingLotSystem = new ParkingLotSystem(10);
    Ticket ticket = new Ticket("H99999");
    Car car = parkingLotSystem.procGetCar(ticket);
    assertNull(car);
  }

  @Test
  void should_return_car_when_get_car_given_normal_ticket_number() {
    ParkingLotSystem parkingLotSystem = new ParkingLotSystem(10);
    Car car1 = new Car("Y88888");
    Ticket ticket1 = parkingLotSystem.procParking(car1);
    assertNotNull(ticket1);

    assertEquals(car1.getLicensePlate(), parkingLotSystem.procGetCar(ticket1).getLicensePlate());
  }
}