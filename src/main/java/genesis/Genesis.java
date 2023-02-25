package genesis;

import java.io.IOException;

import exception.EmptyListException;
import exception.InvalidArgumentException;
import exception.KeywordNotFoundException;
import task.TaskList;
import utility.Ui;
import utility.Storage;

import exception.UnknownCommandException;

/**
 * The Genesis class handles the main functionality of the chatbot program. It processes user inputs and
 * calls the appropriate methods to execute the corresponding actions.
 */
public class Genesis {

    private TaskList tasks;

    public Genesis() {
        this.tasks = new TaskList();
    }

    /**
     * Processes user input and calls the corresponding methods to execute the corresponding actions.
     *
     * @param userInput the user input string to process
     */
    public void askGenesis(String userInput) {
        Validator validator = new Validator();

        try {
            Ui.breakLine();

            String[] contentArr = userInput.split(" ", 2);
            String command = contentArr[0];

            switch (command) {
            case "list":
                tasks.list();
                return;

            case "mark":
                validator.validateIndex(contentArr);
                tasks.markTask(contentArr[1]);
                break;

            case "unmark":
                validator.validateIndex(contentArr);
                tasks.unmarkTask(contentArr[1]);
                break;

            case "todo":
                validator.validateDescription(contentArr);
                tasks.addTodo(contentArr[1]);
                break;

            case "deadline":
                validator.validateDescription(contentArr);
                tasks.addDeadline(contentArr[1]);
                break;

            case "event":
                validator.validateDescription(contentArr);
                tasks.addEvent(contentArr[1]);
                break;

            case "delete":
                validator.validateIndex(contentArr);
                tasks.deleteTask(contentArr[1]);
                break;
            case "find":
                tasks.findTask(contentArr[1]);
                break;
            default:
                throw new UnknownCommandException();
            }

            Storage.saveToFile(tasks);

        } catch (InvalidArgumentException e) {
            System.out.println(e.getMessage());
        } catch (EmptyListException e) {
            System.out.println("☹ OOPS!!! List is empty, nothing to show");
        } catch (UnknownCommandException e) {
            System.out.println("I'm sorry, but I don't know what that means :-(");
            System.out.println("Please use one of the predefined command");
            Ui.helpAll();
        } catch (KeywordNotFoundException e) {
            System.out.println("☹ OOPS!!! The keyword you searched does not match any of the entries");
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! Index is not a number");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! Task index does not exist");
        } catch (IOException e) {
            System.out.println("☹ OOPS!!! Failed to update to local file storage");
        } finally {
            Ui.breakLine();
        }
    }
}
