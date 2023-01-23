import java.util.Scanner;

public class Inu {

    public static final String greeting = " Woof Woof! I'm Inu! Your personal Shib-Assistant ^.^\n";
    public static final String prompt = " What can I do for you today?\n";
    public static final String farewell = " Bye. Hope to see you again soon!\n";
    public static final String divider = "____________________________________________________________\n";

    public Inu() {
        System.out.println(divider + greeting);
        System.out.println(prompt + divider);
        readCommand();
    }

    public void readCommand() {

        String line;
        String checkLine;
        Scanner in = new Scanner(System.in);

        line = in.nextLine();
        checkLine = line.toLowerCase();

        while (!checkLine.equals("bye")) {

            System.out.println(divider + line + '\n' + divider);
            line = in.nextLine();
            checkLine = line.toLowerCase();

        }

        System.out.println(divider + farewell + divider);

    }

    public static void main(String[] args) {
        new Inu();
    }
}
