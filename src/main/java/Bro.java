import java.util.Scanner;
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
                    String mark = tasks.get(i).mark();
                    String name = tasks.get(i).getName();
                    reply.append(" ").append(i + 1).append(".[").append(mark).append("] ").append(name).append("\n");
                }
                break;
            case "mark":
                int indexOfMarkedTask = Integer.parseInt(words[1]) - 1;
                boolean isValidated1 = validateTask(tasks.size(), indexOfMarkedTask, reply);
                if (isValidated1) {
                    reply = new StringBuilder(" Marked " + tasks.get(indexOfMarkedTask).getName() + " as done.");
                    tasks.get(indexOfMarkedTask).setCompleted();
                }
                else {
                    reply = new StringBuilder(TASK_DOES_NOT_EXIST);
                }
                break;
            case "unmark":
                int indexOfUnmarkedTask = Integer.parseInt(words[1]) - 1;
                boolean isValidated2 = validateTask(tasks.size(), indexOfUnmarkedTask, reply);
                if (isValidated2) {
                    reply = new StringBuilder(" Marked " + tasks.get(indexOfUnmarkedTask).getName() + " as not done.");
                    tasks.get(indexOfUnmarkedTask).setUncompleted();
                }
                else {
                    reply = new StringBuilder(TASK_DOES_NOT_EXIST);
                }
                break;
            default:
                Task task = new Task(line);
                tasks.add(task);
                reply = new StringBuilder(" added: " + line);
            }
            System.out.println(HORIZONTAL_LINE + reply + HORIZONTAL_LINE);  // Output reply
        }
    }

    /**
     * Checks if Task Index input is in range of the current list of tasks
     *
     * @param tasks List of Tasks
     * @param taskIndex Index of task being validated
     * @param reply Bro's reply
     * @return Bro's reply
     */
    private static boolean validateTask(int tasks, int taskIndex, StringBuilder reply) {
        if (taskIndex + 1 > tasks || taskIndex < 0) {
            return false;
        }
        return true;
    }
}
