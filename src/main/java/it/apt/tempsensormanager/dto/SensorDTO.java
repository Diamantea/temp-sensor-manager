package it.apt.tempsensormanager.dto;

import java.util.Objects;

public class SensorDTO
{
    private Long id;
    private String name;
    private String description;
    private String serialNo;

    public SensorDTO(String name, String description, String serialNo)
    {
        this.name = name;
        this.description = description;
        this.serialNo = serialNo;
    }

    public SensorDTO(Long id, String name, String description, String serialNo)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.serialNo = serialNo;
    }


    public Long getId()
    {
        return id;
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
        SensorDTO sensorDTO = (SensorDTO) o;
        return id.equals(sensorDTO.id) && name.equals(sensorDTO.name) && description.equals(sensorDTO.description) && serialNo.equals(sensorDTO.serialNo);
    }


    @Override
    public int hashCode()
    {
        return Objects.hash(id, name, description, serialNo);
    }
}
