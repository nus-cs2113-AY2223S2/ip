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
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                command.printList(taskList);
            }
            else if(userInput.contains("delete")){
                command.deleteItem(userInput, taskList);
            } else if(userInput.contains("mark")) {
                if (userInput.contains("unmark")) {
                    command.unMarkItem(userInput, taskList);
                } else {
                    command.markItem(userInput, taskList);
                }
            } else if (userInput.contains("event")){
                command.createEvent(userInput,taskList);
            } else if (userInput.contains("deadline")) {
                command.createDeadline(userInput, taskList);
            } else if (userInput.contains("todo")){
                command.createToDo(userInput,taskList);
            } else {
               error.invalidCommand();
               Text.printHelp();
            }
            System.out.println("What would you like to do?");
            userInput = myObj.nextLine();
        }
        Text.printBye();
    }
}