import org.json.JSONObject;

/**
 * Erweiterung der Klasse Task ohne zusätzliche Attribute.
 * Repräsentiert eine einfache Aufgabe.
 * 
 * @author Max
 */
public class TaskSimple extends TaskStructured {

    /**
     * Konstruktor mit vollständigen Attributen.
     *
     * @param category  Kategorie der Aufgabe
     * @param title     Titel der Aufgabe
     * @param content   Beschreibung der Aufgabe
     * @param completed Erledigt-Status der Aufgabe
     */
    public TaskSimple(String category, String title, String content, boolean completed) {
        super(category, title, content, completed);
    }

    /**
     * Konstruktor ohne Erledigt-Status (standardmäßig false).
     *
     * @param category Kategorie der Aufgabe
     * @param title    Titel der Aufgabe
     * @param content  Beschreibung der Aufgabe
     */
    public TaskSimple(String category, String title, String content) {
        super(category, title, content);
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
                " content: " + content +
                " completed: " + (completed ? "true" : "false");
    }

    @Override
    /**
     * Wandelt eine TaskSimple in ein JSONObject um.
     * 
     * @param t TaskSimple-Objekt
     * @return JSONObject mit TaskSimple-Daten
     */
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();

        return json;
    }
}
