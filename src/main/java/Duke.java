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
    public static final int DELETE_TASK_NUMBER_INDEX = 7;

    public static String printCurrNumOfTask() {
        return "\tNow you have " + Task.numOfTasks + " tasks in the list.";
    }

    public static String markTask(String input) {
        // Extract the index of the task to be marked
        String output;
        int itemIndex;
        try {
            itemIndex = Integer.parseInt(input.substring(MARK_TASK_NUMBER_INDEX)) - 1;
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            return "\tSorry, you need to state a task number after 'mark'.";
        }

        try {
            Task.tasks.get(itemIndex).setTaskStatus(true);
            output = "\tNice! I've marked this task as done:" + System.lineSeparator() + "\t\t"
                    + Task.tasks.get(itemIndex);
        } catch (IndexOutOfBoundsException e) {
            return "\tSorry, the task does not exist.";
        }
        return output;
    }

    public static String unmarkTask(String input) {
        // Extract the index of the task to be unmarked
        String output;
        int itemIndex;
        try {
            itemIndex = Integer.parseInt(input.substring(UNMARK_TASK_NUMBER_INDEX)) - 1;
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            return "\tSorry, you need to state a task number after 'unmark'.";
        }

        try {
            Task.tasks.get(itemIndex).setTaskStatus(false);
            output = "\tOK, I've marked this task as not done yet:" + System.lineSeparator() + "\t\t"
                    + Task.tasks.get(itemIndex);
        } catch (IndexOutOfBoundsException e) {
            return "\tSorry, the task does not exist.";
        }
        return output;
    }

    public static String deleteTask(String input) {
        // Extract the index of the task to be deleted
        String output;
        int itemIndex;
        try {
            itemIndex = Integer.parseInt(input.substring(DELETE_TASK_NUMBER_INDEX)) - 1;
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            return "\tSorry, you need to state a task number after 'delete'.";
        }

        try {
            output = "\tNoted. I've removed this task." + System.lineSeparator() + "\t\t"
                    + Task.tasks.get(itemIndex);
            Task.tasks.remove(itemIndex);
            Task.numOfTasks--;
        } catch (IndexOutOfBoundsException e) {
            return "\tSorry, the task does not exist.";
        }
        output += System.lineSeparator() + printCurrNumOfTask();
        return output;
    }

    // Get an overview of all added tasks
    public static String getList() {
        String output = "\tHere are the tasks in your list:" + System.lineSeparator();
        for (int i = 0; i < Task.numOfTasks; i++) {
            output += "\t" + (i + 1) + "." + Task.tasks.get(i);

            // Add newline if it is not the last task in the list
            if (i != Task.numOfTasks - 1) {
                output += System.lineSeparator();
            }
        }
        return output;
    }

    // Add new Todo task to ArrayList
    public static void createTodoTask(String input) throws DukeException {
        if ((input.trim()).length() == 0) {
            // Task has no description
            throw new DukeException();
        }

        Task.tasks.add(Task.numOfTasks, new Todo(input));
    }

    // Add new Deadline task to ArrayList
    public static void createDeadlineTask(String input) throws DukeException {
        // Extract description and by parts from input
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
        Task.tasks.add(Task.numOfTasks, new Deadline(description, by));
    }

    // Add new Event task to ArrayList
    public static void createEventTask(String input) throws DukeException {
        // Extract description, from and to parts from input
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
            // No space between /from and /to in command
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

        Task.tasks.add(Task.numOfTasks, new Event(description, from, to));
    }

    // Add a new task to ArrayList
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
        Task.numOfTasks++;
        output += "\t" + Task.tasks.get(Task.numOfTasks - 1) + System.lineSeparator();
        output += printCurrNumOfTask();
        return output;
    }

    public static String processCommand(String input, String firstWord, String restOfInput) throws DukeException {
        switch (firstWord) {
        case "delete":
            return deleteTask(input);
        case "list":
            return getList();
        case "todo":
            // Fallthrough
        case "deadline":
            // Fallthrough
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

    // Determine Duke's response
    public static String getResponse(String input) {
        // Get first word from input
        String firstWord;
        String restOfInput = "";
        int indexOfFirstSpace = input.indexOf(" ");
        if (indexOfFirstSpace != -1) {
            // Separate first word from the rest of input
            firstWord = input.substring(0, indexOfFirstSpace);
            restOfInput = input.substring(indexOfFirstSpace + 1);
        } else {
            // Only 1 word in input
            firstWord = input;
        }

        // Determine the type of command
        String output;
        try {
            output = processCommand(input, firstWord, restOfInput);
        } catch (DukeException e) {
            output = "\tInvalid command.";
        }
        return output;
    }

    // Add a saved Todo task to ArrayList
    public static void addOldTodo(String line) {
        // Extracting description of Todo task
        int isDone = Integer.parseInt(line.substring(0,1));
        String description = line.substring(2);

        Task.tasks.add(Task.numOfTasks, new Todo(description));
        if (isDone == 1) {
            Task.tasks.get(Task.numOfTasks).setTaskStatus(true);
        } else {
            Task.tasks.get(Task.numOfTasks).setTaskStatus(false);
        }
        Task.numOfTasks++;
    }

    // Add a saved Deadline task to ArrayList
    public static void addOldDeadline(String line) {
        // Extracting isDone, description and by of Deadline task
        int isDone = Integer.parseInt(line.substring(0,1));
        String restOfLine = line.substring(2);
        int indexOfBy = restOfLine.indexOf("|");
        String description = restOfLine.substring(0, indexOfBy);
        String by = restOfLine.substring(indexOfBy + 1);

        Task.tasks.add(Task.numOfTasks, new Deadline(description, by));
        if (isDone == 1) {
            Task.tasks.get(Task.numOfTasks).setTaskStatus(true);
        } else {
            Task.tasks.get(Task.numOfTasks).setTaskStatus(false);
        }
        Task.numOfTasks++;
    }

    // Add a saved Event task to ArrayList
    public static void addOldEvent(String line) {
        // Extracting isDone, description, from and by of Event task
        int isDone = Integer.parseInt(line.substring(0,1));
        String restOfLine = line.substring(2);
        int indexOfFrom = restOfLine.indexOf("|");
        String description = restOfLine.substring(0, indexOfFrom);
        String fromAndToPart = restOfLine.substring(indexOfFrom + 1);

        int indexOfTo = fromAndToPart.indexOf("|");
        String from = fromAndToPart.substring(0, indexOfTo);
        String to = fromAndToPart.substring(indexOfTo + 1);

        Task.tasks.add(Task.numOfTasks, new Event(description, from, to));
        if (isDone == 1) {
            Task.tasks.get(Task.numOfTasks).setTaskStatus(true);
        } else {
            Task.tasks.get(Task.numOfTasks).setTaskStatus(false);
        }
        Task.numOfTasks++;
    }

    // Add past saved tasks to ArrayList
    public static void addOldTask(String line) {
        // Retrieve task type from text file
        String taskType = line.substring(0,1);
        String restOfLine = line.substring(2);

        // Determine what kind of task to add
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
            // determine task type
            String taskType = Task.tasks.get(i).getTaskType();

            // Add task type and isDone for each task
            String lineToWrite = taskType;
            if (Task.tasks.get(i).isDone) {
                lineToWrite += "|1";
            } else {
                lineToWrite += "|0";
            }

            // Add description for each task
            lineToWrite += "|" + Task.tasks.get(i).description;

            // Add by or (from and to) fields depending on task type
            if (taskType.equals("D")) {
                lineToWrite += "|" + Task.tasks.get(i).getBy();
            } else if (taskType.equals("E")) {
                lineToWrite += "|" + Task.tasks.get(i).getFrom() + "|" + Task.tasks.get(i).getTo();
            }
            lineToWrite += System.lineSeparator();

            fw.write(lineToWrite);
        }
        fw.close();
    }

    public static String saveTasks(String dataFilePath) {
        try {
            writeToFile(dataFilePath);
        } catch (IOException e) {
            return "\tData was not saved to file." + System.lineSeparator();
        }
        return "\tTasks were successfully saved to data file." + System.lineSeparator();
    }

    public static void getSavedTasks(String dataFilePath) throws FileNotFoundException {
        File dir = new File("data");
        File f = new File(dataFilePath);

        // Creates directory for data file if it does not exist
        if (!dir.exists()){
            dir.mkdirs();
        }

        // Create data file if it does not exist
        try {
            f.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred when creating the data file.");
        }

        // Reading from text file to get stored data
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String line = s.nextLine();
            addOldTask(line);
        }
    }

    public static void main(String[] args) {
        // Attempt to retrieve tasks that are stored in data file
        String dataFilePath = "data/duke.txt";
        try {
            getSavedTasks(dataFilePath);
        } catch (FileNotFoundException e) {
            System.out.println("Data file was not found.");
            return;
        }

        // Greetings from Duke
        String greetMsg = HORIZONTAL_LINE
                + "\tHello! I'm Duke"
                + System.lineSeparator()
                + "\tWhat can I do for you?"
                + System.lineSeparator()
                + HORIZONTAL_LINE;
        System.out.println(greetMsg);

        // Start prompting user for commands
        String input;
        String chatOutput;
        Scanner in = new Scanner(System.in);
        boolean shouldContinueChat = true;
        do {
            input = in.nextLine();
            if (input.equals("bye")) {
                shouldContinueChat = false;
                chatOutput = saveTasks(dataFilePath);
                chatOutput += "\tBye. Hope to see you again soon!";
            } else {
                chatOutput = getResponse(input);
            }
            System.out.println(HORIZONTAL_LINE + chatOutput + System.lineSeparator() + HORIZONTAL_LINE);
        } while (shouldContinueChat);
    }
}
