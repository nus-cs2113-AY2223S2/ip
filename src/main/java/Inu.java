import java.util.Scanner;

public class Inu {

    private String[] todoList;
    public static final String greeting = " Woof Woof! I'm Inu! Your personal Shib-Assistant ^.^\n";
    public static final String prompt = " What can I do for you today?\n";
    public static final String farewell = " Bye. Hope to see you again soon!\n";
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

            System.out.println((i + 1) + ". " + todoList[i]);

        }

    }

    public void setTodoList() {

        this.todoList = new String[100];

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

                System.out.println(divider);
                getTodoList(listIndex);
                System.out.println(divider);

            } else {

                todoList[listIndex] = line;
                ++listIndex;

                System.out.println(divider + "added: " + line + '\n' + divider);

            }

            line = in.nextLine();
            checkLine = line.toLowerCase();

        }

    }

    public static void main(String[] args) {
        new Inu();
    }
}
