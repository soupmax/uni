import java.time.*;

public class TaskTimed extends Task {
    LocalDate dueDate;

    public TaskTimed(String category, String title, String description, LocalDate dueDate, boolean priority,
            boolean completed) {
        super(category, title, description, priority, completed);
        this.dueDate = dueDate;
    }

    public TaskTimed(String category, String title, String description, LocalDate dueDate, boolean priority) {
        super(category, title, description, priority);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "category: " + category + "\n" + "title: " + title + "description: " + description + "priority: "
                + (priority ? "true" : "false") + "dueDate: " + dueDate.toString() + "completed: "
                + (completed ? "true" : "false");
    }
}
