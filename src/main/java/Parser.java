import java.util.Scanner;

public class Parser {

    public static void cmdToExcecute() {

        Scanner in = new Scanner(System.in);

        String cmd;
        cmd = in.nextLine();

        while (!(cmd.equals("bye"))) {
            if (cmd.equals("list")) {
                TaskList.listTask();
            } else {
                String[] list = cmd.split(" ", 2);

                if (list[0].equals("mark")) {
                    TaskList.markTask(list);
                } else if (list[0].equals("unmark")) {
                    TaskList.unmarkTask(list);
                } else if (list[0].equals("todo")) {
                    TaskList.makeToDo(list);
                } else if (list[0].equals("event")) {
                    TaskList.makeEvent(list);
                } else if (list[0].equals("deadline")) {
                    TaskList.makeDeadline(list);
                } else if (list[0].equals("delete")) {
                    TaskList.deleteTask(list);
                } else if (list[0].equals("find")) {
                    TaskList.findTask(list);
                } else {
                    Ui.printDash();
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    Ui.printDash();
                }
            }
            cmd = in.nextLine();
        }

    }
}


