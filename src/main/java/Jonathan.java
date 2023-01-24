import java.util.Objects;
import java.util.Scanner;

public class Jonathan {
    public static void main(String[] args) {
        Scanner input = new Scanner((System.in));
        String command;
        String line = "    ____________________________________________________________";
        String welcome = line + "\n    Hello! I'm Jonathan\n" +
                "    What can I do for you?\n" + line + "\n";
        String goodbye = "    Bye. Hope to see you again soon!\n" + line;

        String[] list = new String[100];
        int taskCounter = 0;

        System.out.println(welcome);

        while(true) {
            command = input.nextLine();

            if(Objects.equals(command, "bye")) {
                System.out.println(goodbye);
                break;
            } else if(Objects.equals(command, "list")) {
                lookupTask(list, taskCounter);
            } else {
                list[taskCounter] = command;
                taskCounter += 1;
                System.out.println(line + "\n    added: " + command + "\n" + line + "\n");
            }

        }
    }

    public static void lookupTask(String[] list, int counter) {
        System.out.println("    ____________________________________________________________");
        for(int i=0; i < counter; i++) {
            System.out.println("    " + (i+1) + ". "  + list[i]);
        }
        System.out.println("    ____________________________________________________________\n");
    }

}
