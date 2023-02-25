package commands;
import exceptions.InvalidAddTaskException;
import exceptions.InvalidCommandException;
import exceptions.TaskListOutofBoundsException;
import userInterface.Print;
import file.FileManager;
import tasklist.Tasklist;
import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Command {

    public static void runCommand(String userInput){
        String [] userInputArray = userInput.split(" ",2);
        String command = Tasklist.retrieveCommand(userInputArray);
        try{
            switch (command){
            case "bye": 
                Print.printGoodbye();
                FileManager.saveFile();
                System.exit(0);
            case "list":
                Tasklist.displayTaskList();
                break;
            case "mark":
                Tasklist.markTask(Tasklist.retrieveMarkIndex(userInputArray));
                break;
            case "unmark":
                Tasklist.unmarkTask(Tasklist.retrieveMarkIndex(userInputArray));
                break;
            case "delete":
                Tasklist.deleteTask(Tasklist.retrieveMarkIndex(userInputArray));
                break;    
            case "todo":
                Tasklist.addTodo(userInputArray);
                break;
            case "deadline":
                Tasklist.addDeadline(userInputArray);
                break;
            case "event":
                Tasklist.addEvent(userInputArray);
                break;
            default:
                Tasklist.throwInvalidCommand();
                break;
            }
        } catch (NumberFormatException e){
            System.out.println("Please type an integer behind mark/unmark/delete!");
        } catch (TaskListOutofBoundsException e){
            System.out.println(TaskListOutofBoundsException.taskListOutOfBoundsMessage);
        } catch (InvalidCommandException e){
            System.out.println(InvalidCommandException.invalidCommandMessage);
        } catch (InvalidAddTaskException e){
            System.out.println(InvalidAddTaskException.invalidAddTaskMessage);
        } catch (IOException e){
            System.out.println("File Write Error");
        } catch (DateTimeParseException e){
            System.out.println("Pls enter the date in format yyyy-mm-dd!");
        }
    }
}
