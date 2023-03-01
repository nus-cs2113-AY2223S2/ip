package duke;

import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.greet_user();
        Tasklist.findFile();

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        String[] command = input.split(" ", 2);

        while (!"bye".equals(input)) {
            switch (command[0]) {
                case "todo":
                    Tasklist.Todo(command[1]);
                    break;
                case "deadline":
                    String[] d = command[1].split("/by", 2);
                    String d_description = d[0];
                    String d_by = d[1];
                    Tasklist.deadline(d_description, d_by);
                    break;
                case "event":
                    String[] er = command[1].split("/", 3);
                    String e_description = er[0];
                    String e_start = er[1];
                    String e_end = er[2];
                    Tasklist.event(e_description, e_start, e_end);
                    break;
                case "list":
                    Tasklist.list();
                    break;
                case "mark":
                    Integer m_index = Integer.valueOf(command[1]);
                    Tasklist.markTask(m_index);
                    break;
                case "unmark":
                    Integer u_index = Integer.valueOf(command[1]);
                    Tasklist.unmarkTask(u_index);
                    break;
                case "delete":
                    Integer d_index = Integer.valueOf(command[1]);
                    Tasklist.delete(d_index);
                    break;
                case "find":
                    String keyword = command[1];
                    Tasklist.find(keyword);
                    break;
                default:
                    System.out.println("There was an error. Please try again");
                    break;
            }
            input = scan.nextLine();
            command = input.split(" ", 2);
        }

        System.out.println("Goodbye. Hope to see u again :) \n");
        scan.close();
        Tasklist.saveFile();
    }

}