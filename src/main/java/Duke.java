import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner((System.in));
        String command;
        String line = "    ____________________________________________________________";
        String welcome = line + "\n    Hello! I'm Piss\n" +
                "    What can I do for you?\n" + line + "\n";
        String goodbye = "    Bye. Hope to see you again soon!\n" + line;

        System.out.println(welcome);

        while(true) {
            command = input.nextLine();

            if(!Objects.equals(command, "bye")) {
                System.out.println(line + "\n" + command + "\n" + line + "\n");
            } else {
                System.out.println(goodbye);
                break;
            }

        }
    }
}
