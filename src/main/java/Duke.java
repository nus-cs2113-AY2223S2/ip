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
        while(true){
            switch (userCommand){
                case "bye":
                    exit = true;
                default:
            }
            if(exit){
                System.out.println(divider);
                break;
            }
            else{
                System.out.println(divider);
                System.out.println(userCommand);
                System.out.println(divider);
                userCommand = scanner.nextLine();

            }
        }


        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(divider);
    }
}
