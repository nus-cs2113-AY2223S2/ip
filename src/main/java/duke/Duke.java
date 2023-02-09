package duke;

import java.util.Scanner;
public class Duke {
    private static final int LIST_SIZE = 100;
    private static int taskNum = 0;
    public static void main(String[] args) {
        showGreetings();

        String command = "";
        Scanner in = new Scanner(System.in);
        Task[] storedTasks = new Task[LIST_SIZE];         // array of stored tasks

        while (!command.equals("bye")) {
            printLineBreak();
            command = in.nextLine();
            String[] commandLine = command.split(" ", 2);
            printLineBreak();
            doCommand(storedTasks, commandLine);

        }



    }

    private static void doCommand(Task[] storedTasks, String[] commandLine) {
        switch (commandLine[0]) {
        case "todo":
            addTodoTask(storedTasks, commandLine);
            break;
        case "deadline":
            addDeadlineTask(storedTasks, commandLine);
            break;
        case "event":
            addEventTask(storedTasks, commandLine);
            break;
        case "mark":
            markTask(storedTasks, commandLine);
            break;
        case "unmark":
            unmarkTask(storedTasks, commandLine);
            break;
        case "list":
            listTasks(storedTasks);
            break;
        case "bye":
            System.out.println("Bye. Hope to see you again soon!");
            break;
        default:
            try {
                throwErrorInput();
            } catch (IllegalDukeArgumentException e) {
                System.out.println("Oh no! I don't understand what you are saying.");
            }
        }
    }

    private static void throwErrorInput() throws IllegalDukeArgumentException {
        throw new IllegalDukeArgumentException();
    }

    private static void listTasks(Task[] storedTasks) {
        listMessage();
        for (int i = 0; i < taskNum; i++) {
            System.out.println((i + 1) + ". " + storedTasks[i].getTypeIcon() +
                    storedTasks[i].getStatusIcon() + " " + storedTasks[i].getDescription());
        }
    }

    private static void unmarkTask(Task[] storedTasks, String[] commandLine) {
        int unmarkIndex = Integer.parseInt(commandLine[1]) - 1;
        storedTasks[unmarkIndex].setDone(false);
        System.out.println("OK, I've marked this task as not done yet.");
        System.out.println(storedTasks[unmarkIndex].getTypeIcon() +
                storedTasks[unmarkIndex].getStatusIcon() + " " + storedTasks[unmarkIndex].getDescription());
    }

    private static void markTask(Task[] storedTasks, String[] commandLine) {
        int taskIndex = Integer.parseInt(commandLine[1]) - 1;
        storedTasks[taskIndex].setDone(true);
        System.out.println("Nice! I've marked this task as done.");
        System.out.println(storedTasks[taskIndex].getTypeIcon() +
                storedTasks[taskIndex].getStatusIcon() + " " + storedTasks[taskIndex].getDescription());
    }

    private static void addEventTask(Task[] storedTasks, String[] commandLine) {
        String[] eventString = commandLine[1].split("/from");
        String[] eventStartEnd = eventString[1].split("/to");
        Event ev = new Event(eventString[0], eventStartEnd[0], eventStartEnd[1]);
        storedTasks[taskNum] = ev;
        addTaskMessage();
        System.out.println("  " + ev.getTypeIcon() +
                ev.getStatusIcon() + " " + ev.getDescription() + "(from: " + ev.getStart() + " to: " + ev.getEnd() +")");
        taskNum++;
        displayTasksNum();
    }

    private static void addTodoTask(Task[] storedTasks, String[] commandLine) {
        try {
            validateTodo(commandLine);
        } catch (IllegalDukeArgumentException e) {
            System.out.println("Oh no! The description of a todo cannot be empty.");
            return;
        }
        Todo td = new Todo(commandLine[1]);
        storedTasks[taskNum] = td;
        addTaskMessage();
        System.out.println("  " + td.getTypeIcon() + td.getStatusIcon() + " " + td.getDescription());
        taskNum++;
        displayTasksNum();
    }

    private static void validateTodo(String[] commandLine) throws IllegalDukeArgumentException{
        if (commandLine.length == 1) {
            throw new IllegalDukeArgumentException();
        }
    }

    private static void addDeadlineTask(Task[] storedTasks, String[] commandLine) {
        String[] deadlineString = commandLine[1].split("/by");
        Deadline dl = new Deadline(deadlineString[0], deadlineString[1]);
        storedTasks[taskNum] = dl;
        addTaskMessage();
        System.out.println("  " + storedTasks[taskNum].getTypeIcon() +
                dl.getStatusIcon() + " " + dl.getDescription() + "(by: " + dl.getBy() + ")");
        taskNum++;
        displayTasksNum();
    }

    private static void showGreetings() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }
    private static void printLineBreak() {
        System.out.println("--------------------------------------------------------------");
    }

    private static void addTaskMessage() {
        System.out.println("Got it. I've added this task.");
    }
    private static void listMessage() {
        System.out.println("Here are the tasks in your list:");
    }

    private static void displayTasksNum() {
        System.out.println("Now you have " + taskNum + " task(s) in the list.");
    }
}
