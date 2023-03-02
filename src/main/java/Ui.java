public class Ui {
    private static final String DIVIDER  = "______________________________";
    public static void printLogo() {
        String logoMessage = "Hello from\n" +
                " ____        _        \n" +
                "|  _ \\ _   _| | _____ \n" +
                "| | | | | | | |/ / _ \\\n" +
                "| |_| | |_| |   <  __/\n" +
                "|____/ \\__,_|_|\\_\\___|";
        System.out.println(logoMessage);
    }

    public static void greetUser() {
        String greeting = DIVIDER + System.lineSeparator() + "Hello! I'm Jarvis!"
                + System.lineSeparator() + "What can I do for you?"
                + System.lineSeparator() + DIVIDER + System.lineSeparator();
        System.out.println(greeting);
    }

    public static void sayByeToUser() {
        String bye = DIVIDER + System.lineSeparator() + "Bye. Hope to see you again soon!"
                + System.lineSeparator() + DIVIDER + System.lineSeparator();
        System.out.println(bye);
    }

    public static void unknownTaskError() {
        System.out.println(DIVIDER + System.lineSeparator()
                + "☹ OOPS!!! I'm sorry, but I don't know what that means :-("
                + System.lineSeparator() + DIVIDER);
    }

    public static void invalidTaskNumberError() {
        System.out.println(DIVIDER + System.lineSeparator()
                + "You did not key in a task number. Please key in a valid task number and try again!"
                + System.lineSeparator() + DIVIDER);
    }

    public static void emptyDescriptionTodo() {
        String errorMessage = DIVIDER + System.lineSeparator() +
                "☹ OOPS!!! The description of a todo cannot be empty." + System.lineSeparator() + DIVIDER;
        System.out.println(errorMessage);
    }

    public static void emptyDescriptionEvent() {
        String errorMessage = DIVIDER + System.lineSeparator() +
                "☹ OOPS!!! The description of a event cannot be empty." + System.lineSeparator() + DIVIDER;
        System.out.println(errorMessage);
    }

    public static void emptyDescriptionDeadline() {
        String errorMessage = DIVIDER + System.lineSeparator() +
                "☹ OOPS!!! The description of a deadline cannot be empty." + System.lineSeparator() + DIVIDER;
        System.out.println(errorMessage);
    }

    public static void emptyDescriptionNumber() {
        String errorMessage = DIVIDER + System.lineSeparator() +
                "☹ OOPS!!! The task number cannot be empty." + System.lineSeparator() + DIVIDER;
        System.out.println(errorMessage);
    }
}
