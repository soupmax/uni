import javax.swing.*;
import java.awt.*;

public class TabPanel extends JPanel {
    private GridView grid;
    private String category;

    public TabPanel(String category) {
        this.category = category;
        setLayout(new BorderLayout());

        grid = new GridView();

        // Initiale Ladung
        reload();

        add(grid, BorderLayout.CENTER);
    }

    public void reload() {
        Task[] loadedTasks = InOut.loadCategoryTasks(category);
        grid.putNewTasks(loadedTasks);
    }

    public String getCategory() {
        return category;
    }
}
