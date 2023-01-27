import java.util.*;

public class Duke {
    static final String HORIZONTAL_LINES ="____________________________________________________________";
    public static void sendLogo(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void sendGreeting(){
        String greeting_word="____________________________________________________________\n"
            + "Hello! I'm miniJohn\n"
            + "What can I do for you?\n"
            + "____________________________________________________________\n"
            + "Bye. Hope to see you again soon!\n"
            + "____________________________________________________________";
        System.out.println(greeting_word);
    }

    public static String getUserInput(){
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        return line;
    }

    public static String executeCommand(String userCommand){
        String feedback = userCommand;
        if(userCommand.equals("bye")){
            feedback = "END";
        }
        return feedback;
    }

    public static void showResultToUser(String feedback){
        System.out.println("\t"+HORIZONTAL_LINES);
        System.out.println("\t"+feedback);
        System.out.println("\t"+HORIZONTAL_LINES);

    }

    public static void main(String[] args) {
        sendLogo();
        sendGreeting();
        while(true){
            String userCommand = getUserInput();
            String feedback = executeCommand(userCommand);
            if(feedback=="END"){
                break;
            }
            showResultToUser(feedback);

        }
    }
}
