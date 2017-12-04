package com.patterns.observer;

public class RedAlarm extends Alarms {

    public RedAlarm(int maxTemperature) {
        super(maxTemperature);
    }

    @Override
    public void temperatureChanged(int temperature) {
        super.temperatureChanged(temperature);
    }

    @Override
    protected void printAlarmText() {
        System.out.println("Red Alarm Activated!");
        System.exit(0);
    }
}
