import java.util.Scanner;

public class Duke {

    public static void greet_user() {
        System.out.println("Hello! I'm Duke \n");
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("How can i help u? \n");
    }

    public static void echo() {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        if ("bye".equals(input)) {
            System.out.println("Goodbye. Hope to see u again :) \n");
        } else {
            System.out.println(input);
            echo();
        }

        scan.close();

    }

    public static void main(String[] args) {
        greet_user();

        String[] inputs;
        inputs = new String[101];
        int counter = 0;

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        while (!"bye".equals(input)) {
            if ("list".equals(input)) {
                for (int i = 0; i < counter; i++) {
                    System.out.println(i + 1 + "." + " " + inputs[i]);
                }
            } else {
                System.out.println("added: " + input);
                inputs[counter] = input;
                counter++;
            }
            scan = new Scanner(System.in);
            input = scan.nextLine();

        }

        System.out.println("Goodbye. Hope to see u again :) \n");

        scan.close();

    }

}
