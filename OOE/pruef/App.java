package pruef;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    public static void main(String[] args) {
        System.out.println("start");

        Task task = new Task("Einkaufen", "Milch, Brot, Eier besorgen");
        TaskPanel taskPanel = new TaskPanel(task);

        JFrame frame = new JFrame("Task Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.add(taskPanel);
        frame.setVisible(true);

        System.out.println("end");
    }
}