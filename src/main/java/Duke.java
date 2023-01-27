import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you ?");
        Scanner ScanObj = new Scanner(System.in);

        TaskManager listofItems = new TaskManager();
        String UserCmd = ScanObj.nextLine();
        int taskId = 0;
        while (!UserCmd.equals("bye")) {
            String[] WordsinUserCmd = UserCmd.split(" ");
            System.out.println(WordsinUserCmd[0]);
            if (UserCmd.equals("list")) {
                listofItems.listTask();
            } else if (WordsinUserCmd[0].equals("mark")) {
                System.out.println("this is a mark command");
                listofItems.markTask(Integer.parseInt(WordsinUserCmd[1]) - 1);
            } else if (WordsinUserCmd[0].equals("unmark")) {
                System.out.println("this is a unmark command");
                listofItems.unmarkTask(Integer.parseInt(WordsinUserCmd[1]) - 1);
            } else {
                System.out.print("added: ");
                System.out.println(UserCmd);
                listofItems.addTask(UserCmd, taskId);
                taskId++;
            }
            UserCmd = ScanObj.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon !");
    }
}
