package genesis;

import java.io.IOException;
import java.util.ArrayList;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

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

    private void handleListTasks() throws GenesisException {
        if (tasks.size() < 1) {
            throw new GenesisException("List is empty, nothing to show");
        }

        ConsolePrinter.listTasks(tasks);
    }

    private void handleMarkTask(String content) {
        int index = Integer.parseInt(content) - 1;
        Task task = tasks.get(index);
        task.setIsDone(true);

        ConsolePrinter.onTaskMarked(task.getListDescription());
    }

    private void handleUnmarkTask(String content) {
        int index = Integer.parseInt(content) - 1;
        Task task = tasks.get(index);
        task.setIsDone(false);

        ConsolePrinter.onTaskUnmarked(task.getListDescription());
    }

    private void handleTodo(String description) throws GenesisException {
        Todo todo = new Todo(description);
        tasks.add(todo);

        ConsolePrinter.onTaskAdded(todo.getListDescription(), tasks.size());
    }

    private void handleDeadline(String content) throws GenesisException {
        String[] parts = content.split(" /by ", 2);
        if (parts.length < 2) {
            throw new GenesisException("Description for deadline is invalid");
        }

        String description = parts[0];
        String by = parts[1];

        Deadline deadline = new Deadline(description, by);
        tasks.add(deadline);

        ConsolePrinter.onTaskAdded(deadline.getListDescription(), tasks.size());
    }

    private void handleEvent(String content) throws GenesisException {
        String[] parts = content.split(" /from | /to ", 3);
        if (parts.length < 3) {
            throw new GenesisException("Description for event is invalid");
        }

        String description = parts[0];
        String from = parts[1];
        String to = parts[2];

        Event event = new Event(description, from, to);
        tasks.add(event);

        ConsolePrinter.onTaskAdded(event.getListDescription(), tasks.size());
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
            case "list": {
                handleListTasks();
                return;
            }
            case "mark": {
                validateIndex(contentArr);
                handleMarkTask(contentArr[1]);
                break;
            }
            case "unmark": {
                validateIndex(contentArr);
                handleUnmarkTask(contentArr[1]);
                break;
            }
            case "todo": {
                validateDescription(contentArr);
                handleTodo(contentArr[1]);
                break;
            }
            case "deadline": {
                validateDescription(contentArr);
                handleDeadline(contentArr[1]);
                break;
            }
            case "event": {
                validateDescription(contentArr);
                handleEvent(contentArr[1]);
                break;
            }
            default: {
                throw new UnknownCommandException("I'm sorry, but I don't know what that means :-(");
            }
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
