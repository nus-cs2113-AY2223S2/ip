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
        String[] listofItems = new String[100];
        String UserCmd = ScanObj.nextLine();
        int counter = 0;
        while (!UserCmd.equals("bye")) {
            if (UserCmd.equals("list")) {
                int j = 1;
                for (String i : listofItems) {
                    System.out.print(j);
                    System.out.print(". ");
                    System.out.println(i);
                    j++;
                    if (j > counter) {
                        break;
                    }
                }
            } else {
                System.out.print("added: ");
                System.out.println(UserCmd);
                listofItems[counter] = UserCmd;
                counter++;
            }
            UserCmd = ScanObj.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon !");
    }
}
