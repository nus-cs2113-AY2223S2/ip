import java.util.Scanner;

public class Duke {
    public static void printDivider(){
        String divider = "  ____________________________________";
        System.out.println(divider);
    }

    public static void printLogo(){
        String logo = "  ____        _        \n"
                + " |  _ \\ _   _| | _____ \n"
                + " | | | | | | | |/ / _ \\\n"
                + " | |_| | |_| |   <  __/\n"
                + " |____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo);
    }

    public static void printWelcome(){
        String welcomeMessage = "   Hello! I'm Duke\n   What can I do for you?";

        printDivider();
        printLogo();
        System.out.println(welcomeMessage);
        printDivider();
    }

    public static void printExit(){
        String exitMessage = "  Bye. Hope to see you again soon!";

        System.out.println(exitMessage);
        printDivider();
    }

    public static void main(String[] args) {

        printWelcome();
        Scanner input = new Scanner(System.in);

        while (input.hasNextLine()){
            String inputString = input.next();
            switch(inputString){
            case "bye":
                printExit();
                System.exit(0);
                break;

            default:
                input.nextLine();
                printDivider();
                System.out.println(inputString);
                printDivider();
            }
        }
        
    }
}
