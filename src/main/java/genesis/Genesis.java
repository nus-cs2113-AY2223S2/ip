package genesis;

import java.io.IOException;
import java.util.ArrayList;

import task.Task;

import utility.ConsolePrinter;
import utility.FileHandler;

import exception.GenesisException;
import exception.UnknownCommandException;

public class Genesis {

    private ArrayList<Task> tasks;

    public Genesis() {
        try {
            this.tasks = FileHandler.loadFromFile();
            System.out.println("Task list loaded successfully.");
        } catch (IOException e) {
            this.tasks = new ArrayList<>();
            System.out.println("There is no existing task list found. Initializing new task list.");
        } catch (IndexOutOfBoundsException | GenesisException e) {
            this.tasks = new ArrayList<>();
            System.out.println("Task list is corrupted. Initializing new task list.");
        } finally {
            ConsolePrinter.breakLine();
        }
    }

    private void validateIndex(String[] contentArr) throws GenesisException {
        if (contentArr.length < 2) {
            throw new GenesisException("Index cannot be empty");
        }
    }

    private void validateDescription(String[] contentArr) throws GenesisException {
        if (contentArr.length < 2 || contentArr[1].isBlank()) {
            throw new GenesisException("The description cannot be empty.");
        }
    }

    public void askGenesis(String userInput) {
        try {
            ConsolePrinter.breakLine();

            String[] contentArr = userInput.split(" ", 2);
            String command = contentArr[0];

            switch (command) {
            case "list":
                CommandHandler.handleListTasks(tasks);
                return;

            case "mark":
                validateIndex(contentArr);
                CommandHandler.handleMarkTask(contentArr[1], tasks);
                break;

            case "unmark":
                validateIndex(contentArr);
                CommandHandler.handleUnmarkTask(contentArr[1], tasks);
                break;

            case "todo":
                validateDescription(contentArr);
                CommandHandler.handleTodo(contentArr[1], tasks);
                break;

            case "deadline":
                validateDescription(contentArr);
                CommandHandler.handleDeadline(contentArr[1], tasks);
                break;

            case "event":
                validateDescription(contentArr);
                CommandHandler.handleEvent(contentArr[1], tasks);
                break;

            case "delete":
                validateIndex(contentArr);
                CommandHandler.handleDelete(contentArr[1], tasks);
                break;

            default:
                throw new UnknownCommandException("I'm sorry, but I don't know what that means :-(");
            }

            FileHandler.saveToFile(tasks);

        } catch (GenesisException e) {
            System.out.println(e.getMessage());
        } catch (UnknownCommandException e) {
            System.out.println(e.getMessage());
            System.out.println("Please use one of the predefined command");
            ConsolePrinter.helpAll();
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! Index is not a number");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! Task index does not exist");
        } catch (IOException e) {
            System.out.println("☹ OOPS!!! Failed to update to local file storage");
        } finally {
            ConsolePrinter.breakLine();
        }
    }
}
