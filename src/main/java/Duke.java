import java.util.Scanner;


public class Duke {
    public static final int MAX_NUMBER_OF_TASKS = 100;
    public static int tasksIndex = 0;
    public static boolean isInUse = true;

    public static void main(String[] args) {
        // how do i abstract these 2 things into a function? can i initialize inside a function?
        Task[] tasks = new Task[MAX_NUMBER_OF_TASKS];
        Scanner in = new Scanner(System.in);
        // how to deal with "Exception in thread "main" java.util.NoSuchElementException: No line found"?

        greetUser();
        while (isInUse) {
            String userInput = getUserInput(in);
            String[] processedInputs = processUserInput(userInput);
            showResultToUser(tasks, processedInputs);
        }
    }

    public static void showResultToUser(Task[] tasks, String[] processedInputs) {
        if (processedInputs[0].equals("bye")) {
            isInUse = false; // can i do this?***
            printExitMessage();
        } else if (processedInputs[0].equals("list")) {
            listTasks(tasks);
        } else if (processedInputs[0].equals("mark")) {
            int indexToBeMarked = Integer.parseInt(processedInputs[1]) - 1; // 0-indexed
            tasks[indexToBeMarked].setDone(true);
            printNotification(tasks[indexToBeMarked], "mark", tasksIndex + 1);
        } else if (processedInputs[0].equals("unmark")) {
            int indexToBeUnmarked = Integer.parseInt(processedInputs[1]) - 1; // 0-indexed
            tasks[indexToBeUnmarked].setDone(false);
            printNotification(tasks[indexToBeUnmarked], "unmark",tasksIndex + 1);
        } else if (processedInputs[0].equals("todo")) {
            tasks[tasksIndex] = new ToDo(processedInputs[1]);
            printNotification(tasks[tasksIndex], "todo", tasksIndex + 1);
            tasksIndex++;
        } else if (processedInputs[0].equals("deadline")) { // now, if the array has 2 elements, then it is "deadline" case
            String[] taskNameAndBy = furtherProcessInputForDeadline(processedInputs);
            tasks[tasksIndex] = new Deadline(taskNameAndBy[0], taskNameAndBy[1]);
            printNotification(tasks[tasksIndex], "deadline", tasksIndex + 1);
            tasksIndex++;
        } else { // must be "event" case
            String[] fromAndToAndTaskName = furtherProcessInputForEvent(processedInputs);
            tasks[tasksIndex] = new Event(fromAndToAndTaskName[2], fromAndToAndTaskName[0], fromAndToAndTaskName[1]);
            printNotification(tasks[tasksIndex], "event", tasksIndex + 1);
            tasksIndex++;
        }
        return;
    }

    private static String[] furtherProcessInputForDeadline(String[] processedInputs) {
        // not sure what names to use for the variables
        String[] taskNameAndIdentifierAndBy = processedInputs[1].split("/",2);
        String[] identifierAndBy = taskNameAndIdentifierAndBy[1].split(" ",2);
        String [] taskNameAndBy = {taskNameAndIdentifierAndBy[0], identifierAndBy[1]};
        return taskNameAndBy;
    }
    private static String[] furtherProcessInputForEvent(String[] processedInputs) {
        // not sure what names to use for the variables
        String[] taskNameAndFromAndTo = processedInputs[1].split("/", 3);
        String[] identifierAndFrom = taskNameAndFromAndTo[1].split(" ",2);
        String[] identifierAndTo = taskNameAndFromAndTo[2].split(" ",2);
        String[] fromAndToAndTaskName = {identifierAndFrom[1], identifierAndTo[1], taskNameAndFromAndTo[0]};
        return fromAndToAndTaskName;
    }


    private static void printNotification(Task task, String modification, int numberOfTasks) {
        boolean isRequiredToShowNumberOfTasks = false;
        if (modification.equals("unmark")) {
            System.out.println("OK, I've marked this task as not done yet:");
        } else if (modification.equals("mark")) {
            System.out.println("Nice! I've marked this task as done:");
        } else { // adding either deadline, event or todo
            System.out.print("Got it. I've added this task:\n" + "  ");
            isRequiredToShowNumberOfTasks = true;
        }
        task.printTask();
        if (isRequiredToShowNumberOfTasks) {
            System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
        }
    }
    private static void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void listTasks(Task[] tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasksIndex; i++) {
            System.out.print(i + 1);
            System.out.print(".");
            tasks[i].printTask();
        }
    }

    private static void greetUser() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");
    }

    private static String getUserInput(Scanner in) {
        // Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        return userInput;
    }



    private static String[] processUserInput(String userInput) {
        // first, split up the string ONCE with " " delimiter to separate the command & information
        // processedInputs[0] is command, processedInputs[1] is the information
        String[] processedInputs = userInput.split(" ", 2);
        return processedInputs;
    }
}