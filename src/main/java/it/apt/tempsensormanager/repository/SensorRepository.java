package it.apt.tempsensormanager.repository;

import it.apt.tempsensormanager.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, Long>
{
}
