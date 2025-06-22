/**
 * {@code TaskSimplePanel} ist eine spezialisierte Unterklasse von
 * {@link TaskStructuredPanel}, die eine einfache Aufgabe vom Typ
 * {@link TaskSimple} darstellt.
 *
 * <p>
 * Sie übernimmt die Darstellung und Funktionalität von
 * {@link TaskStructuredPanel}
 * und passt diese für Aufgaben ohne Fälligkeitsdatum an.
 * </p>
 *
 * <p>
 * Die Klasse ist primär für strukturierte Aufgaben gedacht, die keinen
 * Zeitbezug benötigen.
 * </p>
 * 
 * @author Max
 */
public class TaskSimplePanel extends TaskStructuredPanel {

    /**
     * Erzeugt ein neues {@code TaskSimplePanel} für die gegebene einfache Aufgabe.
     *
     * @param task           Die einfache Aufgabe, die dargestellt werden soll.
     * @param onStatusChange Eine Callback-Funktion, die ausgeführt wird, wenn sich
     *                       der Erledigt-Status der Aufgabe ändert (z. B. zur
     *                       Aktualisierung der Anzeige).
     */
    public TaskSimplePanel(TaskSimple task, Runnable onStatusChange) {
        super((TaskStructured) task, onStatusChange);
    }
}
