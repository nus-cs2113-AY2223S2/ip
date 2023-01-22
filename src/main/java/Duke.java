import java.util.Scanner;

public class Duke {
    private static final Task[] tasks = new Task[100];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        int counter = 0;

        while (true) {
            String line = in.nextLine();

            if (line.equals("list")) {
                for (int i = 0; i < counter; ++i) {
                    boolean isDone = tasks[i].isDone();
                    String symbol = isDone ? "X" : " ";
                    System.out.printf("%d:[%s] %s\n", i + 1, symbol, tasks[i].getTaskName());
                }
            } else {
                if (line.contains("mark")) {
                    String[] array = line.split(" ");
                    int index = Integer.parseInt(array[1]) - 1;
                    boolean toMark = (array[0].equals("mark")) ? true : false;
                    String remark = toMark ? "Nice! I've marked this task as done:" : "OK, I've marked this task as not done yet:";
                    tasks[index].setDone(toMark);
                    boolean isDone = tasks[index].isDone();
                    String symbol = isDone ? "X" : " ";
                    System.out.printf("%s\n[%s] %s\n", remark, symbol, tasks[index].getTaskName());
                    continue;
                }

                Task newTask = new Task(line);
                tasks[counter] = newTask;
                counter += 1;
                if (line.equals("bye")) {
                    System.out.println(line);
                    break;
                }
                System.out.println("added: " + line);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
        in.close();
    }
}
