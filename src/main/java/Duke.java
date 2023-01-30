import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private static String LINE = "____________________________________________________________";
    private static ArrayList<Tasks> list = new ArrayList<Tasks>();
    public static void greet() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }
    public static void bye() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }
    public static void processInput(String input) {
        String inst = input.split("\\s+")[0];
        if (input.equalsIgnoreCase("list")) {
            printList();
            return;
        } else if (inst.equalsIgnoreCase("mark")) {
            markTask(input);
        } else if (inst.equalsIgnoreCase("unmark")) {
            unmarkTask(input);
        } else if (inst.equalsIgnoreCase("todo")) {
            todoTask(input);
        } else if(inst.equalsIgnoreCase("deadline")) {
            deadlineTask(input);
        } else if(inst.equalsIgnoreCase("event")) {
            eventTask(input);
        } else {
            unrecognisedInput();
        }
    }
    private static void unrecognisedInput() {
        System.out.println(LINE);
        System.out.println("Key again! I don't recognise your input!");
        System.out.println(LINE);
    }
    private static void eventTask(String input) {
        String[] tokens = input.split("\\s+", 2);
        String task = tokens[1].split("/")[0];
        String from = tokens[1].split("/")[1];
        String to = tokens[1].split("/")[2];
        Event e = new Event(task, from, to);
        list.add(e);
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(e);
        System.out.println("Now you have " + list.size() + " tasks in the list");
        System.out.println(LINE);
    }
    private static void deadlineTask(String input) {
        String[] tokens = input.split("\\s+", 2);
        String task = tokens[1].split("/")[0];
        String deadline = tokens[1].split("/")[1];
        Deadline d = new Deadline(task, deadline);
        list.add(d);
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(d);
        System.out.println("Now you have " + list.size() + " tasks in the list");
        System.out.println(LINE);
    }
    private static void todoTask(String input) {
        String[] tokens = input.split("\\s+", 2);
        String task = tokens[1];
        ToDo t = new ToDo(task);
        list.add(t);
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        System.out.println("Now you have " + list.size() + " tasks in the list");
        System.out.println(LINE);
    }
    private static void unmarkTask(String input) {
        int itemNum = Integer.parseInt(input.split("\\s+")[1]) - 1;
        if (!exist(itemNum)) { return; }
        list.get(itemNum).unmarkDone();
        System.out.println(LINE);
        System.out.println("OK! I've marked this task as not done yet:");
        System.out.println(list.get(itemNum));
        System.out.println(LINE);
    }
    private static void markTask(String input) {
        int itemNum = Integer.parseInt(input.split("\\s+")[1]) - 1;
        if (!exist(itemNum)) { return; }
        list.get(itemNum).markDone();
        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(list.get(itemNum));
        System.out.println(LINE);
    }
    private static void printList() {
        if(list.size() == 0) {
            System.out.println(LINE);
            System.out.println("Nothing on your list yet!");
            System.out.println(LINE);
        } else {
            System.out.println(LINE);
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i += 1) {
                System.out.println(Integer.toString(i+1) + "." + list.get(i));
            }
            System.out.println(LINE);
        }
    }

    private static boolean exist(int itemNum) {
        if(itemNum > list.size()-1) {
            System.out.println(LINE);
            System.out.println("Item does not exist!");
            System.out.println(LINE);
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        greet();
        while (true) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            if(input.equalsIgnoreCase("bye")) {
                bye();
                break;
            }
            processInput(input);
        }
    }
}
