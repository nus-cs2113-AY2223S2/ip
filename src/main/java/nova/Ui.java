package nova;

public class Ui {
    public static final String LOGO = " __ _   __   _  _   __\n" +
            "(  ( \\ /  \\ / )( \\ / _\\ \n" +
            "/    /(  O )\\ \\/ //    \\ \n" +
            "\\_)__) \\__/  \\__/ \\_/\\_/ \n";

    public static final String WELCOME_MESSAGE = "Hello! I'm your assistant \n";
    public static final String GREETING_MESAGE = "\nHow can i help u? \n";
    public static final String FAREWELL_MESSAGE = "Goodbye. Hope to see u again :) \n";

    /**
     * Prints a welcome message
     */
    public static void greet_user() {
        System.out.println(WELCOME_MESSAGE + LOGO + GREETING_MESAGE);
    }

    /**
     * Prints an exit message
     */
    public static void goodbye_user() {
        System.out.println(FAREWELL_MESSAGE);
    }

}
