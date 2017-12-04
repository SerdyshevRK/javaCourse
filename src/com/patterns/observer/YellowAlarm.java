package com.patterns.observer;

public class YellowAlarm extends Alarms{

    public YellowAlarm(int maxTemperature) {
        super(maxTemperature);
    }

    @Override
    public void temperatureChanged(int temperature) {
        super.temperatureChanged(temperature);
    }

    @Override
    protected void printAlarmText() {
        System.out.println("Yellow Alarm Activated!");
    }
}
