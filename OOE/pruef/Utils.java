import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Hilfsklasse mit statischen Methoden zur Verarbeitung von Aufgaben.
 * 
 * <p>
 * Derzeit bietet sie eine zentrale Sortierfunktion für {@link Task}-Objekte,
 * insbesondere für die Anzeige in der Benutzeroberfläche.
 * </p>
 * 
 * <p>
 * Die Sortierung priorisiert zeitgebundene Aufgaben
 * und ordnet sie nach Fälligkeitsdatum und Titel.
 * </p>
 * 
 * @author Max
 */
public class Utils {

    /**
     * Sortiert ein Array von {@link Task}-Objekten nach folgenden Kriterien:
     *
     * <ol>
     * <li>{@link TaskTimed}-Objekte zuerst, sortiert nach Fälligkeitsdatum
     * (aufsteigend)</li>
     * <li>Andere Aufgabentypen folgen, sortiert mit {@link LocalDate#MAX} als
     * Platzhalter</li>
     * <li>Bei gleichem Datum erfolgt eine alphabetische Sortierung nach Titel
     * (case-insensitive)</li>
     * </ol>
     *
     * <p>
     * Diese Methode verändert das übergebene Array direkt (in-place).
     * </p>
     *
     * @param tasks Das zu sortierende Array von Aufgaben.
     * @return Das sortierte Array (identische Referenz wie Eingabe).
     */
    public static Task[] sortTasks(Task[] tasks) {
        if (tasks == null)
            return new Task[0];

        Arrays.sort(tasks, Comparator
                .comparing((Task task) -> {
                    if (task instanceof TaskTimed timedTask) {
                        return timedTask.getDueDate();
                    }
                    return LocalDate.MAX;
                })
                .thenComparing(task -> task.title.toLowerCase()));

        return tasks;
    }
}
