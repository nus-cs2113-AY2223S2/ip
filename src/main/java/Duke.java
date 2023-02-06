import java.util.Scanner;

public class Duke {
    public static final int ARRAY_LENGTH = 100;

    public static void main(String[] args) {
        greeting();
        Task[] tasks = new Task[ARRAY_LENGTH];
        Scanner in = new Scanner(System.in);

        boolean isExit = false;
        while (!isExit) {
            String messageFromUser;
            messageFromUser = in.nextLine();

            if (hasTaskKeyword(messageFromUser)) {
                Task newTask = handleTaskCreation(messageFromUser);
                addToList(newTask, tasks);
            } else if (messageFromUser.startsWith("mark") || messageFromUser.startsWith("unmark")) {
                changeTaskStatus(messageFromUser, tasks);
            } else if (messageFromUser.equals("list")) {
                displayList(tasks);
            } else if (messageFromUser.equals("bye")) {
                exitGreeting();
                isExit = true;
            } else {
                System.out.println("Invalid instruction. Please try again.");
                horizontalLine();
            }
        }
    }

    public static boolean hasTaskKeyword(String messageFromUser) {
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

    public static void addToList(Task newTask, Task[] tasks) {
        horizontalLine();
        System.out.println("Sure, I've added this task: ");
        displayTask(newTask);
        int currentIndexInTaskStorage = Task.getNumberOfTasks();
        tasks[currentIndexInTaskStorage] = newTask;
        printNumberOfTasks(currentIndexInTaskStorage);
        horizontalLine();
    }

    public static void changeTaskStatus(String sentence, Task[] tasks) {
        String[] words = sentence.split(" ");
        int taskNumber = Integer.parseInt(words[1]);
        Task currentTask = tasks[taskNumber];
        if (words[0].trim().equals("mark")) {
            currentTask.markAsDone();
        } else {
            currentTask.markAsUndone();
        }
        horizontalLine();
    }

    public static void displayList(Task[] taskStorage) {
        int totalNumberOfTasks = Task.getNumberOfTasks();
        if (totalNumberOfTasks > 0) {
            System.out.println("Here are the tasks in your list: ");
            for (int index = 1; index <= totalNumberOfTasks; index += 1) {
                Task currentTask = taskStorage[index];
                System.out.print(index + ". ");
                displayTask(currentTask);
            }
        } else {
            System.out.println("There are no tasks to display.");
        }
        horizontalLine();
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
            Deadline currentDeadline = (Deadline) currentTask;
            String due = currentDeadline.getDueInfo();
            System.out.print("(by:" + due + ")");
            System.out.println();
            break;
        case "[E]":
            Event currentEvent = (Event) currentTask;
            String eventStart = currentEvent.getEventStartInfo();
            String eventEnd = currentEvent.getEventEndInfo();
            System.out.print("(from:" + eventStart + "to:" + eventEnd + ")");
            System.out.println();
            break;
        default:
            System.out.println("Unknown event type error");
        }
    }

    public static void printNumberOfTasks(int currentIndex) {
        if (currentIndex == 1) {
            System.out.println("Your first task!");
        } else {
            System.out.println("You currently have " + currentIndex + " tasks in the list.");
        }
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

    public static void horizontalLine() {
        System.out.println("________________________________________");
    }
}
