import org.json.JSONObject;
import org.json.JSONException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;

import org.json.JSONArray;

public class InOut {
    public static void saveTask(Task task) {
        JSONArray taskArray = new JSONArray();
        String fileName = "./tasks.json";
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
            if (t instanceof TaskTimed) {
                taskArray.put(taskTimedToJSON((TaskTimed) t));
            } else if (t instanceof TaskSimple) {
                taskArray.put(taskSimpleToJSON((TaskSimple) t));
            } else if (t instanceof Task) {
                taskArray.put(taskToJSON(t));
            }
        }

        try (FileWriter file = new FileWriter(fileName)) {
            file.write(taskArray.toString(4));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Task[] loadAllTasks() {
        String fileName = "./tasks.json";
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
            return tasks;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return new Task[0]; // Rückgabe eines leeren Arrays im Fehlerfall
        }
    }

    public static String[] getAllTabs() {
        Task[] tasks = loadAllTasks();
        return Arrays.stream(tasks)
                .map(t -> t.category)
                .distinct()
                .toArray(String[]::new);
    }

    // Methode zum Laden mehrerer Aufgaben aus einer Datei
    public static Task[] loadCategoryTasks(String cat) {
        String fileName = "./tasks.json";
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
                String category = json.getString("category");

                // skip tasks in anderen kategorien (werden in anderen panels angezeigt)
                if (!category.equals(cat)) {
                    continue;
                }
                Task t = JSONToTask(json);
                tasks[i] = t;
            }

            return tasks;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return new Task[0]; // Rückgabe eines leeren Arrays im Fehlerfall
        }
    }

    private static Task JSONToTask(JSONObject json) {
        String category = json.getString("category");
        String title = json.getString("title");
        String description = json.getString("description");
        boolean priority = json.getBoolean("priority");
        boolean completed = json.getBoolean("completed");

        if (json.has("dueDate")) {
            // es ist eine TaskTimed
            LocalDate dueDate = LocalDate.parse(json.getString("dueDate"));
            return new TaskTimed(category, title, description, dueDate, priority, completed);
        }
        return new TaskSimple(category, title, description, priority, completed);
    }

    private static JSONObject taskToJSON(Task t) {
        JSONObject json = new JSONObject();
        json.put("category", t.category);
        json.put("title", t.title);
        json.put("description", t.description);
        json.put("completed", t.completed);
        json.put("priority", t.priority);

        return json;
    }

    private static JSONObject taskTimedToJSON(TaskTimed t) {
        JSONObject json = taskToJSON(t);
        json.put("dueDate", t.dueDate.toString());

        return json;
    }

    private static JSONObject taskSimpleToJSON(TaskSimple t) {
        JSONObject json = taskToJSON(t);

        return json;
    }
}
