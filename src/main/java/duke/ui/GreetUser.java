package duke.ui;

public class GreetUser {
    /**
     * Prints out the greeting message to the user
     */
    public static void greetUser() {
        String logo = " .----------------.  .----------------.  .----------------.  .----------------. \n" +
                "| | |_   ___ `.  | || ||_   _||_   _|| || | |_  ||_  _|  | || | |_   ___  |  | |\n" +
                "| |   | |   `. \\ | || |  | |    | |  | || |   | |_/ /    | || |   | |_  \\_|  | |\n" +
                "| |   | |    | | | || |  | '    ' |  | || |   |  __'.    | || |   |  _|  _   | |\n" +
                "| |  _| |___.' / | || |   \\ `--' /   | || |  _| |  \\ \\_  | || |  _| |___/ |  | |\n" +
                "| | |________.'  | || |    `.__.'    | || | |____||____| | || | |_________|  | |\n" +
                " '----------------'  '----------------'  '----------------'  '----------------' ";
        System.out.println("Hello from\n" + logo);
        String greetMessage = "Hello! I'm Duke\n"
                + "Send me a list of things to remember!\n"
                + "Type <bye> to exit";
        System.out.println(greetMessage);
    }
}
