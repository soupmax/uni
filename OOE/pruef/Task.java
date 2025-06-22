import org.json.JSONObject;

/**
 * Repräsentiert eine Aufgabe mit Titel, Beschreibung, Kategorie und
 * Status.
 * 
 * @author Max
 */
public abstract class Task {
    /** Kategorie der Aufgabe */
    public String category;

    /** Inhalt der Aufgabe */
    public String content;

    /** Titel der Aufgabe */
    public String title;

    /**
     * Erstellt eine neue Aufgabe mit Titel (für Strukturierte Aufgaben)
     *
     * @param content  Inhalt der Aufgabe
     * @param category Kategorie der Aufgabe
     */
    public Task(String category, String title, String content) {
        this.content = content;
        this.category = category;
        this.title = title;
    }

    /**
     * Erstellt eine neue Aufgabe ohne Titel (für Fließtext Aufgaben)
     *
     * @param content  Inhalt der Aufgabe
     * @param category Kategorie der Aufgabe
     */
    public Task(String category, String content) {
        this.content = content;
        this.category = category;
        this.title = "none";
    }

    /**
     * Gibt eine String-Darstellung der Aufgabe zurück.
     *
     * @return Beschreibung der Aufgabe inklusive Kategorie, Titel, Beschreibung und
     *         Erledigt-Status
     */
    public String toString() {
        return content;
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
        json.put("content", content);
        json.put("title", title);

        return json;
    }
}
