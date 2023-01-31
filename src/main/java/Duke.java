import java.util.Scanner;

public class Duke {

    public static final int MARK_TASK_NUMBER_INDEX = 5;
    public static final int UNMARK_TASK_NUMBER_INDEX = 7;

    public static String markTask(String input) {
        int itemIndex = Integer.parseInt(input.substring(MARK_TASK_NUMBER_INDEX)) - 1;
        Task.tasks[itemIndex].setTaskStatus(true);
        return "\tNice! I've marked this task as done:" + System.lineSeparator() + "\t\t"
                + Task.tasks[itemIndex];
    }

    public static String unmarkTask(String input) {
        int itemIndex = Integer.parseInt(input.substring(UNMARK_TASK_NUMBER_INDEX)) - 1;
        Task.tasks[itemIndex].setTaskStatus(false);
        return "\tOK, I've marked this task as not done yet:" + System.lineSeparator() + "\t\t"
                + Task.tasks[itemIndex];
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

    public static void createTodoTask(String input) {
        Task.tasks[Task.numOfTasks] = new Todo(input);
    }

    public static void createDeadlineTask(String input) {
        int byIndex = input.indexOf("/by");
        String description = (input.substring(0, byIndex)).trim();
        String restOfInput = input.substring(byIndex);

        int startingIndex = (restOfInput).indexOf(" ");
        String by = restOfInput.substring(startingIndex + 1);
        Task.tasks[Task.numOfTasks] = new Deadline(description, by);
    }

    public static void createEventTask(String input) {
        int descriptionIndex = input.indexOf("/from");
        String description = (input.substring(0, descriptionIndex)).trim();
        String restOfInput = input.substring(descriptionIndex);
        
        int fromIndex = restOfInput.indexOf(" ");
        int toIndex = restOfInput.indexOf("/to");
        String from = (restOfInput.substring(fromIndex, toIndex)).trim();
        String to = (restOfInput.substring(toIndex + 4)).trim();
        Task.tasks[Task.numOfTasks] = new Event(description, from, to);
    }

    public static String addTask(String itemType, String restOfInput) {
        String output = "\tGot it. I've added this task:" + System.lineSeparator() + "\t";
        switch (itemType) {
        case "todo":
            createTodoTask(restOfInput);
            break;
        case "deadline":
            createDeadlineTask(restOfInput);
            break;
        case "event":
            createEventTask(restOfInput);
            break;
        }
        output += "\t" + Task.tasks[Task.numOfTasks] + System.lineSeparator();
        Task.numOfTasks++;
        output += "\tNow you have " + Task.numOfTasks + " tasks in the list.";
        return output;
    }

    public static String getResponse(String input) {
        String output;

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
        switch (firstWord) {
        case "list":
            output = getList();
            break;
        case "todo":
        case "deadline":
        case "event":
            output = addTask(firstWord, restOfInput);
            break;
        case "mark":
            output = markTask(input);
            break;
        case "unmark":
            output = unmarkTask(input);
            break;
        default:
            output = "\tInvalid command.";
        }
        return output;
    }

    public static void main(String[] args) {
        String greetMsg = "\t____________________________________________________________"
                + System.lineSeparator()
                + "\tHello! I'm Duke"
                + System.lineSeparator()
                + "\tWhat can I do for you?"
                + System.lineSeparator()
                + "\t____________________________________________________________"
                + System.lineSeparator();
        System.out.println(greetMsg);

        String input;
        String chatOutput;
        Scanner in = new Scanner(System.in);
        boolean shouldContinueChat = true;
        do {
            input = in.nextLine();
            if (input.equals("bye")) {
                shouldContinueChat = false;
                chatOutput = "\tBye. Hope to see you again soon!";
            } else {
                chatOutput = getResponse(input);
            }
            String horizontalLine = "\t____________________________________________________________"
                    + System.lineSeparator();
            System.out.println(horizontalLine + chatOutput + System.lineSeparator() + horizontalLine);
        } while (shouldContinueChat);
    }
}
