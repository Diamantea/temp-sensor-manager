package it.apt.tempsensormanager.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.util.Objects;

@Entity
public class Sensor
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(unique = true, nullable = false)
    private String serialNo;

    @OneToMany(mappedBy = "sensor")
    private List<Measure> measures;

    public Sensor() {}


    public Sensor(String name, String description, String serialNo)
    {
        this.id = null;
        this.name = name;
        this.description = description;
        this.serialNo = serialNo;
    }


    public String getName()
    {
        return name;
    }


    public String getDescription()
    {
        return description;
    }


    public String getSerialNo()
    {
        return serialNo;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        Sensor sensor = (Sensor) o;
        return Objects.equals(id, sensor.id) && Objects.equals(name, sensor.name) && Objects.equals(
            description,
            sensor.description) && Objects.equals(serialNo, sensor.serialNo);
    }


    @Override
    public int hashCode()
    {
        return Objects.hash(id, name, description, serialNo);
    }
}
