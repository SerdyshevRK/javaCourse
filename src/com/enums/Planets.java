package com.enums;

public enum Planets {

    MERCURY("Меркурий", 3.3011e23d, 2439.7d, 69.8169e6d, 46.0012e6d),
    VENUS("Венера", 4.8675e24d, 6051.8d, 108.939e6d, 107.477e6d),
    EARTH("Земля", 5.97237e24d, 6371.0d, 152.1e6d, 147.095e6d),
    MARS("Марс", 6.4171e23d, 3389.5d, 249.2e6d, 206.7e6d),
    JUPITER("Юпитер", 1.8982e27d, 69911.0d, 816.62e6d, 740.52e6d),
    SATURN("Сатурн", 5.6834e26d, 58232d, 1514.5e6d, 1352.55e6d),
    URANUS("Уран", 8.6810e25d, 25362d, 3008e6d, 2742e6d),
    NEPTUNE("Нептун", 1.0243e26d, 24622d, 4540e6d, 4460e6d);

    private String name;
    private double mass;                    // kilograms
    private double meanRadius;              // kilometers
    private double aphelion;                // kilometers
    private double perihelion;              // kilometers

    Planets(String name, double mass, double radius, double aphelion, double perihelion){
        this.name = name;
        this.mass = mass;
        this.meanRadius = radius;
        this.aphelion = aphelion;
        this.perihelion = perihelion;
    }

    @Override
    public String toString() {
        return "Название: " + name + "\n" +
                "Масса: " + mass + " кг\n" +
                "Средний радиус: " + meanRadius + " км\n" +
                "Апогелий: " + aphelion + " км\n" +
                "Перигелий: " + perihelion + " км";
    }
}
