import java.util.Scanner;

public class Inu {

    private Task[] todoList;
    public static final String greeting = " Woof Woof! I'm Inu! Your personal Shib-Assistant ^.^\n";
    public static final String prompt = " What can I do for you today?\n";
    public static final String farewell = " Bye. Hope to see you again soon ^.^!\n";
    public static final String listMessage = " Woof! Here are your current tasks ^.^:\n";
    public static final String markMessage = " Woof! I've marked this task as completed for you:\n";
    public static final String unmarkMessage = " Woof! I've marked this task as incompleted for you:\n";
    public static final String divider = "____________________________________________________________\n";

    public Inu() {

        System.out.println(divider + greeting);
        System.out.println(prompt + divider);

        setTodoList();
        readCommand();

        System.out.println(divider + farewell + divider);

    }

    public void getTodoList(int listIndex) {

        for (int i = 0; i < listIndex; ++i) {

            System.out.println((i + 1) + ". " + "[" + todoList[i].getStatusIcon() + "] " + todoList[i].getDescription());

        }

    }

    public void setTodoList() {

        this.todoList = new Task[100];

    }

    public void readCommand() {

        String line;
        String checkLine;

        int listIndex = 0;

        Scanner in = new Scanner(System.in);

        line = in.nextLine();
        checkLine = line.toLowerCase();

        while (!checkLine.equals("bye")) {

            if (checkLine.equals("list")) {

                System.out.println(divider + listMessage);
                getTodoList(listIndex);
                System.out.println("\n" + divider);

            } else if (checkLine.startsWith("mark ")) {

                /* This ensures that the mark command is read correctly only if an index follows it */

                String index = checkLine.substring(5, checkLine.length());
                int taskIndex = Integer.parseInt(index) - 1;
                todoList[taskIndex].setDone();

                System.out.println(divider + markMessage + "[" + todoList[taskIndex].getStatusIcon() + "] " + todoList[taskIndex].getDescription() + "\n" + divider);

            } else if (checkLine.startsWith("unmark ")) {

                String index = checkLine.substring(7, checkLine.length());
                int taskIndex = Integer.parseInt(index) - 1;
                todoList[taskIndex].resetDone();

                System.out.println(divider + unmarkMessage + "[" + todoList[taskIndex].getStatusIcon() + "] " + todoList[taskIndex].getDescription() + "\n" + divider);

            } else {

                Task task = new Task(line);
                todoList[listIndex] = task;
                ++listIndex;

                System.out.println(divider + "added: " + task.getDescription() + '\n' + divider);

            }

            line = in.nextLine();
            checkLine = line.toLowerCase();

        }

    }

    public static void main(String[] args) {
        new Inu();
    }
}
