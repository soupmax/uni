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

        // Tabs hinzufügen – eigene Klassen
        tabbedPane.addTab("Arbeit", new TabPanel("Arbeit"));
        tabbedPane.addTab("Uni", new TabPanel("Uni"));
        frame.add(tabbedPane);

        // frame.setSize(400, 200);add(tabbedPane);

        frame.setVisible(true);

        System.out.println("end");
    }
}