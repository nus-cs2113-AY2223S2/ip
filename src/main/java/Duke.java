import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) throws DukeException {
        String logo = "Hello! I'm Duke\n" + "What can I do for you?\n";
        System.out.println(logo);
        Scanner in = new Scanner(System.in);
        String response;

        List<Task> list = new ArrayList<>();
        while (true) {
            response = in.nextLine();
            if (response.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            try {
                if (response.equals("list")) {
                    int size = list.size();
                    if (size == 0) {
                        System.out.println("Bro.... You have nothing on your list. Add something to your list to be productive");
                        continue;
                    }
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < size; i++) {
                        System.out.println((i + 1) + "." + list.get(i).toString());
                    }
                    continue;
                } else if (response.startsWith("mark")) {
                    int tasknumber = Integer.parseInt(response.split(" ")[1]);
                    if (tasknumber > 0 && tasknumber <= list.size()) {
                        list.get(tasknumber - 1).markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(list.get(tasknumber - 1).toString());
                    } else {
                        System.out.println("I cannot find which task you have selected. Please choose another one");
                        continue;
                    }
                    continue;
                } else if (response.startsWith("unmark")) {
                    int tasknumber = Integer.parseInt(response.split(" ")[1]);
                    if (tasknumber > 0) {
                        list.get(tasknumber - 1).markAsNotDone();
                        System.out.println("Never-mind!! Unmarking");
                        System.out.println(list.get(tasknumber - 1).toString());
                    } else {
                        System.out.println("I cannot find which task you have selected. Please choose another one");
                        continue;
                    }
                    continue;
                } else if (response.startsWith("deadline")) {
                    try {
                        String splitresponse = response.split(" ", 2)[1];
                        String descresponse = splitresponse.split("/by")[0];
                        String dateresponse = splitresponse.split("/by")[1];
                        list.add(new Deadline(descresponse, dateresponse));
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException();
                    }
                } else if (response.startsWith("event")) {
                    try {
                        String splitresponse = response.split(" ", 2)[1];
                        String descresponse = splitresponse.split("/from")[0];
                        String timeresponse = splitresponse.split("/from")[1];
                        String fromresponse = timeresponse.split("/to")[0];
                        String toresponse = timeresponse.split("/to")[1];
                        list.add(new Event(descresponse, fromresponse, toresponse));
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException();
                    }
                } else if (response.startsWith("todo")) {
                    try {
                        String description = response.split(" ", 2)[1];
                        list.add(new Todo(description));
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException();
                    }
                } else {
                    throw new DukeException();
                }
                int size = list.size();
                System.out.println("Got it. I've added this task:");
                System.out.println(list.get(size - 1).toString());
                System.out.println("Now you have " + size + " tasks in the list");
            }  catch (DukeException e) {
                System.out.println("Illegal Command");
            }
        }
        }
}

