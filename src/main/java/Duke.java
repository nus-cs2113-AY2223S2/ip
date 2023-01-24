import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String logo =
                  " __   __\n"
                + "|  | |  |   ____     _  _   _    _\n"
                + "|  |_|  |  / _  \\   | |/_\\ | |  | |\n"
                + "|   _   | | |_|  \\  |  /   | \\_/  |\n"
                + "|__| |__|  \\___/\\_\\ |_|     \\__/|_|\n";

        String divider = "____________________________________________________________";

        System.out.println(divider);
        System.out.println(logo);
        System.out.println("Hello! I'm Haru");
        System.out.println("What can I do for you?");
        System.out.println(divider);

        String userCommand = scanner.nextLine();

        boolean exit = false;
        String[] storedUserTexts = new String[100];
        int userTextCount = 0;
        while(!exit){
            System.out.println(userCommand);
            switch (userCommand){
                case "list":
                    // display list
                    System.out.println(divider);
                    for(int i=0;i<userTextCount;i++){
                        System.out.println((i+1)+". "+storedUserTexts[i]);
                    }
                    System.out.println(divider);
                    userCommand = scanner.nextLine();
                    break;
                case "bye":
                    exit = true;
                    break;
                default:
                    System.out.println(divider);
                    System.out.println("added: " + userCommand);
                    System.out.println(divider);
                    storedUserTexts[userTextCount] = userCommand;
                    userCommand = scanner.nextLine();
                    userTextCount++;
                    break;
            }
        }

        System.out.println(divider);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(divider);
    }
}
