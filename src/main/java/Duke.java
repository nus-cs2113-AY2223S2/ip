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

        String input = scan.next();
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
        int counter = 0;

        Tasks[] list_of_tasks = new Tasks[101];
        for (int k = 0; k < 101; k++) {
            list_of_tasks[k] = new Tasks("");
        }

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        while (!"bye".equals(input)) {
            if ("list".equals(input)) {
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < counter; i++) {
                    System.out.println(
                            i + 1 + "." + " " + list_of_tasks[i].getStatusIcon() + " " + list_of_tasks[i].description);
                }
            } else if (input.length() >= 4 && input.substring(0, 4).equals("mark")) {
                Integer index = Integer.valueOf(input.substring(5, input.length()));
                list_of_tasks[index - 1].markAsDone();
                System.out.println("Nice! This task is completed");
                System.out
                        .println(list_of_tasks[index - 1].getStatusIcon() + " " + list_of_tasks[index - 1].description);
            } else if (input.length() >= 6 && input.substring(0, 6).equals("unmark")) {
                Integer index = Integer.valueOf(input.substring(7, input.length()));
                list_of_tasks[index - 1].markAsUnDone();
                System.out.println("Ok, This task is still not complete");
                System.out
                        .println(list_of_tasks[index - 1].getStatusIcon() + " " + list_of_tasks[index - 1].description);
            } else {
                System.out.println("added: " + input);

                list_of_tasks[counter].description = input;
                list_of_tasks[counter].isDone = false;
                counter++;
            }
            scan = new Scanner(System.in);
            input = scan.nextLine();

        }

        System.out.println("Goodbye. Hope to see u again :) \n");

        scan.close();

    }

}
