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

  //  @Autowired
  //  alertRepository alerts;

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

  //  @Transactional(readOnly = true)
  //  public List<alertInfo> findHighAlerts(){
  //      return alerts.findHighAlerts();
 //   }

 //   @Transactional(readOnly = true)
 //   public List<alertInfo> findVehicleAlerts(String vin){
  //      return alerts.findVehicleAlerts(vin);
  //  }

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

/*
    public void throwAlerts(vehicleUpdate vehicle){
        Optional<vehicleInfo> vehicleInfo = vehicles.findById(vehicle.getVin());
        Optional<vehicleTirePressure> tirePressure = tires.findById(new vehicleId(vehicle.getVin(),vehicle.getTimestamp()));

        if(vehicleInfo.isPresent()){
            if(vehicleInfo.get().getRedlineRpm() < vehicle.getEngineRpm())
            {
                alertInfo alertInfo = new alertInfo(vehicle.getVin(),vehicle.getTimestamp());
                alertInfo.setPriority("HIGH");
                alertInfo.setAlertType("RPM");
                alertInfo.setMessage(" Vehicle moving at high RPM");
                alerts.save(alertInfo);
            }
            if(vehicleInfo.get().getMaxFuelVolume()*0.1 > vehicle.getFuelVolume()){
                alertInfo alertInfo = new alertInfo(vehicle.getVin(),vehicle.getTimestamp());
                alertInfo.setAlertType("FUEL");
                alertInfo.setPriority("MEDIUM");
                alertInfo.setMessage(" LOW VOLUME ALERT");
                alerts.save(alertInfo);
            }

            if(tirePressure.get().getRearLeft() < 32 || tirePressure.get().getRearLeft() > 36 || tirePressure.get().getRearRight() < 32 || tirePressure.get().getRearRight() > 36  ||
                    tirePressure.get().getFrontLeft() < 32 || tirePressure.get().getFrontLeft() > 36 || tirePressure.get().getFrontRight() < 32 || tirePressure.get().getFrontRight() > 36 )
            {
                alertInfo alertInfo = new alertInfo(vehicle.getVin(),vehicle.getTimestamp());
                alertInfo.setPriority("LOW");
                alertInfo.setAlertType("TIRE");
                alertInfo.setMessage(" PLEASE CHECK TIRE PRESSURE");
                alerts.save(alertInfo);
            }

            if(vehicle.isEngineCoolantLow() || vehicle.isCheckEngineLightOn()) {
                alertInfo alertInfo = new alertInfo(vehicle.getVin(),vehicle.getTimestamp());
                alertInfo.setPriority("LOW");
                alertInfo.setAlertType("ENGINE");
                alertInfo.setMessage(" PLEASE CHECK ENGINE-COOLANT and ENGINE CONDITION");
                alerts.save(alertInfo);
            }
        }

    }

*/

}