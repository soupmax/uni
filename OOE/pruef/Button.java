import javax.swing.*;
import java.awt.*;

public class Button extends JPanel {
    public Button(String type) {
        setLayout(new FlowLayout());

        switch (type) {
            case "addTask":
                addTaskButton();
                break;
            default:
                // Optional: Platzhalter oder Fehler anzeigen
                add(new JLabel("Unbekannter Button-Typ: " + type));
                break;
        }
    }

    private void addTaskButton() {
        JButton squareButton = new JButton("+");

        // Quadratgröße festlegen
        int size = 50;
        squareButton.setPreferredSize(new Dimension(size, size));

        squareButton.setFont(new Font("SansSerif", Font.BOLD, 20));

        squareButton.addActionListener(e -> {
            openInputDialog();
        });

        this.add(squareButton);
    }

    private void openInputDialog() {
        Frame owner = (Frame) SwingUtilities.getWindowAncestor(this);
        TaskInputDialog dialog = new TaskInputDialog(owner);
        Task newTask = dialog.showDialog();

        if (newTask != null) {
            System.out.println(
                    "Neue Aufgabe: " + newTask.title + ", " + newTask.description + ", erledigt: " + newTask.completed);
            // Optional: Füge den Task irgendwo hinzu
        } else {
            System.out.println("Abgebrochen.");
        }
    }
}
