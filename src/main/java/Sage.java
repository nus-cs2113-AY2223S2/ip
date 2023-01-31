import java.util.ArrayList;
import java.util.Scanner;

public class Sage {
    public static void main(String[] args) {
        Display UI = new Display();
        TaskList taskList = new TaskList();
        Scanner s = new Scanner(System.in);
        UI.WelcomeUser();
        while (s.hasNextLine()) {
            String[] output;
            String input = s.nextLine();
            if (input.equals("bye")) {
                UI.GoodByeUser();
                break;
            } else if (input.equals("list")) {
                UI.DisplayTask(taskList);
            } else if (input.contains("unmark")) {
                output = input.split(" ");
                taskList.UnmarkTask(Integer.parseInt(output[1]), UI);
            } else if (input.contains("mark")) {
                output = input.split(" ");
                taskList.MarkTask(Integer.parseInt(output[1]), UI);
            } else if (input.contains("todo")) {
                taskList.AddTask(input.substring(5));
                output = input.split("/");
                UI.AddedTask(output, TaskType.TODO, taskList);
            } else if (input.contains("deadline")) {
                output = input.split("/");
                taskList.AddTask(output[0].substring(9), output[1].substring(3));
                UI.AddedTask(output, TaskType.DEADLINE, taskList);
            } else if (input.contains("event")) {
                output = input.split("/");
                taskList.AddTask(output[0].substring(6), output[1].substring(5), output[2].substring(3));
                UI.AddedTask(output, TaskType.EVENT, taskList);
            } else {
                UI.UnknownInput();
            }
        }
    }
}
