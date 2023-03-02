import java.io.IOException;

public class Parser{

    public boolean isExit(String input){
        return input.equals("bye");
    }

    public void executeLine(TaskList taskList, String input) throws IOException {
        String[] inputSplit = input.split(" ", 2);
        String commandName = inputSplit[0].trim();

        switch (commandName){
            case "todo":
                Command addTodo = new AddTodoCommand();
                addTodo.executeCommand(taskList, input);
                Storage.updateFile(taskList);
                break;

            case "deadline":
                Command addDeadline = new AddDeadlineCommand();
                addDeadline.executeCommand(taskList, input);
                Storage.updateFile(taskList);
                break;

            case "event":
                Command addEvent = new AddEventCommand();
                addEvent.executeCommand(taskList, input);
                Storage.updateFile(taskList);
                break;

            case "list":
                Command list = new ListCommand();
                list.executeCommand(taskList, input);
                break;

            case "mark":
                Command mark = new MarkTaskCommand();
                mark.executeCommand(taskList, input);
                Storage.updateFile(taskList);
                break;

            case "unmark":
                Command unmark = new UnmarkTaskCommand();
                unmark.executeCommand(taskList, input);
                Storage.updateFile(taskList);
                break;

            case "delete":
                Command delete = new DeleteTaskCommand();
                delete.executeCommand(taskList, input);
                Storage.updateFile(taskList);
                break;

            case "find":
                Command find = new FindTaskCommand();
                find.executeCommand(taskList, input);
                break;
            case "help":
                Command help = new HelpCommand();
                help.executeCommand(taskList, input);

            default:
        }
    }

}
