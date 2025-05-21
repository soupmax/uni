import java.time.*;

public class TaskTimed extends Task {
    LocalDate dueDate;
    boolean priority;

    public TaskTimed(String title, String description, LocalDate dueDate, boolean priority, boolean completed) {
        super(title, description, completed);
        this.dueDate = dueDate;
        this.priority = priority;
    }

    public TaskTimed(String title, String description, LocalDate dueDate, boolean priority) {
        super(title, description);
        this.dueDate = dueDate;
        this.priority = priority;
    }

    public TaskTimed() {
        super("tasktimed", "das ist ein test");
        this.dueDate = LocalDateTime.now().toLocalDate();
        this.priority = false;
    }
}
