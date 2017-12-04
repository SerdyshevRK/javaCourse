package com.patterns.observer;

public class ObserverMain {
    public static void main(String[] args) {
        Sensor sensor = new Sensor();
        sensor.registerAlarm(new GreenAlarm(100));
        sensor.registerAlarm(new YellowAlarm(300));
        sensor.registerAlarm(new RedAlarm(600));

        int i;
        for (i = 0; i < 400; i++) {
            sensor.startListen(i);
        }
        for (; i > 200; i--) {
            sensor.startListen(i);
        }
        for (; i < 800; i++) {
            sensor.startListen(i);
        }
    }
}
