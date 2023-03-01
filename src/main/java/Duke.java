import Commands.Text;
import Commands.command;
import Commands.error;
import Tasks.Task;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Text.printLogo();
        Scanner myObj = new Scanner(System.in);
        String userInput;
        userInput = myObj.nextLine();
        ArrayList<Task> taskList = new ArrayList<Task>(100);
        while (!userInput.equals("bye")) { //if user inputs "by" the loop will break
            if (userInput.equals("list")) { //to list all tasks
                command.printList(taskList);
            } else if(userInput.contains("delete")){ //to delete an item from the list
                command.deleteItem(userInput, taskList);
            } else if(userInput.contains("mark")) { //to check if an input is to mark/unmark an item
                if (userInput.contains("unmark")) {
                    command.unMarkItem(userInput, taskList);
                } else {
                    command.markItem(userInput, taskList);
                }
            } else if (userInput.contains("event")){ //to add an event to the list
                command.createEvent(userInput,taskList);
            } else if (userInput.contains("deadline")) { //to add a deadline to the list
                command.createDeadline(userInput, taskList);
            } else if (userInput.contains("todo")){  //to add to do item to the list
                command.createToDo(userInput,taskList);
            } else { //For any command that is not within the list of commands
               error.invalidCommand();
               Text.printHelp();
            }
            System.out.println("What would you like to do?");
            userInput = myObj.nextLine(); //to take in next input
        }
        Text.printBye();
    }
}