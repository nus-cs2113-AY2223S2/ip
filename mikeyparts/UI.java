package mikeyparts;

import mikeyparts.TaskList;
import task.Task;
import java.util.ArrayList;

import static mikeyparts.Storage.saveToFile;
import static mikeyparts.TaskList.tasks;

public class UI {

    /**
     * Prints out a formatted version of the task with its type, completion status, description and date (if any)
     *
     * @param taskNumber the number of the task in the list
     */
    public static void printTask(ArrayList<Task> tasks, int taskNumber) {
        System.out.println("[" + tasks.get(taskNumber).getTaskType() + "]" + "["
                + tasks.get(taskNumber).getStatusIcon() + "] " +  tasks.get(taskNumber).getName() + " "
                + tasks.get(taskNumber).getDate());
    }

    /**
     * Prints out a series of messages confirming to the user that their task has been added
     */
    public static void addTaskMessage(ArrayList<Task> tasks) {
        System.out.println("Now yous got " + tasks.size() + " thing(s) ta do in ya list");
        System.out.println("Anythin else ya need me ta help ya with?");
        System.out.println();
    }

    public static void welcomeMessage() {
        System.out.println("Its yobbo-in time innit bruv\n");
        System.out.println();
        System.out.println("Ello bruv, me name's Mikey!");
        System.out.println("How can ah help ya bruv?");
        System.out.println();
    }

    public static void printList(ArrayList<Task> tasks) {
        System.out.println("Aight bruv here's ya list of stuff yous gotta do");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + "[" + tasks.get(i).getTaskType() + "]" + "["
                    + tasks.get(i).getStatusIcon() + "] " +  tasks.get(i).getName() + " "
                    + tasks.get(i).getDate());
        }
    }

    public static void printExitMessage() {
        System.out.println("Cheerio mate! I'll be seein ya soon, innit? o7");
        System.out.println();
    }

    public static void printInvalidInputMessage() {
        System.out.println("Crikey bruv, yous 'avin a laugh? I don know what that means!");
        System.out.println();
    }

    public static void printMarkTasksMessage(ArrayList<Task> tasks, int i) {
        System.out.println("Well done bruv, you finished this: ");
        printTask(tasks, i);
        System.out.println();
    }

    public static void printInvalidMarkMessage() {
        System.out.println("Bloody 'ell buddy, I can't mark an imaginary task now innit?");
        System.out.println();
    }

    public static void printInvalidUnmarkMessage() {
        System.out.println("Bloody 'ell buddy, I can't unmark an imaginary task now innit?");
        System.out.println();
    }

    public static void printUnmarkTasksMessage(ArrayList<Task> tasks, int i) {
        System.out.println("Aye good Sir/Ma'am, I've marked that uncompleted: ");
        printTask(tasks, i);
        System.out.println();
    }

    public static void printTodoTaskMessage(ArrayList<Task> tasks) {
        System.out.println("Gotcha mate, added this task to your list: ");
        printTask(tasks, (tasks.size() - 1));
        System.out.println();
        addTaskMessage(tasks);
    }

    public static void printInvalidTaskMessage() {
        System.out.println("Oi mate, I can't create an empty task yea?");
        System.out.println();
    }

    public static void printDeadlineTaskMessage(ArrayList<Task> tasks) {
        System.out.println("Gotcha mate, added this task to your list: ");
        printTask(tasks, (tasks.size() -1));
        System.out.println();
        addTaskMessage(tasks);
    }

    public static void printEventTaskMessage(ArrayList<Task> tasks) {
        System.out.println("Gotcha mate, added this task to your list: ");
        printTask(tasks, (tasks.size() -1));
        System.out.println();
        addTaskMessage(tasks);
    }

    public static void printDeleteErrorMessage() {
        System.out.println("C'mon bruv, I can't deletes an imaginary task now, can I?");
        System.out.println();
    }

    public static void printFindErrorMessage() {
        System.out.println("Sorry mate, ah can't find something that ain't there!");
        System.out.println();
    }

    public static void printFileNotFoundMessage() {
        System.out.println("Ooh deary me bruv, I reckon that file don't exists yet, I'm gonna go ahead and make one");
        System.out.println();
    }

    public static void printFileCreationErrorMessage() {
        System.out.println("Oopsie mate, I can't create a new file");
        System.out.println();
    }
}
