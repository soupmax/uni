import javax.swing.*;
import java.awt.*;

public class GridView extends JPanel {

    private JPanel buttonPanel;
    private JPanel priorityPanel;
    private JPanel openPanel;
    private JPanel donePanel;

    private JPanel priorityContent;
    private JPanel openContent;
    private JPanel doneContent;

    private JPanel tasksWrapperPanel;

    public GridView() {
        setLayout(new BorderLayout());

        // Oben: fester Button-Bereich
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(new CButton("addTask"));
        buttonPanel.add(new CButton("deletePanel"));
        add(buttonPanel, BorderLayout.NORTH);

        // Panels für die Inhalte der Kategorien
        priorityContent = new JPanel();
        openContent = new JPanel();
        doneContent = new JPanel();

        // Panels für die drei Kategorien (mit Überschrift und Inhalt)
        priorityPanel = makeCategoryPanel("Priorität", priorityContent);
        openPanel = makeCategoryPanel("Unerledigt", openContent);
        donePanel = makeCategoryPanel("Erledigt", doneContent);

        // Wrapper für alle Kategorien
        tasksWrapperPanel = new JPanel();
        tasksWrapperPanel.setLayout(new GridLayout(1, 3));
        tasksWrapperPanel.add(priorityPanel);
        tasksWrapperPanel.add(openPanel);
        tasksWrapperPanel.add(donePanel);

        // ScrollPane um alle Aufgabenbereiche
        JScrollPane scrollPane = new JScrollPane(tasksWrapperPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel makeCategoryPanel(String title, JPanel content) {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBorder(BorderFactory.createTitledBorder(title));

        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        wrapper.add(content, BorderLayout.CENTER);

        return wrapper;
    }

    public void putNewTasks(Task[] tasks) {
        // Inhalte leeren
        priorityContent.removeAll();
        openContent.removeAll();
        doneContent.removeAll();

        if (tasks != null) {
            for (Task task : tasks) {
                if (task == null)
                    continue;

                JPanel panel;
                if (task instanceof TaskTimed) {
                    panel = new TaskTimedPanel((TaskTimed) task);
                } else if (task instanceof TaskSimple) {
                    panel = new TaskSimplePanel((TaskSimple) task);
                } else {
                    panel = new TaskPanel(task);
                }

                // Styling für Task-Panels
                panel.setMaximumSize(new Dimension(600, 150)); // breite begrenzen
                panel.setAlignmentX(Component.LEFT_ALIGNMENT);
                panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

                // Kategorisieren
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
