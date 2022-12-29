package it.apt.tempsensormanager.service;

import it.apt.tempsensormanager.model.Sensor;
import java.util.List;
import java.util.Optional;

public interface SensorService
{
    List<Sensor> findAll();
}
