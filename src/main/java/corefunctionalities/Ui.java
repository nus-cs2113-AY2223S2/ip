package corefunctionalities;

import dataypes.Task;
import exceptions.EmptyList;
//import helpers.ExceptionGenerator;
//import helpers.Parser;

import java.util.Scanner;

/**
 * A Class that deals with user input and outputs which are shown after processing these inputs. Contains instances of
 * {@link String} and {@link Scanner}
 *
 * @author Muthya Narayanchary Akhil
 */
public class Ui {
    protected String userInput;
    //protected Parser parser = new Parser();
    //protected ExceptionGenerator exceptionGenerator = new ExceptionGenerator();


    /**
     * An empty Constructor to initialize an object of type {@link Ui}.
     */
    public Ui () {}

    /**
     * Reads in input from the user, via the {@link Scanner} object. Used in {@link Duke} to read input.
     */
    public void readCommand() {
        Scanner in = new Scanner(System.in);
        this.userInput = in.nextLine();
    }

    /**
     * Returns {@link String} user input. This method is used in {@link Ui#nullChecker()}.
     *
     * @return A {@link String} which represents the user input.
     */
    public String getUserInput() {
        return this.userInput;
    }

    /**
     * This method shows the opening screen for {@link Duke}.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("If you are unsure of the commands, type 'help'");
        System.out.println("---------------------------------------------------------------------------------");
    }

    /**
     * This method is used to print a line composed of "--".
     */
    public void printLine() {
        System.out.println("\t---------------------------------------------------------------------------------");
    }

    /**
     * This method list all the {@link Task} objects in the {@link TaskList#taskList}.
     * This method is used by {@link helpers.Command#commandlistTasks(TaskList)}
     *
     * @param taskList The {@link java.util.ArrayList<Task>} containing all the tasks.
     * @throws EmptyList If the list is empty.
     */
    public void listTasks(TaskList taskList) throws EmptyList {
        if(taskList.getSize()==0) {
            throw new EmptyList();
        }
        this.printLine();
        System.out.println("\tHere are the tasks in your list:");
        for(int i = 0; i< taskList.getSize();i+=1) {
            System.out.println('\t' + Integer.toString(i+1) + "." + taskList.getTask(i).getStatusAndDescription());
            // can be further optimized.
        }
        this.printLine();
    }

    /**
     * This prints the task which was unmarked
     * by {@link helpers.Command#commandUnMarkTask(String, TaskList, FileHandler)}
     *
     * @param userInput The input command supplied by the user
     * @param taskList The {@link TaskList} containing the <code>taskList</code>
     */
    public void printUnmarkedTask(String userInput, TaskList taskList) {
        this.printLine();
        System.out.println("\tNice! I've marked this task as not done:");//modifying element in the tasklist
        System.out.println("\t\t" +
                taskList.getTask(Integer.parseInt(userInput.split(" ")[1]) - 1).getStatusAndDescription());
        this.printLine();
    }

    /**
     * This prints the task which was marked by {@link helpers.Command#commandMarkTask(String, TaskList, FileHandler)}
     *
     * @param userInput The input command supplied by the user
     * @param taskList The {@link TaskList} containing the <code>taskList</code>
     */
    public void printMarkedTask(String userInput, TaskList taskList) {
        this.printLine();
        System.out.println("\tNice! I've marked this task as done:");//modifying element in the tasklist
        System.out.println("\t\t" +
                taskList.getTask(Integer.parseInt(userInput.split(" ")[1]) - 1).getStatusAndDescription());
        this.printLine();
    }

    /**
     * This method prints the number of {@link Task} objects which are present in {@link TaskList#taskList}. This method
     * is used by {@link Ui}
     *
     * @param currentIndex The size of the {@link TaskList#taskList}
     */
    public void printNoTasks(int currentIndex) {
        if(currentIndex==1) {
            System.out.println("\tNow you have " + Integer.toString(currentIndex) + " task in the list");
        } else {
            System.out.println("\tNow you have " + Integer.toString(currentIndex) + " tasks in the list");
        }
    }

    /**
     * Formats the {@link Ui#printNoTasks(int)} appropriately and prints it. Used by methods in {@link helpers.Command}
     *
     * @param taskList
     */
    public void printTaskEnding (TaskList taskList) {
        this.printNoTasks(taskList.getSize());
        this.printLine();
    }


    /**
     * This method prints out the parting statements from {@link Duke}.
     */
    public void sayBye() {
        this.printLine();
        System.out.println("\tBye. Hope to see you again soon!");
        this.printLine();
    }

    /**
     * This method prints the Command Line User Guide should the user ever need it.
     */
    public void displayHelper() {
        this.printLine();
        System.out.println("\tHi! These are the commands which duke understands!");
        this.printLine();
        System.out.println("\tlist - This would display all the existing Tasks in the Task List");
        this.printLine();
        System.out.println("\ttodo - Creates a todo, use it by adding 'todo' and some description. " +
                "An example is listed below:");
        System.out.println("\t\t'todo get milk'");
        this.printLine();
        System.out.println("\tdeadline - Creates a deadline, use it by adding 'deadline' followed by some description" +
                " and a deadline which follows '/by'");
        System.out.println("\tNote that the dates must follow the following format: yyyy-mm-dd");
        System.out.println("\t\t'deadline get milk /by 2023-12-01'");
        this.printLine();
        System.out.println("\tevent - Creates an event, use it by adding 'event' ,some description, " +
                "a start date followed by '/from' and an end date followed by '/to'");
        System.out.println("\tNote that the dates must follow the following format: yyyy-mm-dd");
        System.out.println("\t\t'event get some milk /from 2023-03-01 /to 2023-03-02");
        this.printLine();
        System.out.println("\tmark - mark would inform Duke to mark a task as complete. " +
                "To invoke this, type 'mark' followed by the serial number of the specific task");
        System.out.println("\t\t 'mark 1'");
        this.printLine();
        System.out.println("\tunmark - unmark would inform Duke to unmark a task as incomplete. To invoke this, " +
                "type 'unmark' followed by the serial number of the specific task");
        System.out.println("\t\t 'unmark 1'");
        this.printLine();
        System.out.println("\tdelete - delete would inform Duke to delete a task. " +
                "To invoke this type 'delete' followed by the serial number of the specific task");
        System.out.println("\t\t 'delete 1'");
        this.printLine();
        System.out.println("\tfind - find would inform Duke to look for a certain phrase across all the Tasks. " +
                "To invoke this type 'find' followed by the phrase you wish to look for");
        System.out.println("\t\t 'find book'");
        this.printLine();
        System.out.println("\tbye - to exit the program!");
        this.printLine();
    }

    /**
     * This method checks if the input read by {@link Ui#readCommand()} is non-empty.
     */
    public void nullChecker() {
        while(this.getUserInput().equals("") || this.getUserInput().equals(" ")) {
            this.printLine();
            System.out.println("\tSorry please enter a valid input ");
            this.printLine();
            this.readCommand();
        }
    }


    /**
     * This method prompts the user to enter a valid input, if the input read by {@link Ui#readCommand()} is not valid.
     */
    public void validCommand() {
        this.printLine();
        System.out.println("\tPlease enter a valid input");
        this.printLine();
    }

    /**
     * This method prints out the details of the {@link Task} Object which was
     * deleted from the {@link TaskList#taskList}
     *
     * @param item The {@link Task} which needs to be deleted
     * @param taskList The {@link TaskList#taskList} containing all the <code>Tasks</code>
     */
    public void printDeleteCommand(Task item, TaskList taskList) {
        this.printLine();
        System.out.println("\tNoted! I've removed this task!");
        System.out.println("\t\t" + item.getStatusAndDescription());
        this.printNoTasks(taskList.getSize());
        this.printLine();
    }


}
