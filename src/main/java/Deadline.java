public class Deadline extends Task {

    protected String whenDue;

    public Deadline(String description){
        super(description);
        String[] lineComponents = description.split("/",2);
        String[] descriptionComponents = lineComponents[0].split(" ",2);
        String[] dueDatePrepositions = lineComponents[1].split(" ",2);
        this.whenDue = "(" + dueDatePrepositions[0] + ": " + dueDatePrepositions[1] + ")";
        this.description = descriptionComponents[1] + this.whenDue;
        this.isDone = false;
        this.taskLabel = "[D]";
    }
    public static int add(String line, Task[] list, int currentNumber) {
        System.out.println("     Got it. I've added this task:");
        Deadline newTask = new Deadline(line);
        System.out.println("       " + newTask.taskLabel + newTask.getStatusIcon() + " " + newTask.description);
        list[currentNumber] = newTask;
        ++currentNumber;
        System.out.println("     Now you have " + currentNumber + " tasks in the list.");
        return currentNumber;
    }

}
