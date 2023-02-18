package orca;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Scanner;

public class Orca {

    private static Ui ui;

    static final boolean FINISHED = true;
    static final String FILE_PATH = "./data/orca.txt";

    static ArrayList<Task> tasks = new ArrayList<>();
    static Scanner in = new Scanner(System.in);
    static String userInput = "";
    static CommandType commandType;
    static int taskNo;
    static Task newTask;

    public Orca() {
        ui = new Ui();
        File f = new File(FILE_PATH);
        if (!f.exists()) {
            f.getParentFile().mkdirs();
        } else {
            // Load data from file.
            try {
                FileReader reader = new FileReader(FILE_PATH);
                BufferedReader bufferedReader = new BufferedReader(reader);

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    boolean isDone = line.charAt(4) == 'X';
                    if (line.charAt(1) == 'T') {
                        String description = line.substring(7);
                        tasks.add(new Todo(description, isDone));
                    } else if (line.charAt(1) == 'D') {
                        String description = line.substring(7, line.indexOf(" (by: "));
                        String by = line.substring(line.indexOf(" (by: ") + 6, line.length() - 1);
                        tasks.add(new Deadline(description, by, isDone));
                    } else if (line.charAt(1) == 'E') {
                        String description = line.substring(7, line.indexOf(" (from: "));
                        String from =
                                line.substring(line.indexOf(" (from: ") + 8, line.indexOf(" to: "));
                        String to = line.substring(line.indexOf(" to: ") + 5, line.length() - 1);
                        tasks.add(new Event(description, from, to, isDone));
                    }
                }
                reader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void writeToFile() {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                writer.write(task.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void findCommandType() {
        if (userInput.equals("bye")) {
            commandType = CommandType.BYE;
        } else if (userInput.equals("list")) {
            commandType = CommandType.LIST;
        } else if (userInput.startsWith("mark")) {
            commandType = CommandType.MARK;
        } else if (userInput.startsWith("unmark")) {
            commandType = CommandType.UNMARK;
        } else if (userInput.startsWith("todo")) {
            commandType = CommandType.TODO;
        } else if (userInput.startsWith("deadline")) {
            commandType = CommandType.DEADLINE;
        } else if (userInput.startsWith("event")) {
            commandType = CommandType.EVENT;
        } else if (userInput.startsWith("delete")) {
            commandType = CommandType.DELETE;
        } else {
            commandType = CommandType.UNKNOWN;
        }
    }

    public static boolean isInputAvailable() {
        return in.hasNextLine();
    }

    public static void readUserInput() {
        userInput = in.nextLine();
    }

    public static void markTask(int taskNo) throws OrcaException {
        try {
            tasks.get(taskNo - 1).setDone(true);
        } catch (NullPointerException e) {
            throw new OrcaException("There is no task with this number.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new OrcaException("There is no task with this number.");
        }
        ui.printMarkedTask(tasks.get(taskNo - 1));
    }

    public static void unmarkTask(int taskNo) throws OrcaException {
        try {
            tasks.get(taskNo - 1).setDone(false);
        } catch (NullPointerException e) {
            throw new OrcaException("There is no task with this number.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new OrcaException("There is no task with this number.");
        }
        ui.printUnmarkedTask(tasks.get(taskNo - 1));
    }

    public static void addTask(Task newTask) {
        tasks.add(newTask);
    }

    private static void deleteTask(int taskNo) throws OrcaException {
        try {
            Task removedTask = tasks.remove(taskNo - 1);
            ui.printRemovedTask(removedTask, tasks.size());
        } catch (IndexOutOfBoundsException e) {
            throw new OrcaException("There is no task with this number.");
        }
    }

    public static int parseTaskNo(String userInput, int startIdx) throws OrcaException {
        try {
            return Integer.parseInt(userInput.substring(startIdx));
        } catch (NumberFormatException e) {
            throw new OrcaException("I cannot parse the integer.");
        } catch (StringIndexOutOfBoundsException e) {
            throw new OrcaException("I cannot parse the integer.");
        }
    }

    public static boolean executeCommand() throws OrcaException {
        switch (commandType) {
            case BYE:
                ui.printByeMessage();
                writeToFile();
                return FINISHED;
            case LIST:
                ui.printTasks(tasks);
                break;
            case MARK:
                taskNo = parseTaskNo(userInput, 5);
                markTask(taskNo);
                break;
            case UNMARK:
                taskNo = parseTaskNo(userInput, 7);
                unmarkTask(taskNo);
                break;
            case TODO:
                newTask = new Todo(userInput, 5);
                addTask(newTask);
                ui.printLatestAddedTask(tasks);
                break;
            case DEADLINE:
                newTask = new Deadline(userInput, 9);
                addTask(newTask);
                ui.printLatestAddedTask(tasks);
                break;
            case EVENT:
                newTask = new Event(userInput, 6);
                addTask(newTask);
                ui.printLatestAddedTask(tasks);
                break;
            case DELETE:
                taskNo = parseTaskNo(userInput, 7);
                deleteTask(taskNo);
                break;
            default:
                throw new OrcaException("Sorry. I can't understand what you mean. :(");
        }
        return !FINISHED;
    }

    public static void runOrca() {
        boolean isFinished = false;
        while (isInputAvailable()) {
            readUserInput();
            findCommandType();
            try {
                isFinished = executeCommand();
            } catch (OrcaException e) {
                System.out.println(e.getMessage());
            }
            if (isFinished) {
                break;
            }
        }
    }

    public static void finishProcess() {
        in.close();
    }

    public void run() {
        ui.printGreetingMessage();
        runOrca();
        finishProcess();
    }

    public static void main(String[] args) {
        new Orca().run();
    }
}
