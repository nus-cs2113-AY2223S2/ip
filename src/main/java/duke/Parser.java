package duke;

public class Parser {

    Ui ui;
    TaskList tasks;

    public Parser(TaskList tasks) {
        ui = new Ui();
        this.tasks = tasks;
    }

    enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }
    
    /**
     * Adds a new task to the TaskList and calls methods to print the appropriate output.
     *
     * @param type Enum type representing either a TODO, EVENT, or DEADLINE.
     * @param argsSplit, String array of length 2 with command in first index and arguments in second index.
     * @throws InsufficientParametersException If not enough parameters are passed in for a respective command.
     */
    private void handleAddTask(TaskType type, String[] argsSplit) throws InsufficientParametersException {
        if (argsSplit.length < 2) {
            throw new InsufficientParametersException();
        }
        String args = argsSplit[1];
        
        Task task;
        String[] argsList;

        switch (type) {
        case TODO:
            task = new Todo(args);
            break;
        case DEADLINE:
            // Split into arguments for description and by field
            argsList = args.split(" /by ");
            if (argsList.length < 2) {
                throw new InsufficientParametersException();
            }
            task = new Deadline(argsList[0], argsList[1]);
            break;
        case EVENT:
            // Uses regex to split into arguments for description, from field, and to field.
            argsList = args.split(" \\/(from|to) ");
            if (argsList.length < 3) {
                throw new InsufficientParametersException();
            }
            task = new Event(argsList[0], argsList[1], argsList[2]);
            break;
        default:
            throw new RuntimeException("Unrecognized Task Type");
        }

        tasks.addTask(task);
        ui.printAddTaskConfirmation(task, tasks.length());
    }


    public boolean parseInput(String input) {
        // Splits into only two sections, one for command name and one for arguments
        try {
            String[] splitIntoArgs = input.split(" ", 2);
            String cmd = splitIntoArgs[0];

            switch (cmd) {
            case "bye":
                ui.printSimpleMessage("Bye. Hope to see you again soon!");
                return false;
            case "list":
                ui.printList(tasks);
                break;
            case "mark":
                if (splitIntoArgs.length < 2) {
                    throw new InsufficientParametersException();
                }
                int toMark = Integer.parseInt(splitIntoArgs[1]) - 1;
                tasks.getTask(toMark).markDone();
                ui.printMessageWithTask("Ok, I've marked this task as done:", tasks.getTask(toMark));
                break;
            case "unmark":
                if (splitIntoArgs.length < 2) {
                    throw new InsufficientParametersException();
                }
                int toUnmark = Integer.parseInt(splitIntoArgs[1]) - 1;
                tasks.getTask(toUnmark).unmarkDone();
                ui.printMessageWithTask("Ok, I've marked this task as not done yet:", tasks.getTask(toUnmark));
                break;
            case "delete":
                if (splitIntoArgs.length < 2) {
                    throw new InsufficientParametersException();
                }
                int toDelete = Integer.parseInt(splitIntoArgs[1]) - 1;
                ui.printMessageWithTask("Noted. I've removed this task:", tasks.getTask(toDelete));
                tasks.removeTask(toDelete);
                break;
            case "todo":
                handleAddTask(TaskType.TODO, splitIntoArgs);                
                break;
            case "deadline":
                handleAddTask(TaskType.DEADLINE, splitIntoArgs);
                break;
            case "event":
                handleAddTask(TaskType.EVENT, splitIntoArgs);
                break;
            case "find":
                if (splitIntoArgs.length < 2) {
                    throw new InsufficientParametersException();
                }
                String keyword = splitIntoArgs[1];
                ui.printMatches(tasks, keyword);
                break;
            default:
                throw new UnknownCommandException();
            }
            return true;
        } catch (UnknownCommandException e) {
            ui.printSimpleMessage("Command not found. Please enter a valid command!");
            return true;
        } catch (InsufficientParametersException e) {
            ui.printSimpleMessage(
                "You have not provided enough parameters for this command. Please recheck your syntax!");
            return true;
        } catch (IncorrectIndexException e) {
            ui.printSimpleMessage("Please enter a valid index.");
            return true;
        }
    }
}
