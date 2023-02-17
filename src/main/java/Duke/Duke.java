package Duke;

import java.util.ArrayList;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Arrays;

import java.nio.file.Path;
import java.nio.file.Files;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
public class Duke {
    private static final String LINE = "____________________________________________________________";
    private static final String BLANK = "    ";
    private static final String storagePath = "src/Storage.txt";


    public static void main(String[] args) {

        printWelcome();
        Scanner in = new Scanner(System.in);
        String inputString;
        ArrayList<Task> taskList = new ArrayList<>();
        int numberOfTasks = 0;

        try {
            numberOfTasks += initializeTaskList(storagePath, taskList);
        } catch (FileNotFoundException e) {
            System.out.println("Save file not found");
        }

        while (true) {
            try {
                inputString = in.nextLine();
                String[] command = inputString.split(" ", 2);

                switch (command[0]) {
                case "list":
                    doList(taskList, numberOfTasks);
                    break;

                case "bye":
                    doExit();
                    saveTaskList(taskList, numberOfTasks);
                    return;

                case "delete":
                    deleteTask(taskList, Integer.parseInt(command[1]) - 1   , numberOfTasks);
                    numberOfTasks -= 1;
                    break;

                //mark/unmark command
                case "mark":
                case "unmark":
                    doMarkOrUnmarked(taskList, numberOfTasks, command);
                    break;

                //add task to list
                case "todo":
                    addTodo(taskList, numberOfTasks, command);
                    numberOfTasks += 1;
                    break;

                case "deadline":
                    addDeadline(taskList, numberOfTasks, command);
                    numberOfTasks += 1;
                    break;

                case "event":
                    addEvent(taskList, numberOfTasks, command);
                    numberOfTasks += 1;
                    break;

                default:
                    System.out.println("Unknown command issued");

                }
            }

            catch (IndexOutOfBoundsException e) {
                System.out.println("Wrong index");
            }
            catch (NumberFormatException e) {
                System.out.println("Number only for argument");
            }
            catch (DukeException e) {
            }
            catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }

        }
    }

    private static void saveTaskList(ArrayList<Task> taskList, int numberOfTasks) throws IOException {
        writeToFile(storagePath, "");
        for (int i = 0; i < numberOfTasks; i += 1) {
            Task taskIndex = taskList.get(i);
            appendToFile(storagePath, taskIndex.saveInfo());
        }
    }

    private static int initializeTaskList(String filePath, ArrayList<Task> taskList) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        int numberOfSaves = 0;
        while (s.hasNext()) {
            String saveString = s.nextLine();
            String[] saveData = saveString.split("t/|m/|n/|s/");
            switch (saveData[0]) {
            case "T":
                taskList.add(new Todo(saveData[2]));
                if (saveData[1].startsWith("m/")) {
                    if (saveData[1].substring(2) == "X") {
                        taskList.get(numberOfSaves).setDone();
                    }
                }
                numberOfSaves += 1;
                break;

            case "D":
                taskList.add(new Deadline(saveData[2], saveData[3]));
                if (saveData[1].startsWith("m/")) {
                    if (saveData[1].substring(2) == "X") {
                        taskList.get(numberOfSaves).setDone();
                    }
                }
                numberOfSaves += 1;
                break;

            case "E":
                taskList.add(new Event(saveData[2], saveData[3], saveData[4]));
                if (saveData[1].startsWith("m/")) {
                    if (saveData[1].substring(2) == "X") {
                        taskList.get(numberOfSaves).setDone();
                    }
                }
                numberOfSaves += 1;
                break;
            }

        }
        return numberOfSaves;
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void addTodo(ArrayList<Task> taskList, int numberOfTasks, String[] command) {
            taskList.add(new Todo(command[1]));
            taskList.get(numberOfTasks).printAddTask(numberOfTasks);
    }

    private static void addDeadline (ArrayList<Task> taskList, int numberOfTasks, String[] command) throws DukeException {
            if (!command[1].contains("/by")) {
                System.out.println("Error: Use /by");
                throw new DukeException();
            }
            String[] deadLineInputs = command[1].split("/by ", 2);
            taskList.add(new Deadline(deadLineInputs[0],deadLineInputs[1]));
            taskList.get(numberOfTasks).printAddTask(numberOfTasks);
    }

    private static void addEvent(ArrayList<Task> taskList, int numberOfTasks, String[] command) throws DukeException {
        if (!(command[1].contains("/from ") && command[1].contains("/to "))) {
            System.out.println("Error: Use /from and /to");
            throw new DukeException();
        }
            String[] eventInputs = command[1].split("/from|/to");
            taskList.add(new Event(eventInputs[0], eventInputs[1], eventInputs[2]));
            taskList.get(numberOfTasks).printAddTask(numberOfTasks);
    }

    private static void doMarkOrUnmarked (ArrayList<Task> taskList, int numberOfTasks, String[] command) throws DukeException{
        int indexToChange = Integer.parseInt(command[1]) - 1;
        //Check for error
        if (indexToChange >= numberOfTasks || indexToChange < 0) {
            System.out.println("Index not found");
            throw new DukeException();
        }
        if (command[0].equals("mark")) {
            taskList.get(indexToChange).setDone();
            taskList.get(indexToChange).printMarkedTask();
        } else {
            taskList.get(indexToChange).setNotDone();
            taskList.get(indexToChange).printUnmarkedTask();
        }
    }

    private static void printWelcome() {
        String logo = BLANK + LINE + "\n"
                + BLANK + "Hello! I'm Duke.Duke\n"
                + BLANK + "What can I do for you?\n"
                + BLANK + LINE;
        System.out.println("\n" + logo);
    }

    private static void doList(ArrayList<Task> taskList, int numberOfTasks) {
        System.out.println(BLANK + LINE);
        for (int i = 0; i < numberOfTasks; i += 1) {
            System.out.print(BLANK + (i + 1) + ".");
            taskList.get(i).printTask();
        }
        System.out.println(BLANK + LINE);
    }
    private static void doExit() {
        System.out.println(BLANK + LINE);
        System.out.println(BLANK + "Bye. Hope to see you again soon!");
        System.out.println(BLANK + LINE);
    }
    private static void deleteTask(ArrayList<Task> tasks, int indexToDelete, int numberOfTasks) {
        tasks.get(indexToDelete).printDeleteTask(numberOfTasks);
        tasks.remove(indexToDelete);
    }
}
