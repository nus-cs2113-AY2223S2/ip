package duke;

import duke.command.*;

public class Parser {

    public static Command parse(String input) {
        String[] command = input.split(" ", 2);

        switch (command[0]) {
        case "bye":
            return new ByeCommand();
        //isRunning = false;

        case "list":
            return new ListCommand();

        case "mark":
            try {
                int taskNumber = Integer.parseInt(command[1]);
                return new MarkTaskCommand((taskNumber));

            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Please indicate the task number to be marked.");
            }
            break;
        case "unmark":
            try {
                int taskNumber = Integer.parseInt(command[1]);
                return new UnmarkTaskCommand((taskNumber));
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Please indicate the task number to be unmarked.");
            }
            break;
        case "delete":
            try {
                int taskNumber = Integer.parseInt(command[1]);
                return new DeleteCommand(taskNumber);

            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Please indicate the task number to be deleted.");
            }
            break;

        case "todo":
            try {
                AddTodoCommand todoCommand = new AddTodoCommand(command[1]);
                return todoCommand;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("OOPS!!! The description of a todo cannot be empty.");
                //throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }

            break;
        case "deadline":
            try {

                if (command[1].contains("/by")) {
                    String[] components = command[1].split(" /by");
                    AddDeadlineCommand deadlineCommand = new AddDeadlineCommand(components[0], components[1]);
                    return deadlineCommand;
                }

                else {
                    System.out.println("Invalid format. Remember to use '/by' to indicate the time.");
                }


            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("OOPS!!! The description of a deadline cannot be empty.");
                //throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            } /*catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Please specify a time for the deadline.");
            }
            */
            break;
        case "event":
            try {
                if (command[1].matches("(.*)" + "/from" + "(.*)" + "/to" + "(.*)")) {
                    try {
                        String[] components = command[1].split(" /from | /to "); //split string using "/from" and "/to"
                        AddEventCommand eventCommand = new AddEventCommand(components[0], components[1], components[2]);
                        return eventCommand;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Please specify both the starting and ending time of the event");
                    }
                } else {
                    System.out.println("Incorrect format. Specify events in the format 'event A /from B to /C'");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("OOPS!!! The description of an event cannot be empty.");
            }
            break;
        default:
            UI.printInvalidMessage();
        }
        return new Command();
    }
}
