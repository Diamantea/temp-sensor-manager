package it.apt.tempsensormanager.unit.controller;

import it.apt.tempsensormanager.controller.SensorController;
import it.apt.tempsensormanager.dto.SensorDTO;
import it.apt.tempsensormanager.model.Sensor;
import it.apt.tempsensormanager.service.SensorService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class SensorControllerTest
{
    @Mock
    private SensorService sensorService;

    private SensorController sensorController;

    @BeforeEach
    void init() {
        this.sensorController = new SensorController(this.sensorService);
    }

    @Test
    public void testAllSensorsEmpty() {

        Mockito.when(this.sensorService.findAll())
            .thenReturn(List.of());

        List<SensorDTO> sensors = this.sensorController.getAll();

        assertEquals(List.of(), sensors);

    }

    @Test
    public void testAllSensorsWithSomeElements() {

        List<Sensor> sensorsForService = List.of(
            getSensorModel(1L,"one", "oneDSF", "one desc"),
            getSensorModel(2L,"two", "twoXNS", "two desc")
        );
        Mockito.when(this.sensorService.findAll())
            .thenReturn(sensorsForService);

        List<SensorDTO> actual = this.sensorController.getAll();

        var expected = List.of(
            getSensorDTO(1L,"one", "oneDSF", "one desc"),
            getSensorDTO(2L,"two", "twoXNS", "two desc")
        );
        assertEquals(expected, actual);

    }

    private Sensor getSensorModel(Long id, String name, String serialNo, String desc)
    {
        var sensor = new Sensor(name, serialNo, desc);

        sensor.setId(id);

        return sensor;
    }

    private SensorDTO getSensorDTO(Long id, String name, String serialNo, String desc)
    {
        return new SensorDTO(id, name, serialNo, desc);
    }
}
