package UserInterface;

import FileIO.DukeFile;
import TaskItems.Event;
import TaskItems.Todos;

import java.util.ArrayList;

public class UI {
    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("what can i do for you");
    }

    public static void ListItems(ArrayList<Todos> todoItems) {
        System.out.println("-------TODO-LIST------");
        System.out.println("----------------------");
        for (int i = 0; i < todoItems.size(); i++) {
            char x = ' ';
            if (todoItems.get(i).isMarked) {
                x = 'X';
            }
            System.out.println(i + 1 + ". " + "[" + todoItems.get(i).type + "]" + "[" + x + "] " + todoItems.get(i).item);
        }
        System.out.println("----------------------");
    }

    public static void Mark(String UserInput, ArrayList<Todos> todoItems) {
        int i = Integer.parseInt(UserInput.substring(5));
        todoItems.get(i - 1).setMark();
        System.out.println("Item has been marked");
        System.out.println(i + ". " + "[" + todoItems.get(i - 1).type + "]" + "[X] " + todoItems.get(i-1).item);
    }

    public static void unMark(String UserInput, ArrayList<Todos> todoItems) {
        int i = Integer.parseInt(UserInput.substring(7));
        todoItems.get(i - 1).unMark();
        System.out.println("Item has been unmarked");
        System.out.println(i + ". " + "[" + todoItems.get(i - 1).type + "]" + "[ ] " + todoItems.get(i - 1).item);

    }

    public static void addEvent(String UserInput, int count, ArrayList<Todos> todoItems) {
        System.out.println("Got it, ive done the Following");
        System.out.println("Added: " + UserInput);
        System.out.println("now you have: " + count + " tasks in this list.");
        Event x = new Event(UserInput, false, "E");
        todoItems.add(x);
    }

    public static void DeleteItem(ArrayList<Todos> todoItems, String UserInput) {
        System.out.println("i have deleted the task:" + todoItems.get(Integer.parseInt(UserInput.substring(7))-1).item);
        todoItems.remove(Integer.parseInt(UserInput.substring(7))-1);
    }

    public static void Deadline(String UserInput, int count) {
        String date = UserInput.substring(UserInput.indexOf("/by") + 4);
        UserInput = UserInput.substring(UserInput.indexOf(" ") + 1, UserInput.indexOf('/') - 1) + " (by: " + date + ")";
        System.out.println("Got it, ive done the Following");
        System.out.println("Added: " + UserInput);
        System.out.println("now you have: " + count + " tasks in this list.");
    }

    public static void addTodo(ArrayList<Todos> todoItems, int new_tasks, String UserInput) {
        System.out.println("Got it, Ive done the Following!");
        System.out.println("Added: " + "[" + todoItems.get(new_tasks).type + "]" + "[ ]" + UserInput.substring(UserInput.indexOf((" "))));
        int count = new_tasks + 1;
        System.out.println("now you have: " + count + " tasks in this list.");
    }

    public static void sayBye() {
        System.out.println("Bye! see you soon!");
    }

    public static void Error() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
