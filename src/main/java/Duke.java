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
        Scanner scanObj = new Scanner(System.in);
        TaskManager listofItems = new TaskManager();
        String userCmd = scanObj.nextLine();
        int taskId = 0;

        while (!userCmd.equals("bye")) {
            String[] userCmdasWords = userCmd.split(" ");
            System.out.println(userCmdasWords[0]);
            if (userCmd.equals("list")) {
                listofItems.listTask();
            } else if (userCmdasWords[0].equals("mark")) {
                System.out.println("this is a mark command");
                listofItems.markTask(Integer.parseInt(userCmdasWords[1]) - 1);
            } else if (userCmdasWords[0].equals("unmark")) {
                System.out.println("this is a unmark command");
                listofItems.unmarkTask(Integer.parseInt(userCmdasWords[1]) - 1);
            } else {
                System.out.print("added: ");
                System.out.println(userCmd);
                listofItems.addTask(userCmd, taskId);
                taskId++;
            }
            userCmd = scanObj.nextLine();
        }

        scanObj.close();
        System.out.println("Bye. Hope to see you again soon !");
    }
}
