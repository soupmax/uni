import org.json.JSONObject;
import org.json.JSONException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;

/**
 * Utility-Klasse zur Verwaltung von Tasks mit JSON-basierter
 * Speicherung und Laden aus einer Datei (./tasks.json).
 *
 * <p>
 * Unterstützt unterschiedliche Task-Typen ({@link TaskSimple},
 * {@link TaskTimed}, {@link TaskFreeform}).
 * Die Daten werden als JSON gespeichert und gelesen, wobei die
 * org.json-Bibliothek verwendet wird.
 * </p>
 *
 * <p>
 * Funktionen dieser Klasse:
 * </p>
 * <ul>
 * <li>Speichern einzelner oder aller Tasks</li>
 * <li>Laden aller oder kategoriebasierter Tasks</li>
 * <li>Aktualisieren vorhandener Tasks</li>
 * <li>Löschen aller Tasks einer Kategorie</li>
 * <li>Ermitteln aller Kategorienamen</li>
 * </ul>
 *
 * @author Max
 */
public class InOut {
    private static String fileName = "./tasks.json";

    /**
     * Speichert eine einzelne Task. Bestehende Tasks werden geladen,
     * die neue Task hinzugefügt und anschließend das gesamte Array gespeichert.
     *
     * @param task Die zu speichernde Task (TaskSimple, TaskTimed oder TaskFreeform)
     */
    public static void saveTask(Task task) {
        JSONArray taskArray = new JSONArray();
        Task[] existingTasks = null;
        Task[] allTasks = null;

        File f = new File(fileName);
        if (f.exists() && f.length() > 0) {
            existingTasks = loadAllTasks();
            allTasks = new Task[existingTasks.length + 1];
            System.arraycopy(existingTasks, 0, allTasks, 0, existingTasks.length);
            allTasks[allTasks.length - 1] = task;
        } else {
            allTasks = new Task[1];
            allTasks[0] = task;
        }
        for (Task t : allTasks) {
            taskArray.put(t.toJSON());
        }

        try (FileWriter file = new FileWriter(fileName)) {
            file.write(taskArray.toString(4));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Überschreibt alle gespeicherten Tasks mit dem gegebenen Array.
     *
     * @param tasks Array von Tasks, das vollständig gespeichert werden soll
     */
    public static void saveAllTasks(Task[] tasks) {
        JSONArray arr = new JSONArray();

        for (Task t : tasks) {
            arr.put(t.toJSON());
        }

        try (FileWriter file = new FileWriter(fileName)) {
            file.write(arr.toString(4)); // schön formatiert
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lädt alle Tasks aus der Datei {@code ./tasks.json}.
     *
     * @return Ein sortiertes Array aller gespeicherten Tasks,
     *         oder {@code null}, wenn Datei leer oder nicht vorhanden,
     *         oder ein leeres Array bei Fehlern
     */
    public static Task[] loadAllTasks() {
        File f = new File(fileName);
        if (!f.exists() || f.length() < 1) {
            return null;
        }
        try {
            String content = new String(Files.readAllBytes(Paths.get(fileName)));
            JSONArray taskArray = new JSONArray(content);

            Task[] tasks = new Task[taskArray.length()];
            for (int i = 0; i < taskArray.length(); i++) {
                JSONObject json = taskArray.getJSONObject(i);
                tasks[i] = JSONToTask(json);
            }
            return Utils.sortTasks(tasks);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return new Task[0]; // Rückgabe eines leeren Arrays im Fehlerfall
        }
    }

    /**
     * Gibt ein Array aller in der Datei gespeicherten Kategoriennamen zurück.
     *
     * @return Array mit eindeutigen Kategorienamen
     */
    public static String[] getAllTabs() {
        Task[] tasks = loadAllTasks();
        if (tasks == null)
            return new String[0];
        return Arrays.stream(tasks)
                .map(t -> t.category)
                .distinct()
                .toArray(String[]::new);
    }

    /**
     * Lädt alle Tasks einer bestimmten Kategorie.
     *
     * @param cat Kategorie, nach der gefiltert werden soll
     * @return Array der Tasks dieser Kategorie,
     *         {@code null} wenn Datei leer/nicht vorhanden,
     *         oder ein leeres Array bei Fehlern
     */
    public static Task[] loadCategoryTasks(String cat) {
        File f = new File(fileName);
        if (!f.exists() || f.length() < 1) {
            return null;
        }
        try {
            String content = new String(Files.readAllBytes(Paths.get(fileName)));
            JSONArray taskArray = new JSONArray(content);

            List<Task> tasksList = new ArrayList<>();

            for (int i = 0; i < taskArray.length(); i++) {
                JSONObject json = taskArray.getJSONObject(i);
                String category = json.getString("category");

                if (!category.equals(cat)) {
                    continue;
                }
                Task t = JSONToTask(json);
                tasksList.add(t);
            }

            return Utils.sortTasks(tasksList.toArray(new Task[0]));
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return new Task[0];
        }
    }

    /**
     * Löscht alle Tasks einer bestimmten Kategorie und speichert das Ergebnis.
     *
     * @param category Name der zu löschenden Kategorie
     */
    public static void deleteCategoryTasks(String category) {
        Task[] tasks = loadAllTasks();

        if (tasks == null)
            return;

        List<Task> filteredTasks = Arrays.stream(tasks)
                .filter(t -> !t.category.equals(category))
                .toList();

        saveAllTasks(filteredTasks.toArray(new Task[0]));
    }

    /**
     * Aktualisiert eine bestehende Task, falls sie existiert.
     * Andernfalls wird sie neu hinzugefügt.
     *
     * @param updatedTask Die zu aktualisierende oder hinzuzufügende Task
     */
    public static void updateTask(Task updatedTask) {
        Task[] tasks = loadAllTasks();

        List<Task> updatedList = new ArrayList<>();
        boolean taskExists = false;

        if (tasks != null) {
            for (Task t : tasks) {
                if (isSameTask(t, updatedTask)) {
                    taskExists = true;
                    updatedList.add(updatedTask);
                } else {
                    updatedList.add(t);
                }
            }
        }

        if (!taskExists) {
            updatedList.add(updatedTask);
        }

        saveAllTasks(updatedList.toArray(new Task[0]));
    }

    /**
     * Prüft, ob eine bestimmte Kategorie mindestens eine Fließtext-Aufgabe enthält.
     *
     * @param category Name der Kategorie
     * @return {@code true}, wenn eine {@link TaskFreeform} vorhanden ist, sonst
     *         {@code false}
     */
    public static boolean isFreeformCategory(String category) {
        Task[] tasks = loadCategoryTasks(category);
        for (Task t : tasks) {
            if (t.title == "none") {
                return true;
            }
        }
        return false;
    }

    /**
     * Interne Hilfsmethode zum Vergleichen zweier Tasks.
     *
     * @param a Erste Task
     * @param b Zweite Task
     * @return {@code true}, wenn beide Tasks denselben Typ, Titel und dieselbe
     *         Kategorie haben
     */
    private static boolean isSameTask(Task a, Task b) {
        return a.getClass().equals(b.getClass()) &&
                a.title.trim().equalsIgnoreCase(b.title.trim()) &&
                a.category.trim().equalsIgnoreCase(b.category.trim());
    }

    /**
     * Wandelt ein {@link JSONObject} in eine konkrete Task-Instanz um.
     * Unterstützt {@link TaskSimple}, {@link TaskTimed} und {@link TaskFreeform}.
     *
     * @param json Die JSON-Repräsentation einer Task
     * @return Die erzeugte Task-Instanz
     */
    private static Task JSONToTask(JSONObject json) {
        String category = json.getString("category");
        String title = json.getString("title");
        String content = json.getString("content");

        if (title.equals("none")) {
            return new TaskFreeform(category, content);
        }

        boolean completed = json.getBoolean("completed");

        if (json.has("dueDate")) {
            LocalDate dueDate = LocalDate.parse(json.getString("dueDate"));
            return new TaskTimed(category, title, content, dueDate, completed);
        }
        return new TaskSimple(category, title, content, completed);
    }
}
