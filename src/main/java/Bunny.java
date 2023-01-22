import java.util.Scanner;

public class Bunny {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        BunnySession bunny = new BunnySession();

        bunny.printMessage("Hello! I'm Bunny.\nWhat can I do for you?");

        while(true) {
            String input = in.nextLine();
            if(input.equals("bye")) {
                break;
            }
            bunny.printMessage(input);
        }

        bunny.printMessage("Bye. Hope to see you again soon!");
    }
}
