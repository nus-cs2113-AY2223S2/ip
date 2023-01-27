import java.util.Scanner;

public class Duke {
    private static String line = "__________________________________________________________";

    public static void addList() {
        Scanner in = new Scanner(System.in);

        String[] inputList = new String[100];
        String userInput = in.nextLine();
        boolean exit = false;
        int length = 0;
        while (!exit) {
            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                for (int i = 0; i < length; i++) {
                    System.out.println((i+1) + ". " +inputList[i]);
                }
                System.out.println(line);
            } else {
                System.out.println(line);
                System.out.println("added: " + userInput);
                System.out.println(line);
                inputList[length] = userInput;
                length++;
            }
            userInput = in.nextLine();
        }
        in.close();
    }

    public static void greet() {
        String line = "__________________________________________________________";
        System.out.println(line);
        System.out.println("Hello! i'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    public static void bye() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line + '\n');
        return;
    }

    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";*/
        //System.out.println("Hello from\n" + logo);
        greet();
        addList();
        bye();
    }
}
