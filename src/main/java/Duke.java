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
                System.out.println("Here are the tasks in your list:");

                // store in list
                for (int i = 1; num >= i; i += 1) {
                    System.out.println(i + ". " + list[i - 1]);
                }

                System.out.println("____________________________________________________________");
                // read next command
                cmd = in.nextLine();
            } else {
                String arr[] = cmd.split(" ",2);

                // marking test as done
                if (arr[0].equals("unmark")) {
                    int x;
                    x = Integer.parseInt(arr[1]);
                    String val[] = list[x-1].split("] ");
                    list[x-1] = "[ ] " + val[1];

                    System.out.println("____________________________________________________________");
                    System.out.println("Ok! Marking this task as not done yet");
                    System.out.println(list[x-1]);
                    System.out.println("____________________________________________________________");



                } else if (arr[0].equals("mark")) {
                    int x;
                    x = Integer.parseInt(arr[1]);

                    String val[] = list[x-1].split("] ");
                    list[x-1] = "[X] " + val[1];
                    System.out.println("____________________________________________________________");
                    System.out.println("Ok! Marking this task as done");
                    System.out.println(list[x-1]);
                    System.out.println("____________________________________________________________");




                }
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