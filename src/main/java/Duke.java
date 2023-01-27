import java.util.Scanner;
public class Duke {
    public static void exitMessage() {
        System.out.println("Go away Anna");
        System.out.println("O-kay bye......");
    }
    public static void main(String[] args) {
        System.out.println("Hi it's Anna!\nWhat can I do for you?");
        Scanner in = new Scanner(System.in);
        String input;
        boolean exit = false;
        while (!exit) {
            input = in.nextLine();
            if (input.equals("bye")) {
                exitMessage();
                exit = true;
            } else {
                System.out.println(input);
            }
        }
    }
}
