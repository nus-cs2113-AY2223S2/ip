import java.util.Scanner;
import java.util.ArrayList;

public class ThomasShelby {
    public static void main(String[] args) {
        System.out.print("Good day, I'm Thomas Shelby\nTo what do I owe the pleasure?\n");
        ArrayList<String> inputStrings = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        while (true) {
            String line = in.nextLine();
            if (line.equals("bye")) {
                break;
            } else if (line.equals("list")) {
                for (int i = 0; i < inputStrings.size(); i++) {
                    System.out.println((i + 1) + ". " + inputStrings.get(i));
                }
            } else {
                inputStrings.add(line);
                System.out.println("added: " + line);
            }
        }
        System.out.println("There's no rest for me in this world. Perhaps in the next. Goodbye.");
    }
}
