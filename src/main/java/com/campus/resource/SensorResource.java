package com.campus.resource;

import com.campus.datastore.DataStore;
import com.campus.exception.SensorUnavailableException;
import com.campus.model.Sensor;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("/sensors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorResource {

    // GET sensors (with filtering)
    @GET
    public Collection<Sensor> getSensors(@QueryParam("type") String type) {

        if (type == null) {
            return DataStore.sensors.values();
        }

        return DataStore.sensors.values().stream()
                .filter(s -> s.getType().equalsIgnoreCase(type))
                .toList();
    }

    // POST create sensor
    @POST
    public Sensor addSensor(Sensor sensor) {

        if (sensor.getType() == null || sensor.getType().isEmpty()) {
            throw new BadRequestException("Sensor type is required");
        }

        DataStore.sensors.put(sensor.getId(), sensor);
        return sensor;
    }

    // GET sensor by ID
    @GET
    @Path("/{id}")
    public Sensor getSensor(@PathParam("id") int id) {

        Sensor sensor = DataStore.sensors.get(id);

        if (sensor == null) {
            throw new SensorUnavailableException("Sensor not found with id: " + id);
        }

        return sensor;
    }

    // Sub-resource Locator 
    @Path("/{id}/readings")
    public SensorReadingResource getReadingResource(@PathParam("id") int id) {

        if (!DataStore.sensors.containsKey(id)) {
            throw new SensorUnavailableException("Sensor not found with id: " + id);
        }

        return new SensorReadingResource(id);
    }
}