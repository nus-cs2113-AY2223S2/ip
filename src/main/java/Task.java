public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
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

    protected static Task[] totalTasks = new Task[100];

    public static void conductInstruction(String command, Task[] listOfTasks, int index) {
        System.out.println("    ____________________________________________________________\n");
        if (command.equals("bye")) {
            System.out.println("    Bye. Hope to see you again soon!");
        } else if (command.equals("list")) {
            System.out.println("    Here are the tasks in your list:");
            for (int i = 0; i < index; ++i) {
                int counter = i + 1;
                System.out.print("    " + counter + ".[");
                if (listOfTasks[i].isDone) {
                    System.out.print("X] ");
                } else {
                    System.out.print(" ] ");
                }
                System.out.println(listOfTasks[i].description);

            }
        } else if (command.startsWith("mark")) {
            String[] seperated = command.split(" ");
            int number = Integer.parseInt(seperated[1])-1;
            listOfTasks[number].setDone(true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("    [X] " + listOfTasks[number].description);

        } else if (command.startsWith("unmark")) {
            String[] seperated = command.split(" ");
            int number = Integer.parseInt(seperated[1])-1;
            listOfTasks[number].setDone(false);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("    [ ] " + listOfTasks[number].description);


        }
    }


}