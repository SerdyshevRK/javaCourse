package com.patterns.observer;

public abstract class Alarms implements Alarm {
    private final int MAX_TEMPERATURE;
    private boolean isTriggered = false;

    public Alarms(int maxTemperature) {
        this.MAX_TEMPERATURE = maxTemperature;
    }

    @Override
    public void temperatureChanged(int temperature) {
        if (temperature >= MAX_TEMPERATURE) {
            if (isTriggered)
                return;
            printAlarmText();
            isTriggered = true;
        }
        if (temperature < MAX_TEMPERATURE && isTriggered)
            isTriggered = false;
    }

    protected abstract void printAlarmText();
}
