//to handle all changes made to list of tasks during run time
import java.util.ArrayList;
public class TaskList {

    /** Starting index for task description for ToDo */
    private static final int TODO = 5;
    /** Starting index for task description for Deadline */
    private static final int DEADLINE = 9;
    /** Starting index for task description for Event */
    private static final int EVENT = 6;
    /** An array of Task that has not been deleted since initialisation */
    private ArrayList<Task> taskList;

    TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns Task at the index of interest
     *
     * @param index Position of Task in the ArrayList.
     * @return Task
     */
    public Task get(int index) {
        return this.taskList.get(index);
    }

    /**
     * Returns the number of Task that have not been deleted thus far
     *
     * @return size of the taskList ArrayList as int
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Returns a new ArrayList of Task with
     * the task of interest marked or unmarked.
     * If the Task cannot be found, an error is thrown
     *
     * @param input details of Task to be marked or unmarked.
     * @return Updated ArrayList of Task
     * @throws IncorrectParameterException If the Task cannot be found
     */
    public ArrayList<String> markTask(String input) throws IncorrectParameterException {
        ArrayList<String> responseList = new ArrayList<String>();
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        try {
            Task updatedTask = this.taskList.get(index).mark();
            if (input.contains("unmark")) {
                updatedTask = updatedTask.unmark();
                responseList.add("OK, I've marked this task as not done yet:");
            } else {
                responseList.add("Nice! I've marked this task as done:");
            }
            this.taskList.set(index, updatedTask);
            responseList.add("  " + updatedTask.toString());
        } catch (IndexOutOfBoundsException a) {
            throw new IncorrectParameterException("The index provided does not exist! ");
        }
        return responseList;
    }

    /**
     * Returns a new ArrayList of Task with
     * the task of interest removed.
     * If the Task cannot be found, an error is thrown
     *
     * @param input details of Task to be deleted.
     * @return Updated ArrayList of Task
     * @throws MissingFieldException If there are missing fields
     */
    public ArrayList<String> deleteTask(String input) throws MissingFieldException {
        ArrayList<String> responseList = new ArrayList<String>();
        String[] inputArray = input.split(" ");
        if (inputArray.length == 1) {
            throw new MissingFieldException("There are missing fields in your input!");
        } else {
            try {
                int index = Integer.parseInt(inputArray[1]) - 1;
                Task removedTask = this.taskList.get(index);
                this.taskList.remove(index);

                responseList.add("Noted. I've removed this task:");
                responseList.add("  " + removedTask.toString());
                responseList.add("Now you have " + taskList.size() + " tasks in the list.");
            } catch (IndexOutOfBoundsException a) {
                throw new MissingFieldException("The index provided is invalid!");
            }
        }
        return responseList;
    }

    /**
     * Determine the type of Task to create
     *
     * @param input details of input by user
     * @throws Exception If any error is encountered in the
     * process of creating a Task
     */
    public ArrayList<String> createTask(String input) throws Exception{
        ArrayList<String> responseList = new ArrayList<String>();
        if (Parser.parse(input, "todo")) {
            this.createToDo(input);
        } else if (Parser.parse(input, "deadline")) {
            this.createDeadline(input);
        } else if (Parser.parse(input, "event")) {
            this.createEvent(input);
        }
        responseList.add("Got it. I've added this task:");
        responseList.add("  " + this.get(this.size() - 1).toString());
        responseList.add("Now you have " + this.size() + " tasks in the list");
        return responseList;
    }

    /**
     * Adds a new ToDo Task to the current ArrayList of Task.
     * If there are missing fields, an error is thrown
     *
     * @param input details of Task to be created.
     * @throws MissingFieldException If there are missing fields
     */
    public void createToDo(String input) throws MissingFieldException {
        String description = input.substring(TODO, input.length());
        if (description.length() < 1) {
            throw new MissingFieldException("The description of a todo cannot be empty.");
        }
        ToDo toDo = new ToDo(false, description);
        this.taskList.add(toDo);
    }

    /**
     * Adds a new Deadline Task to the current ArrayList of Task.
     * If there are missing fields, an error is thrown
     *
     * @param input details of Task to be created.
     * @throws MissingFieldException If there are missing fields
     */
    public void createDeadline(String input) throws MissingFieldException {
        try {
            String[] inputArray = input.split("/by ");
            String description = inputArray[0].substring(TaskList.DEADLINE, inputArray[0].length() - 1);
            String deadline = inputArray[1];

            Deadline deadlineTask = new Deadline(false, description, deadline);
            this.taskList.add(deadlineTask);
        } catch (IndexOutOfBoundsException a) {
            throw new MissingFieldException("There are missing fields in your input!");
        }
    }

    /**
     * Adds a new Event Task to the current ArrayList of Task.
     * If there are missing fields, an error is thrown
     *
     * @param input details of Task to be created
     * @throws MissingFieldException If there are missing fields
     */
    public void createEvent(String input) throws MissingFieldException {
        try {
            String[] inputArray = input.split("/from ");
            String description = inputArray[0].substring(TaskList.EVENT, inputArray[0].length() - 1);
            String[] dateTimeArray = inputArray[1].split("/to");
            String from = dateTimeArray[0];
            String to = dateTimeArray[1];

            Event event = new Event(false, description, from, to);
            this.taskList.add(event);
        } catch (IndexOutOfBoundsException a) {
            throw new MissingFieldException("There are missing fields in your input!");
        }
    }

    /**
     * Finds all Task that contains the substring.
     *
     * @param input keyword to be found
     * @return toString() representation of all Task
     * that contain the keyword
     */
    public ArrayList<String> findTasks(String input) {
        String keyword = input.substring(5, input.length());
        ArrayList<String> matchingTasks = new ArrayList<String>();
        matchingTasks.add("Here are the matching tasks in your list: ");
        int index = 1;
        for (Task t : this.taskList) {
            if (t.containsKeyword(keyword)) {
                matchingTasks.add(index + "." +t.toString());
                index++;
            }
        }
        if (matchingTasks.size() == 1) {
            matchingTasks = new ArrayList<String>();
            matchingTasks.add("There is no task that match that description!");
        }
        return matchingTasks;
    }

    /**
     * Return ArrayList<Task> that contains all Task
     * that are not deleted thus far
     *
     * @return  toString() representation of Task in the form of
     * an ArrayList
     */
    public ArrayList<String> printTasks() {
        ArrayList<String> responseList = new ArrayList<String>();
        responseList.add("Here are the tasks in your list:");
        for (int i = 0; i < this.taskList.size(); i++) {
            int index = i + 1;
            responseList.add(index + "." + this.taskList.get(i).toString());
        }
        return responseList;
    }
}
