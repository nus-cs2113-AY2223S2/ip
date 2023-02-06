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
            } else {
                addToList(messageFromUser, taskStorage);
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
                System.out.println(i + "." + currentTask.getStatus() + currentTask.getTaskInfo());
            }
        }
        horizontalLine();
    }

    public static void addToList(String messageFromUser, Task[] taskStorage) {
        horizontalLine();
        //Task newTask = new Task(messageFromUser);
        Task newTask = createDeadline(messageFromUser); // to test
        System.out.println("added: " + messageFromUser);
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


    public static void horizontalLine() {
        System.out.println("________________________________________");
    }
}
