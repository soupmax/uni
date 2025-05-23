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
     * @param priority    Prioritätsstatus der Aufgabe
     * @param completed   Erledigt-Status der Aufgabe
     */
    public TaskSimple(String category, String title, String description, boolean priority, boolean completed) {
        super(category, title, description, priority, completed);
    }

    /**
     * Konstruktor ohne Erledigt-Status (standardmäßig false).
     *
     * @param category    Kategorie der Aufgabe
     * @param title       Titel der Aufgabe
     * @param description Beschreibung der Aufgabe
     * @param priority    Prioritätsstatus der Aufgabe
     */
    public TaskSimple(String category, String title, String description, boolean priority) {
        super(category, title, description, priority);
    }

    /**
     * Gibt eine String-Darstellung der Aufgabe zurück.
     *
     * @return Beschreibung der Aufgabe inklusive Kategorie, Titel, Beschreibung,
     *         Priorität und Erledigt-Status
     */
    @Override
    public String toString() {
        return "category: " + category + "\n" +
                "title: " + title +
                " description: " + description +
                " priority: " + (priority ? "true" : "false") +
                " completed: " + (completed ? "true" : "false");
    }
}
