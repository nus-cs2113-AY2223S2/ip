package Gilbert.ui;

import Gilbert.messages.Messages;

public class UI {
    //takes in input from user or file

    /**
     * Prints out the greeting message for the user.
     *
     */
    public static void userGreeting() {
        System.out.println("  ________.___.____   __________________________________________\n" +
                " /  _____/|   |    |  \\______   \\_   _____/\\______   \\__    ___/\n" +
                "/   \\  ___|   |    |   |    |  _/|    __)_  |       _/ |    |   \n" +
                "\\    \\_\\  \\   |    |___|    |   \\|        \\ |    |   \\ |    |   \n" +
                " \\______  /___|_______ \\______  /_______  / |____|_  / |____|   \n" +
                "        \\/            \\/      \\/        \\/         \\/           ");
        System.out.println(Messages.GREET);
    }

    /**
     * Prints out the exit message for the user.
     *
     */
    public static void userExit() {
        System.out.println(Messages.BYE);
    }


}
