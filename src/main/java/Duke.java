import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("____________________________________________________________\nHola! I'm Duke\n" + logo);
        System.out.println("How can I help you today?\n____________________________________________________________");

        // store in array
        String[] list = new String[100];
        // num of items in list
        int num;
        num = 0;

        // take in input
        String cmd;
        Scanner in = new Scanner(System.in);
        cmd = in.nextLine();

        // if input is bye
        while (!(cmd.equals("bye"))) {
            if (cmd.equals("list")) {
                System.out.println("____________________________________________________________");

                // store in list
                for (int i = 1; num >= i; i += 1) {
                    System.out.println(i + ". " + list[i - 1]);
                }

                System.out.println("____________________________________________________________");
                // read next command
                cmd = in.nextLine();
            } else {
                System.out.println("____________________________________________________________");
                System.out.println("added: " + cmd);
                System.out.println("____________________________________________________________");

                list[num] = cmd;
                num += 1;
                // read next command
                cmd = in.nextLine();
            }
        }

        System.out.println("____________________________________________________________\nBye! Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
