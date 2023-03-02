package AllCommands;

import BasisSupport.Spliterator;
import BasisSupport.Status;
import BasisSupport.TransformString;
import Support.Task;
import Support.TaskList;

// Related method (Totally three methods: todo, deadline, event)
public class AddCommand extends Commands {
    // The line String is to accept the input command and description from the user
    // The same for all the other command classes
    public static String line;

    public AddCommand(String line) {
        this.line = line;
    }

    /**
     * This method is to deal with three types of command that the user want to add to their task list
     * todo: T
     * deadline: D
     * event: E
     * After doing so, the task list size will increase by 1
     * Necessary feedback from the system is done by the println functions.
     *
     * @param tasks All the tasks we have in the storage
     */
    public static void addCommandMethod(TaskList tasks) {
        Spliterator.printSpliterator();
        System.out.println("Got it. I've added: this task:");
        int indexOfSpace = line.indexOf(" ");
        String firstLetter = line.substring(0, indexOfSpace);

        // Information is the content without the first letter
        String information = line.substring(indexOfSpace + 1);
        information = TransformString.transformString(information);

        // Obtain the target taskStatus from the command
        Status taskStatus;
        switch (firstLetter) {
        case "todo": {
            taskStatus = Status.T;
            break;
        }
        case "deadline": {
            taskStatus = Status.D;
            break;
        }
        case "event": {
            taskStatus = Status.E;
            break;
        }
        default:
            return;
        }

        Task currentTask = new Task(information, taskStatus);
        tasks.addTask(currentTask);
        System.out.println("  " + currentTask.showTask());
        System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
        Spliterator.printSpliterator();
    }
}