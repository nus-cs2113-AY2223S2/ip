import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String line = "____________________________________________________\n";
        System.out.println("Hello from\n" + logo);
        System.out.print(line + "Hello! I'm Duke\n" + "What can I do for you?\n" + line);
        String[] list = new String[100];
        boolean[] isDone = new boolean[100];
        String toFill;
        int listCount = 0;
        while(true) {
            Scanner userInput = new Scanner(System.in);
            String input = userInput.nextLine();
            if (input.equals("bye")) {
                System.out.print(line + "Bye. Hope to see you again soon!\n" + line);
                break;
            } else if (input.startsWith("mark")) {
                int mark = Integer.parseInt(input.substring(5));
                isDone[mark - 1] = true;
                System.out.println(line + "Nice! I've marked this task as done:\n" + "  [X] " + list[mark - 1]);
                System.out.print(line);
            } else if (input.startsWith("unmark")) {
                int unmark = Integer.parseInt(input.substring(7));
                isDone[unmark - 1] = false;
                System.out.println(line + "OK, I've marked this task as not done yet:\n" + "  [ ] "+ list[unmark - 1]);
                System.out.print(line);
            } else if(input.equals("list")) {
                System.out.print(line + "Here are the tasks in your list:\n");
                for (int i = 0; i < listCount; i++) {
                    if (isDone[i]) {
                        toFill = "X";
                    }
                    else {
                        toFill = " ";
                    }
                    System.out.println(i + 1 + ". [" + toFill + "] " + list[i]);
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
