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
 * Bei Fließtext-Aufgaben wird der Textbereich bearbeitbar angezeigt.
 * </p>
 *
 * @author Fabian
 */
public abstract class TaskPanel extends JPanel {

    /** Titel der Aufgabe (nur bei strukturierten Aufgaben sichtbar) */
    private JLabel titleLabel;

    /** Beschreibung oder Fließtext der Aufgabe */
    protected JTextArea contentArea;

    /** Die dargestellte Aufgabe */
    protected Task task;

    /**
     * Erstellt ein neues Panel zur Anzeige einer Aufgabe.
     *
     * @param task Die darzustellende Aufgabe.
     */
    public TaskPanel(Task task) {
        this.task = task;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        setPreferredSize(new Dimension(300, 100));
        setBackground(new Color(245, 245, 245));

        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        // Titel nur anzeigen, wenn es sich nicht um eine Fließtext-Aufgabe handelt
        if (!task.title.equals("none")) {
            titleLabel = new JLabel(task.title);
            titleLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        }

        // Beschreibung / Textbereich
        boolean freeform = "none".equals(task.title);

        contentArea = new JTextArea(task.content);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        contentArea.setEditable(freeform); // Nur Fließtext-Aufgaben sind editierbar
        contentArea.setOpaque(freeform);
        contentArea.setBackground(getBackground());

        if (!freeform) {
            add(titleLabel);
        }

        add(new JScrollPane(contentArea));

        // Möglichkeit für Unterklassen, weitere UI-Elemente hinzuzufügen
        AddExtraComponent(task);
    }

    /**
     * Fügt zusätzliche Informationen zum Panel hinzu.
     * Diese Methode kann von Unterklassen überschrieben werden (z.&nbsp;B. um ein
     * Fälligkeitsdatum oder Checkbox anzuzeigen).
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
