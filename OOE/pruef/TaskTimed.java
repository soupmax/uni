import java.time.*;

/**
 * {@code TaskTimed} erweitert die Klasse {@link Task} um ein Fälligkeitsdatum.
 * 
 * <p>
 * Sie stellt zusätzliche Methoden zur Verfügung, um zu prüfen, ob eine Aufgabe
 * heute fällig oder bereits überfällig ist.
 * </p>
 * 
 * @author Max
 */
public class TaskTimed extends Task {
    /** Das Fälligkeitsdatum der Aufgabe. */
    private LocalDate dueDate;

    /**
     * Konstruktor mit vollständigen Attributen.
     *
     * @param category    Kategorie der Aufgabe.
     * @param title       Titel der Aufgabe.
     * @param description Beschreibung der Aufgabe.
     * @param dueDate     Fälligkeitsdatum der Aufgabe.
     * @param completed   Erledigt-Status der Aufgabe.
     */
    public TaskTimed(String category, String title, String description, LocalDate dueDate, boolean completed) {
        super(category, title, description, completed);
        this.dueDate = dueDate;
    }

    /**
     * Konstruktor ohne Erledigt-Status (wird standardmäßig auf {@code false}
     * gesetzt).
     *
     * @param category    Kategorie der Aufgabe.
     * @param title       Titel der Aufgabe.
     * @param description Beschreibung der Aufgabe.
     * @param dueDate     Fälligkeitsdatum der Aufgabe.
     */
    public TaskTimed(String category, String title, String description, LocalDate dueDate) {
        super(category, title, description);
        this.dueDate = dueDate;
    }

    /**
     * Gibt das Fälligkeitsdatum der Aufgabe zurück.
     *
     * @return das {@link LocalDate}-Objekt des Fälligkeitsdatums.
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * Gibt eine formatierte Textdarstellung der Aufgabe zurück.
     *
     * @return eine Zeichenkette mit allen relevanten Aufgabeninformationen.
     */
    @Override
    public String toString() {
        return String.format(
                "category: %s\n" +
                        "title: %s\n" +
                        "description: %s\n" +
                        "dueDate: %s\n" +
                        "completed: %s",
                category, title, description, dueDate, completed ? "true" : "false");
    }

    /**
     * Prüft, ob die Aufgabe heute fällig ist.
     *
     * @return {@code true}, wenn das Fälligkeitsdatum dem heutigen Datum
     *         entspricht.
     */
    public boolean isDueToday() {
        return dueDate.isEqual(LocalDate.now());
    }

    /**
     * Prüft, ob die Aufgabe überfällig ist (Fälligkeitsdatum liegt vor dem heutigen
     * Datum).
     *
     * @return {@code true}, wenn die Aufgabe überfällig ist.
     */
    public boolean isOverDue() {
        return dueDate.isBefore(LocalDate.now());
    }
}
