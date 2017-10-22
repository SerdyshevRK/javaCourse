public class Triangle extends Shape {
    private Point left;
    private Point top;
    private Point right;

    public Triangle(Point left, Point top, Point right){
        this.left = left;
        this.top = top;
        this.right = right;
    }

    @Override
    public double getArea() {
        return Math.sqrt(0.5 * getPerimeter() * (0.5 * getPerimeter() - lineLength(top, left)) *
                (0.5 * getPerimeter() - lineLength(top, right)) *
                (0.5 * getPerimeter() - lineLength(left, right)));
    }

    @Override
    public double getPerimeter() {
        return lineLength(top, left) + lineLength(top, right) + lineLength(left, right);
    }
}
