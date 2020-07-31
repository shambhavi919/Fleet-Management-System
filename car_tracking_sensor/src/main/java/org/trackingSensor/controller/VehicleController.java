package org.trackingSensor.controller;

import org.trackingSensor.entity.*;
import org.trackingSensor.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController

public class VehicleController {

    @Autowired
    VehicleService service;

    @GetMapping("/vehicles")
    public List<Vehicle> findAll(){ return service.findAll(); }

    @GetMapping("/vehicles/{id}")
    public Vehicle findByID(@PathVariable("id") String vin){
        return service.findById(vin);
    }

    @GetMapping("/geolocation/{vin}")
    public List<Reading> findGeoLocation(@PathVariable("vin") String vin){
        return service.findGeoLocation(vin);
    }

    @PostMapping(value = "/readings", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Reading create(@RequestBody Reading reading){
        Wheels pressure = new Wheels();
        pressure.vin = reading.getVin();
        pressure.timestamp = reading.getTimestamp();
        pressure.frontRight = reading.wheels.frontRight;
        pressure.frontLeft = reading.wheels.frontLeft;
        pressure.rearRight = reading.wheels.rearRight;
        pressure.rearLeft = reading.wheels.rearLeft;
        service.create(pressure);

        return service.create(reading);

    }

    @PutMapping(value = "/vehicles", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vehicle> update(@RequestBody List<Vehicle> vehicles){
        for(Vehicle vehicle : vehicles) {
            service.update(vehicle);
        }
        return vehicles;
    }






}
