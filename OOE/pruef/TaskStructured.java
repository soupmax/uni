import org.json.JSONObject;

/**
 * {@code TaskStructured} ist eine abstrakte Oberklasse für strukturierte
 * Aufgaben,
 * die über einen Titel, eine Beschreibung, eine Kategorie und einen
 * Erledigt-Status verfügen.
 *
 * <p>
 * Sie stellt gemeinsame Funktionalitäten für konkrete Unterklassen wie
 * {@link TaskSimple}
 * und {@link TaskTimed} bereit.
 * </p>
 * 
 * @author Fabian
 */
public abstract class TaskStructured extends Task {

    /** Gibt an, ob die Aufgabe erledigt ist */
    protected boolean completed;

    /**
     * Erstellt eine neue strukturierte Aufgabe mit vollständigen Attributen.
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
     * Erstellt eine neue strukturierte Aufgabe, die standardmäßig als nicht
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
    @Override
    public String toString() {
        return "category: " + category + "\n" +
                "title: " + title +
                " content: " + content +
                " completed: " + (completed ? "true" : "false");
    }

    /**
     * Wandelt diese strukturierte Aufgabe in ein {@link JSONObject} um.
     *
     * @return Ein JSONObject mit den Attributen dieser Aufgabe
     */
    @Override
    protected JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("category", category);
        json.put("title", title);
        json.put("content", content);
        json.put("completed", completed);
        return json;
    }
}
