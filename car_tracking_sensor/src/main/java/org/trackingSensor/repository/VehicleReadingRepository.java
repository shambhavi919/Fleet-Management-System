package org.trackingSensor.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.trackingSensor.entity.Reading;
import org.trackingSensor.entity.VehicleId;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VehicleReadingRepository extends CrudRepository<Reading, VehicleId> {

    @Query(value = " select * from reading v where v.vin = :vin and TIMESTAMPDIFF(minute, current_timestamp, v.timestamp) < 30 ", nativeQuery = true)
    Optional<List<Reading>> findGeoLocation(@Param("vin") String vin);
}
