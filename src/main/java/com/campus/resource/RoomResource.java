package com.campus.resource;

import com.campus.datastore.DataStore;
import com.campus.exception.RoomNotEmptyException;
import com.campus.model.Room;
import com.campus.exception.RoomNotFoundException;
import com.campus.model.Sensor;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoomResource {

    // GET all rooms
    @GET
    public Collection<Room> getAllRooms() {
        return DataStore.rooms.values();
    }

    // GET room by ID
    @GET
    @Path("/{id}")
    public Room getRoom(@PathParam("id") int id) {

        Room room = DataStore.rooms.get(id);

        if (room == null) {
            throw new RoomNotFoundException("Room not found with id: " + id);
        }

        return room;
    }

    // POST create room
    @POST
    public Room addRoom(Room room) {

        if (room.getName() == null || room.getName().isEmpty()) {
            throw new BadRequestException("Room name is required");
        }

        if (room.getCapacity() <= 0) {
            throw new BadRequestException("Capacity must be greater than 0");
        }

        DataStore.rooms.put(room.getId(), room);
        return room;
    }
    
    @DELETE
    @Path("/{id}")
    public String deleteRoom(@PathParam("id") int id) {

        Room room = DataStore.rooms.get(id);

        if (room == null) {
            throw new RoomNotFoundException("Room not found with id: " + id);
        }

        // Check if any sensor belongs to this room
        boolean hasSensors = DataStore.sensors.values().stream()
                .anyMatch(sensor -> sensor.getRoomId() == id);

        if (hasSensors) {
            throw new RoomNotEmptyException("Cannot delete room: sensors are assigned to it");
        }

        DataStore.rooms.remove(id);

        return "Room deleted successfully";
    }
    
    @GET
    @Path("/{id}/sensors")
    public Collection<Sensor> getSensorsByRoom(@PathParam("id") int id) {

        if (!DataStore.rooms.containsKey(id)) {
            throw new RoomNotFoundException("Room not found with id: " + id);
        }

        return DataStore.sensors.values().stream()
                .filter(sensor -> sensor.getRoomId() == id)
                .toList();
    }
}