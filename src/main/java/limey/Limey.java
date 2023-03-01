package limey;
import limey.command.Task;
import limey.exception.commandNotFoundException;
import limey.iohandler.Parser;
import limey.iohandler.Ui;
import java.util.ArrayList;
import limey.iohandler.Storage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.stream.Collectors;
import limey.command.TaskList;


public class Limey {

    public static void main(String[] args) {
        //initialise variables
        String inLine;
        String firstWord;
        String[] wordList;
        ArrayList<Task> tasks = new ArrayList<>();
        String filePath = "SavedList";
        File f = new File(filePath);
        if (f.exists() && !f.isDirectory()) {
            Storage.retrieveSavedList(tasks);
        }

        //start user interface
        Ui.introMessage();
        Scanner in = new Scanner(System.in);
        inLine = in.nextLine().trim();
        wordList = Parser.splitInput(inLine);
        firstWord = Parser.getFirstWord(inLine);
        initialiseLimey(firstWord, tasks, wordList, inLine, in);

        exitLimey(tasks);
    }

    /**
     * Reads every input line from the command line interface to detect for any
     * given command (find, list, mark, unmark, delete, help, bye, todo, deadline, event)
     * If input command is not "bye", the respective function of the command will be done
     * and it will wait for the next command. If the input command is "bye"
     * the program will terminate
     *
     * @param firstWord the command given by the user input
     * @param tasks the list of current tasks
     * @param wordList string array of individual words given by the user input
     * @param inLine input line read from the user input on the command line interface
     * @param in the scanner to read new input lines from Command Line Interface
     */
    private static void initialiseLimey(String firstWord, ArrayList<Task> tasks, String[] wordList, String inLine, Scanner in) {
        while (!firstWord.equals("bye")) { //loop until input 'bye'
            switch (firstWord) { //switch case to decide what to do
            case "find":
                    String searchTerm = inLine.trim().substring(4).trim();
                    ArrayList<Task> listFound = new ArrayList<>(tasks.stream()
                            .filter(t -> t.getTaskIdentity().contains(searchTerm))
                            .collect(Collectors.toList()));
                    if(listFound.isEmpty()){
                        Ui.printNoFoundTask(searchTerm);
                        break;
                    } else if (searchTerm.equals("")) {
                        Ui.invalidMessage("Empty Search term");
                        break;
                    }
                    Ui.printTaskList(listFound, listFound.size());
                break;
            case "list":
                try {
                    Ui.printTaskList(tasks, Task.numTasks);
                } catch (IndexOutOfBoundsException e) {
                    Ui.invalidMessage("Invalid Index");
                }
                break;
            case "mark":
                TaskList.printMarkTask(tasks, wordList);
                break;
            case "unmark":
                TaskList.printUnmarkTask(tasks, wordList);
                break;
            case "delete":
                TaskList.printDeleteTask(tasks, wordList);
                break;
            case "help":
                Ui.helpMessage();
                break;
            default:
                try {
                    TaskList.makeNewTask(tasks, inLine.trim(), firstWord);
                } catch (commandNotFoundException e) {
                    Ui.invalidMessage("Invalid Command");
                } catch (StringIndexOutOfBoundsException e) {
                    Ui.invalidMessage("String Index out of bounds");
                }
                break;
            }
            inLine = in.nextLine().trim();
            wordList = Parser.splitInput(inLine);
            firstWord = wordList[0];
        }
    }

    /**
     * Stops the limey program after saving the current list and printing the goodbye message
     *
     * @param tasks the list of current tasks to be saved
     */
    private static void exitLimey(ArrayList<Task> tasks) {
        String filePath = "SavedList";
        File tempDir = new File(filePath);
        if (Task.numTasks == 0 & tempDir.exists()) {
            try {
                Storage.clearFile(filePath);
            } catch (IOException e) {
                Ui.invalidMessage("File cannot be cleared properly");
            }
        } else {
            try {
                Storage.writeToFile(filePath, tasks);
            } catch (IOException e) {
                Ui.invalidMessage("File not found");
            }
        }
        Ui.sayBye();
    }
}

