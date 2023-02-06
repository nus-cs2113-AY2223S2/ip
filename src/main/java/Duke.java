import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        greeting();
        Task[] taskStorage = new Task[100];

        boolean is_exit = false;
        while (!is_exit) {

            String messageFromUser;
            Scanner in = new Scanner(System.in);
            messageFromUser = in.nextLine();

            if (messageFromUser.startsWith("mark") || messageFromUser.startsWith("unmark")) {
                changeTaskStatus(messageFromUser, taskStorage);
            } else if (messageFromUser.equals("bye")) {
                exitGreeting();
                is_exit = true;
            } else if (messageFromUser.equals("list")) {
                displayList(taskStorage);
            } else if (hasTaskCreationKeyword(messageFromUser)) {
                Task newTask = handleTaskCreation(messageFromUser);
                addToList(newTask, taskStorage);
            } else {
                System.out.println("Invalid instruction. Please try again.");
            }
        }
    }

    public static void changeTaskStatus(String sentence, Task[] taskStorage) {
        String[] words = sentence.split(" ");
        int taskNumber = Integer.parseInt(words[1]);
        Task t = taskStorage[taskNumber];
        if (words[0].trim().equals("mark")) {
            t.markAsDone();
        } else {
            t.markAsUndone();
        }
        horizontalLine();
    }

    public static void greeting() {
        horizontalLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        horizontalLine();
    }

    public static void exitGreeting() {
        System.out.println("Bye. Hope to see you again soon!");
        horizontalLine();
    }

    public static void displayList(Task[] taskStorage) {
        int totalNumberOfTasks = Task.getNumberOfTasks();
        if (totalNumberOfTasks > 0) {
            System.out.println("Here are the tasks in your list: ");
            for (int i = 1; i <= totalNumberOfTasks; i += 1) {
                Task currentTask = taskStorage[i];
                displayTask(currentTask);
                System.out.println(i + "." + currentTask.getStatus() + currentTask.getTaskInfo());
            }
        } else {
            System.out.println("There are no tasks to display.");
        }
        horizontalLine();
    }

    public static void addToList(Task newTask, Task[] taskStorage) {
        horizontalLine();
        System.out.println("Sure, I've added this task: ");
        displayTask(newTask);
        int currentIndexInTaskStorage = Task.getNumberOfTasks();
        taskStorage[currentIndexInTaskStorage] = newTask;
        horizontalLine();
    }

    public static ToDo createToDo(String messageFromUser) {
        // Remove the word "todo" from message
        messageFromUser = messageFromUser.substring(4);
        ToDo newToDo = new ToDo(messageFromUser);
        return newToDo;
    }

    public static Deadline createDeadline(String messageFromUser) {
        // Remove the word "deadline" from message
        messageFromUser = messageFromUser.substring(8);
        String[] messageComponents = messageFromUser.split("/by", 2);
        Deadline newDeadline = new Deadline(messageComponents[0], messageComponents[1]);
        return newDeadline;
    }

    public static Event createEvent(String messageFromUser) {
        // Remove the word "event" from message
        messageFromUser = messageFromUser.substring(5);
        // Event has 3 aspects message /from startDate /to endDate
        String[] messageComponents = messageFromUser.split("/from", 2);
        String[] timeComponents = messageComponents[1].split("/to", 2);
        Event newEvent = new Event(messageComponents[0], timeComponents[0], timeComponents[1]);
        return newEvent;
    }

    public static boolean hasTaskCreationKeyword(String messageFromUser) {
        boolean isToDo = messageFromUser.startsWith("todo");
        boolean isDeadline = messageFromUser.startsWith("deadline");
        boolean isEvent = messageFromUser.startsWith("event");
        return (isToDo || isDeadline || isEvent);
    }

    public static Task handleTaskCreation(String messageFromUser) {
        Task newTask;
        if (messageFromUser.startsWith("todo")) {
            newTask = createToDo(messageFromUser);
        } else if (messageFromUser.startsWith("deadline")) {
            newTask = createDeadline(messageFromUser);
        } else {
            newTask = createEvent(messageFromUser);
        }
        return newTask;
    }

    public static void displayTask(Task currentTask) {
        // Check task type
        String taskType = currentTask.getTaskType();
        System.out.print(taskType + " ");
        System.out.print(currentTask.getStatus());
        System.out.print(currentTask.getTaskInfo());

        switch (taskType) {
        case "[T]":
            System.out.println();
            break;
        case "[D]":
            Deadline currentDeadline = (Deadline)currentTask;
            String due = currentDeadline.getDueInfo();
            System.out.print("(by: " + due + " )");
            System.out.println();
            break;
        case "[E]":
            Event currentEvent = (Event)currentTask;
            String eventStart = currentEvent.getEventDateAndStartTime();
            String eventEnd = currentEvent.getEndTime();
            System.out.print("from: " + eventStart + " to: " + eventEnd + " )");
            System.out.println();
            break;
        default:
            System.out.println("Unknown event type error");
        }
    }


    public static void horizontalLine() {
        System.out.println("________________________________________");
    }
}
