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

        // take in input
        String cmd;
        Scanner in = new Scanner(System.in);
        cmd = in.nextLine();

        // if input is bye
        while (!(cmd.equals("bye"))) {
            System.out.println("____________________________________________________________");
            System.out.println(cmd);
            System.out.println("____________________________________________________________");
            cmd = in.nextLine();
        }

        System.out.println("____________________________________________________________\nBye! Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
