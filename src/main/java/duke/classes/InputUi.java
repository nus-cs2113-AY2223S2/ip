package duke.classes;

public class InputUi {
    protected Task task;
    protected int count;

    public InputUi(Task task, int count) {
        this.task = task;
        this.count = count;
    }

    public void showMarkedTask() {
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    public void showUnmarkedTask() {
        System.out.println("OK, I've marked this task as not done yet:\n" + task);
    }

    public void showDeletedTask() {
        System.out.println("Noted, I've removed this task\n" + task);
    }

    public void showRemainingTasks() {
        System.out.println("Now you have " + count + " tasks in the list");
    }

    public void showTaskAdded() {
        System.out.println("Got it. I've added this task: \n" + task + "\nNow you have " + (count + 1) + " tasks in your list." );
    }
}
