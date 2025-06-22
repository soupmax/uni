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
 * Unterstützt unterschiedliche Task-Typen (TaskSimple, TaskTimed).
 * 
 * Funktionen:
 * - Speichern einzelner oder aller Tasks in JSON-Datei
 * - Laden aller Tasks oder kategoriebasierter Tasks
 * - Löschen von Tasks nach Kategorie
 * - Aktualisieren vorhandener Tasks
 * - Ermitteln aller existierenden Kategorien (Tabs)
 * 
 * Die Klasse verwendet die org.json Bibliothek zur JSON-Verarbeitung.
 * 
 * @author Max
 */
public class InOut {
    private static String fileName = "./tasks.json";

    /**
     * Speichert eine einzelne Task. Lädt vorhandene Tasks aus der Datei,
     * fügt die neue Task hinzu und speichert das aktualisierte Array.
     * 
     * @param task Die zu speichernde Task (TaskSimple oder TaskTimed)
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
     * Speichert ein Array von Tasks komplett in der JSON-Datei.
     * Überschreibt den bisherigen Inhalt.
     * 
     * @param tasks Array von Tasks zum Speichern
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
     * Lädt alle Tasks aus der JSON-Datei.
     * 
     * @return Ein Array aller gespeicherten Tasks, oder null falls Datei leer/nicht
     *         vorhanden,
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
     * Ermittelt alle unterschiedlichen Kategorien (Tabs) aus den gespeicherten
     * Tasks.
     * 
     * @return Array der Kategorienamen, ggf. leer
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
     * Lädt alle Tasks einer bestimmten Kategorie aus der JSON-Datei.
     * 
     * @param cat Kategorie-Name
     * @return Array von Tasks der angegebenen Kategorie, oder null falls Datei
     *         leer/nicht vorhanden,
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

            // Da einige Indizes übersprungen werden, besser Liste benutzen
            List<Task> tasksList = new ArrayList<>();

            for (int i = 0; i < taskArray.length(); i++) {
                JSONObject json = taskArray.getJSONObject(i);
                String category = json.getString("category");

                // skip tasks in anderen kategorien (werden in anderen panels angezeigt)
                if (!category.equals(cat)) {
                    continue;
                }
                Task t = JSONToTask(json);
                tasksList.add(t);
            }

            return Utils.sortTasks(tasksList.toArray(new Task[0]));
            // return tasksList.toArray(new Task[0]);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return new Task[0]; // Rückgabe eines leeren Arrays im Fehlerfall
        }
    }

    /**
     * Löscht alle Tasks einer bestimmten Kategorie.
     * 
     * @param category Kategorie, deren Tasks gelöscht werden sollen
     */
    public static void deleteCategoryTasks(String category) {
        Task[] tasks = loadAllTasks();

        if (tasks == null)
            return;

        // Alle Tasks außer der zu löschenden Kategorie behalten
        List<Task> filteredTasks = Arrays.stream(tasks)
                .filter(t -> !t.category.equals(category))
                .toList();

        saveAllTasks(filteredTasks.toArray(new Task[0]));
    }

    /**
     * Aktualisiert eine bestehende Task durch Ersetzen anhand von Titel, Kategorie
     * und Beschreibung.
     * Speichert danach alle Tasks neu.
     * 
     * @param updatedTask Task mit den neuen Werten
     */
    public static void updateTask(Task updatedTask) {
        System.out.println("→ updateTask() wurde betreten");
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
            updatedList.add(updatedTask); // neu hinzufügen
        }

        saveAllTasks(updatedList.toArray(new Task[0]));

    }

    /**
     * Prüft, ob eine Kategorie Fließtext aufgaben enthält
     * 
     * @param category Name der Kategorie
     * @return
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

    private static boolean isSameTask(Task a, Task b) {
        return a.getClass().equals(b.getClass()) &&
                a.title.trim().equalsIgnoreCase(b.title.trim()) &&
                a.category.trim().equalsIgnoreCase(b.category.trim());
    }

    /**
     * Wandelt ein JSONObject in eine entsprechende Task-Instanz (TaskSimple oder
     * TaskTimed) um.
     * 
     * @param json JSON-Daten der Task
     * @return Task-Objekt
     */
    private static Task JSONToTask(JSONObject json) {
        String category = json.getString("category");
        String title = json.getString("title");
        String content = json.getString("content");

        // fließtext aufgaben haben keine completet eigenschaft, weshalb diese eher
        // abgefertigt werden
        if (title.equals("none")) {
            // title ist hier nicht wichtig, da er immer none ist
            return new TaskFreeform(category, content);
        }

        boolean completed = json.getBoolean("completed");

        if (json.has("dueDate")) {
            // es ist eine TaskTimed
            LocalDate dueDate = LocalDate.parse(json.getString("dueDate"));
            return new TaskTimed(category, title, content, dueDate, completed);
        }
        return new TaskSimple(category, title, content, completed);
    }

}
