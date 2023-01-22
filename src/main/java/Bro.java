import java.util.Scanner;
public class Bro {
    public static void main(String[] args) {
        String horizontalLine = "\n───────────────────────────────────────────────────────────────\n";
        String logo = horizontalLine + " Sup. I'm Bro.\n" + " What do you want?" + horizontalLine;
        System.out.println(logo);

        // User Input
        String line, reply = "";
        Scanner in = new Scanner(System.in);
        boolean endInput = false;
        while (!endInput) {
            line = in.nextLine();
            switch (line) {
            case "list":
                reply = " list";
                break;
            case "blah":
                reply = " blah";
                break;
            case "bye":
                reply = " Bye bye.";
                endInput = true;
                break;
            default:
                reply = " I don't understand.";
            }
            System.out.println(horizontalLine + reply + horizontalLine);
        }
    }
}
