package sage.utility;

import java.util.ArrayList;

import sage.tasktypes.Deadline;
import sage.tasktypes.Event;
import sage.tasktypes.Todo;
import sage.tasktypes.Task;

public class Display {
    private static final String LINE =
            "█████╗█████╗█████╗█████╗█████╗█████╗█████╗\n" +
                    "╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝";
    private static final String LOGO = "░██████╗░█████╗░░██████╗░███████╗\n" +
            "██╔════╝██╔══██╗██╔════╝░██╔════╝\n" +
            "╚█████╗░███████║██║░░██╗░█████╗░░\n" +
            "░╚═══██╗██╔══██║██║░░╚██╗██╔══╝░░\n" +
            "██████╔╝██║░░██║╚██████╔╝███████╗\n" +
            "╚═════╝░╚═╝░░╚═╝░╚═════╝░╚══════╝";

    public String printLine() {
        return LINE;
    }

    public void printWelcomeUser() {
        String welcome = "Hello! I'm SAGE, the knowledgeable one\n"
                + "What can I do for you today?\n";
        System.out.println(LOGO);
        System.out.println(welcome);
    }

    public void printGoodByeUser() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * This method prints a message to the console indicating the addition of a TODO task to the task list and its details.
     *
     * @param taskDescription The description of the task.
     * @param taskCount       The number of tasks in list.
     */
    public void printAddedTask(String taskDescription, int taskCount) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("[T][ ] " + taskDescription);
        System.out.println("Now you have " + taskCount + " tasks in the list");
        System.out.println(LINE);
    }

    /**
     * This method prints a message to the console indicating the addition of a DEADLINE task to the task list and its details.
     *
     * @param taskDescription The description of the task.
     * @param by              The deadline date/time of the task
     * @param taskCount       The number of tasks in list.
     */
    public void printAddedTask(String taskDescription, String by, int taskCount) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("[D][ ] " + taskDescription + "(by: "
                + by + ")");
        System.out.println("Now you have " + taskCount + " tasks in the list");
        System.out.println(LINE);
    }

    /**
     * This method prints a message to the console indicating the addition of an EVENT task to the task list and its details.
     *
     * @param taskDescription The description of the task.
     * @param from            The starting date/time of the task
     * @param to              The ending date/time of the task
     * @param taskCount       The number of tasks in list.
     */
    public void printAddedTask(String taskDescription, String from, String to, int taskCount) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("[E][ ] " + taskDescription + "(from: "
                + from + " to: " + to + ")");
        System.out.println("Now you have " + taskCount + " tasks in the list");
        System.out.println(LINE);
    }

    public void printTaskNotFound() {
        System.out.println("Uh-oh! Task Not Found!");
    }

    public void printUnknownInput() {
        System.out.println("I don't understand what you mean, please try again!");
    }


    /**
     * This method is used to display a marked task as not done in the task list.
     *
     * @param list       the list of tasks
     * @param taskNumber the taskNumber index that is to be unmarked
     */
    public void printValidUnmark(ArrayList<Task> list, int taskNumber) {

        Task taskObj = list.get(taskNumber - 1);
        System.out.println("OK, I've marked this task as not done yet:");
        if (taskObj instanceof Deadline) {
            System.out.println("[E][ ] " + taskObj.getTaskName() + "(by: " +
                    ((Deadline) taskObj).getByWhen() + ") ");
        } else if (taskObj instanceof Event) {
            Event event = (Event) taskObj;
            System.out.println("[E][ ] " + event.getTaskName() + "(from: "
                    + event.getStartWhen() + " to: " + event.getEndWhen() + ")");
        } else {
            Todo todo = (Todo) taskObj;
            System.out.println("[T][ ] " + todo.getTaskName());
        }
    }

    /**
     * This method is used to display an unmarked task as done in the task list.
     *
     * @param list       the list of tasks
     * @param taskNumber the taskNumber index that is to be marked
     */
    public void printValidMark(ArrayList<Task> list, int taskNumber) {
        Task taskObj = list.get(taskNumber - 1);
        System.out.println("Nice! I've marked this task as done:");
        if (taskObj instanceof Deadline) {
            System.out.println("[E][X] " + taskObj.getTaskName() + "(by: " +
                    ((Deadline) taskObj).getByWhen() + ") ");
        } else if (taskObj instanceof Event) {
            Event event = (Event) taskObj;
            System.out.println("[E][X] " + event.getTaskName() + "(from: "
                    + event.getStartWhen() + " to: " + event.getEndWhen() + ")");
        } else {
            Todo todo = (Todo) taskObj;
            System.out.println("[T][X] " + todo.getTaskName());
        }
    }

    public void printDeletedTask(ArrayList<Task> list, int taskNumber) {
        Task taskObj = list.get(taskNumber - 1);
        System.out.println("Noted. I've removed this task:");
        if (taskObj instanceof Deadline) {
            System.out.println("[E][" + taskObj.printStatusSymbol() + "] " + taskObj.getTaskName() + "(by: " +
                    ((Deadline) taskObj).getByWhen() + ") ");
        } else if (taskObj instanceof Event) {
            Event event = (Event) taskObj;
            System.out.println("[E][" + taskObj.printStatusSymbol() + "] " + event.getTaskName() + "(from: "
                    + event.getStartWhen() + " to: " + event.getEndWhen() + ")");
        } else {
            Todo todo = (Todo) taskObj;
            System.out.println("[T][" + taskObj.printStatusSymbol() + "] " + todo.getTaskName());
        }
        System.out.println("Now you have " + String.valueOf(list.size() - 1) + " tasks in the list.");
    }

}
