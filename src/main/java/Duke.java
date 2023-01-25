import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        String divider = "______________________________\n";
        System.out.println(divider + "Hello! I'm Duke\n" + "What can I do for you?\n" + divider);
        line = in.nextLine();

        //while (!(line = in.nextLine()).equals("bye"))
        while(!(line).equals("bye"))
        {
            System.out.println(divider + line + "\n" + divider);
            line = in.nextLine();
        }
        System.out.println(divider + "Bye. Hope to see you again soon!\n" + divider);
    }
}
