public class Deadline extends Task {

    protected String whenDue;

    public Deadline(String description){
        super(description);
        String[] splitLine = description.split("/",2);
        String[] getDescription = splitLine[0].split(" ",2);
        String[] splitDueDatePreposition = splitLine[1].split(" ",2);
        this.description = getDescription[1];
        this.whenDue = "(" + splitDueDatePreposition[0] + ": " + splitDueDatePreposition[1] + ")";
        this.isDone = false;
        this.label = "[D]";
    }
    public static int addForDeadline(String line, Task[] list, int index) {
        System.out.print("    ____________________________________________________________\n");
        System.out.println("    Got it. I've added this task:");
        Deadline newTask = new Deadline(line);
        System.out.println("      " + newTask.label + newTask.getStatusIcon() + " " + newTask.description + " " + newTask.whenDue);
        list[index] = newTask;
        ++index;
        System.out.println("    Now you have " + index + " tasks in the list.");
        System.out.println("    ____________________________________________________________\n");
        return index;
    }

}
