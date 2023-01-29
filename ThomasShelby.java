import java.util.Scanner;

public class ThomasShelby {
    public static void main(String[] args) {
        System.out.print("Good day, I'm Thomas Shelby\nTo what do I owe the pleasure?\n");
        Scanner in = new Scanner(System.in);
        while (true) {
            String line = in.nextLine();
            if (line.equals("bye")) {
                break;
            } else {
                System.out.println(line);
            }
        }
        System.out.println("There's no rest for me in this world. Perhaps in the next. Goodbye.");
    }
}
