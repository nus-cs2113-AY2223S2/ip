package Duke;

import Duke.Tasks.Task;
import java.io.IOException;
public class Duke{
    public static final int MAX_TASK_LENGTH=100;
    private Storage storage;
    public static Task[] lists = new Task[MAX_TASK_LENGTH];
    public static int index=0;
    /**
     * Read the user input line by line
     * Identify users' command based on the input
     *  Process the command
     * @param line is the user input string.
     * @throws IOException
     */
    public static void readLines(String line) throws IOException {
        while(!line.equals("bye")){
            String[] input=line.split(" ",2);
            String command=input[0];
            try {
                switch (command) {
                case "mark":
                    TaskList.markTask(lists, input);
                    Storage.writeToFile(lists, index);
                    break;
                case "unmark":
                    TaskList.unmarkTask(lists, input);
                    Storage.writeToFile(lists, index);
                    break;
                case "todo":
                    TaskList.addTodo(lists, input, index);
                    index++;
                    Storage.writeToFile(lists, index);
                    break;
                case "event":
                    TaskList.addEvent(lists, input, index);
                    index++;
                    Storage.writeToFile(lists, index);
                    break;
                case "deadline":
                    TaskList.addDeadline(lists, input, index);
                    index++;
                    Storage.writeToFile(lists, index);
                    break;
                case "delete":
                    TaskList.deleteTask(lists, input, index);
                    index--;
                    Storage.writeToFile(lists, index);
                    break;
                case "find":
                    TaskList.findTask(lists, input[1], index);
                    break;
                case "list":
                    Ui.printList(lists, index);
                    break;
                case "help":
                    Ui.printHelp();
                    break;
                default:
                    Ui.printError();
                    break;
                }
            }catch (NumberFormatException e) {
                    System.out.println("Oops! Command should be followed by a number. " +
                            "(A valid index number should be separated by a space after the command)");
            } catch (IndexOutOfBoundsException e) {
                    System.out.println("Oops! Task index out of bound! " +
                            "(A valid index number should be separated by a space after the command)");
            } catch (NullPointerException e) {
                    System.out.println("Oops! Command index out of bound! " +
                            "(A valid index number should be separated by a space after the command)");
            } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
            line=Ui.nextLine();
        }
    }

    public void run() throws IOException {
        Ui.welcomeMessage();
        storage=new Storage();
        index=Storage.initializeList(lists);
        String line=Ui.initializeLine();
        readLines(line);
        Ui.goodbyeMessage();
    }
    public static void main(String[] args) throws IOException {
        try {
            new Duke().run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}