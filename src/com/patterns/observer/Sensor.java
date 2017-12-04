package com.patterns.observer;

import java.util.ArrayList;
import java.util.List;

public class Sensor {
    private List<Alarm> alarms = new ArrayList<>();

    public void registerAlarm(Alarm alarm) {
        this.alarms.add(alarm);
    }

    public void unregisterAlarm(Alarm alarm) {
        this.alarms.remove(alarm);
    }

    public void notifyAllAlarms(int temperature) {
        for (Alarm alarm : alarms) {
            alarm.temperatureChanged(temperature);
        }
    }

    public void startListen(int temperature) {
        notifyAllAlarms(temperature);
    }
}
