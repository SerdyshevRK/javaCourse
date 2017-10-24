package com.figures;

public class Rectangle extends Shape {
    private Point topLeft;
    private Point topRight;
    private Point bottomLeft;
    private Point bottomRight;

    public Rectangle(Point topLeft, Point topRight, Point bottomLeft, Point bottomRight){
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }

    @Override
    public double getArea() {
        return lineLength(topLeft, topRight) * lineLength(topLeft, bottomLeft);
    }

    @Override
    public double getPerimeter() {
        return 2 * lineLength(topLeft, topRight) + 2 * lineLength(topLeft, bottomLeft);
    }
}
