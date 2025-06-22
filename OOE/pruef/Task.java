import org.json.JSONObject;

/**
 * Abstrakte Basisklasse für Aufgaben.
 * <p>
 * Repräsentiert eine generische Aufgabe mit Kategorie, Titel und Inhalt.
 * Wird von konkreten Task-Typen wie {@link TaskSimple}, {@link TaskTimed} oder
 * {@link TaskFreeform} erweitert.
 * </p>
 * <p>
 * Die Klasse bietet grundlegende Methoden zur Serialisierung und gemeinsame
 * Felder.
 * </p>
 * 
 * @author Fabian
 */
public abstract class Task {
    /** Kategorie der Aufgabe. */
    public String category;

    /** Inhalt der Aufgabe. */
    public String content;

    /** Titel der Aufgabe. Bei Fließtext-Aufgaben standardmäßig "none". */
    public String title;

    /**
     * Konstruktor für strukturierte Aufgaben mit Titel.
     *
     * @param category Kategorie der Aufgabe
     * @param title    Titel der Aufgabe
     * @param content  Inhalt der Aufgabe
     */
    public Task(String category, String title, String content) {
        this.content = content;
        this.category = category;
        this.title = title;
    }

    /**
     * Konstruktor für Fließtext-Aufgaben (ohne expliziten Titel).
     *
     * @param category Kategorie der Aufgabe
     * @param content  Inhalt der Aufgabe
     */
    public Task(String category, String content) {
        this.content = content;
        this.category = category;
        this.title = "none";
    }

    /**
     * Gibt den Inhalt der Aufgabe zurück.
     * Kann bei Bedarf überschrieben werden.
     *
     * @return Der Aufgabeninhalt als {@code String}
     */
    @Override
    public String toString() {
        return content;
    }

    /**
     * Serialisiert diese Aufgabe als {@link JSONObject}.
     * Erweiterte Klassen fügen ggf. weitere Felder hinzu.
     * 
     * @return JSON-Repräsentation dieser Aufgabe
     */
    protected JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("category", category);
        json.put("content", content);
        json.put("title", title);

        return json;
    }
}
