import java.util.Scanner;

public class Duke {

    /**
     * Encapsulates print statements for whenever a task is added
     * (as either a todo, deadline, or event).
     * 
     * @param task Reference to task that is added.
     * @param numTasks Total number of tasks in the list.
     *
     */
    private static void printAddTaskConfirmation(Task task, int numTasks) {
        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println("Got it. I've added this task:\n" + task + "\nNow you have " + numTasks + " tasks in the list.");
        System.out.println(line);
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
        Scanner in = new Scanner(System.in);
        
        String line = "____________________________________________________________";

        System.out.println(line);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(line);
        
        Task[] tasks = new Task[100];
        int numTasks = 0;

        
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
                    for (int i = 0; i < numTasks; i++) {
                        System.out.println(Integer.toString(i+1) + "." + tasks[i]);
                    }
                    System.out.println(line);
                } else if (splitIntoArgs[0].equals("mark")) {
                    
                    int toMark = Integer.parseInt(splitIntoArgs[1]) - 1;
                    tasks[toMark].markDone();
                    System.out.println(line);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks[toMark]);
                    System.out.println(line);
                } else if (splitIntoArgs[0].equals("unmark")) {
                    int toUnmark = Integer.parseInt(splitIntoArgs[1]) - 1;
                    tasks[toUnmark].unMarkDone();
                    System.out.println(line);
                    System.out.println("Ok, I've marked this task as not done yet:");
                    System.out.println(tasks[toUnmark]);
                    System.out.println(line);
                } else if (splitIntoArgs[0].equals("todo")) {
                    String description = splitIntoArgs[1];
                    Todo todo = new Todo(description);
                    tasks[numTasks] = todo;
                    numTasks++;
                    printAddTaskConfirmation(todo, numTasks);
                } else if (splitIntoArgs[0].equals("deadline")) {
                    //Uses regex to split into arguments for description and by field
                    String[] argsList = splitIntoArgs[1].split(" /by ");
                    Deadline deadline = new Deadline(argsList[0], argsList[1]);
                    tasks[numTasks] = deadline;
                    numTasks++;
                    printAddTaskConfirmation(deadline, numTasks);
                } else if (splitIntoArgs[0].equals("event")) {
                    //Uses regex to split into arguments for description, from field, and to field.
                    String[] argsList = splitIntoArgs[1].split(" \\/(from|to) ");
                    Event event = new Event(argsList[0], argsList[1], argsList[2]);
                    tasks[numTasks] = event;
                    numTasks++;
                    printAddTaskConfirmation(event, numTasks);
                }
                else {
                    throw new UnknownCommandException();
                }
             
                in.close();    
            } catch (UnknownCommandException e) {
                System.out.println(line);
                System.out.println("Command not found. Please enter a valid command!");
                System.out.println(line);
            }
        }
    }
}
