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
        command = in.nextLine();

        while(true) {
            Conversation.line();
            Conversation.copy(command);
            Conversation.line();
            command = in.nextLine();
            if(command.equals("bye")) {
                Conversation.farewell();
                break;
            }
        }
    }
}
