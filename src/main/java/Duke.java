public class Duke {
    public static void main(String[] args) {
        showWelcomeMessage();

        IO inOut = new IO();

        String userName = getUserName(inOut);

        TaskMaster compileItems = new TaskMaster();

        instantiateList(inOut, userName, compileItems);

    }

    private static void instantiateList(IO inOut, String userName, TaskMaster compileItems) {
        while (true) {
            String userCommand = inOut.readInput();
            //change to switch case
            switch (userCommand) {

            case ("Done"):
                System.out.println("Bye, " + userName + "." + " Hope to see you again soon!\n");
                return;

            case ("See list"):
                System.out.println("Understood. Retrieving To-Do list...");
                compileItems.printList();
                System.out.println("Would you like to add more tasks? Enter 'Done' if you are satisfied with this list.");
                continue;

            default:
                compileItems.addNewItem(userCommand);
                System.out.println("Understood. Added task: " + userCommand + "." + " Anything else?");
            }
        }
    }

    private static String getUserName(IO inOut) {
        System.out.println("What is your name? (Please enter name)\n");
        String userName = inOut.readInput();
        System.out.println("Hello, " + userName + ". You may enter 'See list' to view your current To-Do list.");
        return userName;
    }

    private static void showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke, your personal assistant.\n");
    }
}
