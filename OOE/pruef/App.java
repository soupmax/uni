package pruef;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    public static void main(String[] args) {
        System.out.println("start");

        Task task = new Task();
        TaskPanel taskPanel = new TaskPanel(task);

        TaskTimed ttask = new TaskTimed();
        TaskTimedPanel ttaskPanel = new TaskTimedPanel(ttask);

        JFrame frame = new JFrame("Task Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.add(taskPanel);
        frame.add(ttaskPanel);
        frame.setVisible(true);

        System.out.println("end");
    }
}