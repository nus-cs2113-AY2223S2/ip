import java.util.Scanner;

public class Duke {
    private static final String NAME = "Duke";
    private static final String INITIAL_MSG = String.format("    Hello! I'm %s\n   What can I do for you?", NAME); 
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printResponse(INITIAL_MSG);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            printResponse(line);
        }
        scanner.close();
    }

    private static void printResponse(String response) {
        System.out.println("    ____________________________________________________________");
        System.out.println(String.format("    %s", response));
        System.out.println("    ____________________________________________________________");
    }
}
