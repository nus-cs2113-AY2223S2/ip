import java.util.Scanner;

public class Duke {

    public static String[] list = new String[100];
    public static int listLength = 0;
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

    public static void addToList(String userInput){
        list[listLength] = userInput;
        listLength += 1;
        printSeperator();
        System.out.println("\tadded: " + userInput);
        printSeperator();
    }

    public static void printList(){
        printSeperator();
        for (int i = 0; i < listLength; i++){
            System.out.println("\t" + (i+1) + ". " + list[i]);
        }
        printSeperator();
    }
    public static void ChatPolling(){
        String userInput;
        while(true) {
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();

            if (userInput.equals("bye")) {
                printSeperator();
                break;
            }

            if (userInput.equals("list")) {
                printList();
            }
            else {
                addToList(userInput);
            }

        }
    }


    public static void main(String[] args) {
        printLogo();
        printWelcome();
        ChatPolling();
        printGoodbye();
    }
}
