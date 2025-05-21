import javax.swing.*;
import java.awt.*;

public class TaskInputDialog extends JDialog {
    private JTextField titleField;
    private JTextArea descriptionArea;
    private JCheckBox completedBox;
    private Task resultTask;

    public TaskInputDialog(Frame owner) {
        super(owner, "Neue Aufgabe erstellen", true);
        setLayout(new BorderLayout());
        setSize(300, 250);
        setLocationRelativeTo(owner);

        // Eingabefelder
        JPanel inputPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        titleField = new JTextField();
        descriptionArea = new JTextArea(4, 20);
        completedBox = new JCheckBox("Erledigt");

        inputPanel.add(new JLabel("Titel:"));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Beschreibung:"));
        inputPanel.add(new JScrollPane(descriptionArea));
        inputPanel.add(completedBox);

        add(inputPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("Erstellen");
        JButton cancelButton = new JButton("Abbrechen");

        okButton.addActionListener(e -> {
            resultTask = new Task(
                    titleField.getText(),
                    descriptionArea.getText(),
                    completedBox.isSelected());
            dispose();
        });

        cancelButton.addActionListener(e -> {
            resultTask = null;
            dispose();
        });

        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public Task showDialog() {
        setVisible(true);
        return resultTask;
    }
}
