import javax.swing.*;
import java.awt.*;

/**
 * Die Klasse {@code CButton} ist ein Panel, das je nach angegebenem Typ
 * einen spezifischen Button darstellt.
 *
 * <p>
 * Unterstützte Button-Typen:
 * <ul>
 * <li>{@code "addTask"} – Fügt eine neue Aufgabe über einen Dialog hinzu</li>
 * <li>{@code "deletePanel"} – Löscht die aktuell geöffnete Kategorie (Tab)</li>
 * </ul>
 * </p>
 *
 * <p>
 * Bei unbekanntem Typ wird ein Hinweislabel angezeigt.
 * </p>
 *
 * @author Max
 */
public class CButton extends JPanel {
    int size = 50;

    /**
     * Erstellt ein {@code CButton}-Panel und fügt den entsprechenden Button
     * basierend auf dem übergebenen Typ hinzu.
     *
     * @param type Der Typ des Buttons ({@code "addTask"} oder
     *             {@code "deletePanel"})
     */
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
                add(new JLabel("Unbekannter Button-Typ: " + type));
                break;
        }
    }

    /**
     * Erstellt einen standardisierten Button mit fester Schriftgröße.
     *
     * @param text Die Beschriftung des Buttons
     * @return Ein stilisierter {@link JButton}
     */
    private JButton makeSquareButton(String text) {
        JButton squareButton = new JButton(text);
        // squareButton.setPreferredSize(new Dimension(size, size));
        squareButton.setFont(new Font("SansSerif", Font.BOLD, 20));
        return squareButton;
    }

    /**
     * Fügt einen Button zum Hinzufügen einer neuen Aufgabe hinzu.
     * Öffnet beim Klicken einen Eingabedialog.
     */
    private void addTaskButton() {
        JButton btn = makeSquareButton("neue aufgabe");
        btn.addActionListener(e -> openInputDialog());
        this.add(btn);
    }

    /**
     * Öffnet einen {@link TaskInputDialog}, um eine neue Aufgabe zu erfassen.
     * Wird eine Aufgabe bestätigt, wird sie gespeichert und das Panel neu geladen.
     */
    private void openInputDialog() {
        Frame owner = (Frame) SwingUtilities.getWindowAncestor(this);
        TabPanel tab = (TabPanel) SwingUtilities.getAncestorOfClass(TabPanel.class, this);
        TaskInputDialog dialog = new TaskInputDialog(owner, tab.getCategory());

        TaskStructured newTask = dialog.showDialog();

        if (newTask != null) {
            InOut.saveTask(newTask);
            tab.reload();
        } else {
            System.out.println("Abgebrochen.");
        }
    }

    /**
     * Fügt einen Button zum Löschen des aktuellen Panels (Tabs) hinzu.
     */
    private void addDeletePanelButton() {
        JButton btn = makeSquareButton("aktuelle kategorie löschen");
        btn.addActionListener(e -> deletePanel());
        this.add(btn);
    }

    /**
     * Entfernt das aktuell zugehörige {@link TabPanel} samt aller enthaltenen
     * Aufgaben.
     */
    private void deletePanel() {
        JTabbedPane tabbedPane = (JTabbedPane) SwingUtilities.getAncestorOfClass(JTabbedPane.class, this);
        TabPanel tab = (TabPanel) SwingUtilities.getAncestorOfClass(TabPanel.class, this);

        InOut.deleteCategoryTasks(tab.getCategory());
        tabbedPane.remove(tab);
    }
}
