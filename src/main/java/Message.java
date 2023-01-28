/*
* Use this class to store all text output for PAPA.
* I'm wondering if it's a good idea to use this to store pre-made strings.
* Logo, Greet, Exit, etc
*/
public class Message {
    public static final String LOGO = "██████╗  █████╗ ██████╗  █████╗ \n" +
            "██╔══██╗██╔══██╗██╔══██╗██╔══██╗\n" +
            "██████╔╝███████║██████╔╝███████║\n" +
            "██╔═══╝ ██╔══██║██╔═══╝ ██╔══██║\n" +
            "██║     ██║  ██║██║     ██║  ██║\n" +
            "╚═╝     ╚═╝  ╚═╝╚═╝     ╚═╝  ╚═╝";
    public static final String INTRO = "Hello! I'm PAPA, your Personal Assistant, Personal Angel.\n"
            + "What can I do for you? Type 'help' for a list of commands.";
    public static final String OUTRO = "Bye. Hope to see you again soon!";

    /**
     * Prints a horizontal line of 32 '=' characters.
     */
    public static void printHLine() {
        System.out.println("================================");
    }

    /**
     * Greets the user.
     */
    public static void greet() {
        System.out.println(LOGO);
        // Greet message
        printHLine();
        System.out.println(INTRO);
        printHLine();
    }

    /**
     * Exit, goodbye message.
     */
    public static void exit() {
        System.out.println(OUTRO);
        printHLine();
    }

    public static final String TASK_DONE = "Nice! I've marked this task as done: ";
    public static final String TASK_UNDONE = "Okay, I've marked this task as not done yet: ";

    public static final String HELP_MESSAGE = "Options:\n" +
            "  help        Show these tips.\n" +
            "  list        List out existing tasks.\n" +
            "  bye         Exit PAPA.\n" +
            "  mark <n>    Mark the n-th task as done.\n" +
            "  unmark <n>  Mark the n-th task as undone.";
}
