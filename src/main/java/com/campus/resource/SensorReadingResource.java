package com.campus.resource;

import com.campus.datastore.DataStore;
import com.campus.exception.LinkedResourceNotFoundException;
import com.campus.model.Sensor;
import com.campus.model.SensorReading;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorReadingResource {

    private int sensorId;

    // Constructor 
    public SensorReadingResource(int sensorId) {
        this.sensorId = sensorId;
    }

    // GET readings for this specific sensor
    @GET
    public Collection<SensorReading> getReadings() {
        return DataStore.readings.values().stream()
                .filter(r -> r.getSensorId() == sensorId)
                .toList();
    }

    // POST new reading for this sensor
    @POST
    public SensorReading addReading(SensorReading reading) {

        // force sensorId from URL
        reading.setSensorId(sensorId);

        // validation
        if (!DataStore.sensors.containsKey(sensorId)) {
            throw new LinkedResourceNotFoundException(
                    "Sensor with id " + sensorId + " does not exist"
            );
        }

        // update sensor current value 
        Sensor sensor = DataStore.sensors.get(sensorId);
        sensor.setCurrentValue(reading.getValue());

        DataStore.readings.put(reading.getId(), reading);
        return reading;
    }
}