import java.util.ArrayList;
import java.util.Scanner;

public class Sage {
    public static void main(String[] args) {
        String logo =
                "─────────────────────────────────────────────────────────────\n" +
                        "─██████████████─██████████████─██████████████─██████████████─\n" +
                        "─██░░░░░░░░░░██─██░░░░░░░░░░██─██░░░░░░░░░░██─██░░░░░░░░░░██─\n" +
                        "─██░░██████████─██░░██████░░██─██░░██████████─██░░██████████─\n" +
                        "─██░░██─────────██░░██──██░░██─██░░██─────────██░░██─────────\n" +
                        "─██░░██████████─██░░██████░░██─██░░██─────────██░░██████████─\n" +
                        "─██░░░░░░░░░░██─██░░░░░░░░░░██─██░░██──██████─██░░░░░░░░░░██─\n" +
                        "─██████████░░██─██░░██████░░██─██░░██──██░░██─██░░██████████─\n" +
                        "─────────██░░██─██░░██──██░░██─██░░██──██░░██─██░░██─────────\n" +
                        "─██████████░░██─██░░██──██░░██─██░░██████░░██─██░░██████████─\n" +
                        "─██░░░░░░░░░░██─██░░██──██░░██─██░░░░░░░░░░██─██░░░░░░░░░░██─\n" +
                        "─██████████████─██████──██████─██████████████─██████████████─\n" +
                        "─────────────────────────────────────────────────────────────";
        String welcome = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(welcome);
        TaskList taskList = new TaskList();
        Scanner s = new Scanner(System.in);
        while (s.hasNextLine()) {
            String[] output;
            String echo = s.nextLine();  // Read user input

            if (echo.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (echo.equals("list")) {
                System.out.println("*****************************");
                taskList.ListTask();
                System.out.println("*****************************");
            } else if (echo.contains("unmark")) {
                output = echo.split(" ");
                if (!taskList.MarkTask(Integer.parseInt(output[1]), false)) {
                    System.out.println("Uh-oh! Task Not Found!");
                }
            } else if (echo.contains("mark")) {
                output = echo.split(" ");
                if (!taskList.MarkTask(Integer.parseInt(output[1]), true)) {
                    System.out.println("Uh-oh! Task Not Found!");
                }
            } else if (echo.contains("todo")) {
                taskList.AddTask(echo.substring(5));
                System.out.println("Got it. I've added this task:");
                System.out.println("[T][ ] " + echo.substring(5));
                System.out.println("Now you have " + taskList.GetTaskCount() + " tasks in the list");
            } else if (echo.contains("deadline")) {
                output = echo.split("/");
                taskList.AddTask(output[0].substring(9), output[1].substring(3));
                System.out.println("Got it. I've added this task:");
                System.out.println("[D][ ] " + output[0].substring(9) + "(by: " + output[1].substring(3) + ")");
                System.out.println("Now you have " + taskList.GetTaskCount() + " tasks in the list");
            } else if (echo.contains("event")) {
                output = echo.split("/");
                taskList.AddTask(output[0].substring(6), output[1].substring(5), output[2].substring(3));
                System.out.println("Got it. I've added this task:");
                System.out.println("[E][ ] " + output[0].substring(6) + "(from: " + output[1].substring(5) + " to: " + output[2].substring(3) + ")");
                System.out.println("Now you have " + taskList.GetTaskCount() + " tasks in the list");
            } else {
                System.out.println("I don't understand what you mean, please try again!");
            }
        }
    }
}
