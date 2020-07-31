package org.trackingSensor.repository;

import org.springframework.data.repository.CrudRepository;
import org.trackingSensor.entity.Vehicle;

public interface VehicleRepository extends CrudRepository<Vehicle, String> {

}
