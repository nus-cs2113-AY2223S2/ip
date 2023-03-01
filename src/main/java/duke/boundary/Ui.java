package duke.boundary;

import duke.controller.TaskList;
import duke.exception.InvalidCommandException;
import duke.model.*;

/**
 * Class that is mainly responsible to display an information to the user
 */
public class Ui {
    /**
     * Prefix used when the task is added
     */
    static final String TASK_ADDED_PREFIX = "Got it. I've added this task:" + System.lineSeparator() + "\t";
    /**
     * Prefix used when the task is deleted
     */
    static final String TASK_DELETED_PREFIX = "Noted! I've removed this task" + System.lineSeparator() + "\t";
    /**
     * Prefix used when the task is marked
     */
    static final String TASK_MARKED_PREFIX = "Nice! I've marked this task as done:" + System.lineSeparator() + "\t";
    /**
     * Prefix used when the task is unmarked
     */
    static final String TASK_UNMARKED_PREFIX = "Ok, I've marked this task as not done:" + System.lineSeparator() + "\t";

    /**
     * TaskList attached to this Ui object
     */
    protected TaskList taskList;

    /**
     * Constructor that initiates an Ui object
     *
     * @param taskList the TaskList object attached to this Ui object
     */
    public Ui(TaskList taskList) {
        setTaskList(taskList);
    }

    /**
     * A method that classifies different cases of the command
     *
     * @param command The command object represents tokenized user command
     */
    public void readCommand(Command command) {
        Payload payload = command.getPayload();
        String[] payloadData = payload.getData();
        Task task;
        Event event;
        Deadline deadline;
        try {
            switch (command.getType()) {
                case "list":
                    this.printTaskList();
                    break;
                case "mark":
                    task = taskList.handleMarkTask(payloadData);
                    this.printMarkMessage(task);
                    break;
                case "unmark":
                    task = taskList.handleUnmarkTask(payloadData);
                    this.printUnmarkMessage(task);
                    break;
                case "task":
                    task = taskList.handleAddTask(payloadData);
                    this.printAddMessage(task);
                    break;
                case "todo":
                    ToDo todo = taskList.handleAddTodo(payloadData);
                    this.printAddMessage(todo);
                    break;
                case "deadline":
                    deadline = taskList.handleAddDeadline(payloadData);
                    this.printAddMessage(deadline);
                    break;
                case "event":
                    event = taskList.handleAddEvent(payloadData);
                    this.printAddMessage(event);
                    break;
                case "delete":
                    task = taskList.handleDeleteTask(payloadData);
                    this.printDeleteMessage(task);
                    break;
                case "bye":
                    this.printMessage("Bye. Hope to see you again soon!");
                    System.exit(0);
                default:
                    throw new InvalidCommandException();
            }
        } catch (InvalidCommandException e) {
            this.printMessage(e.getMessage());
        }
    }

    /**
     * Method to print message after marking a task
     *
     * @param task Task object to be marked
     */
    public void printMarkMessage(Task task) {
        this.printMessage(TASK_MARKED_PREFIX + task.toString());
    }

    /**
     * Method to print message after unmarking a task
     *
     * @param task Task object to be unmarked
     */
    public void printUnmarkMessage(Task task) {
        this.printMessage(TASK_UNMARKED_PREFIX + task.toString());
    }

    /**
     * Method to print a message after adding a task
     *
     * @param task Task object to be added
     */
    public void printAddMessage(Task task) {
        this.printMessage(TASK_ADDED_PREFIX + task.toString());
    }

    /**
     * Method to print a message after deleting a task
     *
     * @param task Task object to be deleted
     */
    public void printDeleteMessage(Task task) {
        this.printMessage(TASK_DELETED_PREFIX + task.toString());
    }

    /**
     * Method to print a horizontla line
     */
    public void printHorizontalLine() {
        System.out.println("\t____________________________________________________________");
    }

    /**
     * Method to print a formatted message
     *
     * @param message The message to be formatted
     */
    public void printMessage(String message) {
        this.printHorizontalLine();
        System.out.println("\t" + message);
        this.printHorizontalLine();
    }

    /**
     * Method that set the taskList attached to this Ui object
     *
     * @param taskList The attached taskList object
     */
    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Method to pritn a welcome message of Duke
     */
    public void printDuke() {
        String logo = " ____        _" + System.lineSeparator()
                + "|  _ \\ _   _| | _____" + System.lineSeparator()
                + "| | | | | | | |/ / _ \\" + System.lineSeparator()
                + "| |_| | |_| |   <  __/" + System.lineSeparator()
                + "|____/ \\__,_|_|\\_\\___|" + System.lineSeparator();
        System.out.println("____________________________________________________________" + System.lineSeparator()
                + logo
                + "Hello! I'm duke.Duke!" + System.lineSeparator()
                + "What I can do for you?" + System.lineSeparator()
                + "____________________________________________________________" + System.lineSeparator()
        );
    }

    /**
     * Method that prints the TaskList object representation
     */
    public void printTaskList() {
        this.printMessage(taskList.toString());
    }


}
