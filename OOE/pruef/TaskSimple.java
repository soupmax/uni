public class TaskSimple extends Task {
    public TaskSimple(String category, String title, String description, boolean priority, boolean completed) {
        super(category, title, description, priority, completed);
    }

    public TaskSimple(String category, String title, String description, boolean priority) {
        super(category, title, description, priority);
    }

    @Override
    public String toString() {
        return "category: " + category + "\n" + "title: " + title + "description: " + description + "priority: "
                + (priority ? "true" : "false") + "completed: "
                + (completed ? "true" : "false");
    }
}
