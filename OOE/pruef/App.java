import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    public static void main(String[] args) {
        System.out.println("start");

        JFrame frame = new JFrame("Task Viewer");

        Task task = new Task();
        TaskPanel taskPanel = new TaskPanel(task);

        TaskTimed ttask = new TaskTimed();
        TaskTimedPanel ttaskPanel = new TaskTimedPanel(ttask);

        GridView grid = new GridView();
        grid.addPanel(taskPanel);
        grid.addPanel(ttaskPanel);

        frame.add(grid, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setSize(400, 200);

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        gd.setFullScreenWindow(frame);

        frame.setVisible(true);

        System.out.println("end");
    }
}