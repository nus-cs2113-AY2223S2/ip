package Command;
import java.util.ArrayList;
import Entities.Task;

public class TaskPrinter {
    public static String taskAddedMessage(Task t, int numTasks) {
        return 
            "\t----------------------------------------------------\n" + 
            "\tGot it. I have added this task to the list!\n" + 
            "\t   " + t.toString() + "\n" + 
            "\tNow you have " + numTasks + 
            " task" + (numTasks > 1 ? "s" : "") + 
            " in the list.\n" + 
            "\t----------------------------------------------------\n\n"
        ;
    }

    public static String tasksToStringMessage(ArrayList<Task> tasks) {
        String output = "\t----------------------------------------------------\n";

        if (tasks.size() == 0) {
            output += "\t No tasks found!\n";
        } else {
            output += "\t Here are the tasks in your list:\n";
            for (int i = 0; i < tasks.size(); i++) {
                output += "\t" + (i + 1) + "." + tasks.get(i).toString() + "\n";
            }
        }

        output += "\t----------------------------------------------------\n\n";

        return output;
    }

    public static String markedMessage(Task t) {
        return 
            "\t----------------------------------------------------\n" + 
            "\tNice! I've marked this task as done:\n" + 
            "\t   " + t.toString() + "\n" + 
            "\t----------------------------------------------------\n\n"
        ;
    }

    public static String unmarkedMessage(Task t) {
        return 
            "\t----------------------------------------------------\n" + 
            "\tOK, I've marked this task as not done yet:\n" + 
            "\t   " + t.toString() + "\n" + 
            "\t----------------------------------------------------\n\n"
        ;
    }

    public static String deletedMessage(Task t, int numTasks) {
        return 
            "\t----------------------------------------------------\n" + 
            "\tNoted. I've removed this task:\n" + 
            "\t   " + t.toString() + "\n" + 
            "\tNow you have " + numTasks + 
            " task" + (numTasks > 1 ? "s" : "") + 
            " in the list.\n" + 
            "\t----------------------------------------------------\n\n"
        ;
    }
}
