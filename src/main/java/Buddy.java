import java.util.Scanner;
import java.util.Arrays;

// camelCase used as a coding standard

public class Buddy {

    static final int TOTAL_TASKS = 100;

    public static void main(String[] args) {
        String greeting = "Hello there! I'm Buddy\n"
                + "How may I assist you?";
        String exitMessage = "Hope I was of help to you! Have a great day and see you again, Buddy :)";
        String divider = "________________________________________________________________________________";

        System.out.println(divider);
        System.out.println(greeting);
        System.out.println(divider);

        Task[] listOfThings = new Task[TOTAL_TASKS];  // why cannot private static?
        int currentPosition = 0;            // why cannot private static?
        String command;
        Scanner in = new Scanner(System.in);
        command = in.nextLine();

        while (!command.equals("bye")) {
            int index = 1;
            if (command.equals("list")) {
                for (int i = 0; i < currentPosition; i++) { // while not null
                    System.out.println(index + "." + listOfThings[index - 1]);
                    index++;
                }
            } else if (command.startsWith("mark")) {  // .startsWith(" ")
                int taskNumber = Integer.parseInt(command.substring(5));
                // have to parse
                Task currentTask = listOfThings[taskNumber - 1];
                currentTask.setDone(true);
                System.out.println(divider);
                System.out.println("Great work on completing this task! Marked as done! :)");
                System.out.println(currentTask);
                System.out.println(divider);
            } else if (command.startsWith("unmark")) {
                int taskNumber = Integer.parseInt(command.substring(7));
                Task currentTask = listOfThings[taskNumber - 1];
                currentTask.setDone(false);
                System.out.println(divider);
                System.out.println("Remember to come back to this task! Marked as undone!");
                System.out.println(currentTask);
                System.out.println(divider);
            } else { //todo or deadline or event --> put together so don't have to repeat the same code thrice
                System.out.println(divider);
                System.out.println("Got it! I have added this task: ");
                if (command.startsWith("todo")) {
                    int todo_starting_index = 5;
                    Todo todoBeingAdded = new Todo(command.substring(todo_starting_index));
                    listOfThings[currentPosition] = todoBeingAdded;
                    // Task is not a Todo but Todo is a task
                    System.out.println(todoBeingAdded);
                } else if (command.startsWith("deadline")) {
                    int deadline_starting_index = 9;
                    Task taskBeingAdded = new Task(command.substring(deadline_starting_index)); // task + date + slash (Description)
                    // filter the description and date
                    String taskWithDate = taskBeingAdded.description;
                    int indexOfSlash = taskWithDate.indexOf('/');
                    String taskDescription = taskWithDate.substring(0, (indexOfSlash - 1));   // substring goes to the one before the second index!!!!
                    String date = taskWithDate.substring(indexOfSlash + 4);
                    Deadline deadlineBeingAdded = new Deadline(taskDescription, date);
                    listOfThings[currentPosition] = deadlineBeingAdded;
                    System.out.println(deadlineBeingAdded);

                } else if (command.startsWith("event")) {
                    Task taskBeingAdded = new Task(command.substring(6));
                    // filter the description, from and to
                    String wholeLine = taskBeingAdded.description;
                    int indexOfFirstSlash = wholeLine.indexOf('/');
                    String taskDescription = wholeLine.substring(0, (indexOfFirstSlash - 1));
                    int indexOfSecondSlash = wholeLine.indexOf('/', (indexOfFirstSlash + 1));  //searches from index after first slash
                    String from = wholeLine.substring((indexOfFirstSlash + 6), (indexOfSecondSlash));
                    String to = wholeLine.substring(indexOfSecondSlash + 4);
                    Event eventBeingAdded = new Event(taskDescription, from, to);
                    listOfThings[currentPosition] = eventBeingAdded;
                    System.out.println(eventBeingAdded);
                }

                currentPosition++;
                System.out.print("You currently have " + currentPosition);
                if (currentPosition == 1) {
                    System.out.println(" task remaining! Let's finish it quickly!");
                } else {
                    System.out.println(" tasks remaining! You got this, buddy!");  // all these same for all three subtasks so put at the bottom
                }
            }
            command = in.nextLine();
        }
        System.out.println(divider);
        System.out.println(exitMessage);
        System.out.println(divider);

    }
}
