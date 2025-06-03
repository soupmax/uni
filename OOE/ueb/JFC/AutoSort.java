package JFC;

import java.util.*;

public class AutoSort {

    private Autos autos;

    public AutoSort(Autos autos) {
        this.autos = autos;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nSortieren nach:");
            System.out.println("1. Marke");
            System.out.println("2. Leistung");
            System.out.println("3. Baujahr");
            System.out.println("4. Preis");
            System.out.println("0. Beenden");
            System.out.print("Auswahl: ");

            String eingabe = scanner.nextLine();

            switch (eingabe) {
                case "1":
                    autos.sort(Comparator.comparing(Auto::getMarke));
                    break;
                case "2":
                    autos.sort(Comparator.comparing(Auto::getLeistung).reversed());
                    break;
                case "3":
                    autos.sort(Comparator.comparing(Auto::getBaujahr).reversed());
                    break;
                case "4":
                    autos.sort(Comparator.comparing(Auto::getPreis));
                    break;
                case "0":
                    running = false;
                    System.out.println("Programm beendet.");
                    continue;
                default:
                    System.out.println("Ungültige Eingabe.");
                    continue;
            }

            System.out.println("\nSortierte Autos:");
            for (Auto a : autos) {
                System.out.println(a);
            }
        }

        scanner.close();
    }
}
