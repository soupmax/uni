import javax.swing.*;
import java.awt.*;

/**
 * Die Klasse {@code MainWindow} erstellt ein einfaches JFrame-Fenster mit einem
 * benutzerdefinierten Button,
 * der bei Klick einen einfachen Dialog anzeigt.
 *
 * <p>
 * Der Button ist in einer separaten Klasse {@link CustomButton} definiert.
 * </p>
 * 
 * <p>
 * Das Fenster ist zentriert und hat eine feste Größe.
 * </p>
 * 
 * @author Max
 */
public class MainWindow extends JFrame {

    /**
     * Konstruktor zur Initialisierung des Fensters und Hinzufügen des
     * CustomButtons.
     */
    public MainWindow() {
        setTitle("Test Fenster");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Fügt den Button hinzu
        add(new CustomButton("addTask"));

        setVisible(true);
    }

    /**
     * Der Einstiegspunkt des Programms. Startet das GUI im Event-Dispatch-Thread.
     *
     * @param args Kommandozeilenargumente (nicht verwendet)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainWindow::new);
    }
}

/**
 * Die Klasse {@code CustomButton} stellt ein Panel mit einem Button dar,
 * der je nach Typ konfiguriert wird. Derzeit wird nur der Typ {@code "addTask"}
 * unterstützt.
 * 
 * <p>
 * Bei Klick auf den Button wird ein Info-Dialog geöffnet.
 * </p>
 * 
 * <p>
 * Die Größe und das Layout des Buttons sind vordefiniert.
 * </p>
 * 
 * @author Max
 */
class CustomButton extends JPanel {

    /**
     * Erstellt ein neues {@code CustomButton}-Panel mit einem Button für den
     * übergebenen Typ.
     *
     * @param type der Typ des Buttons, derzeit nur {@code "addTask"} unterstützt
     */
    public CustomButton(String type) {
        setLayout(new FlowLayout());

        if ("addTask".equals(type)) {
            addTaskButton();
        }
    }

    /**
     * Fügt einen quadratischen Button mit der Aufschrift "+" hinzu und registriert
     * einen Klick-Listener, der einen Dialog öffnet.
     */
    private void addTaskButton() {
        JButton squareButton = new JButton("+");

        squareButton.setPreferredSize(new Dimension(50, 50));
        squareButton.setFont(new Font("SansSerif", Font.BOLD, 20));

        squareButton.addActionListener(e -> openInputDialog());

        add(squareButton);
    }

    /**
     * Öffnet ein einfaches {@link JOptionPane}-Dialogfenster.
     * Wird beim Klick auf den "+"-Button aufgerufen.
     */
    private void openInputDialog() {
        Component parent = SwingUtilities.getWindowAncestor(this);
        System.out.println("Parent Window: " + parent);
        JOptionPane.showMessageDialog(parent, "Hier ist dein Dialog", "Dialog geöffnet",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
