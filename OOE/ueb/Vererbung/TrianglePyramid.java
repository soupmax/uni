package Vererbung;

//aufg2
public class TrianglePyramid extends Pyramid {
    private double length;

    public TrianglePyramid(double length, double height) {
        super(height);
        this.length = length;
    }

    @Override
    public double calcVolume() {
        return (length * length * Math.sqrt(3) * height) / 12.0;
    }
}