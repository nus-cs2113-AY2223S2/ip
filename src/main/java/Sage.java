import java.util.ArrayList;
import java.util.Scanner;

public class Sage {
    public static void main(String[] args) {
        Display UI = new Display();
        TaskList taskList = new TaskList();
        Scanner s = new Scanner(System.in);
        UI.welcomeUser();
        while (s.hasNextLine()) {
            String[] output;
            String input = s.nextLine();
            switch (input) {
            case "bye":
                UI.goodByeUser();
                return;
            case "list":
                UI.displayTask(taskList);
                break;
            case "unmark":
                output = input.split(" ");
                taskList.unmarkTask(Integer.parseInt(output[1]), UI);
                break;
            case "mark":
                output = input.split(" ");
                taskList.markTask(Integer.parseInt(output[1]), UI);
                break;
            case "todo":
                output = input.split("/");
                taskList.addTask(input);
                UI.addedTask(output, TaskType.TODO, taskList);
                break;
            case "deadline":
                output = input.split("/");
                taskList.addTask(output[0].substring(9), output[1].substring(3));
                UI.addedTask(output, TaskType.DEADLINE, taskList);
                break;
            case "event":
                output = input.split("/");
                taskList.addTask(output[0].substring(6), output[1].substring(5), output[2].substring(3));
                UI.addedTask(output, TaskType.EVENT, taskList);
                break;
            default:
                UI.unknownInput();
                break;
            }
        }
    }
}
