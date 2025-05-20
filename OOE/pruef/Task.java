package pruef;

public class Task {
    public String title;
    public String description;
    protected boolean completed;

    public Task(String title, String description, boolean completed) {
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.completed = false;
    }

    public Task() {
        this.title = "Einkaufen";
        this.description = "Milch, Brot, Eier besorgen";
        this.completed = false;
    }
}
