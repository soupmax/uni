/**
 * Repräsentiert eine Aufgabe im Fließtext-Stil ohne Titel und Status.
 * <p>
 * Wird typischerweise in einer Textarea innerhalb eines Tabs dargestellt.
 * Diese Art von Aufgabe enthält nur Kategorie und Inhalt.
 * </p>
 * 
 * @author Fabian
 */
public class TaskFreeform extends Task {

    /**
     * Erstellt eine neue Fließtext-Aufgabe.
     *
     * @param category Kategorie der Aufgabe
     * @param content  Inhalt der Aufgabe als freier Text
     */
    public TaskFreeform(String category, String content) {
        super(category, content);
    }
}
