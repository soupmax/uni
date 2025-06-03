import javax.swing.*;
import java.awt.*;

/**
 * Die Klasse {@code CButton} ist ein Panel, das je nach übergebenem Typ einen
 * spezifischen Button anzeigt.
 * <p>
 * Unterstützte Button-Typen:
 * <ul>
 * <li>{@code "addTask"}: Öffnet einen Dialog zur Erstellung einer neuen
 * Aufgabe.</li>
 * <li>{@code "deletePanel"}: Löscht die aktuelle Kategorie (Tab) samt
 * zugehöriger Aufgaben.</li>
 * </ul>
 *
 * <p>
 * Bei unbekanntem Typ wird ein Platzhalter-Label angezeigt.
 * </p>
 * 
 * @author Max
 */
public class CButton extends JPanel {
    int size = 50;

    /**
     * Erstellt ein neues {@code CButton}-Panel mit einem Button entsprechend dem
     * gegebenen Typ.
     *
     * @param type Der Button-Typ ({@code "addTask"}, {@code "deletePanel"})
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
                // Optional: Platzhalter oder Fehleranzeige
                add(new JLabel("Unbekannter Button-Typ: " + type));
                break;
        }
    }

    /**
     * Erstellt einen JButton mit standardisierter Schriftgröße.
     *
     * @param text Beschriftung des Buttons
     * @return Ein formatierter JButton
     */
    private JButton makeSquareButton(String text) {
        JButton squareButton = new JButton(text);
        // squareButton.setPreferredSize(new Dimension(size, size));
        squareButton.setFont(new Font("SansSerif", Font.BOLD, 20));
        return squareButton;
    }

    /**
     * Fügt dem Panel einen Button hinzu, der beim Klick einen Eingabedialog zur
     * Erstellung einer neuen Aufgabe öffnet.
     */
    private void addTaskButton() {
        JButton btn = makeSquareButton("neue aufgabe");
        btn.addActionListener(e -> openInputDialog());
        this.add(btn);
    }

    /**
     * Öffnet den {@link TaskInputDialog} zur Erstellung einer neuen Aufgabe.
     * Nach Bestätigung wird die Aufgabe gespeichert und das zugehörige TabPanel neu
     * geladen.
     */
    private void openInputDialog() {
        Frame owner = (Frame) SwingUtilities.getWindowAncestor(this);
        TabPanel tab = (TabPanel) SwingUtilities.getAncestorOfClass(TabPanel.class, this);
        TaskInputDialog dialog = new TaskInputDialog(owner, tab.getCategory());

        Task newTask = dialog.showDialog();

        if (newTask != null) {
            InOut.saveTask(newTask);
            tab.reload();
        } else {
            System.out.println("Abgebrochen.");
        }
    }

    /**
     * Fügt dem Panel einen Button hinzu, mit dem die aktuelle Kategorie und ihre
     * Aufgaben gelöscht werden können.
     */
    private void addDeletePanelButton() {
        JButton btn = makeSquareButton("aktuelle kategorie löschen");
        btn.addActionListener(e -> deletePanel());
        this.add(btn);
    }

    /**
     * Löscht die Aufgaben der aktuellen Kategorie und entfernt das zugehörige
     * TabPanel aus dem TabbedPane.
     */
    private void deletePanel() {
        JTabbedPane tabbedPane = (JTabbedPane) SwingUtilities.getAncestorOfClass(JTabbedPane.class, this);
        TabPanel tab = (TabPanel) SwingUtilities.getAncestorOfClass(TabPanel.class, this);

        InOut.deleteCategoryTasks(tab.getCategory());
        tabbedPane.remove(tab);
    }
}
