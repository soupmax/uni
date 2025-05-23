/**
 * Erweiterung der Klasse Task ohne zusätzliche Attribute.
 * Repräsentiert eine einfache Aufgabe.
 */
public class TaskSimple extends Task {

    /**
     * Konstruktor mit vollständigen Attributen.
     *
     * @param category    Kategorie der Aufgabe
     * @param title       Titel der Aufgabe
     * @param description Beschreibung der Aufgabe
     * @param completed   Erledigt-Status der Aufgabe
     */
    public TaskSimple(String category, String title, String description, boolean completed) {
        super(category, title, description, completed);
    }

    /**
     * Konstruktor ohne Erledigt-Status (standardmäßig false).
     *
     * @param category    Kategorie der Aufgabe
     * @param title       Titel der Aufgabe
     * @param description Beschreibung der Aufgabe
     */
    public TaskSimple(String category, String title, String description) {
        super(category, title, description);
    }

    /**
     * Gibt eine String-Darstellung der Aufgabe zurück.
     *
     * @return Beschreibung der Aufgabe inklusive Kategorie, Titel, Beschreibung und
     *         Erledigt-Status
     */
    @Override
    public String toString() {
        return "category: " + category + "\n" +
                "title: " + title +
                " description: " + description +
                " completed: " + (completed ? "true" : "false");
    }
}
