package UserInterface;

import FileIO.DukeFile;
import TaskItems.Event;
import TaskItems.Todos;

import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    /**
     * prints to CLI a greeting
     */
    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("what can i do for you");
    }

    /**
     * prints every item that is still on the todo list
     * @param todoItems - an arraylist of type Todos that contains all the events, deadlines and todos.
     */
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

    /**
     * marks a task as complete
     * @param UserInput - the user input indicating which task to mark
     * @param todoItems - the array that contains the relevant taks to be marked
     */
    public static void Mark(String UserInput, ArrayList<Todos> todoItems) {
        int i = Integer.parseInt(UserInput.substring(5));
        todoItems.get(i - 1).setMark();
        System.out.println("Item has been marked");
        System.out.println(i + ". " + "[" + todoItems.get(i - 1).type + "]" + "[X] " + todoItems.get(i-1).item);
    }

    /**
     * marks a task as complete
     * @param UserInput - the user input indicating which task to unmark
     * @param todoItems - the array that contains the relevant taks to be unmarked
     */
    public static void unMark(String UserInput, ArrayList<Todos> todoItems) {
        int i = Integer.parseInt(UserInput.substring(7));
        todoItems.get(i - 1).unMark();
        System.out.println("Item has been unmarked");
        System.out.println(i + ". " + "[" + todoItems.get(i - 1).type + "]" + "[ ] " + todoItems.get(i - 1).item);

    }

    /**
     * a method that adds a new event to the todo list
     * @param UserInput - String that contains info on what event to add and its attributes
     * @param todoItems - the array to which the newly created event will be appended to.
     */
    public static void addEvent(String UserInput, ArrayList<Todos> todoItems) {
        System.out.println("Got it, ive done the Following");
        System.out.println("Added: " + UserInput);
        System.out.println("now you have: " + todoItems.size() + " tasks in this list.");
        Event Item = new Event(UserInput, false, "E");
        todoItems.add(Item);
    }

    /**
     * method that deletes the user requested item from the todo list
     * @param todoItems - the arraylist that will contain the item to be deleted
     * @param UserInput - a String that contains the info on which item to delet
     */
    public static void DeleteItem(ArrayList<Todos> todoItems, String UserInput) {
        System.out.println("i have deleted the task:" + todoItems.get(Integer.parseInt(UserInput.substring(7))-1).item);
        todoItems.remove(Integer.parseInt(UserInput.substring(7))-1);
    }

    /**
     * adds a new Deadline item to teh todo list
     * @param UserInput - the string that contains the relevant info on what deadlines to add
     * @param todoItems - the arraylist to which the new item will be appended to
     */

    public static void Deadline(String UserInput, ArrayList<Todos> todoItems) {
        String date = UserInput.substring(UserInput.indexOf("/by") + 4);
        UserInput = UserInput.substring(UserInput.indexOf(" ") + 1, UserInput.indexOf('/') - 1) + " (by: " + date + ")";
        System.out.println("Got it, ive done the Following");
        System.out.println("Added: " + UserInput);
        System.out.println("now you have: " + todoItems.size() + " tasks in this list.");
    }

    /**
     * creates a new todo item
     * @param todoItems - the array to which a new todo item will be appended to
     * @param UserInput - a string that contains the relevnat info on what todo item to create
     */
    public static void addTodo(ArrayList<Todos> todoItems, String UserInput) {
        System.out.println("Got it, Ive done the Following!");
        System.out.println("Added: " + "[T]" + "[ ]" + UserInput.substring(UserInput.indexOf((" "))));
        System.out.println("now you have: " + todoItems.size() + " tasks in this list.");
    }

    /**
     * Print a Goodbye message when programs exits
     */

    public static void sayBye() {
        System.out.println("Bye! see you soon!");
    }

    /**
     * indictaes an error when a user enters an invalid input
     */
    public static void Error() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * takes in the input String from the user via teh CLI using javas Scanner
     *
     * @return returns the input from teh user as a String
     */
    public static String GetInput() {
        Scanner ReadingUserInput = new Scanner(System.in);
        return ReadingUserInput.nextLine();
    }
}
