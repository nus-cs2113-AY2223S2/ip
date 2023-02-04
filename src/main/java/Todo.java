public class Todo extends Task{
    public Todo(String description){
        super(description);
        String[] lineComponents = description.split(" ", 2);
        this.description = lineComponents[1];
        this.taskLabel = "[T]";
    }

    public static int add(String line, Task[] list, int currentNumber) {
        printHorizontalLine();
        System.out.println("     Got it. I've added this task:");
        Todo newTask = new Todo(line);
        System.out.println("       " + newTask.taskLabel + newTask.getStatusIcon() + " " + newTask.description);
        list[currentNumber] = newTask;
        ++currentNumber;
        System.out.println("     Now you have " + currentNumber + " tasks in the list.");
        return currentNumber;
    }
}
