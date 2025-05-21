import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        System.out.println("start");

        JFrame frame = new JFrame("Task Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        gd.setFullScreenWindow(frame);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Tabs hinzufügen – eigene Klassen
        tabbedPane.addTab("Aufgaben", new TabPanel());
        tabbedPane.addTab("Statistik", new TabPanel());
        frame.add(tabbedPane);

        // frame.setSize(400, 200);add(tabbedPane);

        frame.setVisible(true);

        System.out.println("end");
    }
}