import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

import static InputCheckingPackage.InputChecking.*;


public class Duke {

    private static final ArrayList<Task> tasks = new ArrayList<>();

    private static String filePath = "data/duke.txt";




    public static void main(String[] args) {


        Storage storage = new Storage(filePath);

        storage.readFile(filePath,tasks);

        String command;
        int numberOfTasks = tasks.size();
        String latestResponse = "";
        boolean hasAdditionalTask = false;

        greetUser();

        while (true) {
            command = latestResponse;


            if (!hasAdditionalTask) {
                System.out.println("What can I do for your today?");
                command = readTask();
            }
            boolean isValidInput = checkForValidInput(command);

            while (!isValidInput) {
                command = readTask();
                isValidInput = checkForValidInput(command);
            }
            printHorizontalBar();


            if (command.contains("list")) {
                printTaskList();

            } else if (Objects.equals(command, "bye")) {
                break;

            } else if (command.contains("unmark")) {
                boolean isValidUnmarkInput = checkUnmarkInput(command, tasks.size());
                if (isValidUnmarkInput) {
                    unmarkTask(command, tasks.size());
                }


            } else if (command.contains("mark")) {
                boolean isValidMarkInput = checkMarkInput(command, tasks.size());
                if (isValidMarkInput) {
                    markTask(command, tasks.size());
                }

            } else if (command.contains("delete")) {
                boolean isValidDeleteInput = checkDeleteInput(command,tasks.size());

                if (isValidDeleteInput) {
                    DeleteTask(command);
                }

            } else if (command.contains("deadline")) {
                boolean isValidDeadlineInput = checkDeadlineInput(command);
                if (isValidDeadlineInput) {
                    createDeadline(command);
                }


            } else if (command.contains("event")) {
                boolean isValidEventInput = checkEventInput(command);
                if (isValidEventInput) {
                    createEvent(command);
                }


            } else if (command.contains("todo")) {
                boolean isValidTodoInput = checkTodoInput(command);
                if (isValidTodoInput) {
                    createTask(command);
                }


            } else {
                System.out.println("Sorry I didn't get that!");


            }


            System.out.println("There are currently " + tasks.size() + " task(s) in the list");


            printHorizontalBar();
            latestResponse = checkForAdditionalTask();
            hasAdditionalTask = true;
            if (Objects.equals(latestResponse, "no")) {
                break;
            }


        }

        //to say bye
        System.out.println("Bye! Hope to see you again soon!\n");
        printHorizontalBar();

    }

    private static void DeleteTask(String command) {
        int val = Integer.parseInt(command.substring(7));
        System.out.println("Noted. I've removed this task:");
        Task task = tasks.get(val-1);
        System.out.print(val);
        task.printTask();
        System.out.println(" Now you have "+ tasks.size()+ " tasks in the list.");
        tasks.remove(val-1);
    }


    private static void greetUser() {
        String logo = """
                 ____        _       \s
                |  _ \\ _   _| | _____\s
                | | | | | | | |/ / _ \\
                | |_| | |_| |   <  __/
                |____/ \\__,_|_|\\_\\___|
                """;


        System.out.println(logo);
        printHorizontalBar();
        System.out.println("Hello! I'm Duke! \n");
        printHorizontalBar();
    }


    private static void createTask(String command) {
        command = command.substring(5);
        Task task = new Task(command);
        task.isCompleted = false;
        task.taskName = command;
        System.out.println("added:  " + task.taskName);
        tasks.add(task);
    }

    private static void createEvent(String command) {
        String content = command.substring(5);
        String[] contents = content.split("/");
        String title = contents[0];
        String startTime = contents[1];
        String endTime = contents[2];
        Event newEvent = new Event(title);
        newEvent.setStartTime(startTime);
        newEvent.setEndTime(endTime);
        tasks.add(newEvent);
        newEvent.newEventResponse();
    }

    private static void createDeadline(String command) {
        String content = command.substring(8);
        String[] contents = content.split("/");
        String title = contents[0];
        String dueDate = contents[1];
        Deadline newDeadline = new Deadline(title);
        newDeadline.setEndTime(dueDate);
        tasks.add(newDeadline);
        newDeadline.newDeadlineResponse();
    }

    private static void markTask(String command, int numberOfTasks) {
        String[] result = command.split(" ");
        int taskNum = Integer.parseInt(result[1]);
        if (taskNum > numberOfTasks || taskNum <= 0) {
            System.out.println("Error! No such task exists!");
        } else {
            Task target = tasks.get(taskNum - 1);
            target.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(target.getStatusIcon() + "   " + target.taskName);
        }
    }

    private static void unmarkTask(String command, int numberOfTasks) {
        String[] result = command.split(" ");
        int taskNum = Integer.parseInt(result[1]);
        if (taskNum > numberOfTasks || taskNum <= 0) {
            System.out.println("Error! No such task exists!");
        } else {
            Task target = tasks.get(taskNum - 1);
            target.markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(target.getStatusIcon() + "   " + target.taskName);
        }
    }

    private static void printTaskList() {
        int i = 1;
        for (Task a : Duke.tasks) {
            if (a != null) {
                System.out.print(i);
                a.printTask();
                i++;
            }
        }


    }


    private static void printHorizontalBar() {
        String horizontalBar = "---------------------------------------------------\n";
        System.out.println(horizontalBar);
    }

    private static String readTask() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        return line;

    }

    private static String checkForAdditionalTask() {

        System.out.println("Do you have any other task for me?  ");
        return readTask();


    }


}
