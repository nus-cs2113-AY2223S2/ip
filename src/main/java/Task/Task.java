package Task;
public abstract class Task {
    private String description;
    private boolean isDone;
    private static int total;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        total++;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public void setMark(boolean done) {
        this.isDone = done;
    }
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    public void printMessage(){
        System.out.println("\t____________________________________________________________\r\n"
                + "\t Got it. I've added this task:\r\n"
                + "\t  " + toString() + "\r\n"
                + "\t Now you have " + total + " tasks in the list.\r\n"
                + "\t____________________________________________________________\r\n");
    }
    public static int getTotal() {
        return total;
    }
    public boolean getStatus() {
        return isDone;
    }
    public static void setTotal(int number) {
        total = number;
    }
    public abstract String getTaskType();
    public String getTaskDescription() {
        return description;
    }

    //...
}

