package shizuka;

import java.util.ArrayList;

public class TodoList {
    static final String LINE_BREAK = "____________________\n";
    static final String YOUR_TODO_LIST = "This is your todo list.\n";
    static final String MARKED_TASK = "I have marked Task ";
    static final String UNMARKED_TASK = "I have unmarked Task ";
    static final String IN_LIST = " in your todo list.\n";
    static final int MAX_DEADLINE_ARGS = -1;
    static final int MAX_EVENT_ARGS = 3;
    static final String BY_COMMAND = " /by ";
    static final String FROM_COMMAND = " /from ";
    static final String TO_COMMAND = " /to ";
    static final String SPECIFIC_ITEMS = "Here are the items you are looking for. \n";
    private int numberOfTasks;
    private final ArrayList<Todo> todos;

    public TodoList() {
        numberOfTasks = 0;
        this.todos = new ArrayList<>();
    }

    /**
     * Parses the arguments for the deadline command.
     *
     * @param args The arguments String for the deadline command.
     * @return An array of the arguments.
     * @throws InvalidCommandFormatException If the arguments are not in the correct format.
     */
    private static String[] parseDeadline(String args) throws InvalidCommandFormatException {
        String[] vars = args.split(BY_COMMAND, MAX_DEADLINE_ARGS);
        if (vars.length != 2) {
            throw new InvalidCommandFormatException();
        }
        return vars;
    }

    /**
     * Parses the arguments for the event command.
     *
     * @param args The arguments String for the event command.
     * @return An array of the arguments.
     * @throws InvalidCommandFormatException If the arguments are not in the correct format.
     */
    private static String[] parseEvent(String args) throws InvalidCommandFormatException {
        String[] vars = new String[MAX_EVENT_ARGS];
        String[] tempArray0 = args.split(FROM_COMMAND, 2);
        vars[0] = tempArray0[0];
        String[] tempArray1 = tempArray0[1].split(TO_COMMAND, 2);
        vars[1] = tempArray1[0];
        vars[2] = tempArray1[1];
        if (tempArray0.length != 2 | tempArray1.length != 2) {
            throw new InvalidCommandFormatException();
        }
        return vars;
    }

    /**
     * Adds a Todo item to the list.
     *
     * @param args The description String for the item.
     */
    public void addTodo(String args) {
        this.todos.add(new Todo(args));
        numberOfTasks += 1;
        UI.addToList(args);
    }

    /**
     * Adds a Deadline item to the list.
     *
     * @param args The arguments String for the item.
     */
    public void addDeadline(String args) {
        String[] vars;
        try {
            vars = parseDeadline(args);
        } catch (ArrayIndexOutOfBoundsException | InvalidCommandFormatException e) {
            UI.formatError();
            return;
        }
        this.todos.add(new Deadline(vars[0], vars[1]));
        numberOfTasks += 1;
        UI.addToList(vars[0]);
    }

    /**
     * Adds an Event item to the list.
     *
     * @param args The arguments String for the item.
     */
    public void addEvent(String args) {
        String[] vars;
        try {
            vars = parseEvent(args);
        } catch (ArrayIndexOutOfBoundsException | InvalidCommandFormatException e) {
            UI.formatError();
            return;
        }
        this.todos.add(new Event(vars[0], vars[1], vars[2]));
        numberOfTasks += 1;
        UI.addToList(vars[0]);
    }

    /**
     * Prints the list of items.
     */
    public void list() {
        System.out.print(LINE_BREAK + YOUR_TODO_LIST + LINE_BREAK);
        for (int i = 0; i < numberOfTasks; i += 1) {
            System.out.println(i + 1 + "." + this.todos.get(i).printTask());
        }
        System.out.print(LINE_BREAK);
    }

    /**
     * Marks an item as done.
     *
     * @param num The number of the item to be marked.
     */
    public void mark(int num) {
        this.todos.get(num - 1).isDone = true;
        System.out.println(LINE_BREAK + MARKED_TASK + num + IN_LIST + this.todos.get(num - 1).getStatusIcon()
                + ' ' + this.todos.get(num - 1).description + "\n" + LINE_BREAK);
    }

    /**
     * Marks an item as not done.
     *
     * @param num The number of the item to be unmarked.
     */
    public void unmark(int num) {
        this.todos.get(num - 1).isDone = false;
        System.out.println(LINE_BREAK + UNMARKED_TASK + num + IN_LIST + this.todos.get(num - 1).getStatusIcon()
                + ' ' + this.todos.get(num - 1).description + "\n" + LINE_BREAK);
    }

    /**
     * Removes an item from the list.
     *
     * @param num The number of the item to be removed.
     */
    public void deleteTask(int num) {
        this.todos.remove(num - 1);
        numberOfTasks -= 1;
        System.out.println(LINE_BREAK + "I have deleted Task " + num + IN_LIST + LINE_BREAK);
    }

    /**
     * Reformats the list of items into a String array for saving.
     *
     * @return The String array of items.
     */
    public String[] listWriter() {
        String[] list = new String[numberOfTasks];
        for (int i = 0; i < numberOfTasks; i += 1) {
            list[i] = this.todos.get(i).saveTask();
        }
        return list;
    }

    /**
     * Adds a Todo item to the list from a file.
     *
     * @param args The arguments String for the item.
     */
    public void addTodoFromFile(String[] args) {
        this.todos.add(new Todo(args));
        numberOfTasks += 1;
    }

    /**
     * Adds a Deadline item to the list from a file.
     *
     * @param args The arguments String for the item.
     */
    public void addDeadlineFromFile(String[] args) {
        this.todos.add(new Deadline(args));
        numberOfTasks += 1;
    }

    /**
     * Adds an Event item to the list from a file.
     *
     * @param args The arguments String for the item.
     */
    public void addEventFromFile(String[] args) {
        this.todos.add(new Event(args));
        numberOfTasks += 1;
    }

    /**
     * Finds and prints items in the list that contain a keyword.
     *
     * @param keyword The keyword to be searched for.
     */
    public void find(String keyword) {
        ArrayList<Integer> foundItems = new ArrayList<>();
        for (int i = 0; i < numberOfTasks; i += 1) {
            if (todos.get(i).description.contains(keyword)) {
                foundItems.add(i);
            }
        }
        System.out.print(LINE_BREAK + SPECIFIC_ITEMS + LINE_BREAK);
        for (int index : foundItems) {
            System.out.println(index + 1 + "." + this.todos.get(index).printTask());
        }
        System.out.print(LINE_BREAK);
    }

}
