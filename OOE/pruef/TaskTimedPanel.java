import java.awt.Color;
import javax.swing.*;

/**
 * {@code TaskTimedPanel} ist eine spezialisierte Variante von
 * {@link TaskPanel},
 * die das Fälligkeitsdatum einer zeitgebundenen Aufgabe visuell hervorhebt.
 *
 * <p>
 * Die Hintergrundfarbe signalisiert den Status der Aufgabe:
 * </p>
 * <ul>
 * <li><b>Orange</b>: Aufgabe ist heute fällig</li>
 * <li><b>Rot</b>: Aufgabe ist überfällig</li>
 * <li>Standardfarbe: Aufgabe ist erledigt oder liegt in der Zukunft</li>
 * </ul>
 * 
 * @author Max
 */
public class TaskTimedPanel extends TaskPanel {

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
                setBackground(new Color(255, 235, 205));
            } else if (task.isOverDue()) {
                setBackground(new Color(255, 204, 203));
            }
        }
    }

    /**
     * Fügt ein Label mit dem Fälligkeitsdatum der Aufgabe hinzu.
     *
     * @param task Die darzustellende Aufgabe.
     */
    @Override
    protected void AddExtraComponent(Task task) {
        TaskTimed timedTask = (TaskTimed) task;
        dueDateLabel = new JLabel("Fällig bis: " + timedTask.getDueDate().toString());
        add(dueDateLabel);
    }
}
