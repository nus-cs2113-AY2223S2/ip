import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final static ArrayList<Task> taskList = new ArrayList<>();

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
                    System.out.println(i + 1 + "." + taskList.get(i).toString());
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
                System.out.println(taskList.get(toMark - 1).toString() + '\n' + lineBreak);
            } else {
                Task t;
                if (instruction.toLowerCase().contains("deadline")) {
                    String description = instruction.substring(instruction.indexOf(' ') + 1, instruction.indexOf('/'));
                    String ddl = instruction.substring(instruction.indexOf('/') + 1);
                    String by = ddl.replace("by", "");
                    t = new Deadline(description, by);
                } else if (instruction.toLowerCase().contains("event")) {
                    String substring = instruction.substring(instruction.indexOf(' ') + 1);
                    String[] info = substring.split("/");
                    String from = info[1].replace("from", "");
                    String to = info[2].replace("to", "");
                    t = new Event(info[0], from, to);
                } else if (instruction.toLowerCase().contains("todo")) {
                    String description = instruction.substring(instruction.indexOf(' ') + 1);
                    t = new Todo(description);
                } else {
                    t = new Task(instruction);
                }
                taskList.add(t);
                System.out.println(lineBreak + '\n' + "Got it. I've added this task:");
                System.out.println('\t'+t.toString());
                System.out.println("Now you have " + taskList.size() + " tasks in the list." + '\n' + lineBreak);
            }
        }
    }
}
