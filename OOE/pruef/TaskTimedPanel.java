import javax.swing.*;

public class TaskTimedPanel extends TaskPanel {
    private JLabel dueDateLabel;
    private JLabel priorityLabel;

    public TaskTimedPanel(TaskTimed task, Runnable onStatusChange) {
        super(task, onStatusChange); // ruft den Konstruktor von TaskPanel auf

        dueDateLabel = new JLabel("Fällig bis: " + task.dueDate.toString());
        priorityLabel = new JLabel("Priorität: " + task.priority);

    }

    @Override
    protected void AddExtraComponent(Task task) {
        dueDateLabel = new JLabel("Fällig bis: " + ((TaskTimed) task).dueDate.toString());
        priorityLabel = new JLabel("Priorität: " + ((TaskTimed) task).priority);

        add(dueDateLabel);
        add(priorityLabel);
    }
}
