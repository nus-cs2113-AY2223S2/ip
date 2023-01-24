import java.util.Scanner;

public class Add {
    public static void main(String[] args) {
        String[] list = new String[100];
        String greet = "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________";
        System.out.println(greet);
        String line;
        int total = 0;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                System.out.println("    ____________________________________________________________r");
                for (int i = 0 ; i < total ; i++) {
                    System.out.println(i+1 + ". " + list[i]);
                }
                System.out.println("    ____________________________________________________________");
                line = in.nextLine();
                continue;
            }
            System.out.println("    ____________________________________________________________\n    added: " +
                    line +
                    "\n    ____________________________________________________________");
            list[total] = line;
            line = in.nextLine();
            total++;
        }

        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");
    }
}
