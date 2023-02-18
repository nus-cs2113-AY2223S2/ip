public class Command {

    private String typeOfCommand;
    private String args;
    boolean hasDescription = false;

    public Command(String[] inputs) {
        this.hasDescription = (inputs.length > 1);
        this.typeOfCommand = inputs[0];

        if(this.hasDescription) {
            args = inputs[1];
        }
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        switch (typeOfCommand) {
            case "list":
                taskList.listTask();
                break;
            case "mark":
                if (hasDescription) {
                    try {
                        taskList.markTask(args);
                        storage.save(taskList, ui);
                    } catch (DukeException e) {
                        System.out.println("       " + e.getMessage());
                    }
                } else {
                    System.out.println("       ☹ OOPS!!! The task number cannot be empty.");
                }
                break;
            case "unmark":
                if (hasDescription) {
                    try {
                        taskList.unmarkTask(args);
                        storage.save(taskList, ui);
                    } catch (DukeException e) {
                        System.out.println("       " + e.getMessage());
                    }
                } else {
                    System.out.println("       ☹ OOPS!!! The task number cannot be empty.");
                }
                break;
            case "todo":
                if (hasDescription) {
                    try {
                        taskList.addTodo(args);
                        storage.save(taskList, ui);
                    } catch (DukeException e) {
                        System.out.println("       " + e.getMessage());
                    }
                } else {
                    System.out.println("       ☹ OOPS!!! The description of a todo cannot be empty.");
                }
                break;
            case "deadline":
                if (hasDescription) {
                    try {
                        taskList.addDeadline(args);
                        storage.save(taskList, ui);
                    } catch (DukeException e) {
                        System.out.println("       " + e.getMessage());
                    }
                } else {
                    System.out.println("       ☹ OOPS!!! The description of a deadline cannot be empty.");
                }
                break;
            case "event":
                if (hasDescription) {
                    try {
                        taskList.addEvent(args);
                        storage.save(taskList, ui);
                    } catch (DukeException e) {
                        System.out.println("       " + e.getMessage());
                    }
                } else {
                    System.out.println("       ☹ OOPS!!! The description of an event cannot be empty.");
                }
                break;
            case "delete":
                if(hasDescription) {
                    try {
                        taskList.deleteTask(args);
                        storage.save(taskList, ui);
                    } catch (DukeException e) {
                        System.out.println("       " + e.getMessage());
                    }
                } else {
                    System.out.println("       ☹ OOPS!!! The description of an delete cannot be empty.");
                }

                break;
            default:
                System.out.println("       ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                break;
        }
    }
}
