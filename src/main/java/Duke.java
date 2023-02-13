import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;

public class Duke {
    public static final String HORIZONTAL_LINE = "\t____________________________________________________________"
            + System.lineSeparator();
    public static final int MARK_TASK_NUMBER_INDEX = 5;
    public static final int UNMARK_TASK_NUMBER_INDEX = 7;

    public static String printCurrTask() {
        String output = "\t" + Task.tasks[Task.numOfTasks] + System.lineSeparator();
        Task.numOfTasks++;
        output += "\tNow you have " + Task.numOfTasks + " tasks in the list.";
        return output;
    }

    public static String markTask(String input) {
        int itemIndex = Integer.parseInt(input.substring(MARK_TASK_NUMBER_INDEX)) - 1;
        String output;
        try {
            Task.tasks[itemIndex].setTaskStatus(true);
            output = "\tNice! I've marked this task as done:" + System.lineSeparator() + "\t\t"
                    + Task.tasks[itemIndex];
        } catch (NullPointerException e) {
            output = "\tSorry, the task does not exist.";
        } catch (ArrayIndexOutOfBoundsException e) {
            output = "\tSorry, the task does not exist.";
        }
        return output;
    }

    public static String unmarkTask(String input) {
        int itemIndex = Integer.parseInt(input.substring(UNMARK_TASK_NUMBER_INDEX)) - 1;
        String output;
        try {
            Task.tasks[itemIndex].setTaskStatus(false);
            output = "\tOK, I've marked this task as not done yet:" + System.lineSeparator() + "\t\t"
                    + Task.tasks[itemIndex];
        } catch (NullPointerException e) {
            output = "\tSorry, the task does not exist.";
        } catch (ArrayIndexOutOfBoundsException e) {
            output = "\tSorry, the task does not exist.";
        }
        return output;
    }

    public static String getList() {
        String output = "\tHere are the tasks in your list:" + System.lineSeparator();
        for (int i = 0; i < Task.numOfTasks; i++) {
            output += "\t" + (i + 1) + "." + Task.tasks[i];
            if (i != Task.numOfTasks - 1) {
                output += System.lineSeparator();
            }
        }
        return output;
    }

    public static void createTodoTask(String input) throws DukeException {
        if ((input.trim()).length() == 0) {
            // task has no description
            throw new DukeException();
        }
        Task.tasks[Task.numOfTasks] = new Todo(input);
    }

    public static void createDeadlineTask(String input) throws DukeException {
        int byIndex = input.indexOf("/by");
        if (byIndex == -1 || byIndex == 0) {
            // /by does not exist or task does not have description
            throw new DukeException();
        }
        String description = (input.substring(0, byIndex)).trim();
        String restOfInput = input.substring(byIndex);

        int startingIndex = (restOfInput).indexOf(" ");
        if (startingIndex == -1) {
            // /by has no description
            throw new DukeException();
        }
        String by = (restOfInput.substring(startingIndex)).trim();
        if (by.length() == 0) {
            // /by's description only has spaces
            throw new DukeException();
        }
        Task.tasks[Task.numOfTasks] = new Deadline(description, by);
    }

    public static void createEventTask(String input) throws DukeException {
        int descriptionIndex = input.indexOf("/from");
        if (descriptionIndex == -1 || descriptionIndex == 0) {
            // /from does not exist or task has no description
            throw new DukeException();
        }
        String description = (input.substring(0, descriptionIndex)).trim();
        String restOfInput = input.substring(descriptionIndex);
        
        int fromIndex = restOfInput.indexOf(" ");
        int toIndex = restOfInput.indexOf("/to");
        if (toIndex == -1) {
            // /to does not exist
            throw new DukeException();
        }
        String from;
        try {
            from = (restOfInput.substring(fromIndex, toIndex)).trim();
        } catch (StringIndexOutOfBoundsException e) {
            // no space between /from and /to in command
            throw new DukeException();
        }
        if (from.length() == 0) {
            // /from's description only has spaces
            throw new DukeException();
        }

        String toPartOfInput = restOfInput.substring(toIndex);
        int toDescriptionIndex = toPartOfInput.indexOf(" ");
        if (toDescriptionIndex == -1) {
            // /to has no description
            throw new DukeException();
        }
        String to = (toPartOfInput.substring(toDescriptionIndex)).trim();
        if (to.length() == 0) {
            // /to's description only has spaces
            throw new DukeException();
        }

        Task.tasks[Task.numOfTasks] = new Event(description, from, to);
    }

    public static String addTask(String taskType, String restOfInput) {
        String output = "\tGot it. I've added this task:" + System.lineSeparator() + "\t";
        switch (taskType) {
        case "todo":
            try {
                createTodoTask(restOfInput);
            } catch (DukeException e) {
                return "\tDescription of todo cannot be empty.";
            }
            break;
        case "deadline":
            try {
                createDeadlineTask(restOfInput);
            } catch (DukeException e) {
                return "\tInvalid Command. There may be some missing deadline fields.";
            }
            break;
        case "event":
            try {
                createEventTask(restOfInput);
            } catch (DukeException e) {
                return "\tInvalid Command. There may be some missing event fields.";
            }
            break;
        }
        output += printCurrTask();
        return output;
    }

    public static String getCommand(String input, String firstWord, String restOfInput) throws DukeException {
        switch (firstWord) {
        case "list":
            return getList();
        case "todo":
            // fallthrough
        case "deadline":
            // fallthrough
        case "event":
            return addTask(firstWord, restOfInput);
        case "mark":
            return markTask(input);
        case "unmark":
            return unmarkTask(input);
        default:
            throw new DukeException();
        }
    }

    public static String getResponse(String input) {
        // get first word from input
        String firstWord;
        String restOfInput = "";
        int indexOfFirstSpace = input.indexOf(" ");
        if (indexOfFirstSpace != -1) {
            // separate first word from the rest of input
            firstWord = input.substring(0, indexOfFirstSpace);
            restOfInput = input.substring(indexOfFirstSpace + 1);
        } else {
            // only 1 word in input
            firstWord = input;
        }

        // determine the type of command
        String output;
        try {
            output = getCommand(input, firstWord, restOfInput);
        } catch (DukeException e) {
            output = "\tInvalid command.";
        }
        return output;
    }

    public static void addOldTodo(String line) {
        int isDone = Integer.parseInt(line.substring(0,1));
        String description = line.substring(2);
        Task.tasks[Task.numOfTasks] = new Todo(description);
        if (isDone == 1) {
            Task.tasks[Task.numOfTasks].isDone = true;
        } else {
            Task.tasks[Task.numOfTasks].isDone = false;
        }
        Task.numOfTasks++;
    }

    public static void addOldDeadline(String line) {
        int isDone = Integer.parseInt(line.substring(0,1));
        String restOfLine = line.substring(2);
        int indexOfBy = restOfLine.indexOf("|");
        String description = restOfLine.substring(0, indexOfBy);
        String by = restOfLine.substring(indexOfBy + 1);

        Task.tasks[Task.numOfTasks] = new Deadline(description, by);
        if (isDone == 1) {
            Task.tasks[Task.numOfTasks].isDone = true;
        } else {
            Task.tasks[Task.numOfTasks].isDone = false;
        }
        Task.numOfTasks++;
    }

    public static void addOldEvent(String line) {
        int isDone = Integer.parseInt(line.substring(0,1));
        String restOfLine = line.substring(2);
        int indexOfFrom = restOfLine.indexOf("|");
        String description = restOfLine.substring(0, indexOfFrom);
        String fromAndToPart = restOfLine.substring(indexOfFrom + 1);
        //1|description f|2356|34
        int indexOfTo = fromAndToPart.indexOf("|");
        String from = fromAndToPart.substring(0, indexOfTo);
        String to = fromAndToPart.substring(indexOfTo + 1);

        Task.tasks[Task.numOfTasks] = new Event(description, from, to);
        if (isDone == 1) {
            Task.tasks[Task.numOfTasks].isDone = true;
        } else {
            Task.tasks[Task.numOfTasks].isDone = false;
        }
        Task.numOfTasks++;
    }
    public static void addOldTask(String line) {
        String taskType = line.substring(0,1);
        String restOfLine = line.substring(2);
        switch (taskType) {
        case "T":
            addOldTodo(restOfLine);
            break;
        case "D":
            addOldDeadline(restOfLine);
            break;
        case "E":
            addOldEvent(restOfLine);
            break;
        }
    }
    private static void writeToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < Task.numOfTasks; i++) {
            String taskType = Task.tasks[i].getTaskType();
            String lineToWrite = "";
            lineToWrite += taskType;
            if (Task.tasks[i].isDone) {
                lineToWrite += "|1";
            } else {
                lineToWrite += "|0";
            }
            lineToWrite += "|" + Task.tasks[i].description;

            if (taskType == "D") {
                lineToWrite += "|" + Task.tasks[i].getBy();
            } else if (taskType == "E") {
                lineToWrite += "|" + Task.tasks[i].getFrom() + "|" + Task.tasks[i].getTo();
            }
            lineToWrite += System.lineSeparator();
            fw.write(lineToWrite);
        }
        fw.close();
    }
    public static void getFileContents(String dataFilePath) throws FileNotFoundException {
        File dir = new File("data");
        File f = new File(dataFilePath);

        if (!dir.exists()){
            // creates directory if it does not exist
            dir.mkdirs();
        }
        try {
            // create data file if it does not exist
            f.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred when creating the data file.");
        }

        Scanner s = new Scanner(f);
        // reading from text file to get stored data
        while (s.hasNext()) {
            String line = s.nextLine();
            addOldTask(line);
        }
    }
    public static void main(String[] args) {
        String dataFilePath = "data/duke.txt";
        try {
            getFileContents(dataFilePath);
        } catch (FileNotFoundException e) {
            System.out.println("Data file was not found.");
            return;
        }

        String greetMsg = HORIZONTAL_LINE
                + "\tHello! I'm Duke"
                + System.lineSeparator()
                + "\tWhat can I do for you?"
                + System.lineSeparator()
                + HORIZONTAL_LINE;
        System.out.println(greetMsg);

        String input;
        String chatOutput;
        Scanner in = new Scanner(System.in);
        boolean shouldContinueChat = true;
        do {
            input = in.nextLine();
            if (input.equals("bye")) {
                shouldContinueChat = false;
                try {
                    writeToFile(dataFilePath);
                } catch (IOException e) {
                    System.out.println("Data was not saved to file.");
                    return;
                }
                chatOutput = "\tBye. Hope to see you again soon!";
            } else {
                chatOutput = getResponse(input);
            }
            System.out.println(HORIZONTAL_LINE + chatOutput + System.lineSeparator() + HORIZONTAL_LINE);
        } while (shouldContinueChat);
    }
}
