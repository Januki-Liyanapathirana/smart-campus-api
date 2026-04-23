package com.campus.model;

public class Sensor {

    private int id;
    private String type;
    private int roomId;
    private double currentValue;

    public Sensor() {}

    public Sensor(int id, String type, int roomId) {
        this.id = id;
        this.type = type;
        this.roomId = roomId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getRoomId() { return roomId; }
    public void setRoomId(int roomId) { this.roomId = roomId; }
    
    public double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }
}
