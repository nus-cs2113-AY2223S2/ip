public class Task {
    protected String description;
    protected String type;
    protected boolean isDone;
    private static String line = "__________________________________________________________";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = "0";
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getType() {
        return (type);
    }

    public void printAddTask() {
        //print to show added to list
        System.out.println(line);
        System.out.println("Got it. I've added this task: ");
        printTask();
    }

    public void printTask() {
        String status = getStatusIcon();
        String type = getType();
        System.out.println("[" + type + "][" + status + "]" + description);
    }

    public void markAsDone() {
        this.isDone = !isDone;
        if (this.isDone == true) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("Ok, I've marked this task as not done yet:");
        }
        System.out.println("  [" + getStatusIcon() + "] " + description);
    }

}

