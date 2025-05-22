public class Task {
    public String title;
    public String description;
    public String category;
    public boolean priority;
    protected boolean completed;

    public Task(String category, String title, String description, boolean priority, boolean completed) {
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.category = category;
        this.priority = priority;
    }

    public Task(String category, String title, String description, boolean priority) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.completed = false;
        this.priority = priority;
    }

    public String toString() {
        return "category: " + category + "\n" + "title: " + title + "description: " + description + "priority: "
                + (priority ? "true" : "false") + "completed: "
                + (completed ? "true" : "false");
    }
}
