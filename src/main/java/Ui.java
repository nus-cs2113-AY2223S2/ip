import exceptions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class Ui {
    protected String userInput;
    protected Parser parser;


    Ui () {}

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
    public void listTasks(ArrayList<Task> taskList) {
        for(int i = 0; i< taskList.size();i+=1) {
            System.out.println('\t' + Integer.toString(i+1) + "." + taskList.get(i).getStatusAndDescription());
            // can be further optimized.
        }
    }

    //TODO: Update the parameters to new TaskList Object
    public void printMarkedTask(String userInput, ArrayList<Task> taskList, FileHandler fileObject) {
        this.printLine();
        System.out.println("\tNice! I've marked this task as done:");
        taskList.get(Integer.parseInt(userInput.split(" ")[1]) - 1).markTask(); //modifying element in the tasklist
        try {
            fileObject.populateFile(taskList);
        } catch (IOException e) {
            System.out.println("WHoops");
        }
        System.out.println("\t\t" + taskList.get(Integer.parseInt(userInput.split(" ")[1]) - 1).getStatusAndDescription());
        this.printLine();
    }

    //TODO: Update the parameters to new TaskList Object
    public void printUnmarkedTask(String userInput, ArrayList<Task> taskList, FileHandler fileObject) {
        this.printLine();
        System.out.println("\tNice! I've marked this task as not done:");
        taskList.get(Integer.parseInt(userInput.split(" ")[1]) - 1).unMarkTask(); //modifying the task list
        try {
            fileObject.populateFile(taskList);
        } catch (IOException e) {
            System.out.println("WHoops");
        }
        System.out.println("\t\t" + taskList.get(Integer.parseInt(userInput.split(" ")[1]) - 1).getStatusAndDescription());
        this.printLine();
    }

    public void printNoTasks(int currentIndex) {
        if(currentIndex==1) {
            System.out.println("\tNow you have " + Integer.toString(currentIndex) + " task in the list");
        } else {
            System.out.println("\tNow you have " + Integer.toString(currentIndex) + " tasks in the list");
        }
    }

    //TODO: Update the parameters to new TaskList Object
    public void printTodo(String userInput, ArrayList<Task> taskList) throws EmptyTodo {
        String[] holder = parser.getTodo(userInput);
        if(holder.length<2)
        {
            throw new EmptyTodo();
        }
        this.printLine();
        String input = userInput.replace("todo ", "");
        Todos temp = new Todos(input);
        taskList.add(temp); // this will be an issue, weirdly it isn't
        this.printNoTasks(taskList.size());
        this.printLine();
    }

    //TODO: Update the parameters to new TaskList Object
    public void printDeadline(String userInput, ArrayList<Task> taskList) throws EmptyDeadline, DeadlineMissingPhrase, DeadlineIsBlank {
        this.printLine();
        String[] deadlineAndDescription = parser.getDeadline(userInput);
        if(!userInput.contains("/by ") && userInput.split(" ").length>1) {
            throw new DeadlineMissingPhrase();
        } else if(deadlineAndDescription.length==1) {
            throw new EmptyDeadline();
        } else if(deadlineAndDescription[1].isBlank()) {
            throw new DeadlineIsBlank();
        }
        Deadlines temp = new Deadlines(deadlineAndDescription[0], deadlineAndDescription[1]);
        taskList.add(temp);
        this.printNoTasks(taskList.size());
        this.printLine();
    }

    //TODO: Update the parameters to new TaskList Object
    public void printEvent(String userInput, ArrayList<Task> taskList) throws EmptyEvent, EventMissingBothPhrases, EventMissingToPhrase, EventMissingFromPhrase, EventFromIsBlank, EventToIsBlank {
        this.printLine();
        String [] eventDescription = parser.getEvent(userInput);
        if(!userInput.contains("/from") && userInput.split(" ").length>1) {
            throw new EventMissingFromPhrase();
        } else if(!userInput.contains("/to") && userInput.split(" ").length>1) {
            throw new EventMissingToPhrase();
        } else if(!(userInput.contains("/from") || userInput.contains("/to")) && userInput.split(" ").length>1) {
            throw new EventMissingFromPhrase();
        } else if (eventDescription.length==1) {
            throw new EmptyEvent();
        }else if(eventDescription[1].isBlank()) {
            throw new EventFromIsBlank();
        } else if(eventDescription[2].isBlank()) {
            throw new EventToIsBlank();
        }
        Events temp = new Events(eventDescription[0], eventDescription[1], eventDescription[2]);
        taskList.add(temp);
        this.printNoTasks(taskList.size());
        this.printLine();
    }

    public void sayBye() {
        this.printLine();
        System.out.println("\tBye. Hope to see you again soon!");
        this.printLine();
    }

    public void displayHelper() {
        this.printLine();
        System.out.println("\tHi! These are the commands which duke understands!");
        this.printLine();
        System.out.println("\ttodo - Creates a todo, use it by adding 'todo' and some description. An example is listed below:");
        System.out.println("\t\t'todo get milk'");
        this.printLine();
        System.out.println("\tdeadline - Creates a deadline, use it by adding 'deadline' followed by some description and a deadline which follows '/by'");
        System.out.println("\t\t'deadline get milk /by tomorrow'");
        this.printLine();
        System.out.println("\tevent - Creates an event, use it by adding 'event' ,some description, a start date followed by '/from' and an end date followed by '/to'");
        System.out.println("\t\t'event get some milk /from today /to tomorrow");
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


}
