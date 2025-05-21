import org.json.JSONObject;
import org.json.JSONException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

import org.json.JSONArray;

public class InOut {
    public static void saveTasks(Task[] tasks, String filename) {
        JSONArray taskArray = new JSONArray();

        for (Task task : tasks) {
            if (task instanceof TaskTimed) {
                taskArray.put(taskTimedToJSON((TaskTimed) task));
            } else if (task instanceof TaskSimple) {
                taskArray.put(taskSimpleToJSON((TaskSimple) task));
            } else if (task instanceof Task) {
                taskArray.put(taskToJSON(task));
            }
        }

        try (FileWriter file = new FileWriter(filename)) {
            file.write(taskArray.toString(4)); // 4 = Einrückung
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Methode zum Laden mehrerer Aufgaben aus einer Datei
    public static Task[] loadTasks(String filename, String cat) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filename)));
            JSONArray taskArray = new JSONArray(content);

            Task[] tasks = new Task[taskArray.length()];
            for (int i = 0; i < taskArray.length(); i++) {
                JSONObject json = taskArray.getJSONObject(i);

                String category = json.getString("category");
                String title = json.getString("title");
                String description = json.getString("description");
                boolean completed = json.getBoolean("completed");

                // skip tasks in anderen kategorien (werden in anderen panels angezeigt)
                if (category != cat) {
                    continue;
                }

                if (json.has("dueDate")) {
                    // es ist eine TaskTimed
                    LocalDate dueDate = LocalDate.parse(json.getString("dueDate"));
                    tasks[i] = new TaskTimed(title, description, dueDate, completed);
                    continue;
                }
                tasks[i] = new Task(title, description, completed);
            }

            return tasks;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return new Task[0]; // Rückgabe eines leeren Arrays im Fehlerfall
        }
    }

    private static JSONObject taskToJSON(Task t) {
        JSONObject json = new JSONObject();
        json.put("title", t.title);
        json.put("description", t.description);
        json.put("completed", t.completed);

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
