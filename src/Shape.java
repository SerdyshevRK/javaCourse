public abstract class Shape {
    public abstract double getArea();
    public abstract double getPerimeter();
    public double lineLength(Point end, Point start){
        return Math.sqrt(Math.pow(end.getY() - start.getY(), 2) + Math.pow(end.getX() - start.getX(), 2));
    }
}
