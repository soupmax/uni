import javax.swing.*;
import java.awt.*;

/**
 * Die {@code App}-Klasse ist der Einstiegspunkt der Anwendung.
 * Sie erzeugt das Hauptfenster mit einem {@link JTabbedPane}, in dem
 * verschiedene Task-Kategorien als Tabs angezeigt werden.
 *
 * <p>
 * Zusätzlich gibt es einen "+"-Tab, der es dem Benutzer erlaubt,
 * eine neue Kategorie (Tab) dynamisch hinzuzufügen.
 * </p>
 *
 * <p>
 * Beim Hinzufügen eines neuen Tabs wird der Benutzer über einen
 * {@link JOptionPane}-Dialog zur Eingabe eines Tab-Namens aufgefordert.
 * Bei Bestätigung wird ein neuer Tab mit dem entsprechenden Inhalt erstellt.
 * </p>
 *
 * <p>
 * Beim Schließen des Fensters werden offene Fließtext-Tabs automatisch
 * gespeichert.
 * </p>
 *
 * <p>
 * Das Hauptfenster wird beim Start maximiert dargestellt.
 * </p>
 *
 * @author Max
 */
public class App {

    /**
     * Einstiegspunkt der Anwendung. Konfiguriert Look & Feel, lädt bestehende
     * Kategorien
     * (Tabs), verwaltet Tabwechsel sowie das Erstellen neuer Kategorien und
     * behandelt
     * das Speichern beim Beenden.
     *
     * @param args Kommandozeilenargumente (werden nicht verwendet)
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            // Schriftart auf "Segoe UI", 13pt setzen
            UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 13));
            UIManager.put("Button.font", new Font("Segoe UI", Font.PLAIN, 13));
            UIManager.put("CheckBox.font", new Font("Segoe UI", Font.PLAIN, 13));
            UIManager.put("TextArea.font", new Font("Segoe UI", Font.PLAIN, 13));
            UIManager.put("TextField.font", new Font("Segoe UI", Font.PLAIN, 13));
            UIManager.put("TitledBorder.font", new Font("Segoe UI", Font.BOLD, 13));

            // Einheitliche Farbgebung
            Color background = new Color(245, 245, 245);
            Color foreground = Color.DARK_GRAY;

            UIManager.put("Panel.background", background);
            UIManager.put("Label.foreground", foreground);
            UIManager.put("TextArea.background", background);
            UIManager.put("TextArea.foreground", foreground);
            UIManager.put("ScrollPane.background", background);
            UIManager.put("CheckBox.background", background);
            UIManager.put("CheckBox.foreground", foreground);
            UIManager.put("Button.background", new Color(230, 230, 230));
            UIManager.put("Button.foreground", foreground);

            // Fokus-Rahmen entfernen
            UIManager.put("Button.focus", new Color(0, 0, 0, 0));
        } catch (Exception e) {
            e.printStackTrace(); // Fallback bei Fehler
        }

        // Hauptfenster konfigurieren
        JFrame frame = new JFrame("Task Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(300, 300));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // TabbedPane erstellen
        JTabbedPane tabbedPane = new JTabbedPane();

        // Vorhandene Kategorien laden
        String[] categories = InOut.getAllTabs();
        for (String category : categories) {
            if (InOut.isFreeformCategory(category)) {
                tabbedPane.addTab(category, new TabPanel(category, true));
            } else {
                tabbedPane.addTab(category, new TabPanel(category, false));
            }
        }

        // "+"-Tab hinzufügen
        tabbedPane.addTab("+", new JPanel());

        // Speichern beim Beenden
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                // Nur Freeform-Tabs speichern
                for (int i = 0; i < tabbedPane.getTabCount(); i++) {
                    Component comp = tabbedPane.getComponentAt(i);
                    if (comp instanceof TabPanel panel && panel.isFreeform) {
                        for (Component c : panel.getComponents()) {
                            if (c instanceof TaskFreeformPanel tfp) {
                                InOut.updateTask(tfp.getTask());
                                break;
                            }
                        }
                    }
                }
                System.exit(0);
            }
        });

        // Letzten Tab merken
        final int[] previousIndex = { 0 };

        // Event-Handler blockieren bei programmgesteuertem Wechsel
        final boolean[] ignoreEvent = { false };

        // Wenn nur "+"-Tab vorhanden, direkt neuen Tab anlegen
        if (tabbedPane.getTabCount() == 1) {
            ignoreEvent[0] = true;
            SwingUtilities.invokeLater(() -> {
                NewTabPanelDialog.NewTabData data = NewTabPanelDialog.showDialog(frame);
                if (data != null) {
                    tabbedPane.removeTabAt(tabbedPane.getTabCount() - 1);
                    tabbedPane.addTab(data.category, new TabPanel(data.category, data.isPlainText));
                    tabbedPane.addTab("+", new JPanel());

                    int newIndex = tabbedPane.getTabCount() - 2;
                    tabbedPane.setSelectedIndex(newIndex);
                    previousIndex[0] = newIndex;
                }
                ignoreEvent[0] = false;
            });
        }

        // Listener für Tab-Wechsel
        tabbedPane.addChangeListener(e -> {
            if (ignoreEvent[0])
                return;

            int index = tabbedPane.getSelectedIndex();

            // "+"-Tab wurde ausgewählt
            if (index == tabbedPane.getTabCount() - 1) {
                ignoreEvent[0] = true;
                NewTabPanelDialog.NewTabData data = NewTabPanelDialog.showDialog(frame);
                if (data != null) {
                    tabbedPane.removeTabAt(tabbedPane.getTabCount() - 1);
                    tabbedPane.addTab(data.category, new TabPanel(data.category, data.isPlainText));
                    tabbedPane.addTab("+", new JPanel());

                    int newIndex = tabbedPane.getTabCount() - 2;
                    tabbedPane.setSelectedIndex(newIndex);
                    previousIndex[0] = newIndex;
                } else {
                    // Abbruch: alten Tab wiederherstellen
                    if (previousIndex[0] >= 0 && previousIndex[0] < tabbedPane.getTabCount()) {
                        tabbedPane.setSelectedIndex(previousIndex[0]);
                    }
                }
                ignoreEvent[0] = false;
            } else {
                previousIndex[0] = index;
            }
        });

        frame.add(tabbedPane);
        frame.setVisible(true);
    }
}
