import duke.Task;

import java.util.ArrayList;

public class Ui {
    public static final String LOGO = " ____        _        \n"
                                    + "|  _ \\ _   _| | _____ \n"
                                    + "| | | | | | | |/ / _ \\\n"
                                    + "| |_| | |_| |   <  __/\n"
                                    + "|____/ \\__,_|_|\\_\\___|\n";
    public void welcomeMessage() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
    }

    public void farewellMessage() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println("    ____________________________________________________________");
        System.out.println(errorMessage);
        System.out.println("    ____________________________________________________________");
    }

    public void printTaskList(TaskList taskList) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            System.out.println("     " + (i + 1) + "." + taskList.getTask(i));
        }
        System.out.println("    ____________________________________________________________");
    }

    public void printMarkedTask(Task markedTask) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + markedTask);
        System.out.println("    ____________________________________________________________");
    }

    public void printUnmarkedTask(Task unmarkedTask) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("       " + unmarkedTask);
        System.out.println("    ____________________________________________________________");
    }

    public void printAddedTask(Task addedTask, int numOfTasks) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + addedTask);
        System.out.println("     Now you have " + numOfTasks + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    public void printDeletedTask(Task deletedTask, int numOfTasks) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + deletedTask);
        System.out.println("     Now you have " + numOfTasks + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    public void printFoundTask(TaskList taskList) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the matching tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            System.out.println("     " + (i + 1) + "." + taskList.getTask(i));
        }
        System.out.println("    ____________________________________________________________");
    }

}
