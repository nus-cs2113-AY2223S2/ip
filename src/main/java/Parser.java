

public class Parser {
    /**
     * Parses user input into an array of words, splitting at the first space character.
     *
     * @param userInput the user input string to be parsed.
     * @return an array of words in the user input string.
     */
    public static String[] parse(String userInput) {
        String arr[] = userInput.split(" ", 2);
        return arr;


    }

    /**
     * Prints a goodbye message to the user and exits the program.
     */
    static void handleBye() {
        UI.printBye();
        System.exit(0);
    }

    /**
     * Prints the current list of tasks to the user.
     */
    static void handleList() {
        TasksList.processList();
    }

    /**
     * Marks a task as done.
     *
     * @param remainingWords the remaining words in the user input string after the command word.
     */
    static void handleMark(String remainingWords) {

        int i = Integer.parseInt(remainingWords) - 1;
        TasksList.addMark(remainingWords, i);
    }

    /**
     * UnMarks a task as not done.
     *
     * @param remainingWords the remaining words in the user input string after the command word.
     */
    static void handleUnmark(String remainingWords) {

        int i = Integer.parseInt(remainingWords) - 1;
        TasksList.unMark(remainingWords, i);
    }

    /**
     * Deletes a task.
     *
     * @param remainingWords the remaining words in the user input string after the command word.
     */
    static void handleDelete(String remainingWords) {
        int i = Integer.parseInt(remainingWords) - 1;
        TasksList.deleteTask(i);
    }

    /**
     * Adds a todo to the list.
     *
     * @param remainingWords the remaining words in the user input string after the command word.
     */
    static void handleTodo(String remainingWords) {
        TasksList.addTask(new Todo(remainingWords));
    }

    /**
     * Adds a deadline to the list.
     *
     * @param remainingWords the remaining words in the user input string after the command word.
     */
    static void handleDeadline(String remainingWords) {
        String[] parts = remainingWords.split("/");
        TasksList.addTask(new Deadline(parts[0], parts[1]));
    }

    /**
     * Adds an event to the list.
     *
     * @param remainingWords the remaining words in the user input string after the command word.
     */
    static void handleEvent(String remainingWords) {
        String[] parts = remainingWords.split("/");
        TasksList.addTask(new Event(parts[0], parts[1], parts[2]));
    }

    /**
     * Finds a task containing remainingWords in the list.
     *
     * @param remainingWords the remaining words in the user input string after the command word.
     */
    static void handleFind(String remainingWords) {
        TasksList.findTasks(remainingWords);
    }
}
