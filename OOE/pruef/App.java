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
     * Einstiegspunkt der Anwendung. Erstellt das Hauptfenster und das
     * {@link JTabbedPane}, verwaltet das Hinzufügen neuer Tabs und behandelt
     * entsprechende Benutzerinteraktionen.
     *
     * @param args Kommandozeilenargumente (werden nicht verwendet)
     */
    public static void main(String[] args) {
        // Erstellen und Konfigurieren des Hauptfensters
        JFrame frame = new JFrame("Task Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(300, 300));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Fenster maximieren

        // Erstellen des Tab-Panels
        JTabbedPane tabbedPane = new JTabbedPane();

        // Laden vorhandener Kategorien (Tabs)
        String[] categories = InOut.getAllTabs();
        for (String category : categories) {
            tabbedPane.addTab(category, new TabPanel(category));
        }

        // "+" Tab hinzufügen für das Erstellen neuer Kategorien
        tabbedPane.addTab("+", new JPanel());

        /**
         * Speichert den Index des zuletzt gewählten Tabs.
         * Wird benötigt, um bei einem Abbruch des Namensdialogs den Zustand
         * wiederherzustellen.
         */
        final int[] previousIndex = { 0 };

        /**
         * Verhindert doppelte Events durch programmgesteuerte Tab-Wechsel.
         */
        final boolean[] ignoreEvent = { false };

        /**
         * Sonderfallbehandlung: Wenn zu Beginn nur der "+"-Tab existiert, sofort neuen
         * Namen abfragen.
         */
        if (tabbedPane.getTabCount() == 1) {
            ignoreEvent[0] = true; // Listener blockieren
            SwingUtilities.invokeLater(() -> {
                String newCategory = JOptionPane.showInputDialog(
                        frame, "Neue Kategorie eingeben:", "Neuer Tab", JOptionPane.PLAIN_MESSAGE);
                if (newCategory != null && !newCategory.trim().isEmpty()) {
                    tabbedPane.removeTabAt(0);
                    tabbedPane.addTab(newCategory, new TabPanel(newCategory));
                    tabbedPane.addTab("+", new JPanel());
                    tabbedPane.setSelectedIndex(0);
                    previousIndex[0] = 0;
                }
                ignoreEvent[0] = false; // Listener wieder aktivieren
            });
        }

        /**
         * Listener für Tab-Wechsel:
         * Wenn "+" ausgewählt wird, erscheint ein Dialog zur Eingabe eines neuen
         * Tab-Namens.
         * Wird ein Name eingegeben, wird ein neuer Tab hinzugefügt.
         */
        tabbedPane.addChangeListener(e -> {
            if (ignoreEvent[0])
                return;

            int index = tabbedPane.getSelectedIndex();

            // "+"-Tab wurde ausgewählt
            if (index == tabbedPane.getTabCount() - 1 && tabbedPane.getTabCount() > 0) {
                ignoreEvent[0] = true;
                String newCategory = JOptionPane.showInputDialog(
                        null, "Neue Kategorie eingeben:", "Neuer Tab", JOptionPane.PLAIN_MESSAGE);

                if (newCategory != null && !newCategory.trim().isEmpty()) {
                    // "+"-Tab entfernen, neuen hinzufügen und "+" erneut anhängen
                    tabbedPane.removeTabAt(tabbedPane.getTabCount() - 1);
                    tabbedPane.addTab(newCategory, new TabPanel(newCategory));
                    tabbedPane.addTab("+", new JPanel());

                    // Neu erstellten Tab aktivieren
                    int newIndex = tabbedPane.getTabCount() - 2;
                    tabbedPane.setSelectedIndex(newIndex);
                    previousIndex[0] = newIndex;
                } else {
                    // Bei Abbruch zum vorherigen Tab zurückspringen
                    if (previousIndex[0] >= 0 && previousIndex[0] < tabbedPane.getTabCount()) {
                        tabbedPane.setSelectedIndex(previousIndex[0]);
                    }
                }
                ignoreEvent[0] = false;
            } else {
                // Nutzer hat auf anderen Tab gewechselt
                previousIndex[0] = index;
            }
        });

        // TabbedPane zum Frame hinzufügen und Fenster anzeigen
        frame.add(tabbedPane);
        frame.setVisible(true);
    }
}
