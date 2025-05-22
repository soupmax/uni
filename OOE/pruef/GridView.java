import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GridView extends JPanel {

    private JPanel flowPanel;
    private JScrollPane scrollPane;

    public GridView() {
        setLayout(new BorderLayout());

        // FlowLayout mit linker Ausrichtung & vertikalem Umbruch
        flowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        flowPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ScrollPane
        scrollPane = new JScrollPane(flowPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(scrollPane, BorderLayout.CENTER);
    }

    public void addPanel(JPanel panel) {
        panel.setPreferredSize(new Dimension(300, 200)); // Größe der Einzelpanels
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        flowPanel.add(panel);
        flowPanel.revalidate();
        flowPanel.repaint();
    }

    public void addPanels(List<JPanel> panels) {
        for (JPanel panel : panels) {
            addPanel(panel);
        }
    }

    public void clearPanels() {
        flowPanel.removeAll();
        flowPanel.revalidate();
        flowPanel.repaint();
    }

    public void putNewTasks(Task[] tasks) {
        clearPanels();
        if (tasks != null) {
            for (Task task : tasks) {
                if (task == null) {
                    continue;
                }
                JPanel panel;
                if (task instanceof TaskTimed) {
                    panel = new TaskTimedPanel((TaskTimed) task);
                } else if (task instanceof TaskSimple) {
                    panel = new TaskSimplePanel((TaskSimple) task);
                } else {
                    panel = new TaskPanel(task);
                }
                addPanel(panel);
            }
        } else {
            System.out.println("tasks sind null");
        }
        addPanel(new Button("addTask"));
    }

}
