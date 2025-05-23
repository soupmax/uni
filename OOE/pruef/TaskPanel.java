import javax.swing.*;
import java.awt.*;

/**
 * {@code TaskPanel} ist ein generisches Panel zur Darstellung einer
 * {@link Task}.
 * 
 * <p>
 * Es zeigt den Titel, die Beschreibung und den Status (erledigt/nicht erledigt)
 * einer Aufgabe an.
 * Optional kann die Methode {@link #AddExtraComponent(Task)} von Unterklassen
 * überschrieben werden,
 * um zusätzliche Informationen (z.&nbsp;B. Fälligkeitsdatum) anzuzeigen.
 * </p>
 * 
 * <p>
 * Wenn eine Aufgabe als erledigt markiert wird, wird die zugehörige
 * Callback-Funktion ausgeführt
 * und die Änderung dauerhaft gespeichert.
 * </p>
 * 
 * @author Max
 */
public class TaskPanel extends JPanel {

    private JLabel titleLabel;
    private JTextArea descriptionArea;
    private JCheckBox completedCheckBox;
    private Task task;
    private Runnable onStatusChange; // Wird absichtlich nicht entfernt, dient als Trigger für UI-Aktualisierung

    /**
     * Erstellt ein neues Panel zur Anzeige einer Aufgabe.
     *
     * @param task           Die darzustellende Aufgabe.
     * @param onStatusChange Eine Callback-Funktion, die bei Statusänderung der
     *                       Aufgabe ausgeführt wird,
     *                       z.&nbsp;B. um die Anzeige zu aktualisieren.
     */
    public TaskPanel(Task task, Runnable onStatusChange) {
        this.task = task;
        this.onStatusChange = onStatusChange;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        setPreferredSize(new Dimension(300, 100));

        if (task.completed) {
            setBackground(Color.LIGHT_GRAY);
        }

        // Titel
        titleLabel = new JLabel(task.title);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 14));

        // Beschreibung
        descriptionArea = new JTextArea(task.description);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        descriptionArea.setBackground(getBackground());

        // Erledigt-Checkbox
        completedCheckBox = new JCheckBox("Erledigt", task.completed);
        completedCheckBox.setEnabled(!task.completed); // Nur aktiv, wenn nicht bereits erledigt

        completedCheckBox.addActionListener(e -> {
            task.completed = true;
            InOut.updateTask(task); // Dauerhafte Speicherung
            if (onStatusChange != null) {
                onStatusChange.run(); // Trigger für z.B. Listenaktualisierung
            }
        });

        add(titleLabel);
        add(new JScrollPane(descriptionArea));
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
    protected void AddExtraComponent(Task task) {
        // Standardmäßig keine zusätzlichen Komponenten
    }

    /**
     * Gibt die zugehörige Aufgabe dieses Panels zurück.
     *
     * @return Die aktuell angezeigte {@link Task}.
     */
    public Task getTask() {
        return task;
    }
}
