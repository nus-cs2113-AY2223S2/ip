package Duke;

import Duke.Tasks.Task;
import Duke.Tasks.Todo;
import Duke.Tasks.Event;
import Duke.Tasks.Deadline;
/**
 * Implement methods that operate on the tasks in the task list.
 */
public class TaskList {
    public static void printAddMessage(Task[] Tasks, int taskIndex) {
        System.out.println("--------------------------------");
        System.out.println("Got it. I've added this task:");
        System.out.println(Tasks[taskIndex].toString());
        System.out.println("Now you have " + (taskIndex + 1) + " task(s) in the list");
        System.out.println("--------------------------------");
    }

    public static void addTodo(Task[] list, String[] command, int index) {
        list[index] = new Todo(command[1]);
        printAddMessage(list, index);
    }

    public static void addEvent(Task[] list, String[] command, int index) {
        String[] description = command[1].split(" /from ");
        String[] dates = description[1].split(" /to ");
        String start = dates[0];
        String end = dates[1];
        list[index] = new Event(description[0], start, end);
        printAddMessage(list, index);
    }

    public static void addDeadline(Task[] list, String[] command, int index) {
        String[] description = command[1].split(" /by ");
        String ddl = description[1];
        list[index] = new Deadline(description[0], ddl);
        printAddMessage(list, index);
    }

    public static void deleteTask(Task[] list, String[] command, int index) {

        String number = command[1];
        int deleteIndex = Integer.parseInt(number);
        if (deleteIndex == index) {
            System.out.println("--------------------------------");
            System.out.println("Noted. I've removed this task:");
            System.out.println(list[deleteIndex - 1].toString());
            System.out.println("Now you have " + (index - 1) + " task(s) in the list");
            System.out.println("--------------------------------");
        } else if (deleteIndex >= 1 && deleteIndex <= index - 1) {
            System.out.println("--------------------------------");
            System.out.println("Noted. I've removed this task:");
            System.out.println(list[deleteIndex - 1].toString());
            System.out.println("Now you have " + (index - 1) + " task(s) in the list");
            System.out.println("--------------------------------");
            for (int i = deleteIndex - 1; i < index - 1; i++) {
                list[i] = list[i + 1];
            }
        } else {
            System.out.println("Oops! Delete should be followed by a valid number. ");
        }

    }

    public static void markTask(Task[] list, String[] command) {
        try {
            String number = command[1];
            int markIndex = Integer.parseInt(number);
            list[markIndex - 1].isDone = true;
            System.out.println("--------------------------------");
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("\t" + list[markIndex-1].toString());
            System.out.println("--------------------------------");
        } catch (NumberFormatException e) {
            System.out.println("Oops! Mark should be followed by a number. " +
                    "(A valid index number should be separated by a space after the mark)");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Oops! Mark index out of bound! " +
                    "(A valid index number should be separated by a space after the mark)");
        } catch (NullPointerException e) {
            System.out.println("Oops! Mark index out of bound! " +
                    "(A valid index number should be separated by a space after the mark)");
        }
    }

    public static void unmarkTask(Task[] list, String[] command) {
        try {
            String number = command[1];
            int unMarkIndex = Integer.parseInt(number);
            list[unMarkIndex - 1].isDone = false;
            System.out.println("--------------------------------");
            System.out.println("Nice! I've marked this task as not done yet:");
            System.out.println("\t" + list[unMarkIndex-1].toString());
            System.out.println("--------------------------------");
        } catch (NumberFormatException e) {
            System.out.println("Oops! Unmark should be followed by a number. " +
                    "(A valid index number should be separated by a space after the unmark)");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Oops! Unmark index out of bound! " +
                    "(A valid index number should be separated by a space after the unmark)");
        } catch (NullPointerException e) {
            System.out.println("Oops! Unmark index out of bound! " +
                    "(A valid index number should be separated by a space after the unmark)");
        }
    }
    public static void findTask(Task[] list,String whatToFind, int index){
        boolean contain=false;
        for(int i=0;i<index;i++){
            if(list[i].toString().substring(6).contains(whatToFind)){
                System.out.println("--------------------------------");
                System.out.println("Here are the matching tasks in your list:");
                System.out.println("\t" + list[i].toString());
                System.out.println("--------------------------------");
                contain=true;
            }
        }
        if(!contain){
            System.out.println("--------------------------------");
            System.out.println("Sorry I don't find any content related in the list.");
            System.out.println("--------------------------------");
        }
    }
}
