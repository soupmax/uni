import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Die {@code TaskInputDialog}-Klasse ist ein modaler Dialog zur Eingabe einer
 * neuen {@link Task}.
 *
 * <p>
 * Zusätzlich zu Titel und Beschreibung kann optional ein Fälligkeitsdatum
 * im Format {@code JJJJ-MM-TT} eingegeben werden. Wird ein gültiges Datum
 * eingegeben, wird ein {@link TaskTimed}-Objekt zurückgegeben, sonst ein
 * {@link TaskSimple}.
 * </p>
 * 
 * <p>
 * Das Datum wird auf gültiges Format und darauf geprüft, dass es nicht in der
 * Vergangenheit liegt.
 * </p>
 * 
 * @author Max
 */
public class TaskInputDialog extends JDialog {

    /** Texteingabefeld für den Aufgabentitel */
    private JTextField titleField;

    /** Texteingabebereich für die Aufgabenbeschreibung */
    private JTextArea descriptionArea;

    /** Texteingabefeld für das optionale Fälligkeitsdatum */
    private JTextField dueDateField;

    /** Das Ergebnisobjekt (Task), das vom Dialog erstellt wurde */
    private TaskStructured resultTask;

    /**
     * Erstellt einen neuen {@code TaskInputDialog}.
     *
     * @param owner    das übergeordnete Fenster, zu dem der Dialog modal ist
     * @param category die Kategorie, der die neue Aufgabe zugeordnet werden soll
     */
    public TaskInputDialog(Frame owner, String category) {
        super(owner, "Neue Aufgabe erstellen", true);
        setLayout(new BorderLayout());
        setSize(300, 350);
        setLocationRelativeTo(owner);

        // === Eingabebereich erstellen ===
        JPanel inputPanel = new JPanel(new GridLayout(0, 1, 5, 5));

        titleField = new JTextField();
        titleField.setDocument(new LimitedDocument(50)); // Max. 50 Zeichen
        titleField.setText(category);

        descriptionArea = new JTextArea(4, 20);
        descriptionArea.setDocument(new LimitedDocument(200)); // Max. 200 Zeichen

        dueDateField = new JTextField();
        dueDateField.setDocument(new LimitedDocument(10)); // Format JJJJ-MM-TT

        inputPanel.add(new JLabel("Titel:"));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Beschreibung:"));
        inputPanel.add(new JScrollPane(descriptionArea));
        inputPanel.add(new JLabel("Fälligkeitsdatum (optional, Format: JJJJ-MM-TT):"));
        inputPanel.add(dueDateField);

        add(inputPanel, BorderLayout.CENTER);

        // === Buttonbereich erstellen ===
        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("Erstellen");
        JButton cancelButton = new JButton("Abbrechen");

        // OK-Button: Eingaben validieren und Aufgabe erstellen
        okButton.addActionListener(e -> {
            String title = titleField.getText().trim();
            String description = descriptionArea.getText().trim();
            String dueDateText = dueDateField.getText().trim();

            if (!dueDateText.isEmpty()) {
                try {
                    LocalDate dueDate = LocalDate.parse(dueDateText);
                    if (dueDate.isBefore(LocalDate.now())) {
                        JOptionPane.showMessageDialog(this,
                                "Das Fälligkeitsdatum darf nicht in der Vergangenheit liegen.",
                                "Ungültiges Datum",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    resultTask = new TaskTimed(category, title, description, dueDate);
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(this,
                            "Ungültiges Datum. Bitte im Format JJJJ-MM-TT eingeben.",
                            "Ungültiges Datum",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else {
                resultTask = new TaskSimple(category, title, description);
            }
            dispose();
        });

        // Abbrechen-Button: Dialog schließen ohne Aufgabe zu erstellen
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
     * @return ein {@link Task}-Objekt, wenn der Nutzer bestätigt;
     *         {@code null}, wenn der Dialog abgebrochen wurde
     */
    public TaskStructured showDialog() {
        setVisible(true);
        return resultTask;
    }
}
