/**
 * Represents a command handler to execute user inputs.
 */
public class Command {

    private String typeOfCommand;
    private String args;
    boolean noDescription = false;

    public Command(String[] inputs) {
        this.noDescription = (inputs.length <= 1);
        this.typeOfCommand = inputs[0];

        if (!this.noDescription) {
            args = inputs[1];
        }
    }

    /**
     * Executes the user command based on user input.
     *
     * @param taskList class to store list of tasks at run time.
     * @param ui Ui class for interaction with user.
     * @param storage class for loading and saving of tasks to memory.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        switch (typeOfCommand) {
        case "list":
            taskList.listTask(ui);
            break;
        case "mark":
            if (noDescription) {
                ui.printBigSpace();
                ui.println("☹ OOPS!!! The description of a mark cannot be empty.");
                break;
            }

            try {
                taskList.markTask(args, ui);
                storage.save(taskList, ui);
            } catch (DukeException e) {
                ui.printBigSpace();
                ui.println(e.getMessage());
            }

            break;
        case "unmark":
            if (noDescription) {
                ui.printBigSpace();
                ui.println("☹ OOPS!!! The description of an unmark cannot be empty.");
                break;
            }

            try {
                taskList.unmarkTask(args, ui);
                storage.save(taskList, ui);
            } catch (DukeException e) {
                ui.printBigSpace();
                ui.println(e.getMessage());
            }

            break;
        case "todo":
            if (noDescription) {
                ui.printBigSpace();
                ui.println("☹ OOPS!!! The description of a todo cannot be empty.");
                break;
            }

            try {
                taskList.addTodo(args, ui);
                storage.save(taskList, ui);
            } catch (DukeException e) {
                ui.printBigSpace();
                ui.println(e.getMessage());
            }

            break;
        case "deadline":
            if (noDescription) {
                ui.printBigSpace();
                ui.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                break;
            }

            try {
                taskList.addDeadline(args, ui);
                storage.save(taskList, ui);
            } catch (DukeException e) {
                ui.printBigSpace();
                ui.println(e.getMessage());
            }

            break;
        case "event":
            if (noDescription) {
                ui.printBigSpace();
                ui.println("☹ OOPS!!! The description of an event cannot be empty.");
                break;
            }

            try {
                taskList.addEvent(args, ui);
                storage.save(taskList, ui);
            } catch (DukeException e) {
                ui.printBigSpace();
                ui.println(e.getMessage());
            }

            break;
        case "delete":
            if(noDescription) {
                ui.printBigSpace();
                ui.println("☹ OOPS!!! The description of an delete cannot be empty.");
                break;
            }

            try {
                taskList.deleteTask(args, ui);
                storage.save(taskList, ui);
            } catch (DukeException e) {
                ui.printBigSpace();
                ui.println(e.getMessage());
            }

            break;
        case "find":
            if(noDescription) {
                ui.printBigSpace();
                ui.println("☹ OOPS!!! The description of a find cannot be empty.");
                break;
            }

            try {
                taskList.find(args, ui);
            } catch (DukeException e) {
                ui.printBigSpace();
                ui.println(e.getMessage());
            }

            break;
        default:
            ui.printBigSpace();
            ui.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            break;
        }
    }
}
