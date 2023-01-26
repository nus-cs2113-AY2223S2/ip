import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> taskList = new ArrayList<>();

    @SuppressWarnings("checkstyle:Indentation")
    public static void main(String[] args) {
        String lineBreak = "-----------------";
        System.out.println(lineBreak + '\n' + "Hello! I'm Duke" + '\n'
                + "What can I do for you?" + '\n' + lineBreak);
        String instruction;
        while (true) {
            Scanner myObj = new Scanner(System.in);
            instruction = myObj.nextLine();
            if (instruction.equalsIgnoreCase("list")) {
                System.out.println(lineBreak + '\n'
                        + "Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println(i + 1 + ".[" + taskList.get(i).getStatusIcon() + "] "
                            + taskList.get(i).description);
                }
                System.out.println(lineBreak);
            } else if (instruction.equalsIgnoreCase("bye")) {
                System.out.println(lineBreak + '\n'
                        + "Bye. Hope to see you again soon!" + '\n' + lineBreak);
                break;
            } else if (instruction.toLowerCase().contains("mark")) {
                String[] split = instruction.split("\\s+");
                int toMark = Integer.parseInt(split[1]);
                if (split[0].equalsIgnoreCase("mark")) {
                    taskList.get(toMark - 1).markAsDone();
                    System.out.println("Nice! I've marked this task as done: ");
                } else {
                    taskList.get(toMark - 1).markAsUnDone();
                    System.out.println("OK, I've marked this task as not done yet: ");
                }
                System.out.println("[" + taskList.get(toMark - 1).getStatusIcon() + "] "
                        + taskList.get(toMark - 1).description + '\n' + lineBreak);
            } else {
                Task t = new Task(instruction);
                taskList.add(t);
                System.out.println(lineBreak + '\n' + "added: " + t.description + '\n' + lineBreak);
            }
        }
    }
}
