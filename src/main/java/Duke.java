import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void printAdd(String prompt) {
        System.out.println("    ____________________________________________________________\n" +
                "     added: " + prompt + "\n" +
                "    ____________________________________________________________");
    }
    public static void printMark(Task[] prompt, int number) {
        prompt[number].setDone(true);
        System.out.println("    ____________________________________________________________\n" +
                "     Great job! I will mark this task as done: \n" +
                "     [X] " + prompt[number].getName() + "\n" +
                "    ____________________________________________________________");
    }
    public static void printUnmark(Task[] prompt, int number) {
        prompt[number].setDone(false);
        System.out.println("    ____________________________________________________________\n" +
                "     Alright, I have marked this task as undone: \n" +
                "     [ ] " + prompt[number].getName() + "\n" +
                "    ____________________________________________________________");
    }

    public static void printList(Task[] prompt) {
        System.out.println("    ____________________________________________________________\n" +
                "     These are the tasks in your list: ");
        int counter = 1;
        for (Task item : prompt) {
            System.out.println("     " + counter + ".[" + item.getStatus() + "] " + item.getName());
            counter++;
        }
        System.out.println("    ____________________________________________________________");

    }

    public static void main(String[] args) {

        String greet = "    ____________________________________________________________\n" +
                "     Hello! I'm kimo\n" +
                "     What can I do for you?\n" +
                "   ____________________________________________________________";
        System.out.println(greet);

        Task[] userList = new Task[100];
        String userInput;
        int listLength = 0;
        Scanner in = new Scanner(System.in);

        inputLoop:
        while (true) {
            userInput = in.nextLine();
            switch (userInput) {
            case "list":
                printList(Arrays.copyOf(userList, listLength));
                break;

            case "bye":
                break inputLoop;

            default:
                if (userInput.matches("mark \\d+") ) {
                    String[] array = userInput.split(" ");
                    int listNum = Integer.parseInt(array[1]);
                    if (listNum - 1 < (listLength)) {
                        printMark(userList, listNum - 1);
                    }
                }
                else if (userInput.matches("unmark \\d+")) {
                    String[] array = userInput.split(" ");
                    int listNum = Integer.parseInt(array[1]);
                    if (listNum - 1 < (listLength)) {
                        printUnmark(userList, listNum - 1);
                    }
                }
                else {
                    Task task = new Task(userInput);
                    userList[listLength] = task;
                    listLength++;
                    printAdd(userInput);
                }
            }
        }

        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");
    }
}

/*
String logo = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
System.out.println("Hello from\n" + logo);
*/
