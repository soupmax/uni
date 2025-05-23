import javax.swing.*;
import java.awt.*;

/**
 * Die {@code App}-Klasse ist der Einstiegspunkt der Anwendung.
 * Sie erstellt das Hauptfenster mit einem {@link JTabbedPane},
 * in dem verschiedene Task-Kategorien als Tabs angezeigt werden.
 * 
 * <p>
 * Zusätzlich gibt es einen "+"-Tab, der es dem Benutzer erlaubt,
 * eine neue Kategorie (Tab) dynamisch hinzuzufügen.
 * </p>
 * 
 * <p>
 * Beim Hinzufügen eines neuen Tabs wird der Benutzer über einen
 * {@link JOptionPane} zur Eingabe eines Tab-Namens aufgefordert.
 * Bei Bestätigung wird ein neuer Tab mit dem entsprechenden Inhalt erstellt.
 * </p>
 * 
 * <p>
 * Das Hauptfenster wird beim Start maximiert.
 * </p>
 * 
 * @author Max
 */
public class App {

    /**
     * Einstiegspunkt der Anwendung.
     * Erstellt ein maximiertes JFrame mit einem {@link JTabbedPane},
     * der Tabs für jede gespeicherte Kategorie sowie einen "+"-Tab zur Erstellung
     * neuer Kategorien enthält.
     *
     * @param args Kommandozeilenargumente (werden nicht verwendet)
     */
    public static void main(String[] args) {
        System.out.println("start");

        JFrame frame = new JFrame("Task Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(300, 300));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Fenster maximieren

        JTabbedPane tabbedPane = new JTabbedPane();
        String[] categories = InOut.getAllTabs();
        for (String category : categories) {
            tabbedPane.addTab(category, new TabPanel(category));
        }
        tabbedPane.addTab("+", new JPanel());

        final int[] previousIndex = { 0 };
        final boolean[] ignoreEvent = { false };

        tabbedPane.addChangeListener(e -> {
            if (ignoreEvent[0]) {
                return;
            }
            int index = tabbedPane.getSelectedIndex();

            if (index == tabbedPane.getTabCount() - 1) { // "+" Tab
                ignoreEvent[0] = true;
                String newCategory = JOptionPane.showInputDialog(
                        null, "Neue Kategorie eingeben:", "Neuer Tab", JOptionPane.PLAIN_MESSAGE);

                if (newCategory != null && !newCategory.trim().isEmpty()) {
                    // "+"-Tab entfernen
                    tabbedPane.removeTabAt(tabbedPane.getTabCount() - 1);
                    tabbedPane.addTab(newCategory, new TabPanel(newCategory));
                    tabbedPane.addTab("+", new JPanel());

                    // Zum neuen Tab springen
                    previousIndex[0] = index;
                } else {
                    // Falls abgebrochen: vorherigen Tab wieder auswählen
                    tabbedPane.setSelectedIndex(previousIndex[0]);
                }
                ignoreEvent[0] = false;
            } else {
                previousIndex[0] = index;
            }
        });

        frame.add(tabbedPane);
        frame.setVisible(true);

        System.out.println("end");
    }
}