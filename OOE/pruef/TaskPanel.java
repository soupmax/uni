import javax.swing.*;
import java.awt.*;

/**
 * {@code TaskPanel} ist ein JPanel, das eine Aufgabe visuell darstellt.
 * Es zeigt Titel, Beschreibung und Erledigt-Status an.
 * Optional können zusätzliche Informationen durch die Methode
 * {@code AddExtraComponent} ergänzt werden.
 */
public class TaskPanel extends JPanel {

    private JLabel titleLabel;
    private JTextArea descriptionArea;
    private JCheckBox completedCheckBox;
    private Task task;
    private Runnable onStatusChange;

    /**
     * Erstellt ein neues Panel zur Anzeige einer Aufgabe.
     *
     * @param task           Die darzustellende Aufgabe.
     * @param onStatusChange Ein Callback, der aufgerufen wird, wenn der Status der
     *                       Aufgabe geändert wird (z. B. auf "erledigt").
     */
    public TaskPanel(Task task, Runnable onStatusChange) {
        this.task = task;
        this.onStatusChange = onStatusChange;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        setPreferredSize(new Dimension(300, 100));

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
        completedCheckBox.setEnabled(!task.completed); // nur aktiv, wenn nicht bereits erledigt

        completedCheckBox.addActionListener(e -> {
            task.completed = true; // Aufgabe als erledigt markieren
            InOut.updateTask(task); // Speicherung der Änderung
            if (onStatusChange != null) {
                onStatusChange.run(); // z. B. zum Neuladen der Anzeige
            }
        });

        add(titleLabel);
        add(new JScrollPane(descriptionArea));
        AddExtraComponent(task);
        add(completedCheckBox);
    }

    /**
     * Fügt bei Bedarf zusätzliche Komponenten für abgeleitete Panels hinzu.
     * Kann von Unterklassen wie {@code TaskTimedPanel} überschrieben werden.
     *
     * @param task Die Aufgabe, aus der Informationen extrahiert werden können.
     */
    protected void AddExtraComponent(Task task) {
        // Wird von Unterklassen überschrieben
    }

    /**
     * Gibt die zugehörige Aufgabe dieses Panels zurück.
     *
     * @return Die dargestellte {@code Task}.
     */
    public Task getTask() {
        return task;
    }
}
