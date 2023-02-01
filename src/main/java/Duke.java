import java.util.ArrayList;
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

        boolean isContinue = true;
        while(isContinue) {
            String command = Conversation.readCommand();
            String keyword = command.split(" ")[0];
            switch(keyword) {
            case "list":
                TaskList.listTasks();
                break;
            case "mark":
                TaskList.mark(command);
                break;
            case "unmark":
                TaskList.unmark(command);
                break;
            case "bye":
                Conversation.farewell();
                isContinue = false;
                break;
            default:
                TaskList.addToList(command);
                break;
            }

        }
    }
}
