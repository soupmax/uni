import javax.swing.*;
import java.awt.*;

public class TabPanel extends JPanel {
    public TabPanel() {
        setLayout(new BorderLayout());

        Task task = new Task();
        TaskPanel taskPanel = new TaskPanel(task);

        TaskTimed ttask = new TaskTimed();
        TaskTimedPanel ttaskPanel = new TaskTimedPanel(ttask);

        GridView grid = new GridView();
        grid.addPanel(taskPanel);
        grid.addPanel(ttaskPanel);

        add(grid, BorderLayout.CENTER);
    }
}