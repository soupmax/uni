import java.time.*;

/**
 * Erweiterung der Klasse Task, die zusätzlich ein Fälligkeitsdatum enthält.
 */
public class TaskTimed extends Task {
    /** Das Fälligkeitsdatum der Aufgabe */
    LocalDate dueDate;

    /**
     * Konstruktor mit vollständigen Attributen.
     *
     * @param category    Kategorie der Aufgabe
     * @param title       Titel der Aufgabe
     * @param description Beschreibung der Aufgabe
     * @param dueDate     Fälligkeitsdatum der Aufgabe
     * @param completed   Erledigt-Status der Aufgabe
     */
    public TaskTimed(String category, String title, String description, LocalDate dueDate,
            boolean completed) {
        super(category, title, description, completed);
        this.dueDate = dueDate;
    }

    /**
     * Konstruktor ohne Erledigt-Status (standardmäßig false).
     *
     * @param category    Kategorie der Aufgabe
     * @param title       Titel der Aufgabe
     * @param description Beschreibung der Aufgabe
     * @param dueDate     Fälligkeitsdatum der Aufgabe
     */
    public TaskTimed(String category, String title, String description, LocalDate dueDate) {
        super(category, title, description);
        this.dueDate = dueDate;
    }

    /**
     * Gibt eine String-Darstellung der Aufgabe zurück, inklusive Fälligkeitsdatum.
     *
     * @return Beschreibung der Aufgabe inklusive Kategorie, Titel, Beschreibung,
     *         Fälligkeitsdatum und Erledigt-Status
     */
    @Override
    public String toString() {
        return "category: " + category + "\n" +
                "title: " + title +
                " description: " + description +
                " dueDate: " + dueDate.toString() +
                " completed: " + (completed ? "true" : "false");
    }
}
