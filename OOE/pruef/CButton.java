import javax.swing.*;
import java.awt.*;

public class CButton extends JPanel {
    int size = 50;

    public CButton(String type) {
        setLayout(new FlowLayout());

        switch (type) {
            case "addTask":
                addTaskButton();
                break;
            case "deletePanel":
                addDeletePanelButton();
                break;
            default:
                // Optional: Platzhalter oder Fehler anzeigen
                add(new JLabel("Unbekannter Button-Typ: " + type));
                break;
        }
    }

    private JButton makeSquareButton(String text) {
        JButton squareButton = new JButton(text);
        // squareButton.setPreferredSize(new Dimension(size, size));
        squareButton.setFont(new Font("SansSerif", Font.BOLD, 20));
        return squareButton;
    }

    private void addTaskButton() {
        JButton btn = makeSquareButton("neue aufgabe");
        btn.addActionListener(e -> {
            openInputDialog();
        });

        this.add(btn);
    }

    private void openInputDialog() {
        Frame owner = (Frame) SwingUtilities.getWindowAncestor(this);
        TabPanel tab = (TabPanel) SwingUtilities.getAncestorOfClass(TabPanel.class, this);
        TaskInputDialog dialog = new TaskInputDialog(owner, tab.getCategory());

        Task newTask = dialog.showDialog();

        if (newTask != null) {
            System.out.println("Neue Aufgabe");
            InOut.saveTask(newTask);
            tab.reload();
        } else {
            System.out.println("Abgebrochen.");
        }
    }

    private void addDeletePanelButton() {
        JButton btn = makeSquareButton("aktuelle kategorie löschen");
        btn.addActionListener(e -> {
            deletePanel();
        });

        this.add(btn);
    }

    private void deletePanel() {

        JTabbedPane tabbedPane = (JTabbedPane) SwingUtilities.getAncestorOfClass(JTabbedPane.class, this);
        TabPanel tab = (TabPanel) SwingUtilities.getAncestorOfClass(TabPanel.class, this);

        InOut.deleteCategoryTasks(tab.getCategory());
        tabbedPane.remove(tab);
    }
}
