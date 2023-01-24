import java.util.Scanner;

public class Duke {

    public static void printSeperator(){
        System.out.println("____________________________________________________________\n");
    }

    public static void printWelcome(){
        System.out.println(
                "\tHello! I'm Duke\n" +
                        "\tWhat can I do for you?\n");
        printSeperator();
    }

    public static void printGoodbye(){
        System.out.println(
                "\tBye. Hope to see you again soon!\n"
        );
        printSeperator();
    }
    public static void printLogo(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\tHello from\n" + logo);
        printSeperator();
    }

    public static void echoChatTillBye(){
        String userInput;
        while(true) {
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();
            if (userInput.equals("bye")) {
                printSeperator();
                break;
            }
            printSeperator();
            System.out.println("\t" + userInput);
            printSeperator();
        }
    }


    public static void main(String[] args) {
        printLogo();
        printWelcome();
        echoChatTillBye();
        printGoodbye();
    }
}
