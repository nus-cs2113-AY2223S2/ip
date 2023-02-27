package duke;

public class UI {
    public static String HorizontalLine = "__________________________\n";
    public void printGreeting() {
        System.out.println(HorizontalLine + "Hello! I'm Duke\n" + "What can I do for you?\n"
                + HorizontalLine);
    }

    public void printGoodbye() {
        System.out.println(HorizontalLine + "Goodbye!" + "\n" + HorizontalLine);
    }
}
