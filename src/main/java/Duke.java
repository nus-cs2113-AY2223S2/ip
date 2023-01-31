import java.util.Scanner;
public class Duke {


    private static final String LINE = "____________________________________________________________";
    private static final String BLANK = "    ";

    public static void main(String[] args) {
        String logo = BLANK + LINE + "\n"
                + BLANK + "Hello! I'm Duke\n"
                + BLANK + "What can I do for you?\n"
                + BLANK + LINE;
        System.out.println("\n" + logo);
        Scanner in = new Scanner(System.in);
        String inputString;
        String[] inputs = new String[100];
        boolean isExit = false;
        int numberOfInputs = 0;

        while (!isExit) {
            inputString = in.nextLine();
            if (inputString.equals("list")) {
                doList(inputs, numberOfInputs);
            } else if (inputString.equals("bye")) {
                doExit();
                isExit = true;
            } else {
                inputs[numberOfInputs] = inputString;
                printInput(inputString);
                numberOfInputs += 1;
            }
        }
    }

    private static void doList(String[] inputs, int numberOfInputs) {
        System.out.println(BLANK + LINE);
        for (int i = 0; i < numberOfInputs; i += 1) {
            System.out.println(BLANK + (i + 1) + "." + inputs[i]);
        }
        System.out.println(BLANK + LINE);
    }
    private static void doExit() {
        System.out.println(BLANK + LINE);
        System.out.println(BLANK + "Bye. Hope to see you again soon!");
        System.out.println(BLANK + LINE);
    }
    private static void printInput(String input) {
        System.out.println(BLANK + LINE);
        System.out.println(BLANK + input);
        System.out.println(BLANK + LINE);
    }
}
