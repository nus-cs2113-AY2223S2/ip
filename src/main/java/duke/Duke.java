package duke;
import java.util.Scanner;
import file.FileManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import exceptions.InvalidAddTaskException;
import exceptions.InvalidCommandException;
import exceptions.TaskListOutofBoundsException; 
import tasks.Task;
import userInterface.Print;
import commands.Command;

public class Duke {

    public static ArrayList<Task> tasksList = new ArrayList<Task>();

    public static void main(String[] args){
        try{
            FileManager.readIntoList();
        } catch (FileNotFoundException e){
            System.out.println("File Not Found");
        }
        Print.printWelcome();
        Scanner in = new Scanner(System.in);
        while (true){
            String userInput;
            userInput = in.nextLine();
            String [] userInputArray = userInput.split(" ",2);
            String command = Command.retrieveCommand(userInputArray);
            try{
            switch (command){
            case "bye": 
                Print.printGoodbye();
                FileManager.saveFile();
                return;
            case "list":
                Command.displayTaskList();
                break;
            case "mark":
                Command.markTask(Command.retrieveMarkIndex(userInputArray));
                break;
            case "unmark":
                Command.unmarkTask(Command.retrieveMarkIndex(userInputArray));
                break;
            case "todo":
                Command.addTodo(userInputArray);
                break;
            case "deadline":
                Command.addDeadline(userInputArray);
                break;
            case "event":
                Command.addEvent(userInputArray);
                break;
            default:
                Command.throwInvalidCommand();
                break;
            }
        } catch (NumberFormatException e){
            System.out.println("Please type an integer behind mark/unmark!");
        } catch (TaskListOutofBoundsException e){
            System.out.println("The task you want to mark/unmark is not found!");
        } catch (InvalidCommandException e){
            System.out.println("Please input a valid command!");
        } catch (InvalidAddTaskException e){
            System.out.println("Please the input correct task parameters!");
        } catch (IOException e){
            System.out.println("File Write Error");
        }
    }
    }
}
