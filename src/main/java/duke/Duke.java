package duke;

import duke.command.Command;
import duke.exception.CommandNotRecognisedException;
import duke.exception.IllegalCharacterException;
import duke.exception.InvalidTaskNumberException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.Scanner;

import java.util.ArrayList;

public class Duke {

    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {

        openFile("./data/duke.txt");
        printStartMessage();

        String input;
        Scanner in = new Scanner(System.in);

        do {
            input = in.nextLine();
            try {
                processCommand(input);
            } catch (CommandNotRecognisedException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                printDivider();
            } catch (IllegalCharacterException e) {
                System.out.println("☹ OOPS!!! Input should not contain '|' or '-'.");
                printDivider();
            }
        } while (!input.equals(Command.COMMAND_BYE));

        File fileName = new File("./data/duke.txt");
        writeToFile(fileName);
    }

    public static void openFile(String input) {
        File file = new File(input);
        readFile(file);
    }

    public static void readFile(File file) {
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while((line=br.readLine())!=null) {
                initialiseTaskList(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("You have no pre-existing tasks :)");
        }  catch (IOException e) {
            System.out.println("Error reading file :(");
        }
    }

    public static void writeToFile(File filePath) {
        try {
            PrintWriter pw = new PrintWriter(filePath);
            writeTaskToFile(pw);

        }  catch (FileNotFoundException e) {
            File dir = new File("./data");
            if (!dir.isDirectory()) {
                System.out.println("Data directory not found. Creating new data directory");
                Path path = Paths.get("./data");
                try {
                    Files.createDirectories(path);
                } catch (IOException f) {
                    System.out.println("Failed to create directory");
                }
            }
            if (!filePath.isFile()) {
                System.out.println("File not found. Creating new text file");
                Path fileLoc = Paths.get("./data/duke.txt");
                try {
                    Files.createFile(fileLoc);
                } catch (IOException f) {
                    System.out.println("Failed to create file");
                }
            }
        }
    }

    public static void writeTaskToFile(PrintWriter pw) {
        for (int i = 0; i < tasks.size(); ++i) {
            pw.println(tasks.get(i).printToFile());
        }
        pw.close();
    }
    public static void initialiseTaskList(String line) {
        String[] task = line.split("\\||-");

        switch(task[0].trim()) {
        case "T":
            addTodoTask(task[2].trim());
            break;
        case "D":
            addDeadlineTask(task[2].trim(), task[3].trim());
            break;
        case "E":
            addEventTask(task[2].trim(), task[3].trim(), task[4].trim());
            break;
        }

        if (task[1].contains("X")) {
            try {
                markTaskDone(tasks.size()-1);
            } catch (NumberFormatException e) {
                System.out.println("☹ OOPS!!! Task number should be an integer.");
                printDivider();
            } catch (InvalidTaskNumberException e) {
                System.out.println("☹ OOPS!!! The task specified does not exist in the task list.");
                printDivider();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! The description of 'mark' cannot be empty.");
                printDivider();
            }
        }
    }

    public static void printDivider () {
        System.out.println("____________________________________________________________");
    }

    public static void printStartMessage() {
        String logo = " ____\n"
                + "|    \\ ___ _ _ ___\n"
                + "|  |  | .'| | | -_|\n"
                + "|____/|__,|\\_/|___|\n";

        printDivider();
        System.out.println("List Summary:");
        printTaskList();
        System.out.print(logo);
        System.out.println("Hi, I'm Dave!\n"
                + "What can I do for you?");
        printDivider();
    }

    public static void processCommand (String input) throws CommandNotRecognisedException, IllegalCharacterException {
        if (input.contains("|") || input.contains("-")) {
            throw new IllegalCharacterException();
        }

        String action = input.split(" ")[0];
        String[] taskDesc;

        switch (action) {
        case Command.COMMAND_BYE:
            printBye();
            break;
        case Command.COMMAND_LIST:
            printTaskList();
            break;
        case Command.COMMAND_MARK:
            try {
                markTaskDone(Integer.parseInt(input.split(" ")[1]) - 1);
                tasks.get(Integer.parseInt(input.split(" ")[1].trim())-1).printUnmarkMessage();
            } catch (NumberFormatException e) {
                System.out.println("☹ OOPS!!! Task number should be an integer.");
            } catch (InvalidTaskNumberException e) {
                System.out.println("☹ OOPS!!! The task specified does not exist in the task list.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! The description of 'mark' cannot be empty.");
            }
            printDivider();
            break;
        case Command.COMMAND_UNMARK:
            try {
                markTaskUndone(Integer.parseInt(input.split(" ")[1].trim())-1);
                tasks.get(Integer.parseInt(input.split(" ")[1].trim())-1).printUnmarkMessage();
            } catch (NumberFormatException e) {
                System.out.println("☹ OOPS!!! Task number should be an integer.");
            } catch (InvalidTaskNumberException e) {
                System.out.println("☹ OOPS!!! The task specified does not exist in the task list.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! The description of 'unmark' cannot be empty.");
            }
            printDivider();
            break;
        case Command.COMMAND_TODO:
            try {
                addTodoTask(input.substring(Command.COMMAND_TODO.length()).trim());
                printTaskAdded();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! The description of 'todo' cannot be empty.");
            }
            printDivider();
            break;
        case Command.COMMAND_DEADLINE:
            try {
                taskDesc = input.split("/by");
                addDeadlineTask(taskDesc[0].substring(Command.COMMAND_DEADLINE.length()).trim(), taskDesc[1].trim());
                printTaskAdded();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! The description of 'deadline' should include a task and deadline.");
            }
            printDivider();
            break;
        case Command.COMMAND_EVENT:
            try {
                taskDesc = input.split("/from|/to");
                addEventTask(taskDesc[0].substring(Command.COMMAND_EVENT.length()).trim()
                        , taskDesc[1].trim(), taskDesc[2].trim());
                printTaskAdded();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! The description of 'event' should include a task and time period.");
            }
            printDivider();
            break;
        case Command.COMMAND_DELETE:
            try {
                deleteTask(Integer.parseInt(input.split(" ")[1]) - 1);
            } catch (NumberFormatException e) {
                System.out.println("☹ OOPS!!! Task number should be an integer.");
            } catch (InvalidTaskNumberException e) {
                System.out.println("☹ OOPS!!! The task specified does not exist in the task list.");

            }
            printDivider();
            break;
        default:
            throw new CommandNotRecognisedException();
        }
    }

    public static void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
        printDivider();
    }

    private static void printTaskList() {
        if (tasks.size() == 0) {
            System.out.println("You are free today :)");
        } else {
            for (int i = 0; i < tasks.size(); ++i) {
                System.out.print(i+1 + ".");
                System.out.println(tasks.get(i));
            }
        }
        printDivider();
    }


    private static void markTaskDone(Integer taskIndex) throws InvalidTaskNumberException {
        if (taskIndex < 0 || taskIndex > tasks.size()) {
            throw new InvalidTaskNumberException();
        } else {
            tasks.get(taskIndex).markDone();
        }
    }


    private static void markTaskUndone(Integer taskIndex) throws InvalidTaskNumberException {
        if (taskIndex < 0 || taskIndex > tasks.size()) {
            throw new InvalidTaskNumberException();
        } else {
            tasks.get(taskIndex).markUndone();
        }
    }


    private static void addTodoTask(String task) {
        tasks.add(new ToDo(task));
    }


    private static void addDeadlineTask(String task, String deadline) {
        tasks.add(new Deadline(task, deadline));
    }

    private static void addEventTask(String task, String fromDate, String byDate) {
        tasks.add(new Event(task, fromDate, byDate));
    }

    private static void printTaskAdded() {
        System.out.println("Got it. I've added this task:\n " + tasks.get(tasks.size()-1)
                + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    private static void deleteTask(int taskIndex) throws InvalidTaskNumberException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new InvalidTaskNumberException();
        } else {
            System.out.println("Noted. I've removed this task:\n " + tasks.get(taskIndex)
                    + "\nNow you have " + (tasks.size()-1) + " tasks in the list.");
            tasks.remove(tasks.get(taskIndex));
        }
    }
}
