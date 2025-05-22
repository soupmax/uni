import javax.swing.*;
import java.awt.*;

public class App {
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
                    // tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 2);
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

        // frame.setSize(400, 200);add(tabbedPane);

        frame.setVisible(true);

        System.out.println("end");
    }
}