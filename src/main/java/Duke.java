import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n";

        System.out.println(logo);

        String line = "    ____________________________________________________________\n";

        Scanner in = new Scanner(System.in);
        String action = in.nextLine();
        String bye = "bye";
        while(!action.equals(bye)){
            System.out.println(line);
            System.out.println("     " + action);
            System.out.println(line);
            action = in.nextLine();
        }
        System.out.println(line);
        System.out.println("     " + "Bye. Hope to see you again soon!");
        System.out.println(line);


    }
}
