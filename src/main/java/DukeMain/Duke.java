package DukeMain;

import java.util.Scanner;

public class Duke {
    public static String parseEventString(String input) {
        int startIndex = input.indexOf("from") + 5;
        int endStartIndex = input.indexOf("to");
        int DeadlineIndex = input.indexOf("/to") + 4;
        String start = input.substring(startIndex, endStartIndex - 1).trim();
        String end = input.substring(DeadlineIndex).trim();
        input = input.substring(input.indexOf(" ") + 1, input.indexOf('/') - 1);
        input += ("  (" + "from: " + start + " to: " + end + ")");
        return input;
    }


    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("what can i do for you");

        String input = myObj.nextLine();
        Todos[] todo = new Todos[100];
        int new_tasks = 0;
        while (!input.equalsIgnoreCase("bye")) {
            if (!ErrorHandler.isInputValid(input)) {
                input = myObj.nextLine();
                continue;
            }
            if (input.equalsIgnoreCase("list")) {
                System.out.println("-------TODO-LIST------");
                System.out.println("----------------------");
                for (int i = 0; i < todo.length && todo[i] != null; i++) {
                    char x = ' ';
                    if (todo[i].isMarked) {
                        x = 'X';
                    }
                    System.out.println(i + 1 + ". " + "[" + todo[i].type + "]" + "[" + x + "] " + todo[i].item);
                }
                System.out.println("----------------------");
            } else if (input.startsWith("mark")) {
                int i = Integer.parseInt(input.substring(5));
                todo[i - 1].setMark();
                System.out.println("Item has been marked");
                assert todo[i - 1] != null;
                System.out.println(i + ". " + "[" + todo[i = 1].type + "]" + "[X] " + todo[i - 1].item);
            } else if (input.startsWith("unmark")) {
                int i = Integer.parseInt(input.substring(7));
                todo[i - 1].unMark();
                System.out.println("Item has been unmarked");
                System.out.println(i + ". " + "[" + todo[i - 1].type + "]" + "[ ] " + todo[i - 1].item);
            } else if (input.startsWith("event")) {
                int count = new_tasks + 1;
                input = parseEventString(input);
                System.out.println("Got it, ive done the Following");
                System.out.println("Added: " + input);
                System.out.println("now you have: " + count + " tasks in this list.");
                todo[new_tasks] = new Event(input, false, "E");
                new_tasks++;
            } else if (input.startsWith("deadline")) {
                int count = new_tasks + 1;
                String date = input.substring(input.indexOf("/by") + 4);
                input = input.substring(input.indexOf(" ") + 1, input.indexOf('/') - 1) + " (by: " + date + ")";
                System.out.println("Got it, ive done the Following");
                System.out.println("Added: " + input);
                System.out.println("now you have: " + count + " tasks in this list.");
                todo[new_tasks] = new Deadline(input, false, "D");
                new_tasks++;
            } else if (input.startsWith("todo")) {
                todo[new_tasks] = new Todos(input.substring(input.indexOf((" "))), false, "T");
                System.out.println("Got it, Ive done the Following!");
                System.out.println("Added: " + "[" + todo[new_tasks].type + "]" + "[ ]" + input.substring(input.indexOf((" "))));
                int count = new_tasks + 1;
                System.out.println("now you have: " + count + " tasks in this list.");
                new_tasks++;
            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            input = myObj.nextLine();

        }
        System.out.println("Bye! see you soon!");

    }
}
