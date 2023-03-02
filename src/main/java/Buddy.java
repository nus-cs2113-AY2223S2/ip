import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;


public class Buddy {
    public static int taskCount = 0;

    public static void main(String[] args) {
        ArrayList<Task> listOfThings = new ArrayList<>();
        try {
            Storage.loadFile(listOfThings);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            Storage.createFile();

        }

        String greeting = "Hello there! I'm Buddy\n"
                + "How may I assist you?";
        String listOfCommands = "Here are the commands you can use: todo, deadline, event,  list, mark, unmark, bye";
        String exitMessage = "Hope I was of help to you! Have a great day and see you again, Buddy :)";
        String divider = "________________________________________________________________________________";

        System.out.println(divider);
        System.out.println(greeting);
        System.out.println(listOfCommands);
        System.out.println(divider);


        String command;
        Scanner in = new Scanner(System.in);
        command = in.nextLine();


        while (!command.equals("bye")) {

            int index = 1;
            if (command.equals("list")) {
                for (int i = 0; i < taskCount; i++) { // while not null
                    System.out.println(index + "." + listOfThings.get(index - 1));
                    index++;
                }
            } else if (command.startsWith("mark")) {
                int taskNumberIndexMark = 5;
                int taskNumber = Integer.parseInt(command.substring(taskNumberIndexMark));


                try {
                    Task currentTask = listOfThings.get(taskNumber - 1);
                    currentTask.setDone(true);
                    System.out.println(divider);
                    System.out.println("Great work on completing this task! Marked as done! :)");
                    System.out.println(currentTask);
                    System.out.println(divider);
                } catch (IndexOutOfBoundsException a) {
                    System.out.println("That is not a valid task to mark! Please check your list again and input a valid task");

                }
            } else if (command.startsWith("unmark")) {
                int taskNumberIndexUnmark = 7;
                int taskNumber = Integer.parseInt(command.substring(taskNumberIndexUnmark));

                try {
                    Task currentTask = listOfThings.get(taskNumber - 1);

                    currentTask.setDone(false);
                    System.out.println(divider);
                    System.out.println("Remember to come back to this task! Marked as undone!");
                    System.out.println(currentTask);
                    System.out.println(divider);
                } catch (IndexOutOfBoundsException a) {
                    System.out.println("That is not a valid task to unmark! Please check your list again and input a valid task");

                }
            } else if (command.startsWith("todo") || command.startsWith("deadline") || command.startsWith("event") || command.startsWith("delete") || command.startsWith("find")) { //todo or deadline or event or delete--> put together so don't have to repeat the same code thrice
                System.out.println(divider);
                System.out.println("Got it! I have added this task: ");
                if (command.startsWith("todo")) {
                    int todoStartingIndex = 5;

                    Todo todoBeingAdded = new Todo(command.substring(todoStartingIndex));
                    listOfThings.add(todoBeingAdded);

                    System.out.println(todoBeingAdded);
                    taskCount++;

                } else if (command.startsWith("deadline")) {
                    int deadlineStartingIndex = 9;
                    Task taskBeingAdded = new Task(command.substring(deadlineStartingIndex)); // task + date + slash (Description)
                    // filter the description and date
                    String taskWithDate = taskBeingAdded.description;
                    int indexOfSlash = taskWithDate.indexOf('/');
                    String taskDescription = taskWithDate.substring(0, (indexOfSlash - 1));   // substring goes to the one before the second index!!!!
                    String date = taskWithDate.substring(indexOfSlash + 4);
                    Deadline deadlineBeingAdded = new Deadline(taskDescription, date);
                    listOfThings.add(deadlineBeingAdded);
                    System.out.println(deadlineBeingAdded);
                    taskCount++;

                } else if (command.startsWith("event")) {
                    int eventStartingIndex = 6;
                    Task taskBeingAdded = new Task(command.substring(eventStartingIndex));
                    // filter the description, from and to
                    String wholeLine = taskBeingAdded.description;
                    int indexOfFirstSlash = wholeLine.indexOf('/');
                    String taskDescription = wholeLine.substring(0, (indexOfFirstSlash - 1));
                    int indexOfSecondSlash = wholeLine.indexOf('/', (indexOfFirstSlash + 1));  //searches from index after first slash
                    String from = wholeLine.substring((indexOfFirstSlash + 6), (indexOfSecondSlash));
                    String to = wholeLine.substring(indexOfSecondSlash + 4);
                    Event eventBeingAdded = new Event(taskDescription, from, to);
                    listOfThings.add(eventBeingAdded);
                    System.out.println(eventBeingAdded);
                    taskCount++;

                } else if (command.startsWith("delete")) {
                    int indexOfTaskNumber = 7;
                    int taskNumberToBeDeleted = Integer.parseInt(command.substring(indexOfTaskNumber));
                    listOfThings.remove(taskNumberToBeDeleted - 1);
                    taskCount--;
                    System.out.print("OK! Task deleted :) Type list to see remaining tasks!");
                } else if (command.startsWith("find")){
                    String keyword = command.split(" ")[1].trim().toLowerCase();
                    ArrayList <Task> matchedTasks = new ArrayList<>();
                    matchedTasks = (ArrayList<Task>) listOfThings.stream() // casts list to ArrayList
                            .filter(t -> t.getTaskName().trim().toLowerCase().contains(keyword)).collect(toList());
                    if (!matchedTasks.isEmpty()){
                        System.out.println("Well, I found these matching tasks in your list!");
                    }
                    for (Task task : matchedTasks){

                        System.out.println(task);

                    }
                    if (matchedTasks.isEmpty()){
                        System.out.println("Oops, there are no tasks matching the keyword! Try again with another keyword");
                    }





                }
                System.out.print("You currently have " + taskCount);
                if (taskCount == 1) {
                    System.out.println(" task remaining! Let's finish it quickly!");
                } else {
                    System.out.println(" tasks remaining! You got this, buddy!");  // all these same for all four subtasks so put at the bottom
                }
            } else {
                System.out.println("This command does not exist! Please type a valid command!");
            }
            command = in.nextLine();

        }
        try {
            Storage.updateFile(listOfThings);

        } catch (IOException e) {
            System.out.println("Error occurred");
        }

        System.out.println(divider);
        System.out.println(exitMessage);
        System.out.println(divider);

    }
}
