import java.util.Scanner;

public class Sage {
    public static void main(String[] args) {
        Display ui = new Display();
        TaskList taskList = new TaskList();
        Scanner input = new Scanner(System.in);
        ui.welcomeUser();
        while (input.hasNextLine()) {
            Command command = new Command(input.nextLine());
            switch (command.getTaskType()) {
            case "bye":
                ui.goodByeUser();
                return;
            case "list":
                ui.displayTask(taskList);
                break;
            case "unmark":
                taskList.unmarkTask(command.getTaskDescription(), ui);
                break;
            case "mark":
                taskList.markTask(command.getTaskDescription(), ui);
                break;
            case "todo":
                taskList.addTask(command.getTaskDescription());
                ui.addedTask(command, TaskType.TODO, taskList);
                break;
            case "deadline":
                taskList.addTask(command.getTaskDescription(), command.getBy());
                ui.addedTask(command, TaskType.DEADLINE, taskList);
                break;
            case "event":
                taskList.addTask(command.getTaskDescription(), command.getFrom(), command.getTo());
                ui.addedTask(command, TaskType.EVENT, taskList);
                break;
            default:
                ui.unknownInput();
                break;
            }
        }
    }
}
