
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("あなたの願いを入力下さい");
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                break;
            } else {
                System.out.println("You entered: " + input);

            }

        }
        System.out.println("じゃねえ～");
    }
}
