import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you\n");

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        boolean isBye = false;
        String[] inputs;
        inputs = new String[100];
        int count = 0;
        int number = 1;

        while (!isBye) {

            if (Objects.equals(input, "bye")) {
                isBye = true;
                break;
            }
            else if (Objects.equals(input, "list")) {
                for (int i = 0; i < count; i++) {
                    System.out.println(number + ". " + inputs[i]);
                    number++;
                }
                number = 1;
            }
            else {
                System.out.println("added: " + input);
                inputs[count] = input;
                count++;
            }
            input = scan.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!\n");

    }
}
