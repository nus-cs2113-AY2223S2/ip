public class Greeting {
    
    /**
    * Method to print a line seperator
    */
    public static void printSeperator() {
        System.out.println("____________________________________________________________\n");
    }

    /**
    * Method to print the chatbot Logo
    */
    public static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\tHello from\n" + logo);
    }

    /**
    * Method to print welcome message
    */
    public static void printWelcome() {
        printSeperator();
        System.out.println(
                "\tHello! I'm Duke\n" +
                        "\tWhat can I do for you?\n");
        printSeperator();
    }

    /**
    * Method to print the message informing users that the command input is invalid
    */
    public static void printHelp() {
        printSeperator();
        System.out.println(
                "\tPlease Type a valid command!\n");
        printSeperator();
    }

    /**
    * Method to print the message informing users that the task input is empty
    */
    public static void printEmptyTask() {
        printSeperator();
        System.out.println("\tTask cannot be empty!");
        printSeperator();
    }

    /**
    * Method to print the message informing users that the date input is empty
    */
    public static void printEmptyDate() {
        printSeperator();
        System.out.println("\tDate cannot be empty!");
        printSeperator();
    }

    /**
    * Method to print the message informing users that there is not enough 
    * required information for a particular command
    */
    public static void printEmptyCommand() {
        printSeperator();
        System.out.println("\tPlease use the relevant commands for this task type!");
        printSeperator();
    }

    /**
    * Method to print goodbye message
    */
    public static void printGoodbye() {
        printSeperator();
        System.out.println(
                "\tBye. Hope to see you again soon!\n"
        );
        printSeperator();
    }
}
