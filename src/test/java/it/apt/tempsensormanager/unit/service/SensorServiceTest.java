package it.apt.tempsensormanager.unit.service;

import it.apt.tempsensormanager.dto.SensorDTO;
import it.apt.tempsensormanager.model.Sensor;
import it.apt.tempsensormanager.repository.SensorRepository;
import it.apt.tempsensormanager.service.SensorService;
import it.apt.tempsensormanager.service.SensorServiceImpl;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class SensorServiceTest
{
    @Mock
    private SensorRepository sensorRepository;

    private SensorService sensorService;

    @BeforeEach
    void init() {
        this.sensorService = new SensorServiceImpl(this.sensorRepository);
    }

    @Test
    public void testAllSensorsEmpty() {

        Mockito.when(this.sensorRepository.findAll())
            .thenReturn(List.of());

        List<Sensor> sensors = this.sensorService.findAll();

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

        List<Sensor> actual = this.sensorService.findAll();

        var expected = List.of(
            getSensorModel(1L,"one", "oneDSF", "one desc"),
            getSensorModel(2L,"two", "twoXNS", "two desc")
        );
        assertEquals(expected, actual);

    }

    private Sensor getSensorModel(Long id, String name, String serialNo, String desc)
    {
        var sensor = new Sensor(name, serialNo, desc);

        sensor.setId(id);

        return sensor;
    }
}
