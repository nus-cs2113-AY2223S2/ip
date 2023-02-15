import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final static ArrayList<Task> list = new ArrayList<>();
    static String lineBreak = "-----------------";

    public static void main(String[] args) {

        System.out.println(lineBreak + '\n' + "Hello! I'm Duke" + '\n'
                + "What can I do for you?" + '\n' + lineBreak);
        String instruction;
        while (true) {
            Scanner myObj = new Scanner(System.in);
            instruction = myObj.nextLine();
            if (instruction.equalsIgnoreCase("list")) {
                System.out.println(lineBreak + '\n'
                        + "Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i + 1 + "." + list.get(i).toString());
                }
                System.out.println(lineBreak);
            } else if (instruction.equalsIgnoreCase("bye")) {
                System.out.println(lineBreak + '\n'
                        + "Bye. Hope to see you again soon!" + '\n' + lineBreak);
                break;
            } else if (instruction.toLowerCase().contains("mark")) {
                try {
                    String[] split = instruction.split("\\s+");
                    int toMark = Integer.parseInt(split[1]);
                    if (split[0].equalsIgnoreCase("mark")) {
                        list.get(toMark - 1).markAsDone();
                        System.out.println("Nice! I've marked this task as done: ");
                    } else {
                        list.get(toMark - 1).markAsUnDone();
                        System.out.println("OK, I've marked this task as not done yet: ");
                    }
                    System.out.println(list.get(toMark - 1).toString() + '\n' + lineBreak);
                } catch (NullPointerException e) {
                    System.out.println("Item is not in list!");
                }
            } else if (instruction.toLowerCase().contains("delete")) {
                String[] split = instruction.split("\\s+");
                int toDelete = Integer.parseInt(split[1]);
                taskListDelete(toDelete - 1);
            } else {
                Task t;
                if (instruction.toLowerCase().contains("deadline")) {
                    try {
                        String description = instruction.substring(instruction.indexOf(' ') + 1, instruction.indexOf('/'));
                        String ddl = instruction.substring(instruction.indexOf('/') + 1);
                        String by = ddl.replace("by", "");
                        t = new Deadline(description, by);
                        taskListAdd(t);
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println(lineBreak + '\n' + "☹ OOPS!!! The description of a deadline cannot be empty." + '\n' + lineBreak);
                    }
                } else if (instruction.toLowerCase().contains("event")) {
                    try {
                        String substring = instruction.substring(instruction.indexOf(' ') + 1);
                        String[] info = substring.split("/");
                        String from = info[1].replace("from", "");
                        String to = info[2].replace("to", "");
                        t = new Event(info[0], from, to);
                        taskListAdd(t);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(lineBreak + '\n' + "☹ OOPS!!! The description of a event cannot be empty." + '\n' + lineBreak);
                    }
                } else if (instruction.toLowerCase().contains("todo")) {
                    if (instruction.indexOf(' ') == -1) {
                        System.out.println(lineBreak + '\n' + "☹ OOPS!!! The description of a todo cannot be empty." + '\n' + lineBreak);
                    } else {
                        String description = instruction.substring(instruction.indexOf(' ') + 1);
                        t = new Todo(description);
                        taskListAdd(t);
                    }
                } else {
                    System.out.println(lineBreak + '\n' + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(" + '\n' + lineBreak);
                }
            }
        }
    }

    public static void taskListAdd(Task t) {
        list.add(t);
        System.out.println(lineBreak + '\n' + "Got it. I've added this task:");
        System.out.println('\t' + t.toString());
        System.out.println("Now you have " + list.size() + " tasks in the list." + '\n' + lineBreak);
    }

    public static void taskListDelete(int t) {
        System.out.println(lineBreak + '\n' + "Noted. I've removed this task:");
        System.out.println('\t' + list.get(t).toString());
        list.remove(t);
        System.out.println("Now you have " + list.size() + " tasks in the list." + '\n' + lineBreak);
    }
}
