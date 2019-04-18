package com.tw.test;

import org.omg.CORBA.TCKind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MultipleParkingMgr {
  /*
  * mapping：停车场编号，停车场
  */
  HashMap<String, ParkingLotSystem> parkingLotSystemList = new HashMap<>();

  public boolean addParkingLot(ParkingLotSystem parkingLotSystem) {
    if (parkingLotSystemList.get(parkingLotSystem.getParkingLotNumber()) == null) {
      parkingLotSystemList.put(parkingLotSystem.getParkingLotNumber(), parkingLotSystem);
      return true;
    }
    return false;
  }

  /*
  * 票-》停车场编号 + 原停车场票
  */
  public Ticket procParking(Car car) {
    if ("".equals(car.getLicensePlate())) {
      return null;
    }

    if (!isExistSameCarLicensePlate(car.getLicensePlate())) {
      ParkingLotSystem parkingLotSystem = parkingLotSystemList.get(getParkingLotNumber());
      if (parkingLotSystem != null) {
        Ticket ticket = parkingLotSystem.procParking(car);
        return new Ticket(parkingLotSystem.getParkingLotNumber()+ "-" +ticket.getTicketNumber());
      }
    }
    return null;
  }

  private String getParkingLotNumber() {
    for (Map.Entry<String, ParkingLotSystem> entry : parkingLotSystemList.entrySet()) {
      ParkingLotSystem v = entry.getValue();
      if (v.getAvailableParkingSpace() > 0) {
        return entry.getKey();
      }
    }
    return null;
  }

  private boolean isExistSameCarLicensePlate(String carLicensePlate) {
    for (Map.Entry<String, ParkingLotSystem> entry : parkingLotSystemList.entrySet()) {
      ParkingLotSystem v = entry.getValue();
      if (v.isExistSameCarLicensePlate(carLicensePlate)) {
        return true;
      }
    }

    return false;
  }

  public Car getCarByTicket(Ticket ticket) {
    if (isTicketValid(ticket)) {
      String[] temp = ticket.getTicketNumber().split("-");
      return parkingLotSystemList.get(temp[0]).procGetCar(new Ticket(temp[1]));
    }
    return null;
  }

  private boolean isTicketValid(Ticket ticket) {
    String[] temp = ticket.getTicketNumber().split("-");
    if (temp.length == 2) {
      String parkingLotNumber = temp[0];
      if (parkingLotSystemList.get(parkingLotNumber) != null) {
        return true;
      }
    }
    return false;
  }
}
