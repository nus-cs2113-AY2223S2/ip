package duke;

/**
 * Duke is a task recording robot with different functions.
 * Functions include: delete, mark, unmark, deadline, todo, event, find
 */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    static Parser parser;
    private static UI ui;
    private static Storage storage;
    static TaskList taskList;
    private final static int EXPECTED_EVENT_COMMAND_SIZE = 5;
    public static void main(String[] args) throws IOException{
        storage = new Storage();
        ui = new UI();
        parser = new Parser();
        taskList = new TaskList();
        String command = "";
        Scanner in = new Scanner(System.in);
        loadFile();
        ui.showGreetings();
        while (!command.equals("bye")) {
            ui.printLinebreak();
            command = in.nextLine();
            String[] commandLine = parser.parseCommand(command);
            ui.printLinebreak();
            doCommand(commandLine);
        }
    }

    /**
     * Loads file and parses file contents.
     * @throws IOException
     */
    private static void loadFile() throws IOException {
        File file = storage.createFile();
        Scanner fileReader = new Scanner(file);
        while (fileReader.hasNext()) {
            Parser.parseFile(fileReader);
            taskList.incrementTaskNum();
        }
    }

    /**
     * Handles command input by user.
     * @param commandLine the commands the user has inputted
     */
    private static void doCommand(String[] commandLine) {
        switch (commandLine[0]) {
        case "todo":
            addTodoTask(commandLine);
            break;
        case "deadline":
            addDeadlineTask(commandLine);
            break;
        case "event":
            addEventTask(commandLine);
            break;
        case "mark":
            markTask(commandLine);
            break;
        case "unmark":
            unmarkTask(commandLine);
            break;
        case "list":
            listTasks();
            break;
        case "delete":
            deleteTask(commandLine);
            break;
        case "find":
            findItem(commandLine);
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

    /**
     * list all the tasks stored in the taskList.
     */
    public static void listTasks() {
        ui.printListMessage();
        int count = 1;
        for (Task i : taskList.getStoredTasks()) {
            printTaskBasedOnType(count, i);
            count++;
        }
    }

    /**
     * deletes task from taskList.
     * @param commandLine commands the user has inputted
     */
    public static void deleteTask(String[] commandLine) {
        processDelete(commandLine);
        try{
            storage.updateFile(taskList.getStoredTasks());
        } catch (IOException e) {
            System.out.println("Oops! Something broke: " + e);
        }
        taskList.decrementTaskNum();
    }

    private static void processDelete(String[] commandLine) {
        int delIndex = Integer.parseInt((commandLine[1])) - 1;
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskList.getStoredTasks().get(delIndex).getTypeIcon()
                    + taskList.getStoredTasks().get(delIndex).getStatusIcon()
                    + " " + taskList.getStoredTasks().get(delIndex).getDescription());
        taskList.removeItem(delIndex);
    }

    /**
     * un-marks task in taskList.
     * @param commandLine commands the user has inputted
     */
    public static void unmarkTask(String[] commandLine) {
        int unmarkIndex = Integer.parseInt(commandLine[1]) - 1;
        taskList.getStoredTasks().get(unmarkIndex).setDone(false);
        System.out.println("OK, I've marked this task as not done yet.");
        System.out.println(taskList.getStoredTasks().get(unmarkIndex).getTypeIcon() +
                    taskList.getStoredTasks().get(unmarkIndex).getStatusIcon()
                    + " " + taskList.getStoredTasks().get(unmarkIndex).getDescription());
        try{
            storage.updateFile(taskList.getStoredTasks());
        } catch (IOException e) {
            System.out.println("Oops! Something broke: " + e);
        }
    }
    /**
     * marks task in taskList.
     * @param commandLine commands the user has inputted
     */
    public static void markTask(String[] commandLine) {
        int taskIndex = Integer.parseInt(commandLine[1]) - 1;
        taskList.getStoredTasks().get(taskIndex).setDone(true);
        System.out.println("Nice! I've marked this task as done.");
        System.out.println(taskList.getStoredTasks().get(taskIndex).getTypeIcon() +
                taskList.getStoredTasks().get(taskIndex).getStatusIcon() + " " + taskList.getStoredTasks().get(taskIndex).getDescription());

        try{
            storage.updateFile(taskList.getStoredTasks());
        } catch (IOException e) {
            System.out.println("Oops! Something broke: " + e);
        }

    }
    /**
     * adds event type task to taskList.
     * @param commandLine commands the user has inputted
     */
    public static void addEventTask(String[] commandLine) {
        processEvent(commandLine);
        try{
            storage.updateFile(taskList.getStoredTasks());
        } catch (IOException e) {
            System.out.println("Oops! Something broke: " + e);
        }
    }

    private static void processEvent(String[] commandLine) {
        try {
            String[] commandPart = commandLine[1].split(" ");
            if (commandPart.length < EXPECTED_EVENT_COMMAND_SIZE) {
                System.out.println("Length of your command is too short! Length: " + commandPart.length);
                throw new IllegalEventLengthException();
            }
        } catch (IllegalEventLengthException e) {
            return;
        }
        String[] eventString = commandLine[1].split("/from");
        String[] eventStartEnd = eventString[1].split("/to");
        Event ev = new Event(eventString[0], eventStartEnd[0], eventStartEnd[1]);
        taskList.addItem(ev);
        ui.printAddTaskMessage();
        System.out.println("  " + ev.getTypeIcon() +
                ev.getStatusIcon() + " " + ev.getDescription() + "(from: " + ev.getStart() + " to: " + ev.getEnd() +")");
        taskList.incrementTaskNum();
        displayTasksNum();
    }

    /**
     * adds Todo type task to taskList.
     * @param commandLine commands the user has inputted
     */
    public static void addTodoTask(String[] commandLine) {
        try {
            validateTodo(commandLine);
        } catch (IllegalDukeArgumentException e) {
            return;
        }
        Todo td = new Todo(commandLine[1]);
        taskList.addItem(td);
        ui.printAddTaskMessage();
        System.out.println("  " + td.getTypeIcon() + td.getStatusIcon() + " " + td.getDescription());
        taskList.incrementTaskNum();
        displayTasksNum();
        try {
            storage.updateFile(taskList.getStoredTasks());
        } catch (IOException e) {
            System.out.println("Oops! Something broke: " + e);
        }
    }

    /**
     * checks if Todo input statement is valid
     * @param commandLine the commands the user has inputted
     * @throws IllegalDukeArgumentException
     */
    private static void validateTodo(String[] commandLine) throws IllegalDukeArgumentException{
        if (commandLine.length == 1) {
            throw new IllegalDukeArgumentException();
        }
    }
    /**
     * adds deadline type task to taskList.
     * @param commandLine commands the user has inputted
     */
    public static void addDeadlineTask(String[] commandLine) {
        processDeadline(commandLine);
        displayTasksNum();
        try{
            storage.updateFile(taskList.getStoredTasks());
        } catch (IOException e) {
            System.out.println("Oops! Something broke: " + e);
        }
    }

    private static void processDeadline(String[] commandLine) {
        String[] deadlineString = commandLine[1].split("/by");
        Deadline dl = new Deadline(deadlineString[0], deadlineString[1]);
        taskList.addItem(dl);
        ui.printAddTaskMessage();
        System.out.println("  " + dl.getTypeIcon() +
                dl.getStatusIcon() + " " + dl.getDescription() + "(by: " + dl.getBy() + ")");
        taskList.incrementTaskNum();
    }

    /**
     * finds tasks from taskList with matching keyword
     * @param commandLine commands the user has inputted
     */
    public static void findItem(String[] commandLine) {
        String keyword = commandLine[1];
        int count = 1;
        System.out.println("Here are the matching tasks in your list: ");
        for (Task i : taskList.getStoredTasks()) {
            if (i.getDescription().contains(keyword)) {
                printTaskBasedOnType(count, i);
                count++;
            }
        }
    }

    /**
     * Prints output with information based on task
     * @param count integer counting number of tasks
     * @param task task to display
     */
    private static void printTaskBasedOnType(int count, Task task) {
        if (task instanceof Deadline) {
            System.out.println(count + "." + task.getTypeIcon() + task.getStatusIcon() + task.getDescription() + "/by: " + task.getInfo());
        } else if (task instanceof Event) {
            System.out.println(count + "." + task.getTypeIcon() + task.getStatusIcon() + task.getDescription() + "/start, end: " + task.getInfo());
        } else {
            System.out.println(count + "." + task.getTypeIcon() + task.getStatusIcon() + task.getDescription() + task.getInfo());
        }
    }

    private static void displayTasksNum() {
        ui.printTaskNum(taskList.getTaskNum());
    }
}
