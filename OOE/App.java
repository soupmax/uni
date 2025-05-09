import java.time.*;

public class App {
    public static void main(String[] args) {
        System.out.println("start");

        Task task1 = new Task("Aufgabe 1", "Beschreibung 1", false);
        Task task2 = new Task("Aufgabe 2", "Beschreibung 2", true);
        Task[] tasks = { task1, task2 };

        // Speichern der Aufgaben
        InOut.saveTasks(tasks, "tasks.json");

        // Laden der Aufgaben
        Task[] loadedTasks = InOut.loadTasks("tasks.json");
        for (Task task : loadedTasks) {
            System.out.println("Titel: " + task.title);
            System.out.println("Beschreibung: " + task.description);
            System.out.println("Erledigt: " + task.completed);
        }

        System.out.println("end");
    }
}