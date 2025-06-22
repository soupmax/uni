import org.json.JSONObject;

/**
 * {@code TaskSimple} repräsentiert eine einfache strukturierte Aufgabe ohne
 * Fälligkeitsdatum.
 * Sie erbt alle Attribute und Methoden von {@link TaskStructured}.
 *
 * <p>
 * Eine {@code TaskSimple}-Instanz enthält Informationen über die Kategorie, den
 * Titel,
 * den Aufgabeninhalt und ob die Aufgabe erledigt wurde.
 * </p>
 *
 * <p>
 * Die Klasse bietet Konstruktoren zur Erstellung einer Aufgabe mit oder ohne
 * explizitem Erledigt-Status.
 * </p>
 *
 * @author Fabian
 */
public class TaskSimple extends TaskStructured {

    /**
     * Erstellt eine neue {@code TaskSimple}-Aufgabe mit allen Attributen.
     *
     * @param category  Kategorie der Aufgabe
     * @param title     Titel der Aufgabe
     * @param content   Beschreibung der Aufgabe
     * @param completed Erledigt-Status (true = erledigt)
     */
    public TaskSimple(String category, String title, String content, boolean completed) {
        super(category, title, content, completed);
    }

    /**
     * Erstellt eine neue {@code TaskSimple}-Aufgabe, standardmäßig als nicht
     * erledigt.
     *
     * @param category Kategorie der Aufgabe
     * @param title    Titel der Aufgabe
     * @param content  Beschreibung der Aufgabe
     */
    public TaskSimple(String category, String title, String content) {
        super(category, title, content);
    }

    /**
     * Gibt eine textuelle Darstellung der Aufgabe zurück.
     * 
     * @return Eine formatierte Zeichenkette mit den wichtigsten Informationen.
     */
    @Override
    public String toString() {
        return "category: " + category + "\n" +
                "title: " + title +
                " content: " + content +
                " completed: " + (completed ? "true" : "false");
    }

    /**
     * Serialisiert diese {@code TaskSimple} in ein {@link JSONObject}.
     *
     * @return Ein JSON-Objekt mit allen relevanten Attributen.
     */
    @Override
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();
        return json;
    }
}
