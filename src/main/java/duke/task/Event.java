package duke.task;

public class Event extends Task{

    public String from;
    public String to;

    public Event(String taskDescription, String from, String to) {
        super(taskDescription);
        this.from = from;
        this.to = to;
        taskChar = "[E]";
        print();
    }

    @Override
    public void print() {
        formattedTask = taskChar + status + " " + taskDescription + " (from: " + from + " to: " + to + ")";
        System.out.println(formattedTask);
    }
}
