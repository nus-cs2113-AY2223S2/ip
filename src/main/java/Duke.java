public class Duke {
    /**
     * The markAsDone method marks a task as done.
     *
     * @param compileItems The TaskMaster object that contains the task list.
     * @param index        The index of the task to mark as done.
     */
    static void markAsDone(TaskMaster compileItems, int index) {
        TaskManager toBeDone = compileItems.getTask(index);
        boolean hasDukeMarked = toBeDone.markAsDone();
        if (hasDukeMarked == true) {
            System.out.println("You have completed this task. Good job!!");
        } else {
            System.out.println("This task has been marked before. Go on to another task.");
        }
        toBeDone.printTaskAndStatus(index);
    }

    /**
     * The markAsNotDone method marks a task as not done.
     *
     * @param compileItems The TaskMaster object that contains the task list.
     * @param index        The index of the task to mark as not done.
     */
    static void markAsNotDone(TaskMaster compileItems, int index) {
        TaskManager toBeDone = compileItems.getTask(index);
        boolean hasDukeUnMarked = toBeDone.markAsUndone();
        if (hasDukeUnMarked == true) {
            System.out.println("I would strongly encourage you to start on this.");
        } else {
            System.out.println("This task was never completed. I would strongly encourage you to start on it.");
        }
        toBeDone.printTaskAndStatus(index);
    }

    /**
     * The main method is the entry point of the program. It prompts the user for their name,
     * instantiates a TaskMaster object, and handles user commands.
     *
     * @param args Command-line arguments passed to the program.
     */
    public static void main(String[] args) {

        showWelcomeMessage();
        showHelpMessage();

        IO inOut = new IO();

        String userName = getUserName(inOut);

        TaskMaster compileItems = new TaskMaster();

        instantiateList(inOut, userName, compileItems);

    }

    private static void showHelpMessage() {
        System.out.println("=============================================================================================================== \n");
        System.out.println("Here are some useful commands to get you started!\n");
        System.out.println("=============================================================================================================== \n");
        System.out.println("'See list': Take a look at your To-Do list to get your day started!");
        System.out.println("'mark <task number>': Marks task as done. Try entering 'mark 1' to mark your first task as done!");
        System.out.println("'unmark <task number>': Unmarks task as not done. Try entering 'unmark 1' to mark your first task as not done!");
        System.out.println("'Help': If you forgot how to use me, don't be afraid to ask!");
        System.out.println("To add a task, enter a description of said task and I will add it into the list for you :) \n");
        System.out.println("=============================================================================================================== \n");
    }

    /**
     * The instantiateList method handles user commands and updates the task list.
     *
     * @param inOut        The IO object used for reading user input.
     * @param userName     The name of the user.
     * @param compileItems The TaskMaster object that contains the task list.
     */
    private static void instantiateList(IO inOut, String userName, TaskMaster compileItems) {
        while (true) {
            String userCommand = inOut.readInput();
            //change to switch case
            switch (userCommand) {

            case ("Done"):
                System.out.println("Bye, " + userName + "." + " Hope to see you again soon!\n");
                return;

            case ("See list"):
                seeList(compileItems);
                continue;

            case ("Help"):
                showHelpMessage();
                continue;

            default:
                if (userCommand.startsWith("mark") || userCommand.startsWith("unmark")) {
                    String[] substring = userCommand.split(" ");
                    int index = Integer.parseInt(substring[1]) - 1;
                    if (substring[0].equals("mark")) {
                        markAsDone(compileItems, index);
                    } else {
                        markAsNotDone(compileItems, index);
                    }
                    continue;
                }
                compileItems.addNewItem(userCommand);
                System.out.println("Understood. Added task: " + userCommand + "." + " Anything else?");
            }
        }
    }

    private static void seeList(TaskMaster compileItems) {
        System.out.println("Understood. Retrieving To-Do list...");
        compileItems.printList();
        System.out.println("Would you like to add more tasks? Enter 'Done' if you are satisfied with this list.");
    }


    private static String getUserName(IO inOut) {
        System.out.println("What is your name? (Please enter name)\n");
        String userName = inOut.readInput();
        System.out.println("Hello, " + userName + ". You may enter 'See list' to view your current To-Do list.");
        return userName;
    }

    private static void showWelcomeMessage() {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n" + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke, your personal assistant.\n");

    }
}
