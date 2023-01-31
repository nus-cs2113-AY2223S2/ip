import java.util.Scanner;

public class Duke {
    private static int userInputCount = 0;
    private static final String LINE = "--------------------------------------------";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static Task[] userTaskList = new Task[100];
    private static String userInput;
    private static Scanner scan = new Scanner(System.in);

    private enum taskType {
        TODO, DEADLINE, EVENT
    }

    public static void reportError(String description) {
        System.out.println(LINE);
        System.out.println(description);
        System.out.println(LINE);
    }

    public static void greetUser() {
        System.out.println(LINE);
        System.out.println("Hello from\n" + LOGO);
        System.out.println("How can I help you?\n");
        System.out.println("Here are some possibly useful commands:");
        System.out.println(LINE);
        System.out.println("/todo {description} - Add a specific task to ur task list.");
        System.out.println("/list - List out all the tasks in ur task list.");
        System.out.println("/mark {numerical index} - Mark a specific task done.");
        System.out.println("/unmark {numerical index} - Mark a specific task undone.");
        System.out.println("/bye - Terminate the program.");
        System.out.println(LINE);
    }

    public static void goodbyeUser() {
        System.out.println(LINE);
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println(LINE);
    }

    public static void addTodo(String[] userInputArray) {
        String taskDescription = "";
        for (int i = 1; i < userInputArray.length; i++) {
            taskDescription = taskDescription.concat(userInputArray[i]);
            if (i != userInputArray.length - 1) {
                taskDescription = taskDescription.concat(" ");
            }
        }
        Task t = new Todo(taskDescription);
        userTaskList[userInputCount] = t;
        userInputCount++;
        System.out.println(LINE);
        System.out.println("Added the following task [TYPE: TODO]: " + taskDescription);
        System.out.println(LINE);
    }

    public static void addDeadline(String[] userInputArray) {
        if (!userInput.contains("/by")) {
            reportError("Please specify a deadline via the /by command!");
            return;
        }
        String deadlineDescription = "";
        String deadlineCutoff = "";
        Integer userInputArrayIndex = 1;
        // handle description
        for (int i = 1; i < userInputArray.length; i++) {
            if (userInputArray[i].equals("/by")) {
                break;
            } else {
                userInputArrayIndex++;
                deadlineDescription = deadlineDescription.concat(userInputArray[i]);
                if (!userInputArray[i + 1].equals("/by")) {
                    deadlineDescription = deadlineDescription.concat(" ");
                }
            }
        }
        userInputArrayIndex++;
        // handle deadline cutoff
        for (int j = userInputArrayIndex; j < userInputArray.length; j++) {
            userInputArrayIndex++;
            deadlineCutoff = deadlineCutoff.concat(userInputArray[j]);
            if (j != userInputArray.length - 1) {
                deadlineCutoff = deadlineCutoff.concat(" ");
            }
        }
        Task d = new Deadline(deadlineDescription, deadlineCutoff);
        userTaskList[userInputCount] = d;
        userInputCount++;
        System.out.println(LINE);
        System.out.println(
                "Added the following task [TYPE: DEADLINE]:\n" + deadlineDescription + " (To be completed by: "
                        + deadlineCutoff
                        + ")");
        System.out.println(LINE);
    }

    public static void addEvent(String[] userInputArray) {
        if (!userInput.contains("/start") || !userInput.contains("/end")) {
            reportError("Please specify both start and end dates/times via the /start and /end commands!");
            return;
        }
        String eventDescription = "";
        String eventStartTime = "";
        String eventEndTime = "";
        Integer userInputArrayIndex = 1;
        // handle description
        for (int i = 1; i < userInputArray.length; i++) {
            if (userInputArray[i].equals("/start")) {
                break;
            } else {
                userInputArrayIndex++;
                eventDescription = eventDescription.concat(userInputArray[i]);
                if (!userInputArray[i + 1].equals("/start")) {
                    eventDescription = eventDescription.concat(" ");
                }
            }
        }
        userInputArrayIndex++;
        // handle event start time
        for (int j = userInputArrayIndex; j < userInputArray.length; j++) {
            if (userInputArray[j].equals("/end")) {
                break;
            } else {
                userInputArrayIndex++;
                eventStartTime = eventStartTime.concat(userInputArray[j]);
                if (!userInputArray[j + 1].equals("/end")) {
                    eventStartTime = eventStartTime.concat(" ");
                }
            }
        }
        // handle event end time
        for (int k = userInputArrayIndex; k < userInputArray.length; k++) {
            userInputArrayIndex++;
            eventEndTime = eventEndTime.concat(userInputArray[k]);
            if (k != userInputArray.length - 1) {
                eventEndTime = eventEndTime.concat(" ");
            }
        }
        Task d = new Event(eventDescription, eventStartTime, eventEndTime);
        userTaskList[userInputCount] = d;
        userInputCount++;
        System.out.println(LINE);
        System.out.println("Added the following task [TYPE: EVENT]:\n" + eventDescription + " (From: " + eventStartTime
                + " To: " + eventEndTime
                + ")");
        System.out.println(LINE);
    }

    public static void addTask(String[] userInputArray, taskType variation) {
        // check if task description is left empty
        if (userInputArray.length == 1) {
            reportError("Please enter a description for your task!");
            return;
        }
        // handle different task types
        if (variation == taskType.TODO) {
            // HANDLE TODO
            addTodo(userInputArray);
        } else if (variation == taskType.DEADLINE) {
            // HANDLE DEADLINE
            addDeadline(userInputArray);
        } else if (variation == taskType.EVENT) {
            addEvent(userInputArray);
        }

    }

    public static void listTasks() {
        if (userInputCount == 0) {
            reportError("You have no tasks added!");
        } else {
            System.out.println("These are the following tasks you have:");
            for (int i = 0; i < userInputCount; i++) {
                System.out.println(LINE);
                int index = i;
                index++;
                System.out.print(index + ".");
                System.out.println(userTaskList[i]);
                System.out.println(LINE);
            }
        }
    }

    public static void markTask(String[] userInputArray) {
        try {
            int taskIndex = Integer.parseInt(userInputArray[1]);
            taskIndex--;
            userTaskList[taskIndex].markAsDone();
            System.out.println(LINE + System.lineSeparator() + "The following task has been marked done: [X] "
                    + userTaskList[taskIndex].description + System.lineSeparator() + LINE);
        } catch (Exception e) {
            reportError("Please enter a valid numerical index of the task!");
        }

    }

    public static void unmarkTask(String[] userInputArray) {
        try {
            int taskIndex = Integer.parseInt(userInputArray[1]);
            taskIndex--;
            userTaskList[taskIndex].markAsUndone();
            System.out.println(LINE + System.lineSeparator() + "The following task has been marked undone: [ ] "
                    + userTaskList[taskIndex].description + System.lineSeparator() + LINE);
        } catch (Exception e) {
            reportError("Please enter a valid numerical index of the task!");
        }
    }

    public static void echo(String userString) {
        System.out.println(LINE);
        System.out.println(userInput);
        System.out.println(LINE);
    }

    public static void processUserInput() {
        userInput = scan.nextLine();
        // handle single-word input commands with no arguments
        if (userInput.equals("/bye")) {
            goodbyeUser();
            scan.close();
            System.exit(0);
        }
        if (userInput.equals("/list")) {
            listTasks();
        } else {
            // handle multi-word input commands with required arguments
            String[] userInputArray = userInput.split(" ");
            if (userInputArray[0].equals("/todo")) {
                addTask(userInputArray, taskType.TODO);
            } else if (userInputArray[0].equals("/deadline")) {
                addTask(userInputArray, taskType.DEADLINE);
            } else if (userInputArray[0].equals("/event")) {
                addTask(userInputArray, taskType.EVENT);
            } else if (userInputArray[0].equals("/mark")) {
                markTask(userInputArray);
            } else if (userInputArray[0].equals("/unmark")) {
                unmarkTask(userInputArray);
            } else {
                // handle non-command inputs
                echo(userInput);
            }
        }
    }

    public static void main(String[] args) {
        greetUser();
        while (scan.hasNextLine()) {
            processUserInput();
        }
    }
}
