import java.util.Scanner;


public class Duke {
    public static final int MAX_NUMBER_OF_TASKS = 100;
    public static int tasksIndex = 0;
    public static boolean isInUse = true;

    public static void main(String[] args) {
        Task[] tasks = new Task[MAX_NUMBER_OF_TASKS];

        greetUser();
        while (isInUse) {
            String userInput = getUserInput();
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
            /*
            processedInputs = processedInputs[1].split("/", 2);
            String[] furtherProcessedInput = processedInputs[1].split(" ");
            tasks[tasksIndex] = new Deadline(processedInputs[0], furtherProcessedInput[1]);
            */
            String by = furtherProcessInputForDeadline(processedInputs);
            tasks[tasksIndex] = new Deadline(processedInputs[0], by);
            printNotification(tasks[tasksIndex], "deadline", tasksIndex + 1);
            tasksIndex++;
        } else { // must be "event" case
            /*
            processedInputs = processedInputs[1].split("/", 3);
            String[] furtherProcessedInput1 = processedInputs[1].split(" ",2);
            String[] furtherProcessedInput2 = processedInputs[2].split(" ",2);
            */
            String[] fromAndTo = furtherProcessInputForEvent(processedInputs);
            tasks[tasksIndex] = new Event(processedInputs[0], fromAndTo[0], fromAndTo[1]);
            printNotification(tasks[tasksIndex], "event", tasksIndex + 1);
            tasksIndex++;
        }
        return;
    }

    private static String furtherProcessInputForDeadline(String[] processedInputs) {
        String[] taskNameAndIdentifierAndBy = processedInputs[1].split("/",2);
        String[] identifierAndBy = taskNameAndIdentifierAndBy[1].split(" ",2);
        String by = identifierAndBy[1];
        return by;
    }
    private static String[] furtherProcessInputForEvent(String[] processedInputs) {
        String[] taskNameAndFromAndTo = processedInputs[1].split("/", 3);
        String[] identifierAndFrom = taskNameAndFromAndTo[1].split(" ",2);
        String[] identifierAndTo = taskNameAndFromAndTo[2].split(" ",2);
        String[] fromAndTo = {identifierAndFrom[1], identifierAndTo[1]};
        return fromAndTo;
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
            System.out.println("Now you have " + numberOfTasks + " tasks in your list.");
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

    private static String getUserInput() {
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        return userInput;
    }



    private static String[] processUserInput(String userInput) {
        // first, split up the string ONCE with " " delimiter to find the command ("(un)mark"/"todo"/"deadline"/"event"/"list")
        // processedInputs[0] is command, processedInputs[1] is the information
        String[] processedInputs = userInput.split(" ", 2);
        /*
        switch (processedInputs[0]) {
        // for "deadline", split the rest of the string ONCE using "/" as the delimiter -> 2 strings
        case "deadline":
            processedInputs = processedInputs[1].split("/", 2);
            break;
        // for "event", split the rest of the string TWICE using "/" as the delimiter -> 3 strings
        case "event":
            processedInputs = processedInputs[1].split("/", 3);
            break;
        // for "unmark", "mark", "todo", "list"and "bye", don't need to split anymore, can just return inputs
        default:
            break;
        }
        */
        return processedInputs;
    }
}