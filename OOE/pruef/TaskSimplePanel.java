/**
 * {@code TaskSimplePanel} ist eine spezialisierte Unterklasse von
 * {@link TaskPanel},
 * die eine einfache Aufgabe vom Typ {@link TaskSimple} darstellt.
 * <p>
 * Sie übernimmt die Darstellung und Funktionalität von {@link TaskPanel},
 * jedoch mit dem spezifizierten {@link TaskSimple}-Aufgabentyp.
 * 
 * @author Max
 */
public class TaskSimplePanel extends TaskPanel {

    /**
     * Erzeugt ein neues {@code TaskSimplePanel} für die gegebene einfache Aufgabe.
     *
     * @param task           Die einfache Aufgabe, die dargestellt werden soll.
     * @param onStatusChange Runnable, der beim Statuswechsel der Aufgabe ausgeführt
     *                       wird (z.B. Refresh).
     */
    public TaskSimplePanel(TaskSimple task, Runnable onStatusChange) {
        super((Task) task, onStatusChange);
    }
}
