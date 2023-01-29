public class Todo extends Task{
    public Todo(String description){
        super(description);
        String[] splitDescription = description.split(" ", 2);
        this.description = splitDescription[1];
        this.label = "[T]";
    }

    public static int add(String line, Task[] list, int index) {
        System.out.print("    ____________________________________________________________\n");
        System.out.println("    Got it. I've added this task:");
        Todo newTask = new Todo(line);
        System.out.println("      " + newTask.label + newTask.getStatusIcon() + " " + newTask.description);
        list[index] = newTask;
        ++index;
        System.out.println("    Now you have " + index + " tasks in the list.");
        System.out.println("    ____________________________________________________________\n");
        return index;
    }
}
