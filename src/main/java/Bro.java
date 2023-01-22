import java.util.Scanner;
import java.util.ArrayList;
public class Bro {
    public static void main(String[] args) {
        String horizontalLine = "\n───────────────────────────────────────────────────────────────\n";
        String logo = horizontalLine + " Sup bro. I'm Bro.\n" + " What do you want?" + horizontalLine;
        System.out.println(logo);

        // User Input
        String line, reply = "";
        Scanner in = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>(); // Dynamic array to store text entered by user
        boolean endInput = false;
        while (!endInput) {
            line = in.nextLine();
            String[] words = line.split(" ");
            switch (words[0]) {
            case "bye":
                reply = " Bye bye bro.";
                endInput = true;
                break;
            case "list":
                reply = " Your tasks:\n";
                String mark;
                for (int i = 0; i < tasks.size(); ++i) {
                    reply += (" " + (i + 1) + ".[" + tasks.get(i).mark() + "] " + tasks.get(i).getName() + "\n");
                }
                break;
            case "mark":
                int taskIndex = Integer.parseInt(words[1]) - 1;
                if (taskIndex + 1 > tasks.size() || taskIndex < 0) { // Input validation
                    reply = " Bro that task number does not exist...";
                    break;
                }
                reply = " Marked " + tasks.get(taskIndex).getName() + " as done.";
                tasks.get(taskIndex).setCompleted();
                break;
            case "unmark":
                int taskIndex2 = Integer.parseInt(words[1]) - 1;
                if (taskIndex2 + 1 > tasks.size() || taskIndex2 < 0) { // Input validation
                    reply = " Bro that task number does not exist...";
                    break;
                }
                reply = " Marked " + tasks.get(taskIndex2).getName() + " as not done.";
                tasks.get(taskIndex2).setUncompleted();
                break;
            default:
                Task task = new Task(line);
                tasks.add(task);
                reply = " added: " + line;
            }
            System.out.println(horizontalLine + reply + horizontalLine);
        }
    }
}
