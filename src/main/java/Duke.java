import java.util.Scanner;
public class Duke {


    private static final String LINE = "____________________________________________________________\n";
    private static final String BLANK = "    ";

    public static void main(String[] args) {
        String logo = BLANK + LINE
                + BLANK + "Hello! I'm Duke\n"
                + BLANK + "What can I do for you?\n"
                + BLANK + LINE;
        System.out.println("\n" + logo);
        Scanner in = new Scanner(System.in);
        String inputString;
        inputString = in.nextLine();
        boolean isExit = inputString.equals("bye");
        while(!isExit) {
            System.out.println(BLANK + LINE
                               + BLANK + inputString + "\n"
                               + BLANK + LINE);
            inputString = in.nextLine();
            isExit = inputString.equals("bye");
        }
        System.out.println(BLANK + LINE +
                           BLANK + "Bye. Hope to see you again soon!\n" +
                           BLANK + LINE);
    }
}
