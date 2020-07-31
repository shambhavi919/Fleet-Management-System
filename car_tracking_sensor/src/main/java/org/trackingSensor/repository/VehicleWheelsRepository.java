package org.trackingSensor.repository;

import org.springframework.data.repository.CrudRepository;
import org.trackingSensor.entity.Wheels;

public interface VehicleWheelsRepository extends CrudRepository<Wheels, String> {
}
