import Constants.Constants;

import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

import static InputCheckingPackage.InputChecking.*;



public class Duke {

    private static final Task[] tasks = new Task[Constants.MAX_NUMBER_OF_ENTRIES];

    public static void main(String[] args) {

        String command;
        int numberOfTasks = 0;
        String latestResponse = "";
        boolean hasAdditionalTask = false;

        greetUser();

        while (numberOfTasks < 100) {
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
                boolean isValidUnmarkInput = checkUnmarkInput(command, numberOfTasks);
                if (isValidUnmarkInput) {
                    unmarkTask(command, numberOfTasks);
                }


            } else if (command.contains("mark")) {
                boolean isValidMarkInput = checkMarkInput(command, numberOfTasks);
                if (isValidMarkInput) {
                    markTask(command, numberOfTasks);
                }

            } else if (command.contains("deadline")) {
                boolean isValidDeadlineInput = checkDeadlineInput(command);
                if (isValidDeadlineInput) {
                    createDeadline(command, numberOfTasks);
                    numberOfTasks++;
                }


            } else if (command.contains("event")) {
                boolean isValidEventInput = checkEventInput(command);
                if (isValidEventInput) {
                    createEvent(command, numberOfTasks);
                    numberOfTasks++;
                }



            } else {
                createTask(command, numberOfTasks);
                numberOfTasks++;

            }


            System.out.println("There are currently "+numberOfTasks + " task(s) in the list");
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


    private static void createTask(String command, int numberOfTasks) {
        command = command.substring(5);
        Task task = new Task(command);
        task.isCompleted = false;
        task.taskName = command;
        System.out.println("added:  " + task.taskName);
        tasks[numberOfTasks] = task;
    }

    private static void createEvent(String command, int numberOfTasks) {
        String content = command.substring(5);
        String[] contents = content.split("/");
        String title = contents[0];
        String startTime = contents[1];
        String endTime = contents[2];
        Event newEvent = new Event(title);
        newEvent.setStartTime(startTime);
        newEvent.setEndTime(endTime);
        tasks[numberOfTasks] = newEvent;
        newEvent.newEventResponse();
    }

    private static void createDeadline(String command, int numberOfTasks) {
        String content = command.substring(8);
        String[] contents = content.split("/");
        String title = contents[0];
        String dueDate = contents[1];
        Deadline newDeadline = new Deadline(title);
        newDeadline.setEndTime(dueDate);
        tasks[numberOfTasks] = newDeadline;
        newDeadline.newDeadlineResponse();
    }

    private static void markTask(String command, int numberOfTasks) {
        String[] result = command.split(" ");
        int taskNum = Integer.parseInt(result[1]);
        if (taskNum > numberOfTasks || taskNum <= 0) {
            System.out.println("Error! No such task exists!");
        } else {
            Task target = tasks[taskNum - 1];
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
            Task target = tasks[taskNum - 1];
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
