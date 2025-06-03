import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Hilfsklasse mit statischen Methoden zur Verarbeitung von Aufgaben.
 */
public class Utils {

    /**
     * Sortiert ein Array von {@link Task}-Objekten.
     * <p>
     * Kriterien:
     * <ol>
     * <li>Zeitgebundene Aufgaben (TaskTimed) zuerst – sortiert nach
     * Fälligkeitsdatum</li>
     * <li>Andere Aufgaben am Ende (mit LocalDate.MAX)</li>
     * <li>Innerhalb gleicher Kategorie alphabetisch nach Titel</li>
     * </ol>
     *
     * @param tasks Das zu sortierende Aufgaben-Array.
     * @return Das sortierte Array (identische Referenz).
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
