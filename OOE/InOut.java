import org.json.JSONObject;
import org.json.JSONException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;

public class InOut {
    public static void saveTasks(Task[] tasks, String filename) {
        JSONArray taskArray = new JSONArray();

        for (Task task : tasks) {
            JSONObject json = new JSONObject();
            json.put("title", task.title);
            json.put("description", task.description);
            json.put("completed", task.completed);

            taskArray.put(json);
        }

        try (FileWriter file = new FileWriter(filename)) {
            file.write(taskArray.toString(4)); // 4 = Einrückung
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Methode zum Laden mehrerer Aufgaben aus einer Datei
    public static Task[] loadTasks(String filename) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filename)));
            JSONArray taskArray = new JSONArray(content);

            Task[] tasks = new Task[taskArray.length()];
            for (int i = 0; i < taskArray.length(); i++) {
                JSONObject json = taskArray.getJSONObject(i);

                String title = json.getString("title");
                String description = json.getString("description");
                boolean completed = json.getBoolean("completed");

                tasks[i] = new Task(title, description, completed);
            }

            return tasks;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return new Task[0]; // Rückgabe eines leeren Arrays im Fehlerfall
        }
    }
}
