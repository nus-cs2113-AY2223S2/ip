public class TaskList {
    private Task[] tasks;
    private int noOfTasks;

    private void printStatus(int taskNo) {
        if (tasks[taskNo].isDone()) {
            System.out.print("[X] ");
        } else {
            System.out.print("[ ] ");
        }
    }

    private void printTaskName(int taskNo) {
        System.out.print(tasks[taskNo].getName());
    }

    private boolean isValidTaskNo(int taskNo) {
        if (taskNo <= 0) {
            System.out.println("Negative task number entered, please don't try to crash the program a**h***.");
            return false;
        }
        if (taskNo > noOfTasks) {
            System.out.println("Task number does not exist, there are only " + noOfTasks + " tasks in total.");
            return false;
        }
        return true;
    }

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
            System.out.print(i + ". ");
            printStatus(i);
            System.out.println(tasks[i].getName());
        }
    }

    public void markDone(int taskNo) {
        if (!isValidTaskNo(taskNo)) {
            return;
        }
        if (tasks[taskNo].isDone()) {
            System.out.println("Already done.");
        } else {
            tasks[taskNo].setStatus(true);
            System.out.println("Nice! I have marked this task as done.");
            printStatus(taskNo);
            printTaskName(taskNo);
            System.out.println();
        }
    }

    public void unmarkDone(int taskNo) {
        if (!isValidTaskNo(taskNo)) {
            return;
        }
        if (!tasks[taskNo].isDone()) {
            System.out.println("Not done yet. Please finish it.");
        } else {
            tasks[taskNo].setStatus(false);
            System.out.println("Ok I have marked this as not done yet.");
            printStatus(taskNo);
            printTaskName(taskNo);
            System.out.println();
        }
    }
}
