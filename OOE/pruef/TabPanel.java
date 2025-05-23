import javax.swing.*;
import java.awt.*;

/**
 * Das {@code TabPanel} stellt ein Panel dar, das eine bestimmte Task-Kategorie
 * in einem Tab anzeigt.
 * Es enthält eine {@link GridView}, die alle Tasks dieser Kategorie darstellt.
 * 
 * @author Max
 */
public class TabPanel extends JPanel {
    /** Die GridView zur Anzeige von Aufgaben. */
    private GridView grid;

    /** Die Kategorie, zu der dieses Panel gehört. */
    private String category;

    /**
     * Erstellt ein neues {@code TabPanel} für eine bestimmte Kategorie.
     *
     * @param category Die Kategorie, deren Aufgaben angezeigt werden sollen.
     */
    public TabPanel(String category) {
        this.category = category;
        setLayout(new BorderLayout());

        grid = new GridView(category);

        // Initiale Ladung der Aufgaben
        reload();

        add(grid, BorderLayout.CENTER);
    }

    /**
     * Lädt die Aufgaben der aktuellen Kategorie neu und aktualisiert die Anzeige.
     */
    public void reload() {
        Task[] loadedTasks = InOut.loadCategoryTasks(category);
        grid.putNewTasks(loadedTasks);
    }

    /**
     * Gibt die Kategorie dieses Panels zurück.
     *
     * @return Die aktuelle Kategorie.
     */
    public String getCategory() {
        return category;
    }
}
