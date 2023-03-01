package duke;

import duke.exception.UnknownCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final String COMMAND_MARK_TASK = "mark";
    private static final String COMMAND_UNMARK_TASK = "unmark";

    private static final String COMMAND_LIST_TASK_ITEM = "list";
    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_ADD_TODO = "todo";
    private static final String COMMAND_ADD_DEADLINE = "deadline";
    private static final String COMMAND_ADD_EVENT = "event";
    private static final String COMMAND_DELETE_TASK = "delete";
    private static final String FILE_PATH = "./duke.txt";

    private static ArrayList<Task> taskItems = new ArrayList<>();
    private static Ui ui;


    public static void addDeadlineTaskItems(String taskName, String by) {
        taskItems.add(new Deadline(taskName, by));
        ui.showTaskAdded(taskItems);
        saveTaskListToFile();
    }

    public static void addEventTaskItems(String taskName, String from, String to) {
        taskItems.add(new Event(taskName, from, to));
        ui.showTaskAdded(taskItems);
        saveTaskListToFile();
    }

    public static void addTodoTaskItems(String taskName) {
        taskItems.add(new Todo(taskName));
        ui.showTaskAdded(taskItems);
        saveTaskListToFile();
    }


    public static void deleteTaskItems(int taskItemNo) {
        ui.showTaskDeleted(taskItems, taskItemNo);
        taskItems.remove(taskItems.get(taskItemNo));
        saveTaskListToFile();
    }

    public static void markTaskItems(int taskItemNo, String command) {
        if (command.equals(COMMAND_MARK_TASK)) {
            taskItems.get(taskItemNo).setCompleted();
            ui.showTaskCompleted(taskItems, taskItemNo);
            saveTaskListToFile();
        } else {
            taskItems.get(taskItemNo).setIncomplete();
            ui.showTaskIncomplete(taskItems, taskItemNo);
            saveTaskListToFile();
        }
    }


    public static String getInput(Scanner in) {
        String line = in.nextLine();
        return line;
    }

    public static void handleInput(String input) {
        try {
            processInput(input);
        } catch (UnknownCommandException e) {
            ui.showUnknownCmdErr();
        }
    }

    public static void processInput(String input) throws UnknownCommandException {
        String[] words = input.split(" ", 2);
        String command = words[0];

        switch (command) {
        case COMMAND_LIST_TASK_ITEM:
            ui.listTask(taskItems);
            break;
        case COMMAND_MARK_TASK:
        case COMMAND_UNMARK_TASK:
            try {
                int taskItemNo = Integer.parseInt(words[1]) - 1;
                markTaskItems(taskItemNo, command);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showEmptyTaskNoErr();
            } catch (IndexOutOfBoundsException e) {
                ui.showTaskNoOutOfRangeErr();
            } catch (NumberFormatException e) {
                ui.showWrongTaskNoFormatErr();
            }
            break;
        case COMMAND_DELETE_TASK:
            try {
                int taskItemNo = Integer.parseInt(words[1]) - 1;
                deleteTaskItems(taskItemNo);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showEmptyTaskNoErr();
            } catch (IndexOutOfBoundsException e) {
                ui.showTaskNoOutOfRangeErr();
            } catch (NumberFormatException e) {
                ui.showWrongTaskNoFormatErr();
            }
            break;
        case COMMAND_ADD_DEADLINE:
            try {
                words = words[1].split(" /by ");
                String deadlineTaskName = words[0];
                String by = words[1];
                addDeadlineTaskItems(deadlineTaskName, by);
            } catch (IndexOutOfBoundsException e) {
                ui.showEmptyDeadlineDescErr();
            }
            break;
        case COMMAND_ADD_EVENT:
            try {
                words = words[1].split(" /from ");
                String eventTaskName = words[0];
                words = words[1].split(" /to ");
                String from = words[0];
                String to = words[1];
                addEventTaskItems(eventTaskName, from, to);
            } catch (IndexOutOfBoundsException e) {
                ui.showEmptyEventDescErr();
            }
            break;
        case COMMAND_ADD_TODO:
            try {
                String todoTaskName = words[1];
                addTodoTaskItems(todoTaskName);
            } catch (IndexOutOfBoundsException e) {
                ui.showEmptyTodoDescErr();
            }
            break;
        default:
            throw new UnknownCommandException();
        }
    }

    public static void writeToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);

        for (Task taskItem : taskItems) {
            String textToAdd = taskItem.toFile() + "\n";
            fw.write(textToAdd);
        }

        fw.close();
    }

    public static void saveTaskListToFile() {
        try {
            writeToFile(FILE_PATH);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void processFileContents(String content) {
        String[] words = content.split(" \\| ", 2);
        String taskType = words[0];
        words = words[1].split(" \\| ", 2);
        String taskStatus = words[0];

        switch (taskType) {
        case "T":
            String toDoTaskName = words[1];
            taskItems.add(new Todo(toDoTaskName));
            break;
        case "D":
            words = words[1].split(" \\| ", 2);
            String deadlineTaskName = words[0];
            String by = words[1];
            taskItems.add(new Deadline(deadlineTaskName, by));
            break;
        case "E":
            words = words[1].split(" \\| ", 2);
            String eventTaskName = words[0];
            words = words[1].split(" \\| ", 2);
            String from = words[0];
            String to = words[1];
            taskItems.add(new Event(eventTaskName, from, to));
        default:
            break;
        }

        if (taskStatus.startsWith("1")) {
            taskItems.get(taskItems.size() - 1).setCompleted();
        }
    }

    public static void readFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            processFileContents(s.nextLine());
        }
    }

    public static void loadTaskListFromFile() {
        try {
            readFileContents(FILE_PATH);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }
    }

    public Duke() {
        ui = new Ui();
        loadTaskListFromFile();
    }

    public void run() {
        ui.showWelcomeMessage();

        Scanner in = new Scanner(System.in);

        while (true) {
            String input = getInput(in);

            if (input.equals(COMMAND_EXIT)) {
                ui.showExitMessage();
                break;
            } else {
                handleInput(input);
            }
        }

    }

    public static void main(String[] args) {
        new Duke().run();
    }
}