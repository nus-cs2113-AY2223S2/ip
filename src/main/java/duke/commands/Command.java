package duke.commands;

import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidFormatException;
import duke.exceptions.InvalidTaskException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.tasks.TaskList;
import duke.ui.Ui;


public class Command {
    private static TaskList taskList = new TaskList();

    public static void handleCommand(String[] inputArray) throws InvalidCommandException, InvalidTaskException, InvalidFormatException {
        String command = inputArray[0];

        try { 
            switch (command) {
            case "todo":
                if (inputArray.length == 1) {
                    throw new InvalidTaskException(command);
                }
                String todoDetails = inputArray[1];
                Task todo = new Todo(todoDetails);
                taskList.addTask(todo);
                break;

            case "deadline":
                if (inputArray.length == 1) {
                    throw new InvalidTaskException(command);
                }
                if (!inputArray[1].contains("/by")) {
                    throw new InvalidFormatException("/by");
                }
                //splits input according to deadlineDescription, /by
                String[] deadlineDetails = inputArray[1].split(" /by ", 2);

                Task deadline = new Deadline(deadlineDetails[0],deadlineDetails[1]);
                taskList.addTask(deadline);
                break;

            case "event":
                if (inputArray.length == 1) {
                    throw new InvalidTaskException(command);
                }
                if (!inputArray[1].contains("/from") && !inputArray[1].contains("/to")) {
                    throw new InvalidFormatException("/from", "/to");
                }
                //splits input according to eventDescription, /from, /to
                String[] eventDetails = inputArray[1].split(" /from | /to ", 3);

                Task event = new Event(eventDetails[0],eventDetails[1],eventDetails[2]);
                taskList.addTask(event);
                break;

            case "list":
                taskList.printTasks();
                break;

            case "mark":
                if (inputArray.length == 1) {
                    throw new InvalidTaskException(command);
                }
                int taskNum = Integer.parseInt(inputArray[1]);
                taskList.markTask(taskNum);
                break;

            case "unmark":
                if (inputArray.length == 1) {
                    throw new InvalidTaskException(command);
                }
                int unmarkTaskNum = Integer.parseInt(inputArray[1]);
                taskList.unmarkTask(unmarkTaskNum);
                break;
                
            case "delete":
                if (inputArray.length == 1) {
                    throw new InvalidTaskException(command);
                }
                int deleteIndex = Integer.parseInt(inputArray[1]);
                taskList.deleteTask(deleteIndex);
                break;
                
            case "bye":
                Ui.printExit();
                System.exit(0);
                break;

            default:
                throw new InvalidCommandException();
            }
        } catch (InvalidTaskException e) {
            System.out.println(e.getMessage());
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
        } catch (InvalidFormatException e) {
            System.out.println(e.getMessage());
        }
    }
}
