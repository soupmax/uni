package Vererbung;

//aufg2
public class ConePyramid extends Pyramid {
    private double radius;

    public ConePyramid(double radius, double height) {
        super(height);
        this.radius = radius;
    }

    @Override
    public double calcVolume() {
        return (Math.PI * radius * radius * height) / 3.0;
    }
}