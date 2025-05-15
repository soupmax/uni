package pruef;

import javax.swing.*;
import java.awt.*;

public class TaskPanel extends JPanel {
    private JLabel titleLabel;
    private JTextArea descriptionArea;
    private JCheckBox completedCheckBox;

    public TaskPanel(Task task) {
        setLayout(new BorderLayout());
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
        completedCheckBox.setEnabled(false); // Nur anzeigen, nicht bearbeitbar

        add(titleLabel, BorderLayout.NORTH);
        add(new JScrollPane(descriptionArea), BorderLayout.CENTER);
        add(completedCheckBox, BorderLayout.SOUTH);
    }
}
