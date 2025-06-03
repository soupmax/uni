package Vererbung;

//aufg2
public class QuadraticPyramid extends Pyramid {
    private double length;

    public QuadraticPyramid(double length, double height) {
        super(height);
        this.length = length;
    }

    @Override
    public double calcVolume() {
        return (length * length * height) / 3.0;
    }
}