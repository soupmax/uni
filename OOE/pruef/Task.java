/**
 * Repräsentiert eine Aufgabe mit Titel, Beschreibung, Kategorie und
 * Status.
 * 
 * @author Max
 */
public class Task {
    /** Kategorie der Aufgabe */
    public String category;

    /** Titel der Aufgabe */
    public String title;

    /** Beschreibung der Aufgabe */
    public String description;

    /** Gibt an, ob die Aufgabe erledigt ist */
    protected boolean completed;

    /**
     * Erstellt eine neue Aufgabe mit vollständigen Attributen.
     *
     * @param category    Kategorie der Aufgabe
     * @param title       Titel der Aufgabe
     * @param description Beschreibung der Aufgabe
     * @param completed   Erledigt-Status der Aufgabe
     */
    public Task(String category, String title, String description, boolean completed) {
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.category = category;
    }

    /**
     * Erstellt eine neue Aufgabe, die standardmäßig als nicht
     * erledigt gilt.
     *
     * @param category    Kategorie der Aufgabe
     * @param title       Titel der Aufgabe
     * @param description Beschreibung der Aufgabe
     */
    public Task(String category, String title, String description) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.completed = false;
    }

    /**
     * Gibt eine String-Darstellung der Aufgabe zurück.
     *
     * @return Beschreibung der Aufgabe inklusive Kategorie, Titel, Beschreibung und
     *         Erledigt-Status
     */
    public String toString() {
        return "category: " + category + "\n" +
                "title: " + title +
                " description: " + description +
                " completed: " + (completed ? "true" : "false");
    }
}
