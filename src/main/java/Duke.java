
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
public class Duke {
    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    public static void main(String[] args) throws NoKeyException{
        String file = "data/Duke.txt";
        try {
            printFileContents("data/Duke.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        Scanner in = new Scanner(System.in);
        CommandManager.sayHi();
        CommandManager command = new CommandManager();
        command.setUserInput(in.nextLine());
        while (!command.getUserInput().equals("bye")) {
            String[] userInput = command.getUserInput().split(" ", 2);
            String key = userInput[0];
            switch (key) {
            case "mark":
                try {
                    Tasks markTask = Tasks.getTaskList().get(Integer.parseInt(userInput[1]) - 1);
                    markTask.setMarked(true);
                    command.setKey("mark");
                    command.printOutput(markTask);
                } catch (NumberFormatException e) {
                    System.out.println("You entered an INVALID token after mark.\nPlease enter a number" +
                            " after mark. EXAMPLE: mark 2");
                }
                break;
            case "unmark":
                try {
                    Tasks unMarkTask = Tasks.getTaskList().get(Integer.parseInt(userInput[1]) - 1);
                    unMarkTask.setMarked(false);
                    command.setKey("unmark");
                    command.printOutput(unMarkTask);
                } catch (NumberFormatException e) {
                    System.out.println("You entered an INVALID token after mark.\n" +
                            "CORRECT UNMARK FORMAT: unmark 2");
                }
                break;
            case "todo":

                try {
                    Tasks newToDo = new Todo(userInput[1], false);
                    Tasks.addToList(newToDo);
                    command.setKey("add");
                    command.printOutput(newToDo);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("There is NO description!!!");
                }
                break;
            case "deadline":
                try {
                    String[] taskSlashDate = userInput[1].split("/", 2);
                    Tasks newDeadline = new Dateline(taskSlashDate[0], false, taskSlashDate[1]);
                    Tasks.addToList(newDeadline);
                    command.setKey("add");
                    command.printOutput(newDeadline);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("INVALID DEADLINE FORMAT:" + "\nCORRECT DEADLINE FORMAT: \"deadline return book/by Sunday\"");
                }
                break;
            case "event":
                String[] eventSlashDate = userInput[1].split("/", 3);
                Tasks newEvent = new Event(eventSlashDate[0], false, eventSlashDate[1], eventSlashDate[2]);
                Tasks.addToList(newEvent);
                command.setKey("add");
                command.printOutput(newEvent);
                break;
            case "list":
                command.printOutput();
                break;
            default:
                throw new NoKeyException("Come on, give me something that I can work with!");
            }
            try {
                CommandManager.writeToFile(file);
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
            command.setUserInput(in.nextLine());
        }
        CommandManager.sayBye();
    }
}
