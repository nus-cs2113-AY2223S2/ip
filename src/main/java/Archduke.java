class IO {
    /**
     * Prints a string with a space in front. This space is used for indentation.
     * Though optional, that indentation looks kinda nice.
     * 
     * @param s The string to be printed.
     */
    public static void println(String s) {
        System.out.println(" " + s);
    }

    /**
     * Prints a line of underscores, served as a divider between sections. Like the
     * HTML's {@code hr} tag.
     */
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
}

public class Archduke {
    public static void main(String[] args) {
        IO.printLine();
        IO.println("Hello! I'm Archduke");
        IO.println("What can I do for you?");
        IO.printLine();
        IO.println("Bye. Hope to see you again soon!");
        IO.printLine();
    }
}
