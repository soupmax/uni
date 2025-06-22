/**
 * Panel zur Darstellung und Bearbeitung einer {@link TaskFreeform}.
 * <p>
 * Stellt ein Textfeld zur Verfügung, in dem der gesamte Inhalt der Aufgabe
 * frei eingegeben oder bearbeitet werden kann.
 * </p>
 * 
 * @author Max
 */
public class TaskFreeformPanel extends TaskPanel {

    /**
     * Erstellt ein neues {@code TaskFreeformPanel} mit der übergebenen Aufgabe.
     *
     * @param task Die darzustellende {@link TaskFreeform}
     */
    public TaskFreeformPanel(TaskFreeform task) {
        super(task);
    }

    /**
     * Gibt die aktuell bearbeitete Aufgabe zurück – inklusive
     * aller Änderungen im Textfeld.
     *
     * @return Eine neue {@link TaskFreeform}-Instanz mit aktualisiertem Inhalt
     */
    @Override
    public Task getTask() {
        return new TaskFreeform(task.category, contentArea.getText());
    }
}
