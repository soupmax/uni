import java.awt.Color;
import javax.swing.*;

/**
 * {@code TaskTimedPanel} ist eine spezialisierte Variante von
 * {@link TaskStructuredPanel},
 * die das Fälligkeitsdatum einer zeitgebundenen Aufgabe visuell hervorhebt.
 *
 * <p>
 * Die Hintergrundfarbe signalisiert den Status der Aufgabe:
 * </p>
 * <ul>
 * <li><b>Beige (hellorange):</b> Aufgabe ist heute fällig</li>
 * <li><b>Rosa (hellrot):</b> Aufgabe ist überfällig</li>
 * <li>Standardhintergrund: Aufgabe ist noch offen oder bereits erledigt</li>
 * </ul>
 *
 * <p>
 * Zusätzlich wird das Fälligkeitsdatum als {@link JLabel} unter der
 * Beschreibung angezeigt.
 * </p>
 *
 * @author Fabian
 */
public class TaskTimedPanel extends TaskStructuredPanel {

    /** Label zur Anzeige des Fälligkeitsdatums. */
    private JLabel dueDateLabel;

    /**
     * Erstellt ein neues {@code TaskTimedPanel} zur Anzeige einer zeitgebundenen
     * Aufgabe.
     *
     * @param task           Das {@link TaskTimed}-Objekt, das dargestellt werden
     *                       soll.
     * @param onStatusChange Callback, der bei Statusänderung aufgerufen wird (z. B.
     *                       erledigt).
     */
    public TaskTimedPanel(TaskTimed task, Runnable onStatusChange) {
        super(task, onStatusChange);

        // Fälligkeit optisch hervorheben, wenn Aufgabe nicht abgeschlossen ist
        if (!task.completed) {
            if (task.isDueToday()) {
                setBackground(new Color(255, 235, 205)); // Beige / Orange-Ton
            } else if (task.isOverDue()) {
                setBackground(new Color(255, 204, 203)); // Rot-Ton
            }
        }
    }

    /**
     * Fügt ein Label mit dem Fälligkeitsdatum der Aufgabe hinzu.
     * Wird automatisch beim Erstellen des Panels aufgerufen.
     *
     * @param task Die darzustellende Aufgabe (muss vom Typ {@link TaskTimed} sein).
     */
    @Override
    protected void AddExtraComponent(Task task) {
        TaskTimed timedTask = (TaskTimed) task;
        dueDateLabel = new JLabel("Fällig bis: " + timedTask.getDueDate().toString());
        add(dueDateLabel);
    }
}
