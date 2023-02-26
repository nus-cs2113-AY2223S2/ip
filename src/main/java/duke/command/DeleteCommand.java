package duke.command;

import duke.tasklist.TaskList;
import duke.tasks.Task;

import java.util.ArrayList;

import static duke.ui.Ui.printBorder;

public class DeleteCommand {

//    delete task
    public static void deleteTask(TaskList tasks, int taskIndex){
        Task temp = tasks.getTask(taskIndex);
        tasks.removeTask(taskIndex);
        System.out.println("Noted. I've removed this task: \n");
        System.out.println(temp);
        System.out.println("Now you have " + tasks.getSize() + " tasks in the list." );
        printBorder();
    }

}
