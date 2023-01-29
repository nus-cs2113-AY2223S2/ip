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
        boolean endInput = false;
        while (!endInput) {
            line = in.nextLine();
            String[] words = line.split(" ");
            switch (words[0]) {
            case "bye":
                reply = new StringBuilder(" Bye bye bro.");
                endInput = true;
                break;
            case "list":
                reply = new StringBuilder(" Your tasks:\n");
                for (int i = 0; i < tasks.size(); ++i) {
                    Task task = tasks.get(i);
                    String mark = task.mark();
                    reply.append(" ").append(i + 1).append(".[").append(task.getType()).append("]").append("[")
                            .append(mark).append("] ").append(task).append("\n");
                }
                break;
            case "mark":
                int indexOfMarkedTask = Integer.parseInt(words[1]) - 1;
                boolean isValidated1 = validateTask(tasks.size(), indexOfMarkedTask);
                if (isValidated1) {
                    reply = new StringBuilder(" Marked " + tasks.get(indexOfMarkedTask) + " as done.");
                    tasks.get(indexOfMarkedTask).setCompleted();
                }
                else {
                    reply = new StringBuilder(TASK_DOES_NOT_EXIST);
                }
                break;
            case "unmark":
                int indexOfUnmarkedTask = Integer.parseInt(words[1]) - 1;
                boolean isValidated2 = validateTask(tasks.size(), indexOfUnmarkedTask);
                if (isValidated2) {
                    reply = new StringBuilder(" Marked " + tasks.get(indexOfUnmarkedTask) + " as not done.");
                    tasks.get(indexOfUnmarkedTask).setUncompleted();
                }
                else {
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
                int indexOfDeadline = Arrays.asList(words).indexOf("/by");
                for (int i = 1; i < indexOfDeadline; ++i) {
                    deadlineName.append(" ").append(words[i]);
                }
                for (int i = indexOfDeadline + 1; i < words.length; ++i) {
                    by.append(" ").append(words[i]);
                }
                Task deadline = new Deadline(deadlineName.toString().trim(), by.toString().trim());
                tasks.add(deadline);
                reply = new StringBuilder(" added: " + deadline);
                break;
            case "event":
                StringBuilder eventName = new StringBuilder();
                StringBuilder startTime = new StringBuilder();
                StringBuilder endTime = new StringBuilder();
                int indexOfStartTime = Arrays.asList(words).indexOf("/from");
                int indexOfEndTime = Arrays.asList(words).indexOf("/to");
                for (int i = 1; i < indexOfStartTime; ++i) {
                    eventName.append(" ").append(words[i]);
                }
                for (int i = indexOfStartTime + 1; i < indexOfEndTime; ++i) {
                    startTime.append(" ").append(words[i]);
                }
                for (int i = indexOfEndTime + 1; i < words.length; ++i) {
                    endTime.append(" ").append(words[i]);
                }
                Task event = new Event(eventName.toString().trim(), startTime.toString().trim(), endTime.toString().trim());
                tasks.add(event);
                reply = new StringBuilder(" added: " + event);
                break;
            }
            System.out.println(HORIZONTAL_LINE + reply + HORIZONTAL_LINE);  // Output reply
        }
    }

    /**
     * Checks if Task Index input is in range of the current list of tasks
     *
     * @param tasks List of Tasks
     * @param taskIndex Index of Task being validated
     * @return true if Task is valid, false if Task is invalid
     */
    private static boolean validateTask(int tasks, int taskIndex) {
        return taskIndex + 1 <= tasks && taskIndex >= 0;
    }
}
