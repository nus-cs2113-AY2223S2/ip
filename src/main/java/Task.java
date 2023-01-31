public class Task {
    protected String description;
    protected boolean isDone;
    protected String label;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public static void instructionLessAdd(String command, Task[] listOfTasks, int index) {
        System.out.print("    ____________________________________________________________\n");
        if (command.equals("bye")) {
            System.out.println("    Bye. Hope to see you again soon!");
        } else if (command.equals("list")) {
            list(listOfTasks, index);
        } else if (command.matches("mark \\d")) {
            mark(command, listOfTasks);

        } else if (command.matches("unmark \\d")) {
            unmark(command, listOfTasks);
        }
    }

    protected static int add(String line, Task[] list, int index) {
        return index;
    }

    protected static void unmark(String command, Task[] listOfTasks) {
        String[] seperated = command.split(" ");
        int number = Integer.parseInt(seperated[1]) - 1;
        listOfTasks[number].setDone(false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("    " + listOfTasks[number].label + listOfTasks[number].getStatusIcon() + " " + listOfTasks[number].description);
    }

    protected static void mark(String command, Task[] listOfTasks) {
        String[] seperated = command.split(" ");
        int number = Integer.parseInt(seperated[1]) - 1;
        listOfTasks[number].setDone(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("    " + listOfTasks[number].label + listOfTasks[number].getStatusIcon() + " " + listOfTasks[number].description);
    }

    private static void list(Task[] listOfTasks, int index) {
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < index; ++i) {
            int counter = i + 1;
            System.out.print("     " + counter + "." + listOfTasks[i].label + listOfTasks[i].getStatusIcon());
            System.out.println(listOfTasks[i].description);
        }
        System.out.println("    ____________________________________________________________\n");
    }


}