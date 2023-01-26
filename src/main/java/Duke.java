import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner io = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<Task>();
        System.out.println("Hello! I am Duke");
        System.out.println("What can I do for you?");

        String inputt = io.nextLine();
        while (!inputt.equals("bye")) {
            //if list is detected
            if (inputt.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    int index = i + 1;
                    System.out.println(index + "." + taskList.get(i).toString());
                }
            } else if (inputt.contains("mark")) {
                int index = Integer.parseInt(inputt.split(" ")[1]) - 1;
                Task updatedTask = taskList.get(index).mark();
                if (inputt.contains("unmark")) {
                    updatedTask = updatedTask.unmark();
                    System.out.println("OK, I've marked this task as not done yet:");
                } else {
                    System.out.println("Nice! I've marked this task as done:");
                }
                taskList.set(index, updatedTask);
                System.out.println(updatedTask.toString());
            } else {
                Task newTask = new Task(false, inputt);
                taskList.add(newTask);
                System.out.println("added: " + inputt);
            }
            inputt = io.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
