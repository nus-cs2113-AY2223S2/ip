package duke.boundary;

import duke.controller.TaskList;
import duke.exception.InvalidCommandException;
import duke.model.*;

public class Ui {
    static final String TASK_ADDED_PREFIX = "Got it. I've added this task:" + System.lineSeparator() + "\t";
    static final String TASK_DELETED_PREFIX = "Noted! I've removed this task" + System.lineSeparator() + "\t";
    static final String TASK_MARKED_PREFIX = "Nice! I've marked this task as done:" + System.lineSeparator() + "\t";
    static final String TASK_UNMARKED_PREFIX = "Ok, I've marked this task as not done:" + System.lineSeparator() + "\t";

    protected TaskList taskList;
    protected String outputMessage;

    public Ui(TaskList taskList) {
        setTaskList(taskList);
    }

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
                    task = taskList.handleMarkTask(command);
                    this.printMarkMessage(task);
                    break;
                case "unmark":
                    task = taskList.handleUnmarkTask(command);
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

    public void printMarkMessage(Task task) {
        this.printMessage(TASK_MARKED_PREFIX + task.toString());
    }

    public void printUnmarkMessage(Task task) {
        this.printMessage(TASK_UNMARKED_PREFIX + task.toString());
    }

    public void printAddMessage(Task task) {
        this.printMessage(TASK_ADDED_PREFIX + task.toString());
    }

    public void printDeleteMessage(Task task) {
        this.printMessage(TASK_DELETED_PREFIX + task.toString());
    }

    public void printHorizontalLine() {
        System.out.println("\t____________________________________________________________");
    }

    public void printMessage(String message) {
        this.printHorizontalLine();
        System.out.println("\t" + message);
        this.printHorizontalLine();
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

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

    public void printTaskList() {
        this.printHorizontalLine();
        System.out.println("\t" + taskList.toString());
        this.printHorizontalLine();
    }


}
