package app.tasks;

public class Task {
    public static final String line = ("─".repeat(50));
    protected String taskDescription;
    protected boolean isDone;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); //Marks a task done with an X
    }

    public static void taskStatusHandler(Task[] tasks, String commandWord, String commandDescriptor) {
        System.out.println(line);
        int taskNumber = Integer.parseInt(commandDescriptor);
        if (commandWord.equals("mark")) {
            tasks[taskNumber - 1].setDone(true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks[taskNumber - 1]);
        } else {
            tasks[taskNumber - 1].setDone(false);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tasks[taskNumber - 1]);
        }
        System.out.println(line);
    }

    public static void printTasks(Task[] tasks, int[] index) {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < index[0]; i++) {
            System.out.print((i + 1) + ".");
            System.out.println(tasks[i]);
        }
        System.out.println(line);
    }
}
