public class Event extends Task {

    protected String start;
    protected String end;
    public Event(String description) {
        super(description);
        String[] lineComponents = description.split("/", 2);
        String[] descriptionComponents = lineComponents[0].split(" ",2);
        String[] periodComponents = lineComponents[1].split("/",2);
        String[] startPrepositions = periodComponents[0].split(" ", 2);
        String[] endPrepositions = periodComponents[1].split(" ", 2);

        this.start = "(" + startPrepositions[0] + ": " + startPrepositions[1];
        this.end = endPrepositions[0] + ": " + endPrepositions[1] +")";
        this.description = descriptionComponents[1] + this.start + this.end;
        this.isDone = false;
        this.taskLabel = "[E]";
    }

    public static int add(String line, Task[] list, int currentNumber) {
        System.out.print("    ____________________________________________________________\n");
        System.out.println("     Got it. I've added this task:");
        Event newTask = new Event(line);
        System.out.println("       "+ newTask.taskLabel + newTask.getStatusIcon() + " " + newTask.description);
        list[currentNumber] = newTask;
        ++currentNumber;
        System.out.println("     Now you have " + currentNumber + " tasks in the list.");
        return currentNumber;
    }
}
