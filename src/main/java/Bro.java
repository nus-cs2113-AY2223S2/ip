import java.util.Scanner;
import java.util.ArrayList;
public class Bro {
    public static void main(String[] args) {
        String horizontalLine = "\n───────────────────────────────────────────────────────────────\n";
        String logo = horizontalLine + " Sup. I'm Bro.\n" + " What do you want?" + horizontalLine;
        System.out.println(logo);

        // User Input
        String line, reply = "";
        Scanner in = new Scanner(System.in);
        ArrayList<String> storedText = new ArrayList<String>(); // Dynamic array to store text entered by user
        boolean endInput = false;
        while (!endInput) {
            line = in.nextLine();
            switch (line) {
            case "bye":
                reply = " Bye bye.";
                endInput = true;
                break;
            case "list":
                reply = "";
                for (int i = 0; i < storedText.size(); ++i) {
                    reply += (" " + (i + 1) + ". " + storedText.get(i) + "\n");
                }
                break;
            default:
                storedText.add(line);
                reply = " added: " + line;
            }
            System.out.println(horizontalLine + reply + horizontalLine);
        }
    }
}
