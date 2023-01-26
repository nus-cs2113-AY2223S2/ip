public class TaskList {
    private Task[] tasks;
    private int noOfTasks;

    public TaskList(int size) {
        this.tasks = new Task[size];
        noOfTasks = 0;
    }

    public void addTask(String taskName) {
        noOfTasks++;
        tasks[noOfTasks] = new Task(taskName);
        System.out.println("added: " + taskName);
    }

    public void listTasks() {
        if (noOfTasks == 0) {
            System.out.println("No tasks yet. Please input a task");
        }
        for (int i = 1; i <= noOfTasks; i++) {
            System.out.println(i + ". " + tasks[i].getName());
        }
    }
}
