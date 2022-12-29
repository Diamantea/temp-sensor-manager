package it.apt.tempsensormanager.controller;

import it.apt.tempsensormanager.dto.SensorDTO;
import it.apt.tempsensormanager.model.Sensor;
import it.apt.tempsensormanager.service.SensorService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(path="/api/sensors", produces = MediaType.APPLICATION_JSON_VALUE)
public class SensorController
{
    private final SensorService sensorService;


    public SensorController(SensorService sensorService)
    {
        this.sensorService = sensorService;
    }


    @GetMapping()
    public @ResponseBody
    List<SensorDTO> getAll() {
        return sensorListToDTOList(this.sensorService.findAll());
    }


    private List<SensorDTO> sensorListToDTOList(List<Sensor> sensors)
    {
        return sensors
            .stream()
            .map(this::sensorToDTO)
            .collect(Collectors.toList());
    }
    private SensorDTO sensorToDTO(Sensor sensor)
    {
        return new SensorDTO(
            sensor.getId(),
            sensor.getName(),
            sensor.getDescription(),
            sensor.getSerialNo()
        );
    }
}
