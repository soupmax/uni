import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Die {@code TaskInputDialog}-Klasse ist ein modaler Dialog zur Eingabe einer
 * neuen {@link Task}.
 *
 * <p>
 * Zusätzlich zu Titel und Beschreibung kann optional ein
 * Fälligkeitsdatum
 * im Format {@code JJJJ-MM-TT} eingegeben werden. Wird ein gültiges Datum
 * eingegeben,
 * wird ein {@link TaskTimed}-Objekt zurückgegeben, sonst ein
 * {@link TaskSimple}.
 * </p>
 */
public class TaskInputDialog extends JDialog {
    private JTextField titleField;
    private JTextArea descriptionArea;
    private JTextField dueDateField;
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
        setSize(300, 300);
        setLocationRelativeTo(owner);

        // Eingabefelder
        JPanel inputPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        titleField = new JTextField();
        descriptionArea = new JTextArea(4, 20);
        dueDateField = new JTextField(); // Neues Feld für Fälligkeitsdatum

        inputPanel.add(new JLabel("Titel:"));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Beschreibung:"));
        inputPanel.add(new JScrollPane(descriptionArea));
        inputPanel.add(new JLabel("Fälligkeitsdatum (optional, Format: JJJJ-MM-TT):"));
        inputPanel.add(dueDateField);

        add(inputPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("Erstellen");
        JButton cancelButton = new JButton("Abbrechen");

        okButton.addActionListener(e -> {
            String title = titleField.getText().trim();
            String description = descriptionArea.getText().trim();
            String dueDateText = dueDateField.getText().trim();

            if (!dueDateText.isEmpty()) {
                try {
                    LocalDate dueDate = LocalDate.parse(dueDateText);
                    resultTask = new TaskTimed(category, title, description, dueDate);
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(this,
                            "Ungültiges Datum. Bitte im Format JJJJ-MM-TT eingeben.",
                            "Fehler",
                            JOptionPane.ERROR_MESSAGE);
                    return; // abbrechen und Dialog nicht schließen
                }
            } else {
                resultTask = new TaskSimple(category, title, description);
            }
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
     * @return ein {@link Task}-Objekt bei Bestätigung, sonst {@code null} bei
     *         Abbruch
     */
    public Task showDialog() {
        setVisible(true);
        return resultTask;
    }
}
