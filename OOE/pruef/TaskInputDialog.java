import javax.swing.*;
import java.awt.*;

/**
 * Die {@code TaskInputDialog}-Klasse ist ein modaler Dialog zur Eingabe einer
 * neuen {@link Task}.
 * 
 * <p>
 * Der Dialog ermöglicht die Eingabe eines Titels, einer Beschreibung und das
 * Setzen der Priorität.
 * Beim Bestätigen wird ein neues {@link TaskSimple}-Objekt erstellt, das über
 * {@link #showDialog()} zurückgegeben werden kann.
 * </p>
 * 
 * <p>
 * Wird der Dialog abgebrochen, gibt {@link #showDialog()} {@code null} zurück.
 * </p>
 * 
 * <p>
 * Der Dialog wird zentriert zum übergebenen {@link Frame} angezeigt.
 * </p>
 * 
 * @author Max
 */
public class TaskInputDialog extends JDialog {
    private JTextField titleField;
    private JTextArea descriptionArea;
    private JCheckBox priorityBox;
    private Task resultTask;

    /**
     * Erstellt einen neuen {@code TaskInputDialog}.
     *
     * @param owner    das übergeordnete Fenster, zu dem der Dialog modal ist
     * @param category die Kategorie, der die neue Aufgabe zugeordnet werden soll
     */
    public TaskInputDialog(Frame owner, String category) {
        super(owner, "Neue Aufgabe erstellen", true);
        setLayout(new BorderLayout());
        setSize(300, 250);
        setLocationRelativeTo(owner);

        // Eingabefelder
        JPanel inputPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        titleField = new JTextField();
        descriptionArea = new JTextArea(4, 20);
        priorityBox = new JCheckBox("Priorität:");

        inputPanel.add(new JLabel("Titel:"));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Beschreibung:"));
        inputPanel.add(new JScrollPane(descriptionArea));
        inputPanel.add(priorityBox);

        add(inputPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("Erstellen");
        JButton cancelButton = new JButton("Abbrechen");

        okButton.addActionListener(e -> {
            resultTask = new TaskSimple(
                    category,
                    titleField.getText(),
                    descriptionArea.getText(),
                    priorityBox.isSelected());
            dispose();
        });

        cancelButton.addActionListener(e -> {
            resultTask = null;
            dispose();
        });

        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Zeigt den Dialog an und gibt die erstellte Aufgabe zurück.
     * 
     * @return ein {@link TaskSimple}-Objekt bei Bestätigung, sonst {@code null} bei
     *         Abbruch
     */
    public Task showDialog() {
        setVisible(true);
        return resultTask;
    }
}
