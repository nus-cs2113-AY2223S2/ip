package limey;

import limey.command.Deadline;
import limey.command.Event;
import limey.command.Task;
import limey.command.Todo;
import limey.exception.commandNotFoundException;
import limey.exception.invalidDateException;
import limey.iohandler.Parser;
import limey.iohandler.Speech;

import java.util.ArrayList;

import limey.iohandler.FileHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static limey.iohandler.FileHandler.clearFile;

public class Limey {

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        //initialise variables
        String filePath = "C:\\Users\\sunil\\desktop\\NUS\\CS2113\\Indiv_Project\\src\\main\\SavedList";
        File f = new File(filePath);
        if (f.exists() && !f.isDirectory()) {
            retrieveSavedList(tasks);
        }
        String inLine;
        String firstWord;
        String[] wordList;
        //start user interface
        Speech.sayHi();
        Scanner in = new Scanner(System.in);
        inLine = in.nextLine().trim();
        wordList = Parser.splitInput(inLine);
        firstWord = Parser.getFirstWord(inLine);

        initialiseLimey(firstWord, tasks, wordList, inLine, in);

        exitLimey(tasks);
    }

    private static void retrieveSavedList(ArrayList<Task> tasks) {
        try {
            String filePath = "C:\\Users\\sunil\\desktop\\NUS\\CS2113\\Indiv_Project\\src\\main\\SavedList";
            FileHandler.readFileToTasks(filePath, tasks);
        } catch (FileNotFoundException e) {
            Speech.invalidMessage("File not found");
        }
    }

    private static void exitLimey(ArrayList<Task> tasks) {
        String filePath = "C:\\Users\\sunil\\desktop\\NUS\\CS2113\\Indiv_Project\\src\\main\\SavedList";
        File tempDir = new File(filePath);
        if (Task.numTasks == 0 & tempDir.exists()) {
            try {
                clearFile(filePath);
            } catch (IOException e) {
                Speech.invalidMessage("File cannot be cleared properly");
            }
        } else {
            try {
                FileHandler.writeToFile(filePath, tasks);
            } catch (IOException e) {
                Speech.invalidMessage("File not found");
            }
        }
        Speech.sayBye();
    }


    private static void makeNewTask(ArrayList<Task> tasks, String inLine, String firstWord) throws commandNotFoundException {
        Task taskIn;
        inLine = inLine.substring(inLine.indexOf(" ") + 1);
        switch (firstWord) {
        case "deadline":
            try {
                taskIn = new Deadline(inLine);
            } catch (invalidDateException | StringIndexOutOfBoundsException e) {
                Speech.invalidMessage("Invalid deadline date");
                return;
            }
            break;
        case "event":
            try {
                taskIn = new Event(inLine);
            } catch (invalidDateException | StringIndexOutOfBoundsException e) {
                Speech.invalidMessage("Invalid event date");
                return;
            }
            break;
        case "todo": // currently default create a todo object
            taskIn = new Todo(inLine);
            break;
        default:
            throw new commandNotFoundException();
        }
        tasks.add(taskIn);
        Task.numTasks++;
        Speech.printAdded(taskIn, Task.numTasks);
    }


    private static void initialiseLimey(String firstWord, ArrayList<Task> tasks, String[] wordList, String inLine, Scanner in) {
        while (!firstWord.equals("bye")) { //loop until input 'bye'
            switch (firstWord) { //switch case to decide what to do
            case "list":
                try {
                    Speech.printTaskList(tasks, Task.numTasks);
                } catch (IndexOutOfBoundsException e) {
                    Speech.invalidMessage("Invalid Index");
                }
                break;
            case "mark":
                printMarkTask(tasks, wordList);
                break;
            case "unmark":
                printUnmarkTask(tasks, wordList);
                break;
            case "delete":
                printDeleteTask(tasks, wordList);
                break;
            default:
                try {
                    makeNewTask(tasks, inLine.trim(), firstWord);
                } catch (commandNotFoundException e) {
                    Speech.invalidMessage("Invalid Command");
                } catch (StringIndexOutOfBoundsException e) {
                    Speech.invalidMessage("String Index out of bounds");
                }
                break;
            }
            inLine = in.nextLine().trim();
            wordList = Parser.splitInput(inLine);
            firstWord = wordList[0];
        }
    }

    private static void printUnmarkTask(ArrayList<Task> tasks, String[] wordList) {
        try {
            unmarkTask(tasks, wordList);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            Speech.invalidMessage("Index out of bounds.");
        } catch (NumberFormatException e) {
            Speech.invalidMessage("Index given is not a number.");
        }
    }

    private static void unmarkTask(ArrayList<Task> tasks, String[] wordList) {
        String inLine;
        int taskIndex;
        inLine = wordList[1];
        taskIndex = Integer.parseInt(inLine) - 1;
        tasks.get(taskIndex).setDone(false);
        Speech.printUnmarked(tasks.get(taskIndex));
    }

    private static void printMarkTask(ArrayList<Task> tasks, String[] wordList) {
        try {
            markTask(tasks, wordList);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            Speech.invalidMessage("Index out of bounds.");
        } catch (NumberFormatException e) {
            Speech.invalidMessage("Index given is not a number.");
        }
    }

    private static void printDeleteTask(ArrayList<Task> tasks, String[] wordList) {
        try {
            deleteTask(tasks, wordList);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            Speech.invalidMessage("Index out of bounds.");
        } catch (NumberFormatException e) {
            Speech.invalidMessage("Index given is not a number.");
        }
    }

    private static void deleteTask(ArrayList<Task> tasks, String[] wordList) {
        String inLine;
        int taskIndex;
        inLine = wordList[1];
        taskIndex = Integer.parseInt(inLine) - 1;
        Speech.printDeleteTask(tasks.get(taskIndex));
        tasks.remove(taskIndex);
        Task.numTasks--;
    }

    private static void markTask(ArrayList<Task> tasks, String[] wordList) {
        int taskIndex;
        String inLine;
        inLine = wordList[1];
        taskIndex = Integer.parseInt(inLine) - 1;
        tasks.get(taskIndex).setDone(true);
        Speech.printMarked(tasks.get(taskIndex));
    }
}

