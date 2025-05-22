import javax.swing.*;

import java.awt.*;

public class TaskPanel extends JPanel {
    private JLabel titleLabel;
    private JTextArea descriptionArea;
    private JCheckBox completedCheckBox;
    private Task task;
    private Runnable onStatusChange;

    public TaskPanel(Task task, Runnable onStatusChange) {
        this.task = task;
        this.onStatusChange = onStatusChange;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        setPreferredSize(new Dimension(300, 100));

        titleLabel = new JLabel(task.title);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 14));

        descriptionArea = new JTextArea(task.description);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        descriptionArea.setBackground(getBackground());

        completedCheckBox = new JCheckBox("Erledigt", task.completed);
        completedCheckBox.setEnabled(!task.completed); // Nur anzeigen, nicht bearbeitbar

        completedCheckBox.addActionListener(e -> {
            task.completed = true; // Status setzen
            InOut.updateTask(task);
            if (onStatusChange != null) {
                onStatusChange.run(); // GridView neu laden
            }
        });

        add(titleLabel);
        add(new JScrollPane(descriptionArea));
        AddExtraComponent(task);

        add(completedCheckBox);
    }

    protected void AddExtraComponent(Task task) {
        return;
    }

    public Task getTask() {
        return task;
    }
}
