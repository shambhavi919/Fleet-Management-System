package org.trackingSensor.service;

import org.trackingSensor.entity.*;
import org.trackingSensor.exception.VehicleNotFoundException;
import org.trackingSensor.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleRepository vehicles;

    @Autowired
    VehicleReadingRepository readings;

    @Autowired
    VehicleWheelsRepository wheels;

  

    @Transactional(readOnly = true)
    public List<Vehicle> findAll() {
        return (List<Vehicle>) vehicles.findAll();
    }

    @Transactional(readOnly = true)
    public Vehicle findById(String vin) {
        Optional<Vehicle> existing = vehicles.findById(vin);
        if (!existing.isPresent()) {
            throw new VehicleNotFoundException("Vehicle with vin " + vin + " doesn't exist.");
        }
        return existing.get();
    }

    @Transactional
    public List<Reading> findGeoLocation(String vin) {
        Optional<List<Reading>> existing = readings.findGeoLocation(vin);
        if (!existing.isPresent()){
            throw new VehicleNotFoundException("Vehicle with vin " + vin + " doesn't exist.");
        }
        return existing.get();
    }

  

    @Transactional
    public Vehicle update(Vehicle vehicleInfo) {
        Optional<Vehicle> existing = vehicles.findById(vehicleInfo.getVin());
        if(existing.isPresent()) {
            return null;
        }
        return vehicles.save(vehicleInfo);
    }


    @Transactional
    public Reading create(Reading vehicleUpdate) {
        Optional<Vehicle> existing = vehicles.findById(vehicleUpdate.getVin());
        if(!existing.isPresent()){
            throw new VehicleNotFoundException(" Vehicle with vin "+ vehicleUpdate.getVin() + " is not in our records");
        }
        return readings.save(vehicleUpdate);
    }

    @Transactional
    public Wheels create(Wheels pressure) {
        Optional<Vehicle> existing = vehicles.findById(pressure.getVin());
        if(!existing.isPresent()){
            throw new VehicleNotFoundException(" Vehicle with vin "+ pressure.getVin() + " is not in our records");
        }
        return wheels.save(pressure);
    }
            
}
