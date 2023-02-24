public class Ui {
    
    /**
     * Prints the message that is to be shown at the beginning of every session
     */
    public static void printStartMsg () {
        String logo = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
    
    /**
     * Prints a String into the terminal with the given format (with borders above and below to indicate it as the bot output)
     * 
     * @param response The String that represents what is desired to be output
     */
    public static void printResponse(String response) {
        System.out.println("    ____________________________________________________________");
        System.out.println(String.format("%s", response));
        System.out.println("    ____________________________________________________________");
    }
}
