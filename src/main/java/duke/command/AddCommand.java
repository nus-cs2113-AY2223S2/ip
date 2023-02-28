package duke.command;

import duke.exceptions.EmptyTaskException;
import duke.tasklist.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;
import duke.ui.Ui;


/**
 * Represents the add feature in Duke. A task object corresponding to the type
 * specified by the user will be added to the task list.
 * e.g., <code>event attend concert /from Sunday 7pm /to Sunday 10pm </code>
 */
public class AddCommand {

    /**
     * Adds todo task into task list.
     *
     * @param tasks Task list containing all saved task
     * @param name  Name of task
     * @throws EmptyTaskException
     */
    public static void addTodo(TaskList tasks, String name) throws EmptyTaskException {
        try{
            if(name.isEmpty()){
                throw new EmptyTaskException();
            }
            tasks.addTask(new Todo(name, false));
            Ui.printBorder();
            System.out.println("added: " + name + "\n");
            Ui.printBorder();
        } catch (NullPointerException e) {
            Ui.printBorder();
            System.out.println("OOPS! The description of task cannot be empty");
            Ui.printBorder();
        }
    }

    /**
     * Adds deadline task into task list.
     *
     * @param tasks        Task list containing all saved task
     * @param taskDetails  Details of the task, including task name
     *                     and deadline
     */
    public static void addDeadline(TaskList tasks, String taskDetails){
        try {
            String[] taskInfo = taskDetails.split("/by", 2);
            String name = taskInfo[0];
            String deadline = taskInfo[1];
            tasks.addTask(new Deadline(name, deadline, false));
            Ui.printBorder();
            System.out.println("added: " + name + " (by: " + deadline + ")" + "\n");
            Ui.printBorder();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            Ui.printBorder();
            System.out.println("OOPS! The description of task is invalid");
            Ui.printBorder();
        }
    }

    /**
     * Adds event task into task list.
     *
     * @param tasks        Task list containing all saved task
     * @param taskDetails  Details of the event, including event name,
     *                     start date and end date
     */
    public static void addEvent(TaskList tasks, String taskDetails){
        try{
            String[] taskInfo = taskDetails.split("/from|/to", 3);
            String name = taskInfo[0];
            String start = taskInfo[1];
            String end = taskInfo[2];
            tasks.addTask(new Event(name, start, end, false));
            Ui.printBorder();
            System.out.println("added: " + name + " (from: " + start + ", to: " + end + ")" + "\n");
            Ui.printBorder();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            Ui.printBorder();
            System.out.println("OOPS! The description of task is invalid");
            Ui.printBorder();
        }
    }
}
