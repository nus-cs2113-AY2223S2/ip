import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
public class Bro {
    public static final String HORIZONTAL_LINE = "\n───────────────────────────────────────────────────────────────\n";
    public static final String GREETING = " Sup bro. I'm Bro.\n" + " What do you want?";
    public static final String TASK_DOES_NOT_EXIST = " Bro that task number does not exist...";
    public static void main(String[] args) {
        System.out.println(HORIZONTAL_LINE + GREETING + HORIZONTAL_LINE);

        // User Input
        String line;
        StringBuilder reply = new StringBuilder();  // Use StringBuilder as we concatenate Strings in a loop later on
        Scanner in = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();  // Dynamic array to store text entered by user
        boolean haveInput = true;
        while (haveInput) {
            line = in.nextLine();
            String[] arrayOfInputs = line.split(" ");
            switch (arrayOfInputs[0]) {
            case "bye":
                reply = new StringBuilder(" Bye bye bro.");
                haveInput = false;
                break;
            case "list":
                reply = new StringBuilder(" Your tasks:\n");
                for (int i = 0; i < tasks.size(); ++i) {
                    Task currentTask = tasks.get(i);
                    String mark = currentTask.mark();
                    reply.append(" ").append(i + 1).append(".[").append(currentTask.getType()).append("]").append("[")
                            .append(mark).append("] ").append(currentTask).append("\n");
                }
                break;
            case "mark": // Fallthrough
            case "unmark":
                boolean markAsComplete = arrayOfInputs[0].equals("mark");   // this boolean decides if the following markComplete() method marks the task as Completed or Uncompleted
                int taskIndex = Integer.parseInt(arrayOfInputs[1]) - 1;
                try {
                    reply = markComplete(markAsComplete, tasks, taskIndex, reply);
                } catch (invalidTaskIndexException e) {
                    reply = new StringBuilder(TASK_DOES_NOT_EXIST);
                }
                break;
            case "todo":
                Task todo = new ToDo(line);
                tasks.add(todo);
                reply = new StringBuilder(" added: " + todo);
                break;
            case "deadline":
                StringBuilder deadlineName = new StringBuilder();
                StringBuilder by = new StringBuilder();
                int indexOfDeadline = Arrays.asList(arrayOfInputs).indexOf("/by");
                for (int i = 1; i < indexOfDeadline; ++i) {
                    deadlineName.append(" ").append(arrayOfInputs[i]);
                }
                for (int i = indexOfDeadline + 1; i < arrayOfInputs.length; ++i) {
                    by.append(" ").append(arrayOfInputs[i]);
                }
                Task deadline = new Deadline(deadlineName.toString().trim(), by.toString().trim());
                tasks.add(deadline);
                reply = new StringBuilder(" added: " + deadline);
                break;
            case "event":
                StringBuilder eventName = new StringBuilder();
                StringBuilder startTime = new StringBuilder();
                StringBuilder endTime = new StringBuilder();
                int indexOfStartTime = Arrays.asList(arrayOfInputs).indexOf("/from");
                int indexOfEndTime = Arrays.asList(arrayOfInputs).indexOf("/to");
                for (int i = 1; i < indexOfStartTime; ++i) {
                    eventName.append(" ").append(arrayOfInputs[i]);
                }
                for (int i = indexOfStartTime + 1; i < indexOfEndTime; ++i) {
                    startTime.append(" ").append(arrayOfInputs[i]);
                }
                for (int i = indexOfEndTime + 1; i < arrayOfInputs.length; ++i) {
                    endTime.append(" ").append(arrayOfInputs[i]);
                }
                Task event = new Event(eventName.toString().trim(), startTime.toString().trim(), endTime.toString().trim());
                tasks.add(event);
                reply = new StringBuilder(" added: " + event);
                break;
            default:
                reply = new StringBuilder(" Not a valid command bro...");
            }
            System.out.println(HORIZONTAL_LINE + reply + HORIZONTAL_LINE);  // Output reply
        }
    }

    /**
     * Validate the input task index, sets the task completion status and returns Bro's reply according to said completion status.
     *
     * @param markAsComplete boolean that decides if this method marks the input task as Completed or Uncompleted
     * @param tasks List of all tasks
     * @param taskIndex Index of current task in the tasks list
     * @param reply Bro's reply
     * @return reply
     * @throws invalidTaskIndexException
     */
    private static StringBuilder markComplete(boolean markAsComplete, ArrayList<Task> tasks, int taskIndex, StringBuilder reply) throws invalidTaskIndexException {
        if (taskIndex + 1 > tasks.size() || taskIndex < 0) { // Validate if input Task index is valid
            throw new invalidTaskIndexException();
        }
        else if (markAsComplete){   // mark as Completed
            reply = new StringBuilder(" Marked " + tasks.get(taskIndex) + " as done.");
            tasks.get(taskIndex).setCompleted();
            return reply;
        }
        else {                      // mark as Uncompleted
            reply = new StringBuilder(" Marked " + tasks.get(taskIndex) + " as not done.");
            tasks.get(taskIndex).setUncompleted();
            return reply;
        }
    }
}
