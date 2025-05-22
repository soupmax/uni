public class TaskSimplePanel extends TaskPanel {
    public TaskSimplePanel(TaskSimple task, Runnable onStatusChange) {
        super((Task) task, onStatusChange);
    }
}
