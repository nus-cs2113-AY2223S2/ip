import java.util.Scanner;
public class Greet {
    public static void main(String[] args) {
        String greet = "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________";
        System.out.println(greet);
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        String bye = "bye";
        while (!line.equals(bye)) {
            System.out.println("    ____________________________________________________________\n" +
                    line +
                    "\n    ____________________________________________________________");
            line = in.nextLine();
        }

        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");
    }
}
