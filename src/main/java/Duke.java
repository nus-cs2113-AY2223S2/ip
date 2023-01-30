import java.util.Scanner;

public class Duke {
    private static String line = "____________________________________________________________";
    public static final int MAX_SIZE = 100;
    public static void greet() {
        System.out.println(line);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println(line);
    }
    public static void bye() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        String input;
        String[] list = new String[MAX_SIZE];
        int inputCounter = 0;
        while (true) {
            Scanner in = new Scanner(System.in);
            input = in.nextLine();
            list[inputCounter] = input;
            if (input.equalsIgnoreCase("bye")) {
                bye();
                break;
            }
            else if (input.equalsIgnoreCase("list")){
                System.out.println(line);
                for (int i = 0; i < inputCounter; i++) {
                    if (i == 0) {
                        System.out.println((i+1) + ". " + list[i]);
                    }
                    else if (i == (inputCounter - 1)) {
                        System.out.println((i+1) + ". " + list[i]);
                        System.out.println(line);
                    }
                    else {
                        System.out.println((i+1) + ". " + list[i]);
                    }
                }
            }
            else {
                System.out.println(line);
                System.out.println("added: " + list[inputCounter]);
                System.out.println(line);
                inputCounter++;
            }
        }
    }
}
