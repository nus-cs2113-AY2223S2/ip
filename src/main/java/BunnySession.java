public class BunnySession {
    private final static String divider = "____________________________________________________________";
    private final static String globalIndentation = "\t";
    private final static String messageIndentation = " ";

    public void printMessage(String message) {
        this.printMessage(message.split("\n"));
    }
    public void printMessage(String[] messageLines) {
        String output = "";
        output += globalIndentation + divider + "\n";
        for (int i = 0; i < messageLines.length; i++) {
            output += globalIndentation + messageIndentation + messageLines[i] + "\n";
        }
        output += globalIndentation + divider + "\n";

        System.out.print(output);
    }
}
