import javax.swing.*;

/**
 * {@code TaskTimedPanel} ist eine spezialisierte Variante von
 * {@link TaskPanel},
 * die zusätzlich das Fälligkeitsdatum einer zeitgebundenen
 * Aufgabe anzeigt.
 * 
 * @author Max
 */
public class TaskTimedPanel extends TaskPanel {
    /** Label zur Anzeige des Fälligkeitsdatums. */
    private JLabel dueDateLabel;

    /**
     * Erstellt ein neues {@code TaskTimedPanel} für eine zeitgebundene Aufgabe.
     *
     * @param task           Das {@link TaskTimed}-Objekt, das angezeigt werden
     *                       soll.
     * @param onStatusChange Eine Callback-Funktion, die beim Statuswechsel (z. B.
     *                       als erledigt markieren) aufgerufen wird.
     */
    public TaskTimedPanel(TaskTimed task, Runnable onStatusChange) {
        super(task, onStatusChange); // ruft den Konstruktor von TaskPanel auf
    }

    /**
     * Fügt zusätzliche Komponenten (Fälligkeitsdatum) dem Panel
     * hinzu.
     *
     * @param task Die Aufgabe, von der die zusätzlichen Informationen angezeigt
     *             werden sollen.
     */
    @Override
    protected void AddExtraComponent(Task task) {
        dueDateLabel = new JLabel("Fällig bis: " + ((TaskTimed) task).dueDate.toString());

        add(dueDateLabel);
    }
}
