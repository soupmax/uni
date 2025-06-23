import javax.swing.*;
import java.awt.*;

/**
 * {@code TaskStructuredPanel} ist ein spezialisiertes Panel zur Darstellung und
 * Bearbeitung
 * einer strukturierten Aufgabe vom Typ {@link TaskStructured}.
 *
 * <p>
 * Es erweitert {@link TaskPanel} um eine Checkbox zur Anzeige und Änderung des
 * Erledigt-Status der Aufgabe. Wird eine Aufgabe als erledigt markiert, wird
 * sie
 * dauerhaft gespeichert und eine Callback-Funktion zur UI-Aktualisierung
 * aufgerufen.
 * </p>
 *
 * <p>
 * Unterklassen können die Methode {@link #AddExtraComponent(Task)}
 * überschreiben,
 * um zusätzliche Informationen (z.&nbsp;B. Fälligkeitsdatum) darzustellen.
 * </p>
 * 
 * @author Fabian
 */
public class TaskStructuredPanel extends TaskPanel {

    /** Checkbox zur Darstellung und Änderung des Erledigt-Status */
    private JCheckBox completedCheckBox;

    /** Die dargestellte strukturierte Aufgabe */
    private TaskStructured task;

    /**
     * Callback-Funktion, die bei Statusänderung (z.&nbsp;B. erledigt) ausgeführt
     * wird.
     * Dient u.&nbsp;a. zur Aktualisierung der UI.
     */
    private Runnable onStatusChange;

    /**
     * Erstellt ein neues Panel zur Anzeige einer strukturierten Aufgabe.
     *
     * @param task           Die darzustellende Aufgabe.
     * @param onStatusChange Eine Callback-Funktion, die bei Statusänderung der
     *                       Aufgabe ausgeführt wird,
     *                       z.&nbsp;B. um die Anzeige zu aktualisieren.
     */
    public TaskStructuredPanel(TaskStructured task, Runnable onStatusChange) {
        super(task);
        this.task = task;
        this.onStatusChange = onStatusChange;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        setPreferredSize(new Dimension(300, 100));
        setBackground(new Color(245, 245, 245));

        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        if (task.completed) {
            setBackground(Color.LIGHT_GRAY);
        }

        // Erledigt-Checkbox
        completedCheckBox = new JCheckBox("Erledigt", task.completed);
        completedCheckBox.setEnabled(!task.completed); // Nur aktiv, wenn nicht bereits erledigt
        completedCheckBox.setOpaque(false);

        completedCheckBox.addActionListener(e -> {
            task.completed = true;
            InOut.updateTask(task); // Dauerhafte Speicherung
            if (onStatusChange != null) {
                onStatusChange.run(); // Trigger für z.B. Listenaktualisierung
            }
        });

        AddExtraComponent(task);
        add(completedCheckBox);
    }

    /**
     * Fügt zusätzliche Informationen zum Panel hinzu.
     * Diese Methode kann von Unterklassen überschrieben werden (z.&nbsp;B. um ein
     * Fälligkeitsdatum anzuzeigen).
     *
     * @param task Die anzuzeigende Aufgabe.
     */
    @Override
    protected void AddExtraComponent(Task task) {
        // Standardmäßig keine zusätzlichen Komponenten
    }

    /**
     * Gibt die zugehörige Aufgabe dieses Panels zurück.
     *
     * @return Die aktuell angezeigte {@link Task}.
     */
    @Override
    public Task getTask() {
        return task;
    }
}
