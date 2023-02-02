import java.util.Scanner;

public class Sage {
    public static void main(String[] args) {
        Display ui = new Display();
        TaskList taskList = new TaskList();
        Scanner command = new Scanner(System.in);
        ui.welcomeUser();
        while (command.hasNextLine()) {
            String[] output;
            String input = command.nextLine();
            switch (input) {
            case "bye":
                ui.goodByeUser();
                return;
            case "list":
                ui.displayTask(taskList);
                break;
            case "unmark":
                output = input.split(" ");
                taskList.unmarkTask(Integer.parseInt(output[1]), ui);
                break;
            case "mark":
                output = input.split(" ");
                taskList.markTask(Integer.parseInt(output[1]), ui);
                break;
            case "todo":
                output = input.split("/");
                taskList.addTask(input);
                ui.addedTask(output, TaskType.TODO, taskList);
                break;
            case "deadline":
                output = input.split("/");
                taskList.addTask(output[0].substring(9), output[1].substring(3));
                ui.addedTask(output, TaskType.DEADLINE, taskList);
                break;
            case "event":
                output = input.split("/");
                taskList.addTask(output[0].substring(6), output[1].substring(5), output[2].substring(3));
                ui.addedTask(output, TaskType.EVENT, taskList);
                break;
            default:
                ui.unknownInput();
                break;
            }
        }
    }
}
