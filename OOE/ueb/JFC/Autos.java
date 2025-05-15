package JFC;

import java.util.ArrayList;

public class Autos extends ArrayList<Auto> {

	Autos() {
		add(new Auto("VW", "Polo 1.2i", 69, 2010, 7990));
		add(new Auto("VW", "Sharan 2.0 TDI", 145, 2012, 23990));
		add(new Auto("Opel", "Astra 1.4", 115, 2011, 10990));
		add(new Auto("Opel", "MerivaB 1.4", 120, 2013, 11990));
		add(new Auto("Ford", "Fiesta 1.25", 82, 2013, 9950));
		add(new Auto("Ford", "Focus 1.6i", 115, 2012, 10950));
		add(new Auto("Skoda", "Rapid 1.2", 86, 2013, 11950));
		add(new Auto("Skoda", "Yeti 1.8", 160, 2009, 12950));
	}

	public static void main(String[] args) {
		Autos liste = new Autos();
		for (Auto a : liste)
			System.out.println(a);
	}
}
