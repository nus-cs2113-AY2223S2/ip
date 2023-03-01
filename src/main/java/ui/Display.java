package ui;

/**
 * Represents the messages and warnings displayed to user in the Duke UI.
 */
public class Display {
    static public final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    static public final String LINE = "---------------------------------------------------";
    static public final String MESSAGE_WELCOME = LINE + System.lineSeparator() +
            "Hello from\n" + LOGO + System.lineSeparator() + "How can I help you?" + System.lineSeparator()
            + "Here are some possibly useful commands:" + System.lineSeparator() + LINE
            + System.lineSeparator() + "/todo DESCRIPTION - Add a todo task to ur task list."
            + System.lineSeparator() +
            "/deadline DESCRIPTION /by CUTOFF - Add a deadline task to ur task list." + System.lineSeparator() +
            "/event DESCRIPTION /start START_TIME /end END_TIME - Add an event task to ur task list."
            + System.lineSeparator() +
            "/list - Lists task(s) in ur task list." + System.lineSeparator() +
            "/mark NUMERICAL_INDEX - Mark a specific task done." + System.lineSeparator() +
            "/unmark NUMERICAL_INDEX - Mark a specific task undone." + System.lineSeparator() +
            "/find KEYWORD [ADDITIONAL KEYWORDS].. - Lists task(s) matching specified keyword(s)"
            + System.lineSeparator() +
            "/delete NUMERICAL_INDEX - Delete a specific task." + System.lineSeparator() +
            "/bye - Terminate the program." + System.lineSeparator() + LINE;
    static public final String MESSAGE_GOODBYE = LINE + System.lineSeparator() + "Bye! Hope to see you again soon!"
            + System.lineSeparator() + LINE;

    static public void greetUser() {
        System.out.println(MESSAGE_WELCOME);
    }

    static public void goodbyeUser() {
        System.out.println(MESSAGE_GOODBYE);
    }

    static public void printLine() {
        System.out.println(LINE);
    }

    static public void notifyUser(String message) {
        System.out.println(LINE + System.lineSeparator() + "[MESSAGE] " + message + System.lineSeparator() + LINE);
    }

    static public void warnUser(String message) {
        System.out.println(LINE + System.lineSeparator() + "[ERROR] " + message + System.lineSeparator() + LINE);
    }
}
