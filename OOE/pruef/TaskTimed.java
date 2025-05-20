package pruef;

import java.time.*;

public class TaskTimed extends Task {
    LocalDate dueDate;

    public TaskTimed(String title, String description, LocalDate dueDate, boolean completed) {
        super(title, description, completed);
        this.dueDate = dueDate;
    }

    public TaskTimed(String title, String description, LocalDate dueDate) {
        super(title, description);
        this.dueDate = dueDate;
    }

    public TaskTimed() {
        super();
        this.dueDate = LocalDateTime.now();
    }
}
