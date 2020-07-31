package org.trackingSensor.service;

import org.trackingSensor.entity.*;

import java.util.List;
import java.util.Optional;

public interface VehicleService {
    Vehicle update(Vehicle vehicle);
    List<Vehicle> findAll();
    Vehicle findById(String vin);
    Reading create(Reading vehicle);
    Wheels create(Wheels pressure);
    List<Reading> findGeoLocation(String vin);
  
}
