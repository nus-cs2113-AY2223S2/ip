import java.util.Objects;
import java.util.Scanner;

public class Jonathan {
    public static void main(String[] args) {
        Scanner input = new Scanner((System.in));
        String command;
        String line = "    ____________________________________________________________";
        String welcome = line + "\n    Hello! I'm Jonathan\n" +
                "    What can I do for you?\n" + line + "\n";
        String goodbye = "    Bye. Hope to see you again soon!\n" + line;

        Task[] list = new Task[100];
        int taskCounter = 0;

        System.out.println(welcome);

        // Loop until user prompt "bye"
        while (true) {
            command = input.nextLine(); // Prompt input from user

            if (Objects.equals(command, "bye")) {
                System.out.println(goodbye);
                break; // quit the program

            } else if (Objects.equals(command, "list")) {
                lookupTask(list, taskCounter);

            } else if (command.split(" ")[0].equals("mark") || command.split(" ")[0].equals("unmark")) {
                boolean isDone = command.split(" ")[0].equals("mark");
                int taskNum = Integer.parseInt(command.split(" ")[1]);
                Task task = list[taskNum-1];
                changeStatus(task, isDone);

            } else if (command.split(" ")[0].equals("todo")) {
                String description = command.substring(command.indexOf(" ") + 1);
                Todo todo = new Todo(description);
                list[taskCounter] = todo;
                taskCounter += 1;
                addedMessage(todo, taskCounter);

            } else if (command.split(" ")[0].equals("deadline")) {
                String description = command.substring(command.indexOf(" ") + 1, command.indexOf(" /by"));
                String by = command.substring(command.indexOf("/by") + 4);
                Deadline deadline = new Deadline(description, by);
                list[taskCounter] = deadline;
                taskCounter += 1;
                addedMessage(deadline, taskCounter);

            } else if (command.split(" ")[0].equals("event")) {
                String description = command.substring(command.indexOf(" ") + 1, command.indexOf(" /from"));
                String start = command.substring(command.indexOf("/from") + 6, command.indexOf(" /to"));
                String end = command.substring(command.indexOf("/to") + 4);
                Event event = new Event(description, start, end);
                list[taskCounter] = event;
                taskCounter += 1;
                addedMessage(event, taskCounter);

            } else { // add Task
                System.out.println("    ____________________________________________________________\n" +
                        "    Wrong input, please type it correctly!\n" +
                        "    ____________________________________________________________\n");
            }
        }
    }

    /*
    Method to print the added message to the user after prompting input
     */
    public static void addedMessage(Task task, int taskCounter) {
        System.out.println("    ____________________________________________________________\n" +
                "     Got it. I've added this task:\n" +
                "       " + task + "\n" +
                "     Now you have " + taskCounter + " tasks in the list.\n" +
                "    ____________________________________________________________\n");
    }

    /*
    Method to print all the Task, including the Task's status
     */
    public static void lookupTask(Task[] list, int counter) {
        System.out.println("    ____________________________________________________________\n" +
                "    Here are the tasks in your list:");
        for (int i = 0; i < counter; i++) {
            System.out.println("    " + (i+1) + ". "  + list[i]);
        }
        System.out.println("    ____________________________________________________________\n");
    }

    /*
    Method to change the status of a Task
     */
    public static void changeStatus(Task task, boolean isDone){
        task.setDone(isDone);
        // If the Task is mark done
        if (isDone) {
            System.out.println("    ____________________________________________________________\n" +
                    "     Nice! I've marked this task as done:\n" +
                    "       " + task + "\n" +
                    "    ____________________________________________________________\n");
        } else { // Unmark the Task
            System.out.println("    ____________________________________________________________\n" +
                    "     OK, I've marked this task as not done yet:\n" +
                    "       " + task + "\n" +
                    "    ____________________________________________________________\n");
        }
    }

}

