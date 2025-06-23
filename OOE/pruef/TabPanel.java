import javax.swing.*;
import java.awt.*;

/**
 * Das {@code TabPanel} stellt ein Panel dar, das eine bestimmte Task-Kategorie
 * in einem Tab anzeigt.
 * 
 * <p>
 * Abhängig vom Typ der Kategorie kann es entweder eine {@link GridView} für
 * strukturierte Aufgaben (z.&nbsp;B. mit Fälligkeitsdatum)
 * oder ein einzelnes {@link TaskFreeformPanel} für Fließtext-Aufgaben
 * enthalten.
 * </p>
 * 
 * @author Max
 */
public class TabPanel extends JPanel {
    /** Die GridView zur Anzeige strukturierter Aufgaben. */
    private GridView grid;

    /** Die Kategorie, zu der dieses Panel gehört. */
    private String category;

    /** Gibt an, ob dieses Panel Fließtext-Aufgaben enthält. */
    public boolean isFreeform;

    /**
     * Erstellt ein neues {@code TabPanel} für eine bestimmte Kategorie.
     *
     * @param category   Die Kategorie, deren Aufgaben angezeigt werden sollen.
     * @param isFreeform {@code true}, wenn die Kategorie Fließtext-Aufgaben enthält
     */
    public TabPanel(String category, boolean isFreeform) {
        this.category = category;
        this.isFreeform = isFreeform;

        setLayout(new BorderLayout());
        if (isFreeform) {
            Task[] tasks = InOut.loadCategoryTasks(category);
            if (tasks != null && tasks.length > 0) {
                TaskFreeform t = (TaskFreeform) tasks[0];
                add(new TaskFreeformPanel(t));
            } else {
                TaskFreeform t = new TaskFreeform(category, "");
                add(new TaskFreeformPanel(t));
            }
        } else {
            grid = new GridView(category);

            // Initiales Laden der Aufgaben
            reload();

            add(grid, BorderLayout.CENTER);
        }
    }

    /**
     * Lädt die Aufgaben der aktuellen Kategorie neu und aktualisiert die Anzeige
     * in der GridView.
     */
    public void reload() {
        Task[] loadedTasks = InOut.loadCategoryTasks(category);
        grid.putNewTasks(loadedTasks);
    }

    /**
     * Gibt die Kategorie dieses Panels zurück.
     *
     * @return Der Name der Kategorie.
     */
    public String getCategory() {
        return category;
    }
}
