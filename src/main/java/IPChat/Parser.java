package IPChat;

import java.util.Scanner;
import ipchatExceptions.IPChatExceptions;
import static IPChat.Storage.saveContent;
import static IPChat.TaskList.*;
public class Parser {
    // Compilation
    public static void mySequence() throws IPChatExceptions {
        while (checkInput == 0) {
            Scanner input = new Scanner(System.in);
            String statements = input.nextLine();
            String words = statements.split(" ")[0];
            switch (words) {
            case "bye":
                sayBye(statements);
                break;
            case "list":
                try {
                    listTasks(statements);
                } catch (IPChatExceptions e) {
                    System.out.println("Please give an input");
                }
                break;
            case "done":
                markDone(statements);
                saveContent(tasks);
                break;
            case "todo":
                toDoTasks(statements);
                saveContent(tasks);
                break;
            case "deadline":
                deadlineTasks(statements);
                saveContent(tasks);
                break;
            case "event":
                eventTasks(statements);
                saveContent(tasks);
                break;
            case "delete":
                deleteTasks(statements);
                saveContent(tasks);
                break;
            case "find":
                findTasks(statements);
                break;
            default:
                System.out.println("------------------------------------------");
                System.out.println("Please provide accurate readings");
            }
        }
    }
}
