package corefunctionalities;

import dataypes.Task;
import exceptions.EmptyList;
import helpers.ExceptionGenerator;
import helpers.Parser;

import java.util.Scanner;
public class Ui {
    protected String userInput;
    protected Parser parser = new Parser();
    protected ExceptionGenerator exceptionGenerator = new ExceptionGenerator();


    public Ui () {}

    public void readCommand() {
        Scanner in = new Scanner(System.in);
        this.userInput = in.nextLine();
    }
    public String getUserInput() {
        return this.userInput;
    }

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

    public void printLine() {
        System.out.println("\t---------------------------------------------------------------------------------");
    }

    //TODO: Update the parameters to new TaskList Object
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

    //TODO: Update the parameters to new TaskList Object
    public void printUnmarkedTask(String userInput, TaskList taskList, FileHandler fileObject) {
        this.printLine();
        System.out.println("\tNice! I've marked this task as not done:");//modifying element in the tasklist
        System.out.println("\t\t" + taskList.getTask(Integer.parseInt(userInput.split(" ")[1]) - 1).getStatusAndDescription());
        this.printLine();
    }

    //TODO: Update the parameters to new TaskList Object
    public void printMarkedTask(String userInput, TaskList taskList, FileHandler fileObject) {
        this.printLine();
        System.out.println("\tNice! I've marked this task as done:");//modifying element in the tasklist
        System.out.println("\t\t" + taskList.getTask(Integer.parseInt(userInput.split(" ")[1]) - 1).getStatusAndDescription());
        this.printLine();
    }

    public void printNoTasks(int currentIndex) {
        if(currentIndex==1) {
            System.out.println("\tNow you have " + Integer.toString(currentIndex) + " task in the list");
        } else {
            System.out.println("\tNow you have " + Integer.toString(currentIndex) + " tasks in the list");
        }
    }

    // TODO: Change the name and move this to the command module, and only leave the base Todo Here
    //TODO: Also wanna add the FileHandler module here

    public void printTaskEnding (TaskList taskList) {
        this.printNoTasks(taskList.getSize());
        this.printLine();
    }


    //TODO: Update the parameters to new TaskList Object

    public void sayBye() {
        this.printLine();
        System.out.println("\tBye. Hope to see you again soon!");
        this.printLine();
    }

    public void displayHelper() {
        this.printLine();
        System.out.println("\tHi! These are the commands which duke understands!");
        this.printLine();
        System.out.println("\tlist - This would display all the existing Tasks in the Task List");
        this.printLine();
        System.out.println("\ttodo - Creates a todo, use it by adding 'todo' and some description. An example is listed below:");
        System.out.println("\t\t'todo get milk'");
        this.printLine();
        System.out.println("\tdeadline - Creates a deadline, use it by adding 'deadline' followed by some description and a deadline which follows '/by'");
        System.out.println("\tNote that the dates must follow the following format: yyyy-mm-dd");
        System.out.println("\t\t'deadline get milk /by 2023-12-01'");
        this.printLine();
        System.out.println("\tevent - Creates an event, use it by adding 'event' ,some description, a start date followed by '/from' and an end date followed by '/to'");
        System.out.println("\tNote that the dates must follow the following format: yyyy-mm-dd");
        System.out.println("\t\t'event get some milk /from 2023-03-01 /to 2023-03-02");
        this.printLine();
        System.out.println("\tmark - mark would inform Duke to mark a task as complete. To invoke type 'mark' followed by the serial number of the specific task");
        System.out.println("\t\t 'mark 1'");
        this.printLine();
        System.out.println("\tunmark - unmark would inform Duke to unmark a task as incomplete. To invoke type 'unmark' followed by the serial number of the specific task");
        System.out.println("\t\t 'unmark 1'");
        this.printLine();
        System.out.println("\tdelete - delete would inform Duke to delete a task. To invoke type 'delete' followed by the serial number of the specific task");
        System.out.println("\t\t 'delete 1'");
        this.printLine();
        System.out.println("\tfind - find would inform Duke to look for a certain phrase across all the Tasks. To invoke type 'find' followed by the phrase you wish to look for");
        System.out.println("\t\t 'find book'");
        this.printLine();
        System.out.println("\tbye - to exit the program!");
        this.printLine();
    }
    
    public void nullChecker() {
        while(this.getUserInput().equals("") || this.getUserInput().equals(" ")) {
            this.printLine();
            System.out.println("\tSorry please enter a valid input ");
            this.printLine();
            this.readCommand();
        }
    }


    public void validCommand() {
        this.printLine();
        System.out.println("\tPlease enter a valid input");
        this.printLine();
    }

    public void printDeleteCommand(Task item, TaskList taskList) {
        this.printLine();
        System.out.println("\tNoted! I've removed this task!");
        System.out.println("\t\t" + item.getStatusAndDescription());
        this.printNoTasks(taskList.getSize());
        this.printLine();
    }


}
