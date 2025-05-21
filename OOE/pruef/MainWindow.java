import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public MainWindow() {
        setTitle("Test Fenster");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Fügt den Button hinzu
        add(new CustomButton("addTask"));

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainWindow::new);
    }
}

// Separate Klasse für den Button
class CustomButton extends JPanel {
    public CustomButton(String type) {
        setLayout(new FlowLayout());

        if ("addTask".equals(type)) {
            addTaskButton();
        }
    }

    private void addTaskButton() {
        JButton squareButton = new JButton("+");

        squareButton.setPreferredSize(new Dimension(50, 50));
        squareButton.setFont(new Font("SansSerif", Font.BOLD, 20));

        squareButton.addActionListener(e -> {
            openInputDialog();
        });

        add(squareButton);
    }

    private void openInputDialog() {
        Component parent = SwingUtilities.getWindowAncestor(this);
        System.out.println("Parent Window: " + parent);
        JOptionPane.showMessageDialog(parent, "Hier ist dein Dialog", "Dialog geöffnet",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
