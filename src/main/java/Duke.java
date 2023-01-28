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

        ArrayList<Task> ListOfTasks = TaskList.initTaskList();
        boolean isContinue = true;
        while(isContinue) {
            String command = Conversation.readCommand();
            String[] arrOfCommand = command.split(" ");
            switch(arrOfCommand[0]) {
            case "list":
                TaskList.listOut(ListOfTasks);
                break;
            case "mark":
                TaskList.mark(arrOfCommand[1], ListOfTasks);
                break;
            case "unmark":
                TaskList.unmark(arrOfCommand[1], ListOfTasks);
                break;
            case "bye":
                Conversation.farewell();
                isContinue = false;
                break;
            default:
                TaskList.addToList(command, ListOfTasks);
                break;
            }

        }
    }
}
