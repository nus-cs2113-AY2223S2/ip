public class Greeting {
    
    /**
    * Method to print a line seperator
    */
    public static void printSeperator() {
        System.out.println("================================================================================\n");
    }

    /**
    * Method to print the chatbot Logo
    */
    public static void printLogo() {
        String logo = "░█████╗░██╗░░░██╗███╗░░██╗████████╗██╗███████╗\n"
                + "██╔══██╗██║░░░██║████╗░██║╚══██╔══╝██║██╔════╝\n"
                + "███████║██║░░░██║██╔██╗██║░░░██║░░░██║█████╗░░\n"
                + "██╔══██║██║░░░██║██║╚████║░░░██║░░░██║██╔══╝░░\n"
                + "██║░░██║╚██████╔╝██║░╚███║░░░██║░░░██║███████╗\n"
                + "╚═╝░░╚═╝░╚═════╝░╚═╝░░╚══╝░░░╚═╝░░░╚═╝╚══════╝\n";
        System.out.println("\tHello from...\n" + logo);
    }

    /**
    * Method to print welcome message
    */
    public static void printWelcome() {
        printSeperator();
        System.out.println(
                "\tAiyohh... why u wake me up again\n" +
                        "\thow can auntie help you today? faster say already don't waste time okay?\n");
        printSeperator();
    }

    /**
    * Method to print successfull data load message
    */
    public static void printDataFound() {
        Greeting.printSeperator();
        System.out.println("Data load successfull... \nWaking Auntie up");
        Greeting.printSeperator();
    }

    /**
    * Method to print unsuccessfull data load message
    */
    public static void printDataNotFound() {
        Greeting.printSeperator();
        System.out.println("No Past Data Found. Will create a new data file after ending the Bot...");
        Greeting.printSeperator();
    }

    /**
    * Method to print the message informing users that the command input is invalid
    */
    public static void printHelp() {
        printSeperator();
        System.out.println(
                "\tEhh hello... Say thing can say properly or not. Auntie don't understand!\n");
        printSeperator();
    }

    /**
    * Method to print the message informing users that the task input is empty
    */
    public static void printEmptyTask() {
        printSeperator();
        System.out.println("\tTask cannot be empty lah!");
        printSeperator();
    }

    /**
    * Method to print the message informing users that the date input is empty
    */
    public static void printEmptyDate() {
        printSeperator();
        System.out.println("\tDate cannot be empty lah!");
        printSeperator();
    }

    /**
    * Method to print the message informing users that there is not enough 
    * required information for a particular command
    */
    public static void printEmptyCommand() {
        printSeperator();
        System.out.println("\tUse the correct commands for this task type can!");
        printSeperator();
    }

    /**
    * Method to print the message informing users that file may be corrupted
    */
    public static void printReadFileError() {
        printSeperator();
        System.out.println("\tError with reading in tasks to list. Is your file corrupted?");
        printSeperator();
    }

    /**
    * Method to print goodbye message
    */
    public static void printGoodbye() {
        printSeperator();
        System.out.println(
                "\tAuntie very tired talking to you lah. Better not wake me up again ah I tell u first!\n"
        );
        printSeperator();
    }
}
