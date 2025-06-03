package Vererbung;

//aufg2
public abstract class Pyramid {
    protected double height;

    public Pyramid(double height) {
        this.height = height;
    }

    public abstract double calcVolume();

    public String toString() {
        return this.getClass().getSimpleName() + " mit Volumen: " + String.format("%.2f", calcVolume());
    }
}
