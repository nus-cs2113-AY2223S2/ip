//to handle all changes made to list of tasks during run time
import java.util.ArrayList;
import java.util.Arrays;
public class TaskList {

    private static final int TODO = 5;
    private static final int DEADLINE = 9;
    private static final int EVENT = 6;
    private ArrayList<Task> taskList;

    TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public Task get(int index) {
        return this.taskList.get(index);
    }

    public int size() {
        return this.taskList.size();
    }

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
            responseList.add(updatedTask.toString());
        } catch (IndexOutOfBoundsException a) {
            throw new IncorrectParameterException("The index provided does not exist! ");
        }
        return responseList;
    }

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
        responseList.add(this.get(this.size() - 1).toString());
        responseList.add("Now you have " + this.size() + " tasks in the list");
        return responseList;
    }

    public void createToDo(String input) throws MissingFieldException {
        String description = input.substring(TODO, input.length());
        if (description.length() < 1) {
            throw new MissingFieldException("The description of a todo cannot be empty.");
        }
        ToDo toDo = new ToDo(false, description);
        this.taskList.add(toDo);
    }

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

    public void createEvent(String input) throws MissingFieldException {
        try {
            String[] inputArray = input.split("/from ");
            String description = inputArray[0].substring(TaskList.EVENT, inputArray[0].length() - 1);
            String[] dateTimeArray = inputArray[1].split("/to");
            String from = dateTimeArray[0];
            String to = dateTimeArray[1];
            System.out.println(description + "|" +from + "|" + to);

            Event event = new Event(false, description, from, to);
            this.taskList.add(event);
        } catch (IndexOutOfBoundsException a) {
            throw new MissingFieldException("There are missing fields in your input!");
        }
    }

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
            matchingTasks.add("There are no tasks that match that description!");
        }
        return matchingTasks;
    }

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
