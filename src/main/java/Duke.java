public class Duke {
    public static void main(String[] args) {

        greetUser();
        sayBye();
    }

    /**
     * Prints BtB logo and greets the user
     */
    public static void greetUser() {

        String logo = " ____     _    ____\n"
                + "|  _ \\ __| |__|  _ \\\n"
                + "| | //|__   __| | //\n"
                + "| |_\\\\   |  |_| |_\\\\\n"
                + "|____/   |___/|____/\n";
        String dottedLine = "--------------------------------------------------\n";
        String greetings = " Hello! I'm Bob the Bot, aka BtB.\n"
                + " What can I do for you?\n";

        System.out.println(dottedLine + logo + '\n' + greetings
                + dottedLine);
    }

    /**
     * Say bye to user
     */
    public static void sayBye() {
        String dottedLine = "--------------------------------------------------";

        System.out.println(" Bye. Hope to see you again sooooooon (┬┬﹏┬┬)!");
        System.out.println(dottedLine);
    }
}