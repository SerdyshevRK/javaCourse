package com.patterns.observer;

public class GreenAlarm extends Alarms {

    public GreenAlarm(int maxTemperature) {
        super(maxTemperature);
    }

    @Override
    protected void printAlarmText() {
        System.out.println("Green Alarm Activated!");
    }

    @Override
    public void temperatureChanged(int temperature) {
        super.temperatureChanged(temperature);
    }
}
