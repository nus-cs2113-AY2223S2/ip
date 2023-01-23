import java.util.Scanner;

public class Duke {

    private static void printList(String[] s, int count) {
        System.out.println("---------------------------------------------");
        for (int i = 0; i < count; ++i) {
            String index = Integer.toString(i + 1);
            System.out.println(index + ". " + s[i]);
        }
        System.out.println("---------------------------------------------");
    }

    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        System.out.println("---------------------------------------------");
        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("---------------------------------------------");
        boolean isRunning = true;
        String line;
        String[] taskList = new String[100];
        int count = 0;
        while (isRunning) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            switch (line) {
            case "list":
                printList(taskList, count);
                break;
            case "bye":
                isRunning = false;
                break;

            default:
                System.out.println("---------------------------------------------");
                taskList[count] = line;
                count++;
                System.out.println("added: " + line);
                System.out.println("---------------------------------------------");
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("---------------------------------------------");
        return;
    }
}
