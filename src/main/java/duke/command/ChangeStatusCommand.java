package duke.command;

import duke.tasklist.TaskList;

import static duke.ui.Ui.printBorder;

public class ChangeStatusCommand {

    //mark task as done
    public static void markTask(TaskList tasks, int taskIndex){
        tasks.getTask(taskIndex).setStatus("mark");
        System.out.println("Nice! I've marked this task as done: \n");
        System.out.println(tasks.getTask(taskIndex) + "\n");
        printBorder();
    }
    //mark task as undone
    public static void unmarkTask(TaskList tasks, int taskIndex){
        tasks.getTask(taskIndex).setStatus("unmark");
        System.out.println("OK, I've marked this task as not done yet: \n");
        System.out.println(tasks.getTask(taskIndex) + "\n");
        printBorder();
    }
}
