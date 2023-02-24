package duke;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private Ui ui;
    static String FILEPATH = "duke.txt";
    private static int taskCount = 0;
    static ArrayList <Task> tasks = new ArrayList<>();

    public static void addTask(Task t) {
        tasks.add(t);
        taskCount++;
    }
    public static void deleteTask(String commandArgs) throws NoDescriptionException, IndexOutOfBoundsException{
        if (commandArgs.trim().length() == 0) {
            throw new NoDescriptionException();
        }
        final int deleteId = Integer.parseInt(commandArgs) - 1;
        if (deleteId < 0 || deleteId >= taskCount) {
            throw new IndexOutOfBoundsException();
        }
        System.out.println("I've deleted this task ∪･ω･∪:");
        System.out.println(tasks.get(deleteId));
        printLine();
        tasks.remove(deleteId);
        taskCount--;
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        try {
            printFileContents(FILEPATH);
        } catch (FileNotFoundException e) {
            System.out.println("No previous file, Duke will try to create a file to store your data.");
            try {
                new File(FILEPATH).createNewFile();
            } catch (IOException ioe) {
                throw new RuntimeException("Creation of file failed.", ioe);
            }
        }
        printLine();
        System.out.println("Hello! I'm Duke  U ´ᴥ` U\n" + "What can I do for you?");
        printLine();
    }
    private static void writeToFile(String filePath) throws IOException {
        BufferedWriter outputWriter;
        outputWriter = new BufferedWriter(new FileWriter(filePath));
        for (int i = 0; i < taskCount; i += 1) {
            outputWriter.write(tasks.get(i).toString() + System.lineSeparator());
        }
        outputWriter.flush();
        outputWriter.close();
    }
    public static void goodBye() {
        try {
            writeToFile(FILEPATH);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        printLine();
        System.out.println("Bye. Hope to see you again soon!ﾉ~");
        printLine();
    }

    public static void info() {
        printLine();
        System.out.println("This command is not valid, please read through the info and try again :)");
        System.out.println("Type: [todo] [something], and the system will add a new todo item to your list");
        System.out.println("Type: [event] [something] from: [when] to: [when], and the system will add an event and the timing");
        System.out.println("Type: [deadline] [something] by: [when], and the system will add a deadline");
        System.out.println("Type: [mark] [number], and the system will mark the item of the number as done");
        System.out.println("Type: [unmark] [number], and the system will unmark the item of the number.");
        System.out.println("Type: bye, to say goodbye to Duke!");
        System.out.println("Hope it helps!! woof a nice day ੯•໒꒱❤︎");
        printLine();
    }

    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        System.out.println("These are the task from your file: ");
        while (s.hasNext()) {
            String task = s.nextLine();
            System.out.println(task);
            String type = task.substring(1,2);
            final int descriptionId = task.lastIndexOf("]");
            String taskDescription = task.substring(descriptionId+1).trim().replace("(", "").replace(")", "");
            final int doneId = task.indexOf("[X]");
            switch(type) {
            case "T":
                try {
                    addTodo(taskDescription);
                    if (doneId != -1) {
                        tasks.get(taskCount-1).markAsDone();
                    }
                } catch (NoDescriptionException e) {
                    System.out.println("WOOFS!!! Something went wrong");
                    printLine();
                }
                break;
            case "E":
                try {
                    addEvent(taskDescription);
                    if (doneId != -1) {
                        tasks.get(taskCount-1).markAsDone();
                    }
                } catch (NoDescriptionException | FormatException e) {
                    System.out.println("WOOFS!!! Something went wrong");
                    printLine();
                }
                break;
            case "D":
                try {
                    addDeadline(taskDescription);
                    if (doneId != -1) {
                        tasks.get(taskCount-1).markAsDone();
                    }
                } catch (NoDescriptionException | FormatException e) {
                    System.out.println("WOOFS!!! Something went wrong");
                    printLine();
                }
                break;
            }
        }
        printLine();
    }

    public static void process(String s) throws InvalidCommandException {
        final String[] split = s.trim().split("\\s+", 2);
        final String[] commandTypeAndParams = split.length == 2 ? split : new String[]{split[0], ""};
        final String commandType = commandTypeAndParams[0];
        final String commandArgs = commandTypeAndParams[1];
        switch (commandType) {
        case "list":
            printListOfTasks();
            break;
        case "todo":
            try {
                addTodo(commandArgs);
                printLine();
                System.out.println("Got it. I've added this task: \n" + tasks.get(taskCount-1));
                System.out.println("Now you have " + taskCount + " tasks in your list.");
                printLine();
            } catch (NoDescriptionException e) {
                System.out.println("WOOFS!!! The description of a todo cannot be empty.");
                System.out.println("Please try to add todo again υ´• ﻌ •`υ");
                printLine();
            }
            break;
        case "deadline":
            try {
                addDeadline(commandArgs);
                printLine();
                System.out.println("Got it. I've added this task: \n" + tasks.get(taskCount-1));
                System.out.println("Now you have " + taskCount + " tasks in your list.");
                printLine();
            } catch (NoDescriptionException e) {
                System.out.println("WOOFS!!! The description of a deadline cannot be empty.");
                System.out.println("Please try to add deadline again υ´• ﻌ •`υ");
                printLine();
            } catch (FormatException e) {
                System.out.println("WOOFS!!! The format of entering deadline is incorrect.");
                System.out.println("Please try to add deadline again υ´• ﻌ •`υ");
                printLine();
            }
            break;
        case "event":
            try {
                addEvent(commandArgs);
                printLine();
                System.out.println("Got it. I've added this task: \n" + tasks.get(taskCount-1));
                System.out.println("Now you have " + taskCount + " tasks in your list.");
                printLine();
            } catch (NoDescriptionException e) {
                System.out.println("WOOFS!!! The description of a event cannot be empty.");
                System.out.println("Please try to add event again υ´• ﻌ •`υ");
                printLine();
            } catch (FormatException e) {
                System.out.println("WOOFS!!! The format of entering event is incorrect.");
                System.out.println("Please try to add event again υ´• ﻌ •`υ");
                printLine();
            }
            break;
        case "mark":
            try {
                markTask(commandArgs);
            } catch (NoDescriptionException e) {
                System.out.println("WOOFS!!! The index of entering task must be stated.");
                System.out.println("Please try to mark task again υ´• ﻌ •`υ");
                printLine();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("WOOFS!!! The index of entering task is not valid.");
                System.out.println("Please try to mark task again υ´• ﻌ •`υ");
                printLine();
            }
            break;
        case "unmark":
            try {
                unmarkTask(commandArgs);
            } catch (NoDescriptionException e) {
                System.out.println("WOOFS!!! The index of entering task must be stated.");
                System.out.println("Please try to mark task again υ´• ﻌ •`υ");
                printLine();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("WOOFS!!! The index of entering task is not valid.");
                System.out.println("Please try to mark task again υ´• ﻌ •`υ");
                printLine();
            }
            break;
        case "delete":
            try {
                deleteTask(commandArgs);
            } catch (NoDescriptionException e) {
                System.out.println("WOOFS!!! The index of entering task must be stated.");
                System.out.println("Please try to delete task again υ´• ﻌ •`υ");
                printLine();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("WOOFS!!! The index of entering task is not valid.");
                System.out.println("Please try to delete task again υ´• ﻌ •`υ");
                printLine();
            }
            break;
        case "help":
            info();
            break;
        default:
            throw new InvalidCommandException();
        }

    }

    private static String inputCommand() {
        Scanner scan = new Scanner(System.in);
        String s;
        s = scan.nextLine();
        while (s.trim().isEmpty() || s.trim().charAt(0) == '#') {
            s = scan.nextLine();
        }
        return s;
    }

    private static void unmarkTask(String commandArgs) throws NoDescriptionException, IndexOutOfBoundsException {
        if (commandArgs.trim().length() == 0) {
            throw new NoDescriptionException();
        }
        final int unmarkId = Integer.parseInt(commandArgs) - 1;
        if (unmarkId < 0 || unmarkId >= taskCount) {
            throw new IndexOutOfBoundsException();
        }
        if (!tasks.get(unmarkId).isDone) {
            System.out.println("This task hasn't been marked as done yet ∪･ω･∪");
        } else {
            tasks.get(unmarkId).markAsNotDone();
            System.out.println("I've unmarked this task ∪･ω･∪:");
            System.out.println(tasks.get(unmarkId));
        }
        printLine();
    }

    private static void markTask(String commandArgs) throws NoDescriptionException, IndexOutOfBoundsException {
        if (commandArgs.trim().length() == 0) {
            throw new NoDescriptionException();
        }
        final int markId = Integer.parseInt(commandArgs) - 1;
        if (markId < 0 || markId >= taskCount) {
            throw new IndexOutOfBoundsException();
        }
        if (tasks.get(markId).isDone) {
            System.out.println("This task has already been marked as done ੯•໒꒱❤︎");
        } else {
            tasks.get(markId).markAsDone();
            System.out.println("I've marked this task as done ੯•໒꒱❤︎:");
            System.out.println(tasks.get(markId));
        }
        printLine();
    }

    private static void addEvent(String commandArgs) throws NoDescriptionException, FormatException {
        final int indexOfFrom = commandArgs.indexOf("from:");
        final int indexOfTo = commandArgs.indexOf("to:");
        if (indexOfTo == -1 || indexOfFrom == -1) {
            throw new FormatException();
        }
        String eventDescription = commandArgs.substring(0, indexOfFrom).trim();
        String from = commandArgs.substring(indexOfFrom, indexOfTo).trim().replace("from:", "").trim();
        String to = commandArgs.substring(indexOfTo).trim().replace("to:", "").trim();
        if (eventDescription.trim().length() == 0 || from.length() == 0 || to.length() == 0) {
            throw new NoDescriptionException();
        }
        addTask(new Event(eventDescription, from, to));
    }

    private static void addDeadline(String commandArgs) throws NoDescriptionException, FormatException {
        final int indexOfDeadline = commandArgs.indexOf("by:");
        if (indexOfDeadline == -1) {
            throw new FormatException();
        }
        String deadlineDescription = commandArgs.substring(0, indexOfDeadline).trim();
        if (deadlineDescription.trim().length() == 0) {
            throw new NoDescriptionException();
        }
        String deadline = commandArgs.substring(indexOfDeadline).trim().replace("by:", "");
        if (deadline.trim().length() == 0) {
            throw new NoDescriptionException();
        }
        addTask(new Deadline(deadlineDescription, deadline));
    }

    private static void addTodo(String commandArgs) throws NoDescriptionException {
        if ((commandArgs.trim()).length() == 0) {
            throw new NoDescriptionException();
        }
        addTask(new Todo(commandArgs));
    }

    private static void printListOfTasks() {
        for (int i = 0; i < taskCount; i += 1) {
            System.out.print(i + 1);
            System.out.print(". ");
            System.out.println(tasks.get(i));
        }
        printLine();
    }
    public void run(String[] launchArgs) {
        this.ui = new Ui();
        ui.showWelcomeMessage();
        String s = ui.getUserCommand();
        while (!s.equals("bye")) {
            try {
                process(s);
            } catch (InvalidCommandException e) {
                System.out.println("WOOF!! The command is not found, please type 'help' for more info");
                printLine();
            }
            s = inputCommand();
        }
        ui.showGoodByeMessage();
    }

    public static void main(String[] args) {
        new Duke().run(args);
    }
}
