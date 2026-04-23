package com.campus.model;

public class SensorReading {

    private int id;
    private int sensorId;
    private double value;

    public SensorReading() {}

    public SensorReading(int id, int sensorId, double value) {
        this.id = id;
        this.sensorId = sensorId;
        this.value = value;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getSensorId() { return sensorId; }
    public void setSensorId(int sensorId) { this.sensorId = sensorId; }

    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }
}