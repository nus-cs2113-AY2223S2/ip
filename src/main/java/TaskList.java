import java.util.ArrayList;

public class TaskList {
    private static final String line = "__________________________________________________________";

    private static ArrayList<Task> inputList = new ArrayList<>();
    private static int numTasks = 0;

    public static void validTask(String[] userInput) throws DukeException{
        if (userInput.length < 2 && (userInput[0].equals("todo") ||
                userInput[0].equals("event") || userInput[0].equals("deadline"))) {
            throw new DukeException();
        }
    }

//    public static void validTask(String userInput) throws DukeException {
//        String taskNum = userInput.substring(userInput.length()-1);
//        int x = Integer.parseInt(taskNum);
//        if (inputList.get(x-1) == null || inputList.size() == 0) {
//            throw new DukeException();
//        }
//        inputList.get(x-1).markAsDone(userInput);
//    }

    public static void addTask(String userInput) throws DukeException {
        Task t;
        String[] words = userInput.split(" ");
        validTask(words);
        String descriptor = userInput.substring(userInput.indexOf(words[1]), userInput.length());
        if (words[0].equals("todo")) {
            t = new Todo(descriptor);
        } else if (words[0].equals("deadline")) {
            String by = descriptor.split("/by ")[1];
            descriptor = descriptor.split("/by ")[0];
            t = new Deadline(descriptor, by);
        } else if (words[0].equals("event")) {
            String to = descriptor.split("/to ")[1];
            String from = descriptor.split(" /")[1];
            descriptor = descriptor.split("/")[0];
            t = new Event(descriptor, from, to);
        } else {
            throw new IndexOutOfBoundsException();
        }
        inputList.add(t);
        numTasks = inputList.size();
        printAddTask(t);
    }

    public static void deleteTask(String userInput) throws DukeException {
        String taskNum = userInput.substring(userInput.length()-1);
        int x = Integer.parseInt(taskNum);
        if (inputList.get(x-1) == null || inputList.size() == 0) {
            throw new DukeException();
        }
        System.out.println(line + '\n' + "Ok, I have now removed this task: "
                + inputList.get(x-1) + '\n' + line);
        inputList.remove(x-1);
    }
}
