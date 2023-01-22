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
            case "bye":
                reply = " Bye bye.";
                endInput = true;
                break;
            default:
                reply = " " + line;
            }
            System.out.println(horizontalLine + reply + horizontalLine);
        }
    }
}
