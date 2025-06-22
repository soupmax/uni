import org.json.JSONObject;

/**
 * Repräsentiert eine Aufgabe mit Titel, Beschreibung, Kategorie und
 * Status.
 * 
 * @author Max
 */
public abstract class TaskStructured extends Task {

    /** Gibt an, ob die Aufgabe erledigt ist */
    protected boolean completed;

    /**
     * Erstellt eine neue Aufgabe mit vollständigen Attributen.
     *
     * @param category  Kategorie der Aufgabe
     * @param title     Titel der Aufgabe
     * @param content   Beschreibung der Aufgabe
     * @param completed Erledigt-Status der Aufgabe
     */
    public TaskStructured(String category, String title, String content, boolean completed) {
        super(category, title, content);
        this.completed = completed;
    }

    /**
     * Erstellt eine neue Aufgabe, die standardmäßig als nicht
     * erledigt gilt.
     *
     * @param category Kategorie der Aufgabe
     * @param title    Titel der Aufgabe
     * @param content  Beschreibung der Aufgabe
     */
    public TaskStructured(String category, String title, String content) {
        super(category, title, content);
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
                " content: " + content +
                " completed: " + (completed ? "true" : "false");
    }

    /**
     * Wandelt eine Task in ein JSONObject um.
     * 
     * @param t Task-Objekt
     * @return JSONObject mit Task-Daten
     */
    protected JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("category", category);
        json.put("title", title);
        json.put("content", content);
        json.put("completed", completed);

        return json;
    }
}
