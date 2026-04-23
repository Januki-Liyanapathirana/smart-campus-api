package com.campus.datastore;

import com.campus.model.Room;
import com.campus.model.Sensor;
import com.campus.model.SensorReading;

import java.util.HashMap;
import java.util.Map;

public class DataStore {

    public static Map<Integer, Room> rooms = new HashMap<>();
    public static Map<Integer, Sensor> sensors = new HashMap<>();
    public static Map<Integer, SensorReading> readings = new HashMap<>();
}
