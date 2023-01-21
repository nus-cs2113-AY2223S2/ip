import java.util.Scanner;

public class Duke {
    public static void dashSeperator() {//prints a dash line for separating text
        System.out.println("____________________________________________________________");
    }

    public static void sayGreeting() {
        dashSeperator();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        dashSeperator();
    }

    public static void sayGoodbye() {
        dashSeperator();
        System.out.println("Bye. Hope to see you again soon!");
        dashSeperator();
    }

    public static void sayEcho(String input) {
        dashSeperator();
        System.out.println("added: " + input);
        dashSeperator();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
        sayGreeting();
        boolean byeFlag = false;
        Scanner line = new Scanner(System.in);
        String[] userList = new String[100]; //no more than 100 tasks
        int listSize = 0;
        while (!byeFlag) {
            String userInput = line.nextLine();
            if (userInput.equals("bye")) {
                byeFlag = true;
                sayGoodbye();
            } else {
                if (userInput.equals("list")) {
                    for (int i = 0; i < listSize; i++) {
                        System.out.println((i + 1) + ". " + userList[i]);
                    }
                } else {
                    userList[listSize] = userInput;
                    listSize += 1;
                    sayEcho(userInput);
                }
            }
        }
    }
}
