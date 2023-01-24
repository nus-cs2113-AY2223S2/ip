import java.util.Scanner;
public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Conversation.line();
        Conversation.greeting();
        Conversation.question();
        Conversation.line();

        String command;
        Scanner in = new Scanner(System.in);
        String[] list = new String[100];
        int listCount = 0;

        while(true) {
            command = in.nextLine();
            if(command.equals("list")) {
                for(int i = 0; i < listCount; i += 1) {
                    System.out.println((i + 1) + ". " + list[i]);
                }
            }
            else if(command.equals("bye")) {
                Conversation.farewell();
                break;
            }
            else {
                System.out.print("added: ");
                Conversation.copy(command);
                list[listCount] = command;
                listCount += 1;
            }
        }
    }
}
