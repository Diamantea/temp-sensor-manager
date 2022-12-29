package it.apt.tempsensormanager.integration.repository;

import it.apt.tempsensormanager.model.Sensor;
import it.apt.tempsensormanager.repository.SensorRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SensorRepositoryTest
{
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SensorRepository sensorRepository;

    @Test
    public void testSensorJpaMapping() {
        Sensor sensor = putSensorInDB("Sensor 1", "1 desc","S1HSX");

        assertNotNull(sensor);
        assertEquals(1, sensor.getId());
        assertEquals("Sensor 1", sensor.getName());
        assertEquals("1 desc", sensor.getDescription());
        assertEquals("S1HSX", sensor.getSerialNo());
    }

    @Test
    public void testFindAllWithNoSensorInDB() {

        var actual = sensorRepository.findAll();

        assertEquals(List.of(), actual);
    }

    @Test
    public void testFindAllWithWithTwoSensorInDB() {
        putSensorInDB("SensorRepo 1", "1 desc repo","S1HSXREP");
        putSensorInDB("SensorRepo 2", "2 desc repo","S2DKSREP");

        var actual = sensorRepository.findAll();

        var expected = List.of(
            getSensorWithId(1L, "SensorRepo 1", "1 desc repo","S1HSXREP"),
            getSensorWithId(2L, "SensorRepo 2", "2 desc repo","S2DKSREP")
        );
        assertEquals(expected, actual);
    }

    private Sensor putSensorInDB(String name, String desc, String serial)
    {
        Sensor sensor = new Sensor(name, desc, serial);
        return entityManager.persist(sensor);
    }

    private Sensor getSensorWithId(Long id, String name, String serialNo, String desc)
    {
        var sensor = new Sensor(name, serialNo, desc);

        sensor.setId(id);

        return sensor;
    }
}
