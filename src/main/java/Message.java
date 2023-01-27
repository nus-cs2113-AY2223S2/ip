/*
* Use this class to store all text output for PAPA.
* I'm wondering if it's a good idea to use this to store pre-made strings.
* Logo, Greet, Exit, etc
*/
public class Message {
    public static final String logo = "██████╗  █████╗ ██████╗  █████╗ \n" +
                                      "██╔══██╗██╔══██╗██╔══██╗██╔══██╗\n" +
                                      "██████╔╝███████║██████╔╝███████║\n" +
                                      "██╔═══╝ ██╔══██║██╔═══╝ ██╔══██║\n" +
                                      "██║     ██║  ██║██║     ██║  ██║\n" +
                                      "╚═╝     ╚═╝  ╚═╝╚═╝     ╚═╝  ╚═╝";
    public static final String intro = "Hello! I'm PAPA, your Personal Assistant, Personal Angel.\n"
                                     + "What can I do for you?";
    public static final String outro = "Bye. Hope to see you again soon!";

    /**
     * Prints a horizontal line.
     */
    public static void printHLine() {
        System.out.println("================================");
    }

    /**
     * Greets the user.
     */
    public static void greet() {
        System.out.println(logo);
        // Greet message
        printHLine();
        System.out.println(intro);
        printHLine();
    }

    /**
     * Exit, goodbye message.
     */
    public static void exit() {
        System.out.println(outro);
        printHLine();
    }

    public static String taskDone = "Nice! I've marked this task as done: ";
    public static String taskUndone = "Okay, I've marked this task as not done yet: ";

    public static final String helpMessage = "Options:\n" +
                                             "  help        Show these tips.\n" +
                                             "  list        List out existing tasks.\n" +
                                             "  bye         Exit PAPA.\n" +
                                             "  mark <n>    Mark the n-th task as done.\n" +
                                             "  unmark <n>  Mark the n-th task as undone.";
}
