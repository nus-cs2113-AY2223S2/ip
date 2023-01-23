import java.util.Scanner;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke " + System.lineSeparator() + "What can I do for you?");
        ArrayList<Task> todoList = new ArrayList<Task>();
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                for (Task item: todoList) {
                    item.printTask();
                }
                line = in.nextLine();
            } else {
                System.out.println("Added: " + line);
                Task newTask = new Task(line);
                todoList.add(newTask);
                line = in.nextLine();
            }
        }
        System.out.println(System.lineSeparator() + "Bye. Hope to see you again soon!");
    }
}
