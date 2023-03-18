/**
 * UI class that handles output. Prints out text based on method called.
 */
package duke;
import duke.Tasks.*;

public class UI {

    public void printWelcome() {
        System.out.println("Good day, Duke I am. Asistance, you need?");
    }

    public void printBye() {
        System.out.println("See you soon, I hope.");
    }

    public void printInputError() {
        System.out.println("Input format is wrong, I say.");
    }
    public void printAdded(TaskList taskList, int index) {
        System.out.println(taskList.getTask(index).getPrintFormat() + " - Added I have.");
    }

    public void printTaskWithIndex(TaskList taskList, int index) {
        System.out.println((index + 1) + ". " + taskList.getTask(index).getPrintFormat());
    }
    public void printNotInList() {
        System.out.println("It is not in list.");
    }
    public void printNoList() {
        System.out.println("List is empty, I say.");
    }

    /**
     * prints all the Tasks in the List according to index.
     * @param taskList that contains the ArrayList<Task>
     */
    public void printEntireList(TaskList taskList) {
        System.out.println("As shown, list is:");
        for (int i = 0; i < taskList.getList().size(); i++) {
            System.out.println((i + 1) + ". " + taskList.getTask(i).getPrintFormat());
        }
    }
    public void printMarked(TaskList taskList, int index) {
        System.out.println(taskList.getTask(index).getPrintFormat() + " - Marked it, I have.");
    }
    public void printUnmarked(TaskList taskList, int index) {
        System.out.println(taskList.getTask(index).getPrintFormat() + " - Unmarked it, I have.");
    }
    public void printDeleted() {
        System.out.println("Deleted, I have.");
    }

}
