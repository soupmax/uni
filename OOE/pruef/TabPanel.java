import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TabPanel extends JPanel {
    private GridView grid;

    public TabPanel() {
        setLayout(new BorderLayout());

        grid = new GridView();

        // Initiale Ladung
        List<Task> loadedTasks = loadTasks(); // <- dein Speicher-Ladesystem
        grid.reloadFromTasks(loadedTasks);

        add(grid, BorderLayout.CENTER);
    }

    public void reload() {
        List<Task> loadedTasks = loadTasks();
        grid.reloadFromTasks(loadedTasks);
    }

    private List<Task> loadTasks() {
        List<Task> tasks = new List<Task>();
    }
}
