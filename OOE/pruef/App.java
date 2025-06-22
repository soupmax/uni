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
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            // Schriftart überall auf "Segoe UI", 13pt setzen (modern & gut lesbar)
            UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 13));
            UIManager.put("Button.font", new Font("Segoe UI", Font.PLAIN, 13));
            UIManager.put("CheckBox.font", new Font("Segoe UI", Font.PLAIN, 13));
            UIManager.put("TextArea.font", new Font("Segoe UI", Font.PLAIN, 13));
            UIManager.put("TextField.font", new Font("Segoe UI", Font.PLAIN, 13));
            UIManager.put("TitledBorder.font", new Font("Segoe UI", Font.BOLD, 13));

            // Farben für Komponenten vereinheitlichen
            Color background = new Color(245, 245, 245); // helles Grau
            Color foreground = Color.DARK_GRAY;

            UIManager.put("Panel.background", background);
            UIManager.put("Label.foreground", foreground);
            UIManager.put("TextArea.background", background);
            UIManager.put("TextArea.foreground", foreground);
            UIManager.put("ScrollPane.background", background);
            UIManager.put("CheckBox.background", background);
            UIManager.put("CheckBox.foreground", foreground);
            UIManager.put("Button.background", new Color(230, 230, 230)); // leicht abgehoben
            UIManager.put("Button.foreground", foreground);

            // Optional: Button leicht "flacher" wirken lassen
            UIManager.put("Button.focus", new Color(0, 0, 0, 0));
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            e.printStackTrace(); // oder Logging / Fallback
        }
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
            if (InOut.isFreeformCategory(category)) {
                tabbedPane.addTab(category, new TabPanel(category, true));
            } else {
                tabbedPane.addTab(category, new TabPanel(category, false));
            }
        }

        // "+" Tab hinzufügen für das Erstellen neuer Kategorien
        tabbedPane.addTab("+", new JPanel());

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                // fließtext aufgaben speichern/aktualisieren
                for (int i = 0; i < tabbedPane.getTabCount(); i++) {
                    Component comp = tabbedPane.getComponentAt(i);
                    if (comp instanceof TabPanel panel && panel.isFreeform) {
                        for (Component c : panel.getComponents()) {
                            if (c instanceof TaskFreeformPanel tfp) {
                                System.out.println("→ Ich rufe updateTask() jetzt auf");
                                InOut.updateTask(tfp.getTask());
                                break; // bricht aus dieser Tab-Verarbeitung aus – genügt, da nur eine Freeform
                                       // erwartet
                            }
                        }
                    }
                }

                System.exit(0); // Anwendung beenden
            }
        });

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
                NewTabPanelDialog.NewTabData data = NewTabPanelDialog.showDialog(frame);
                if (data != null) {
                    tabbedPane.removeTabAt(tabbedPane.getTabCount() - 1);
                    tabbedPane.addTab(data.category, new TabPanel(data.category, data.isPlainText));
                    tabbedPane.addTab("+", new JPanel());

                    int newIndex = tabbedPane.getTabCount() - 2;
                    tabbedPane.setSelectedIndex(newIndex);
                    previousIndex[0] = newIndex;
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
                NewTabPanelDialog.NewTabData data = NewTabPanelDialog.showDialog(frame);
                if (data != null) {
                    tabbedPane.removeTabAt(tabbedPane.getTabCount() - 1);
                    tabbedPane.addTab(data.category, new TabPanel(data.category, data.isPlainText));
                    tabbedPane.addTab("+", new JPanel());

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
