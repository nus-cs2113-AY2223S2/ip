//package duke;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//
//import tasks.Task;
//import tasks.Deadline;
//import tasks.Event;
//import tasks.Todo;
//
//import dukeException.DukeException;
//
//public class Duke {
//    static ArrayList<Task> tasks = new ArrayList<Task>();
//    public static final String LINE_SPACING =  "\t____________________________________________________________";
//    public static void run() {
//        Ui userUi = new Ui();
//        userUi.greetUser();
//
//    }
//    /*
//    Main function that takes user input and interpets how to store and what to do with it
//     */
//    public static void main(String[] args) {
//
//        Ui userUi = new Ui();
//        userUi.greetUser();
//        try {
//            Files.createDirectories(Path.of("./data"));
//
//            File myObj = new File("./data/duke.txt");
//            if (myObj.createNewFile()) {
//                System.out.println("File created: " + myObj.getName());
//            } else {
//                readDukeText();
//            }
//        } catch (IOException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }
//
//        greetUser();
//
//        while (true) {
//
//            String input = userUi.readInput();
//            String[] splitInput = input.split(" ");
//            int taskSize = tasks.size();
//            switch (splitInput[0]) {
//            case "bye":
//                exit();
//                return;
//            case "todo":
//                insertTodo(input, false);
//                break;
//            case "event":
//                insertEvent(input, false);
//                break;
//            case "deadline":
//                insertDeadline(input, false);
//                break;
//            case "list":
//                listOut();
//                break;
//            case "mark":
//                markTask(splitInput);
//                saveTasks();
//                break;
//            case "unmark":
//                unMarkTask(splitInput);
//                saveTasks();
//                break;
//            case "delete":
//                deleteTask(splitInput);
//                break;
//            default:
//                System.out.println(LINE_SPACING);
//                System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");;
//                System.out.println(LINE_SPACING);
//                break;
//            }
//            if (taskSize != tasks.size()) {
//                saveTasks();
//            }
//        }
//    }
//    public static void saveTasks() {
//        try {
//            FileWriter myWriter = new FileWriter("./data/duke.txt");
//            myWriter.flush();
//            for (Task task : tasks) {
//                myWriter.write(task.toString() + "\n");
//            }
//            myWriter.close();
//            System.out.println("Successfully wrote to the file.");
//        } catch (IOException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }
//    }
//    public static void readDukeText() {
//
//        try {
//            ArrayList<String> tmpStrTasks = new ArrayList<>();
//            tmpStrTasks = (ArrayList<String>) Files.readAllLines(Paths.get("./data/duke.txt"), StandardCharsets.UTF_8);
//            for (int i = 0; i < tmpStrTasks.size(); i++) {
//                System.out.println(tmpStrTasks.get(i).substring(7));
//            }
//            for (int i = 0; i < tmpStrTasks.size(); i++) {
//                String newTask = tmpStrTasks.get(i);
//                Task tsk = null;
//                boolean isMark = newTask.charAt(4) == 'X';
//                switch (newTask.charAt(1)) {
//                case 'T':
//                    insertTodo("todo " + newTask.substring(7), isMark);
//                    break;
//                case 'D':
//                    insertDeadline("deadline " + newTask.substring(7), isMark);
//                    break;
//                case 'E':
//                    insertEvent("event " + newTask.substring(7), isMark);
//                    break;
//                }
//                System.out.println(newTask);
//            }
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
//    }
//
//
//    public static void deleteTask(String[] splitInput) {
//        String tmpTask = tasks.get(Integer.parseInt(splitInput[1]) - 1).toString();
//        tasks.remove(tasks.get(Integer.parseInt(splitInput[1]) - 1));
//        System.out.println("\tNoted. I've removed this task:");
//        System.out.println("\t  " + tmpTask);
//        System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
//    }
//    public static void unMarkTask(String[] splitInput) {
//        tasks.get(Integer.parseInt(splitInput[1]) - 1).unMark();
//        System.out.println(LINE_SPACING);
//        System.out.println("\tOK, I've marked this task as not done yet:");
//        System.out.println("\t  " + tasks.get(Integer.parseInt(splitInput[1]) - 1));
//        System.out.println(LINE_SPACING);
//    }
//    public static void markTask(String[] splitInput) {
//        tasks.get(Integer.parseInt(splitInput[1]) - 1).mark();
//        System.out.println(LINE_SPACING);
//        System.out.println("\tNice! I've marked this task as done:");
//        System.out.println("\t  " + tasks.get(Integer.parseInt(splitInput[1]) - 1));
//        System.out.println(LINE_SPACING);
//    }
//    public static void insertTodo(String input, boolean isMark) {
//
//        try {
//            Task tsk = new Todo(input.substring(5), isMark);
//            tasks.add(tsk);
//            addTaskPrint(tasks, tsk);
//        } catch (IndexOutOfBoundsException de) {
//            printExceptionMsg("todo", "description of a todo cannot be empty.");
//        } catch (DukeException de) {
//
//        }
//    }
//    public static void printExceptionMsg(String task, String err) {
//        System.out.println(LINE_SPACING);
//        System.out.println(" ☹ OOPS!!! The description of a todo cannot be empty.");
//        System.out.println(LINE_SPACING);
//    }
//    public static void addTaskPrint(ArrayList<Task> tasks, Task tsk) {
//        System.out.println(LINE_SPACING);
//        System.out.println("\tGot it. I've added this task:");
//        System.out.println("\t  " + tsk.toString());
//        System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
//        System.out.println(LINE_SPACING);
//    }
//    /*
//    This Returns the input as a Deadline object
//     */
//    public static void insertDeadline(String input, boolean isMark) {
//        int idx = input.indexOf("/by");
//        String desc = input.substring(8, idx);
//        String by = input.substring(idx + 3);
//        Deadline tsk = null;
//        try {
//            tsk = new Deadline(desc, isMark, by);
//            tasks.add(tsk);
//            addTaskPrint(tasks, tsk);
//        } catch (DukeException de) {
//
//        }
//    }
//    /*
//    This Returns the input as a Event object
//     */
//    public static void insertEvent(String input, boolean isMark) {
//        int idx = input.indexOf("/from");
//        int idx1 = input.indexOf("/to");
//        String desc = input.substring(5, idx);
//        String start = input.substring(idx + 5, idx1);
//        String end = input.substring(idx1 + 3);
//        Event tsk = null;
//        try {
//            tsk = new Event(desc, isMark, start, end);
//            tasks.add(tsk);
//            addTaskPrint(tasks, tsk);
//        } catch (DukeException de) {
//
//        }
//    }
//    /*
//    This Adds the input to an input array for the ability to keep track of
//     */
//    public static void addToList(String cmd, ArrayList<String> userInputs) {
//        userInputs.add(cmd);
//        userInputs.set(userInputs.size() - 1, userInputs.size() + ". [ ] " + userInputs.get(userInputs.size() - 1));
//    }
//    /*
//    This method lists out the tasks in order
//    */
//    public static void listOut() {
//        System.out.println(LINE_SPACING);
//        System.out.println("Here are the tasks in your list:");
//        for (int i = 0; i < tasks.size(); i++) {
//            System.out.println("\t" + (i + 1) + "." + tasks.get(i));
//        }
//        System.out.println(LINE_SPACING);
//    }
//    /*
//    Automated greet function
//    */
//    public static void greetUser() {
//        System.out.println(LINE_SPACING);
//        System.out.println("\tHello! I'm Duke");
//        System.out.println("\tWhat can I do for you?");
//        System.out.println(LINE_SPACING);
//    }
//    /*
//    Exit message
//    */
//    public static void exit() {
//        System.out.println(LINE_SPACING);
//        System.out.println("\tBye. Hope to see you again soon!");
//        System.out.println(LINE_SPACING);
//    }
//
//}

package duke;

import duke.Commands.Command;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import dukeException.DukeException;

public class Duke extends Command {

    private Storage storage;
    public static final String LINE_SPACING =  "\t____________________________________________________________";

    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(tasks);
    }

    public void run() {
        ui.greetUser();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.cmd();
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {

        new Duke("./data/duke.txt").run();
    }
}
