package AllCommands;

import BasisSupport.Status;
import BasisSupport.transformString;
import Support.*;

// Related method (Totally three methods)
public class AddCommand extends Commands {
    public static String line;

    public AddCommand(String line) {
        this.line = line;
    }

    public static void addCommandMethod(TaskList tasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added: this task:");
        int indexOfSpace = line.indexOf(" ");
        String firstLetter = line.substring(0, indexOfSpace);

        // Information is the content without the first letter
        String information = line.substring(indexOfSpace + 1);
        information = transformString.transformString(information);

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
        System.out.println("____________________________________________________________");

    }


}