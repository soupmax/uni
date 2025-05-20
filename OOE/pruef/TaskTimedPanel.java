package pruef;

import javax.swing.*;
import java.awt.*;

public class TaskTimedPanel extends TaskPanel {
    private JLabel dueDateLabel;
    private JLabel priorityLabel;

    public TaskTimedPanel(TaskTimed task) {
        super(task); // ruft den Konstruktor von TaskPanel auf

        // Neues Panel unten ergänzen
        JPanel extraPanel = new JPanel(new GridLayout(2, 1));
        dueDateLabel = new JLabel("Fällig bis: " + task.dueDate.toString());
        priorityLabel = new JLabel("Priorität: " + task.priority);

        extraPanel.add(dueDateLabel);
        extraPanel.add(priorityLabel);

        // Füge das zusätzliche Panel unten hinzu
        add(extraPanel, BorderLayout.EAST);
    }
}
