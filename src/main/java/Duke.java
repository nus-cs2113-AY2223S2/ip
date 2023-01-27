import java.util.Scanner;

public class Duke {
    private static String line = "__________________________________________________________";

    public static void addList() {
        Scanner in = new Scanner(System.in);

        Task[] inputList = new Task[101];
        String userInput = in.nextLine();
        boolean exit = false;
        int length = 1;
        while (!exit) {
            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                for (int i = 1; i < length; i++) {
                    System.out.print((i) + ".");
                    inputList[i].printTask();
                }
                System.out.println(line);
            } else if (userInput.contains("unmark")) {
                String taskNum = userInput.substring(userInput.length()-1);
                int x = Integer.parseInt(taskNum);
                inputList[x].unmark();
            } else if (userInput.contains("mark")) {
                String taskNum = userInput.substring(userInput.length()-1);
                int x = Integer.parseInt(taskNum);
                inputList[x].markAsDone();
            } else {
                Task t = new Task(userInput);
                System.out.println(line);
                System.out.println("added: " + userInput);
                System.out.println(line);
                inputList[length] = t;
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
