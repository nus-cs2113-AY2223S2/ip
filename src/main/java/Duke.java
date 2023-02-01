import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String line = "_________________________________\n";
        System.out.println("Hello from\n" + logo);
        System.out.print(line + "Hello! I'm Duke\n" + "What can I do for you?\n" + line);
        String[] list = new String[100];
        int listCount = 0;
        while(true) {
            Scanner userInput = new Scanner(System.in);
            String input = userInput.nextLine();
            if (input.equals("bye")) {
                System.out.print(line + "Bye. Hope to see you again soon!\n" + line);
                break;
            } else if(input.equals("list")) {
                System.out.print(line);
                for (int i = 0; i < listCount; i++) {
                    System.out.println(i + 1 + ". " + list[i]);
                }
                System.out.print(line);
            } else {
                list[listCount] = input;
                listCount++;
                System.out.print(line + "added: " + input + "\n" + line);
            }
        }
    }
}
