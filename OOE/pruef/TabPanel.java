import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TabPanel extends JPanel {
    private GridView grid;
    private String category;

    public TabPanel(String category) {
        this.category = category;
        setLayout(new BorderLayout());

        grid = new GridView();

        // Initiale Ladung
        Task[] loadedTasks = loadTasks(); // <- dein Speicher-Ladesystem
        grid.reloadFromTasks(loadedTasks);

        add(grid, BorderLayout.CENTER);
    }

    public void reload() {
        Task[] loadedTasks = loadTasks();
        grid.reloadFromTasks(loadedTasks);
    }

    private Task[] loadTasks() {
        return InOut.loadTasks("tasks.json", category);
    }
}
