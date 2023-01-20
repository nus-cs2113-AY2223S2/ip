public class Duke {
    // TODO: separate formatting functions another file and class
    private final static String divider = "____________________________________________________________";
    private final static String globalIndentation = "\t";
    private final static String messageIndentation = " ";

    public static String formatMessage(String message) {
        return formatMessage(new String[] { message });
    }
    public static String formatMessage(String[] messageLines) {
        return formatMessage(messageLines, true);
    }

    public static String formatMessage(String message, boolean topDivider) {
        return formatMessage(new String[] { message }, topDivider);
    }

    public static String formatMessage(String[] messageLines, boolean topDivider) {
        String output = "";
        if (topDivider) {
            output += globalIndentation + divider + "\n";
        }
        for (int i = 0; i < messageLines.length; i++) {
            output += globalIndentation + messageIndentation + messageLines[i] + "\n";
        }
        output += globalIndentation + divider + "\n";
        return output;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String indent = "\t";
        System.out.println("Hello from\n" + logo);
        System.out.print(formatMessage(new String[] {"Hello! I'm Duke", "What can I do for you?"}));
        System.out.print(formatMessage("Bye. Hope to see you again soon!", false));
    }
}
