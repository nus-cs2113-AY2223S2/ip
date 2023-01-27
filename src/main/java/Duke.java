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
        Task[] inputs = new Task[100];
        for(int k = 0; k < 100; k++) {
            inputs[k] = new Task("");
        }

        int count = 0;
        int number = 1;

        while (!isBye) {

            if (Objects.equals(input, "bye")) {
                isBye = true;
                break;
            }

            else if (Objects.equals(input, "list")) {
                for (int i = 0; i < count; i++) {
                    System.out.println(number + ".[" + inputs[i].getStatusIcon() + "] " + inputs[i].description);
                    number++;
                }
                number = 1;
            }

            else if (input.length() > 5 && (input.substring(0,5)).equals("mark ") && input.substring(5, input.length()).matches("[0-9]+")) {
                    Integer order = Integer.valueOf(input.substring(5, input.length()));
                    inputs[order - 1].markDone();
                    System.out.println("Nice! I've marked this task as done:\n" + "[" + inputs[order - 1].getStatusIcon() + "] " + inputs[order - 1].description);

                }

            else if (input.length() > 7 && (input.substring(0,7)).equals("unmark ") && input.substring(7, input.length()).matches("[0-9]+")) {
                    Integer order = Integer.valueOf(input.substring(7, input.length()));
                    inputs[order - 1].markUndone();
                    System.out.println("OK, I've marked this task as not done yet:\n" + "[" + inputs[order - 1].getStatusIcon() + "] " + inputs[order - 1].description);
                }

            else {
                System.out.println("added: " + input);
                inputs[count].description = input;
                inputs[count].isDone = false;
                count++;
            }
            input = scan.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
