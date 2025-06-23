import javax.swing.*;
import java.awt.*;

/**
 * {@code GridView} ist ein {@link JPanel}, das strukturierte Aufgaben einer
 * bestimmten
 * Kategorie visuell in drei Spalten darstellt: zeitlich begrenzte Aufgaben,
 * unerledigte Aufgaben
 * und erledigte Aufgaben.
 *
 * <p>
 * Jede Spalte enthält ein eigenes Panel mit einem beschrifteten Rahmen und wird
 * automatisch
 * mit den zugehörigen Aufgaben gefüllt. Zusätzlich befinden sich oberhalb zwei
 * Buttons zum
 * Hinzufügen einer neuen Aufgabe und zum Löschen der gesamten Kategorie.
 * </p>
 *
 * <p>
 * Aufgaben vom Typ {@link TaskFreeform} werden ignoriert, da sie in separaten
 * Panels dargestellt werden.
 * </p>
 *
 * @author Max
 */
public class GridView extends JPanel {

    private JPanel buttonPanel; // Bereich für Steuerbuttons (addTask, deletePanel)
    private JPanel timedPanel; // Panel mit Überschrift und Aufgaben mit Ablaufdatum
    private JPanel openPanel; // Panel mit Überschrift und offenen Aufgaben
    private JPanel donePanel; // Panel mit Überschrift und erledigten Aufgaben

    private JPanel timedContent; // Container für die Aufgaben mit Ablaufdatum
    private JPanel openContent; // Container für offene Aufgaben
    private JPanel doneContent; // Container für erledigte Aufgaben

    private JPanel tasksWrapperPanel; // Wrapper für alle Kategorien-Panels
    private String category; // Kategorie, deren Aufgaben angezeigt werden

    /**
     * Erzeugt eine neue {@code GridView} für eine gegebene Aufgaben-Kategorie.
     *
     * @param category Die Kategorie, deren Aufgaben angezeigt und verwaltet werden
     *                 sollen
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
        timedContent = new JPanel();
        openContent = new JPanel();
        doneContent = new JPanel();

        // Kategorien-Panels mit Titel und Inhalt erstellen
        timedPanel = makeCategoryPanel("Zeitbregrenzt", timedContent);
        openPanel = makeCategoryPanel("Unerledigt", openContent);
        donePanel = makeCategoryPanel("Erledigt", doneContent);

        // Wrapper für alle Kategorien nebeneinander anordnen
        tasksWrapperPanel = new JPanel();
        tasksWrapperPanel.setLayout(new GridLayout(1, 3));
        tasksWrapperPanel.add(timedPanel);
        tasksWrapperPanel.add(openPanel);
        tasksWrapperPanel.add(donePanel);

        // Scrollpane für alle Aufgabenbereiche hinzufügen
        JScrollPane scrollPane = new JScrollPane(tasksWrapperPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Erstellt ein Panel für eine Aufgaben-Kategorie mit Überschrift und Inhalt.
     *
     * @param title   Titel der Kategorie (z. B. "Erledigt")
     * @param content Inhaltspanel, in das Aufgaben eingefügt werden
     * @return Ein {@link JPanel} mit Rahmen und vertikaler Aufgabenliste
     */
    private JPanel makeCategoryPanel(String title, JPanel content) {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBorder(BorderFactory.createTitledBorder(title));

        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        wrapper.add(content, BorderLayout.CENTER);

        return wrapper;
    }

    /**
     * Erstellt ein UI-Panel zur Darstellung der übergebenen strukturierten Aufgabe.
     *
     * @param task Die darzustellende Aufgabe
     * @return Ein passendes {@link JPanel}, abhängig vom konkreten Typ der Aufgabe
     * @throws IllegalArgumentException wenn der Aufgabentyp nicht unterstützt wird
     */
    private JPanel createPanelForTask(TaskStructured task) {
        Runnable refresh = () -> putNewTasks(InOut.loadCategoryTasks(category));

        if (task instanceof TaskTimed)
            return new TaskTimedPanel((TaskTimed) task, refresh);
        else if (task instanceof TaskSimple)
            return new TaskSimplePanel((TaskSimple) task, refresh);
        else
            throw new IllegalArgumentException("unbekannter aufgabentyp");
    }

    /**
     * Lädt neue Aufgaben in die GridView und sortiert sie je nach Status und Typ in
     * die drei Bereiche: zeitbegrenzt, unerledigt oder erledigt.
     * 
     * <p>
     * Alle bisherigen Inhalte werden entfernt. {@link TaskFreeform}-Objekte werden
     * ignoriert.
     * </p>
     *
     * @param tasks Ein Array von {@link Task}-Objekten, das neu dargestellt werden
     *              soll
     */
    public void putNewTasks(Task[] tasks) {
        // Inhalte leeren
        timedContent.removeAll();
        openContent.removeAll();
        doneContent.removeAll();

        if (tasks != null) {
            for (Task task : tasks) {
                // wir wollen hier keine fließtext Aufgaben
                if (task == null || task instanceof TaskFreeform)
                    continue;

                TaskStructured taskStruct = (TaskStructured) task;
                JPanel panel = createPanelForTask(taskStruct);

                // Styling der Task-Panels
                panel.setMaximumSize(new Dimension(600, 150)); // maximale Breite
                panel.setAlignmentX(Component.LEFT_ALIGNMENT);
                panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

                // Zuordnung zur Kategorie
                if (taskStruct.completed) {
                    doneContent.add(panel);
                } else if (taskStruct instanceof TaskTimed) {
                    timedContent.add(panel);
                } else {
                    openContent.add(panel);
                }
            }
        }

        revalidate();
        repaint();
    }
}
