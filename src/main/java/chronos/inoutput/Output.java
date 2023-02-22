package chronos.inoutput;

import chronos.tasktype.Task;
import chronos.tasktype.Stash;

public class Output {
    private static void printLogo() {
        String logo =
                "░█████╗░██╗░░██╗██████╗░░█████╗░███╗░░██╗░█████╗░░██████╗\n" +
                        "██╔══██╗██║░░██║██╔══██╗██╔══██╗████╗░██║██╔══██╗██╔════╝\n" +
                        "██║░░╚═╝███████║██████╔╝██║░░██║██╔██╗██║██║░░██║╚█████╗░\n" +
                        "██║░░██╗██╔══██║██╔══██╗██║░░██║██║╚████║██║░░██║░╚═══██╗\n" +
                        "╚█████╔╝██║░░██║██║░░██║╚█████╔╝██║░╚███║╚█████╔╝██████╔╝\n" +
                        "░╚════╝░╚═╝░░╚═╝╚═╝░░╚═╝░╚════╝░╚═╝░░╚══╝░╚════╝░╚═════╝░\n";
        System.out.println(logo);

    }

    public static void printWelcome() {
        System.out.println("Hello from\n");
        printLogo();
        System.out.println("Tick, tick, boom! I'm Chronos, your personal time manager.\n");
    }

    public static void printHelp() {
        String lineBreak = "===============================================================================================================\n";
        String initMessage = "Here are some useful commands to get you started!\n";
        String helpMenu = "1. todo <task name> - Adds a todo task to the list.\n" +
                "2. deadline <task name> /due <date> - Adds a deadline task to the list.\n" +
                "3. event <task name> /start <date/time> /end <date/time> - Adds an event task to the list.\n" +
                "4. list - Lists all tasks in the list.\n" + "5. mark <task number> - Marks a task as done.\n" +
                "6. unmark <task_number>: Unmarks task as not done\n" + "7. help - If you forgot how to use me, don't be afraid to ask!.\n"
                + "8. Timer - starts a 25 minute Pomodoro timer\n" + "9. done - Exits Chronos. \n";

        System.out.println(lineBreak + initMessage + lineBreak + helpMenu + lineBreak);
    }

    public static void printList(Stash stash) {
        System.out.println("Here's what you have to do:");
        stash.printTasks();
        System.out.print("\n");
    }

    public static void printIsDone(Stash stash, int index) {
        Task task = stash.getTask(index);
        System.out.printf("This task has been marked as %s", task.isDone() ? "done! Good job!" : "undone. Please start on it.");
        System.out.print("\n");
        System.out.printf("  %s", task);
        System.out.print("\n");
    }

    public static void printNewTask(Task task, int stashSize) {
        System.out.print("Added New Task: ");
        System.out.print("\n");
        System.out.printf(" %s", task.toString());
        System.out.print("\n");
        System.out.printf("You now have %d task(s) in the list.", stashSize);
        System.out.print("\n");
    }

    public static void printDeletedTask(Task task, int stashSize) {
        System.out.print("Deleted Task ");
        System.out.print("\n");
        System.out.printf(" %s", task.toString());
        System.out.print("\n");
        System.out.printf("You now have %d task(s) in the list.", stashSize);
        System.out.print("\n");
    }

}
