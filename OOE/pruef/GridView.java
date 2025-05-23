import javax.swing.*;
import java.awt.*;

/**
 * {@code GridView} ist ein JPanel, das Aufgaben in drei Kategorien anzeigt:
 * Priorität, Unerledigt und Erledigt.
 * Es enthält auch Bedienelemente zum Hinzufügen von Aufgaben und Löschen der
 * Kategorie.
 */
public class GridView extends JPanel {

    private JPanel buttonPanel; // Bereich für Steuerbuttons (addTask, deletePanel)
    private JPanel priorityPanel; // Panel mit Überschrift und Aufgaben mit Priorität
    private JPanel openPanel; // Panel mit Überschrift und offenen Aufgaben
    private JPanel donePanel; // Panel mit Überschrift und erledigten Aufgaben

    private JPanel priorityContent; // Container für die Prioritätsaufgaben
    private JPanel openContent; // Container für offene Aufgaben
    private JPanel doneContent; // Container für erledigte Aufgaben

    private JPanel tasksWrapperPanel; // Wrapper für alle Kategorien-Panels
    private String category; // Kategorie, deren Aufgaben angezeigt werden

    /**
     * Erzeugt eine neue GridView für eine gegebene Kategorie.
     *
     * @param category Die Kategorie, deren Aufgaben dargestellt werden sollen.
     */
    public GridView(String category) {
        this.category = category;
        setLayout(new BorderLayout());

        // Obere Leiste mit Buttons
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(new CButton("addTask"));
        buttonPanel.add(new CButton("deletePanel"));
        add(buttonPanel, BorderLayout.NORTH);

        // Inhaltspanels initialisieren
        priorityContent = new JPanel();
        openContent = new JPanel();
        doneContent = new JPanel();

        // Kategorien-Panels mit Titel und Inhalt erstellen
        priorityPanel = makeCategoryPanel("Priorität", priorityContent);
        openPanel = makeCategoryPanel("Unerledigt", openContent);
        donePanel = makeCategoryPanel("Erledigt", doneContent);

        // Wrapper für alle Kategorien nebeneinander anordnen
        tasksWrapperPanel = new JPanel();
        tasksWrapperPanel.setLayout(new GridLayout(1, 3));
        tasksWrapperPanel.add(priorityPanel);
        tasksWrapperPanel.add(openPanel);
        tasksWrapperPanel.add(donePanel);

        // Scrollpane für alle Aufgabenbereiche hinzufügen
        JScrollPane scrollPane = new JScrollPane(tasksWrapperPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Erstellt ein Panel für eine Aufgaben-Kategorie mit Titel und Inhaltspanel.
     *
     * @param title   Titel der Kategorie (z.B. "Priorität").
     * @param content Inhaltspanel, das die Aufgaben dieser Kategorie enthält.
     * @return Ein JPanel mit Titel und Inhalt.
     */
    private JPanel makeCategoryPanel(String title, JPanel content) {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBorder(BorderFactory.createTitledBorder(title));

        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        wrapper.add(content, BorderLayout.CENTER);

        return wrapper;
    }

    /**
     * Erstellt ein passendes Panel für eine gegebene Aufgabe,
     * abhängig vom Typ der Aufgabe (z.B. TaskTimed, TaskSimple).
     *
     * @param task Die Aufgabe, für die ein Panel erzeugt werden soll.
     * @return Das passende JPanel zur Darstellung der Aufgabe.
     */
    private JPanel createPanelForTask(Task task) {
        Runnable refresh = () -> putNewTasks(InOut.loadCategoryTasks(category));

        if (task instanceof TaskTimed)
            return new TaskTimedPanel((TaskTimed) task, refresh);
        else if (task instanceof TaskSimple)
            return new TaskSimplePanel((TaskSimple) task, refresh);
        else
            return new TaskPanel(task, refresh);
    }

    /**
     * Fügt eine Liste von Aufgaben in die entsprechenden Kategorien-Panels ein.
     * Bereits vorhandene Inhalte werden vorher entfernt.
     *
     * @param tasks Array von Aufgaben, die dargestellt werden sollen.
     */
    public void putNewTasks(Task[] tasks) {
        // Inhalte leeren
        priorityContent.removeAll();
        openContent.removeAll();
        doneContent.removeAll();

        if (tasks != null) {
            for (Task task : tasks) {
                if (task == null)
                    continue;

                JPanel panel = createPanelForTask(task);

                // Styling der Task-Panels
                panel.setMaximumSize(new Dimension(600, 150)); // maximale Breite
                panel.setAlignmentX(Component.LEFT_ALIGNMENT);
                panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

                // Zuordnung zur Kategorie
                if (task.completed) {
                    doneContent.add(panel);
                } else if (task.priority) {
                    priorityContent.add(panel);
                } else {
                    openContent.add(panel);
                }
            }
        }

        revalidate();
        repaint();
    }
}
