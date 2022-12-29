package it.apt.tempsensormanager.integration.controller;

import it.apt.tempsensormanager.model.Sensor;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@AutoConfigureTestEntityManager
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SensorControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testAllSensorsEmpty() throws Exception {
        mockMvc.perform(get("/api/sensors")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().json("[]"));

    }

    @Test
    public void testAllSensorsWithTwoSensorInDB() throws Exception {

        putSensorInDB("Sensor 1", "1 desc","S1HSX");
        putSensorInDB("Sensor 2", "2 desc","S2DKS");

        String expected = "[" +
            "{\"id\":1,\"name\":\"Sensor 1\",\"description\":\"1 desc\",\"serialNo\":\"S1HSX\"}," +
            "{\"id\":2,\"name\":\"Sensor 2\",\"description\":\"2 desc\",\"serialNo\":\"S2DKS\"}" +
        "]";
        mockMvc.perform(get("/api/sensors")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().json(expected));

    }


    private void putSensorInDB(String name, String desc, String serial)
    {
        Sensor sensor = new Sensor(name, desc, serial);
        this.entityManager.persist(sensor);
    }
}
