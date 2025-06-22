public class TaskFreeformPanel extends TaskPanel {
    public TaskFreeformPanel(TaskFreeform task) {
        super(task);
    }

    @Override
    public Task getTask() {
        return new TaskFreeform(task.category, contentArea.getText());
    }
}
