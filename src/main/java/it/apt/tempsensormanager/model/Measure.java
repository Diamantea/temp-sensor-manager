package it.apt.tempsensormanager.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Measure
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="measure_value", nullable = false)
    private Float value;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @ManyToOne(optional = false)
    //@JoinColumn(name="sensor_id", nullable=false)
    private Sensor sensor;

    public Measure() {}

    public Measure(Float value, LocalDateTime dateTime, Sensor sensor)
    {
        this.value = value;
        this.dateTime = dateTime;
        this.sensor = sensor;
    }

    public Long getId()
    {
        return id;
    }

    public Float getValue()
    {
        return value;
    }

    public LocalDateTime getDateTime()
    {
        return dateTime;
    }

    public Sensor getSensor()
    {
        return sensor;
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
        Measure measure = (Measure) o;
        return Objects.equals(id, measure.id) && Objects.equals(value, measure.value) && Objects.equals(
            dateTime,
            measure.dateTime) && Objects.equals(sensor, measure.sensor);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, value, dateTime, sensor);
    }
}
