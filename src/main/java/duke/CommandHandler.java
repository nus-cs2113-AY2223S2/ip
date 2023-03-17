package duke;

import java.io.IOException;

public class CommandHandler {
    private final static int EXPECTED_EVENT_COMMAND_SIZE = 5;
    /**
     * list all the tasks stored in the taskList.
     */
    public static void listTasks() {
        Duke.ui.printListMessage();
        int count = 1;
        for (Task i : Duke.taskList.getStoredTasks()) {
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
            Duke.storage.updateFile(Duke.taskList.getStoredTasks());
        } catch (IOException e) {
            System.out.println("Oops! Something broke: " + e);
        }
        Duke.taskList.decrementTaskNum();
    }
    /**
     * Processes delete of tak
     * @param commandLine commands the user has inputted
     */
    private static void processDelete(String[] commandLine) {
        int delIndex = Integer.parseInt((commandLine[1])) - 1;
        System.out.println("Noted. I've removed this task:");
        printMarkChange(delIndex);
        Duke.taskList.removeItem(delIndex);
    }
    /**
     * marks task in taskList.
     * @param commandLine commands the user has inputted
     */
    public static void markTask(String[] commandLine) {
        int taskIndex = Integer.parseInt(commandLine[1]) - 1;
        Duke.taskList.getStoredTasks().get(taskIndex).setDone(true);
        System.out.println("Nice! I've marked this task as done.");
        printMarkChange(taskIndex);
        try{
            Duke.storage.updateFile(Duke.taskList.getStoredTasks());
        } catch (IOException e) {
            System.out.println("Oops! Something broke: " + e);
        }
    }
    private static void printMarkChange(int taskIndex) {
        System.out.print(Duke.taskList.getStoredTasks().get(taskIndex).getTypeIcon() +
                Duke.taskList.getStoredTasks().get(taskIndex).getStatusIcon() + " " + Duke.taskList.getStoredTasks().get(taskIndex).getDescription());
        if (Duke.taskList.getStoredTasks().get(taskIndex) instanceof Deadline) {
            System.out.println("/by: " + Duke.taskList.getStoredTasks().get(taskIndex).getInfo());
        }
        if (Duke.taskList.getStoredTasks().get(taskIndex) instanceof Event) {
            System.out.println("/start, end: " + Duke.taskList.getStoredTasks().get(taskIndex).getInfo());
        }
        else {
            System.out.println();
        }
    }
    /**
     * un-marks task in taskList.
     * @param commandLine commands the user has inputted
     */
    public static void unmarkTask(String[] commandLine) {
        int unmarkIndex = Integer.parseInt(commandLine[1]) - 1;
        Duke.taskList.getStoredTasks().get(unmarkIndex).setDone(false);
        System.out.println("OK, I've marked this task as not done yet.");
        printMarkChange(unmarkIndex);
        try{
            Duke.storage.updateFile(Duke.taskList.getStoredTasks());
        } catch (IOException e) {
            System.out.println("Oops! Something broke: " + e);
        }
    }
    /**
     * Prints output with information based on task
     * @param count integer counting number of tasks
     * @param task task to display
     */
    static void printTaskBasedOnType(int count, Task task) {
        if (task instanceof Deadline) {
            System.out.println(count + "." + task.getTypeIcon() + task.getStatusIcon() + " " + task.getDescription() + "/by:" + task.getInfo());
        } else if (task instanceof Event) {
            System.out.println(count + "." + task.getTypeIcon() + task.getStatusIcon() + " " + task.getDescription() + "/start, end:" + task.getInfo());
        } else {
            System.out.println(count + "." + task.getTypeIcon() + task.getStatusIcon() + " " + task.getDescription() + task.getInfo());
        }
    }
    /**
     * finds tasks from taskList with matching keyword
     * @param commandLine commands the user has inputted
     */
    public static void findItem(String[] commandLine) {
        String keyword = commandLine[1];
        int count = 1;
        System.out.println("Here are the matching tasks in your list: ");
        for (Task i : Duke.taskList.getStoredTasks()) {
            if (i.getDescription().contains(keyword)) {
                printTaskBasedOnType(count, i);
                count++;
            }
        }
    }
    /**
     * adds event type task to taskList.
     * @param commandLine commands the user has inputted
     */
    public static void addEventTask(String[] commandLine) {
        processEvent(commandLine);
        try{
            Duke.storage.updateFile(Duke.taskList.getStoredTasks());
        } catch (IOException e) {
            System.out.println("Oops! Something broke: " + e);
        }
    }
    /**
     * processes event command.
     * @param commandLine commands the user has inputtted
     */
    private static void processEvent(String[] commandLine) {
        try {
            String[] commandPart = commandLine[1].split(" ");
            if (commandPart.length < EXPECTED_EVENT_COMMAND_SIZE || !commandLine[1].contains("/from")|| !commandLine[1].contains("/to")) {
                throw new IllegalEventLengthException();
            }
        } catch (IllegalEventLengthException e) {
            return;
        }
        String[] eventString = commandLine[1].split("/from");
        String[] eventStartEnd = eventString[1].split("/to");
        Event ev = new Event(eventString[0], eventStartEnd[0], eventStartEnd[1]);
        Duke.taskList.addItem(ev);
        Duke.ui.printAddTaskMessage();
        System.out.println("  " + ev.getTypeIcon() +
                ev.getStatusIcon() + " " + ev.getDescription()
                + "(from: " + ev.getStart() + " to: " + ev.getEnd() +")");
        Duke.taskList.incrementTaskNum();
        displayTasksNum();
    }
    /**
     * adds deadline type task to taskList.
     * @param commandLine commands the user has inputted
     */
    public static void addDeadlineTask(String[] commandLine) {
        processDeadline(commandLine);
        displayTasksNum();
        try{
            Duke.storage.updateFile(Duke.taskList.getStoredTasks());
        } catch (IOException e) {
            System.out.println("Oops! Something broke: " + e);
        }
    }
    /**
     * Processes deadline command.
     * @param commandLine commands the user has inputted
     */
    private static void processDeadline(String[] commandLine) {
        String[] deadlineString = commandLine[1].split("/by");
        Deadline dl = new Deadline(deadlineString[0], deadlineString[1]);
        Duke.taskList.addItem(dl);
        Duke.ui.printAddTaskMessage();
        System.out.println("  " + dl.getTypeIcon() +
                dl.getStatusIcon() + " " + dl.getDescription() + "(by: " + dl.getBy() + ")");
        Duke.taskList.incrementTaskNum();
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
        Duke.taskList.addItem(td);
        Duke.ui.printAddTaskMessage();
        System.out.println("  " + td.getTypeIcon() + td.getStatusIcon() + " " + td.getDescription());
        Duke.taskList.incrementTaskNum();
        displayTasksNum();
        try {
            Duke.storage.updateFile(Duke.taskList.getStoredTasks());
        } catch (IOException e) {
            System.out.println("Oops! Something broke: " + e);
        }
    }
    /**
     * checks if Todo input statement is valid
     * @param commandLine the commands the user has inputted
     * @throws IllegalDukeArgumentException
     */
    private static void validateTodo(String[] commandLine) throws IllegalDukeArgumentException {
        if (commandLine.length == 1) {
            throw new IllegalDukeArgumentException();
        }
    }
    /**
     * displays number of tasks
     */
    private static void displayTasksNum() {
        Duke.ui.printTaskNum(Duke.taskList.getTaskNum());
    }
}
