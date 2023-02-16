package duke;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;

public class Duke {

    /**
     * Encapsulates print statements for whenever a task is added
     * (as either a todo, deadline, or event).
     * 
     * @param task     Reference to task that is added.
     * @param numTasks Total number of tasks in the list.
     *
     */
    private static void printAddTaskConfirmation(Task task, int numTasks) {
        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println(
                "Got it. I've added this task:\n" + task + "\nNow you have " + numTasks + " tasks in the list.");
        System.out.println(line);
    }

    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);
        Scanner in = new Scanner(System.in);

        String line = "____________________________________________________________";

        System.out.println(line);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(line);

        File f = new File("duke.txt");

        f.createNewFile();
        Scanner s = new Scanner(f);
        FileWriter fw;

        ArrayList<Task> tasks = new ArrayList<>();

        // load array from file
        while (s.hasNext()) {
            String task = s.nextLine();
            String[] taskSplit = task.split("\\|");
            switch (taskSplit[1]) {
                case "T":
                    Todo todo = new Todo(taskSplit[2]);
                    tasks.add(todo);
                    break;
                case "D":
                    Deadline deadline = new Deadline(taskSplit[2], taskSplit[3]);
                    tasks.add(deadline);
                    break;
                case "E":
                    Event event = new Event(taskSplit[2], taskSplit[3], taskSplit[4]);
                    tasks.add(event);
                    break;
                default:
                    break;
            }
            if (taskSplit[0].equals("1")) {
                tasks.get(tasks.size() - 1).markDone();
            }
        }
        s.close();

        while (true) {
            try {
                System.out.println();
                String cmd = in.nextLine();

                // Splits into only two sections, one for command name and one for arguments
                String[] splitIntoArgs = cmd.split(" ", 2);

                if (cmd.equals("bye")) {
                    System.out.println(line);
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println(line);
                    break;
                } else if (cmd.equals("list")) {
                    System.out.println(line);
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(Integer.toString(i + 1) + "." + tasks.get(i));
                    }
                    System.out.println(line);
                } else if (splitIntoArgs[0].equals("mark")) {
                    if (splitIntoArgs.length < 2) {
                        throw new InsufficientParametersException();
                    }
                    int toMark = Integer.parseInt(splitIntoArgs[1]) - 1;
                    tasks.get(toMark).markDone();
                    System.out.println(line);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(toMark));
                    System.out.println(line);
                } else if (splitIntoArgs[0].equals("unmark")) {
                    if (splitIntoArgs.length < 2) {
                        throw new InsufficientParametersException();
                    }
                    int toUnmark = Integer.parseInt(splitIntoArgs[1]) - 1;
                    tasks.get(toUnmark).unMarkDone();
                    System.out.println(line);
                    System.out.println("Ok, I've marked this task as not done yet:");
                    System.out.println(tasks.get(toUnmark));
                    System.out.println(line);
                } else if (splitIntoArgs[0].equals("todo")) {
                    if (splitIntoArgs.length < 2) {
                        throw new InsufficientParametersException();
                    }
                    String description = splitIntoArgs[1];
                    Todo todo = new Todo(description);
                    tasks.add(todo);
                    printAddTaskConfirmation(todo, tasks.size());
                } else if (splitIntoArgs[0].equals("deadline")) {
                    // Uses regex to split into arguments for description and by field
                    if (splitIntoArgs.length < 2) {
                        throw new InsufficientParametersException();
                    }
                    String[] argsList = splitIntoArgs[1].split(" /by ");
                    if (argsList.length < 2) {
                        throw new InsufficientParametersException();
                    }
                    Deadline deadline = new Deadline(argsList[0], argsList[1]);
                    tasks.add(deadline);
                    printAddTaskConfirmation(deadline, tasks.size());
                } else if (splitIntoArgs[0].equals("event")) {
                    // Uses regex to split into arguments for description, from field, and to field.
                    if (splitIntoArgs.length < 2) {
                        throw new InsufficientParametersException();
                    }
                    String[] argsList = splitIntoArgs[1].split(" \\/(from|to) ");
                    if (argsList.length < 3) {
                        throw new InsufficientParametersException();
                    }
                    Event event = new Event(argsList[0], argsList[1], argsList[2]);
                    tasks.add(event);
                    printAddTaskConfirmation(event, tasks.size());
                } else {
                    throw new UnknownCommandException();
                }

            } catch (UnknownCommandException e) {
                System.out.println(line);
                System.out.println("Command not found. Please enter a valid command!");
                System.out.println(line);
            } catch (InsufficientParametersException e) {
                System.out.println(line);
                System.out.println(
                        "You have not provided enough parameters for this command. Please recheck your syntax!");
                System.out.println(line);
            }
            new FileWriter("duke.txt").close(); // reset output file
            fw = new FileWriter("duke.txt", true);
            for (Task task : tasks) {
                fw.write(task.storeString());
            }
            fw.close();
        }
        in.close();

    }
}
