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
    public static String divider = "________________________________________________________________________________";

    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        try {
            Storage.loadFile(taskList);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            Storage.createFile();

        }

        String greeting = "Hello there! I'm Buddy\n"
                + "How may I assist you?";
        String listOfCommands = "Here are the commands you can use: todo, deadline, event,  list, mark, unmark, bye";
        String exitMessage = "Hope I was of help to you! Have a great day and see you again, Buddy :)";


        System.out.println(divider);
        System.out.println(greeting);
        System.out.println(listOfCommands);
        System.out.println(divider);


        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();


        while (!input.equals("bye")) {

            int index = 1;
            if (input.equals("list")) {
                for (int i = 0; i < taskCount; i++) { // while not null
                    System.out.println(index + "." + taskList.get(index - 1));
                    index++;
                }
            } else if (input.startsWith("mark")) {
                int taskNumberIndexMark = 5;
                int taskNumber = Integer.parseInt(input.substring(taskNumberIndexMark));


                try {
                    Task currentTask = taskList.get(taskNumber - 1);
                    currentTask.setDone(true);
                    System.out.println(divider);
                    System.out.println("Great work on completing this task! Marked as done! :)");
                    System.out.println(currentTask);
                    System.out.println(divider);
                } catch (IndexOutOfBoundsException a) {
                    System.out.println("That is not a valid task to mark! Please check your list again and input a valid task");

                }
            } else if (input.startsWith("unmark")) {
                int taskNumberIndexUnmark = 7;
                int taskNumber = Integer.parseInt(input.substring(taskNumberIndexUnmark));

                try {
                    Task currentTask = taskList.get(taskNumber - 1);

                    currentTask.setDone(false);
                    System.out.println(divider);
                    System.out.println("Remember to come back to this task! Marked as undone!");
                    System.out.println(currentTask);
                    System.out.println(divider);
                } catch (IndexOutOfBoundsException a) {
                    System.out.println("That is not a valid task to unmark! Please check your list again and input a valid task");

                }
            } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event") || input.startsWith("delete") || input.startsWith("find")) {

                if (input.startsWith("todo")) {
                    int todoStartingIndex = 5;

                    Todo todoBeingAdded = new Todo(input.substring(todoStartingIndex));
                    taskList.addTask(todoBeingAdded);

                } else if (input.startsWith("deadline")) {
                    int deadlineStartingIndex = 9;
                    Task taskBeingAdded = new Task(input.substring(deadlineStartingIndex)); // task + date + slash (Description)
                    // filter the description and date
                    String taskWithDate = taskBeingAdded.description;
                    int indexOfSlash = taskWithDate.indexOf('/');
                    String taskDescription = taskWithDate.substring(0, (indexOfSlash - 1));   // substring goes to the one before the second index!!!!
                    String date = taskWithDate.substring(indexOfSlash + 4);
                    Deadline deadlineBeingAdded = new Deadline(taskDescription, date);
                    taskList.addTask(deadlineBeingAdded);


                } else if (input.startsWith("event")) {
                    int eventStartingIndex = 6;
                    Task taskBeingAdded = new Task(input.substring(eventStartingIndex));
                    // filter the description, from and to
                    String wholeLine = taskBeingAdded.description;
                    int indexOfFirstSlash = wholeLine.indexOf('/');
                    String taskDescription = wholeLine.substring(0, (indexOfFirstSlash - 1));
                    int indexOfSecondSlash = wholeLine.indexOf('/', (indexOfFirstSlash + 1));  //searches from index after first slash
                    String from = wholeLine.substring((indexOfFirstSlash + 6), (indexOfSecondSlash));
                    String to = wholeLine.substring(indexOfSecondSlash + 4);
                    Event eventBeingAdded = new Event(taskDescription, from, to);
                    taskList.addTask(eventBeingAdded);


                } else if (input.startsWith("delete")) {
                    int indexOfTaskNumber = 7;
                    int taskNumberToBeDeleted = Integer.parseInt(input.substring(indexOfTaskNumber));
                    taskList.deleteTask(taskNumberToBeDeleted - 1);

                } else if (input.startsWith("find")){
                    String keyword = input.split(" ")[1].trim().toLowerCase();
                    ArrayList <Task> matchedTasks = new ArrayList<>();
                    matchedTasks = (ArrayList<Task>) taskList.stream() // casts list to ArrayList
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

            } else {
                System.out.println("This input does not exist! Please type a valid input!");
            }
            input = in.nextLine();

        }
        try {
            Storage.updateFile(taskList);

        } catch (IOException e) {
            System.out.println("Error occurred");
        }

        System.out.println(divider);
        System.out.println(exitMessage);
        System.out.println(divider);

    }
}
