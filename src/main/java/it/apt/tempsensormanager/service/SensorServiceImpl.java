package it.apt.tempsensormanager.service;

import it.apt.tempsensormanager.model.Sensor;
import it.apt.tempsensormanager.repository.SensorRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SensorServiceImpl implements SensorService
{

    private final SensorRepository sensorRepository;


    public SensorServiceImpl(SensorRepository sensorRepository)
    {
        this.sensorRepository = sensorRepository;
    }


    @Override
    public List<Sensor> findAll()
    {
        return this.sensorRepository.findAll();
    }
}
