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
            + System.lineSeparator() + "/todo { description } - Add a todo task to ur task list."
            + System.lineSeparator() +
            "/deadline { description } /by { cutoff } - Add a deadline task to ur task list." + System.lineSeparator() +
            "/event { description } /start { start time } /end { end time } - Add an event task to ur task list."
            + System.lineSeparator() +
            "/list - List out all the tasks in ur task list." + System.lineSeparator() +
            "/mark { numerical index } - Mark a specific task done." + System.lineSeparator() +
            "/unmark { numerical index } - Mark a specific task undone." + System.lineSeparator() +
            "/delete { numerical index } - Delete a specific task." + System.lineSeparator() +
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
        System.out.println(LINE + System.lineSeparator() + "[MESSAGE]" + message + System.lineSeparator() + LINE);
    }

    static public void warnUser(String message) {
        System.out.println(LINE + System.lineSeparator() + "[ERROR] " + message + System.lineSeparator() + LINE);
    }
}
