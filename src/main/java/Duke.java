
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static ArrayList<Task> tasksList = new ArrayList<Task>();

    private static void addTasks(Task a) {
        tasksList.add(a);
        System.out.println("Now you have " + tasksList.size() + " tasks in your list");
    }

    private static void printMarking(int i) {
        System.out.println(String.format(" [%s] [%s] %s",
                tasksList.get(i).getTypeIcon(), tasksList.get(i).getStatusIcon(), tasksList.get(i).getDescription()));
    }

    private static void addMark(String remainingWords) {
        System.out.println("You are crushing it,1 task down!");
        Task.markAsDone(tasksList.get(Integer.parseInt(remainingWords) - 1));
        int i = Integer.parseInt(remainingWords) - 1;
        printMarking(i);
    }

    private static void unMark(String remainingWords) {
        System.out.println("I have unchecked it for you");
        Task.markAsNotDone(tasksList.get(Integer.parseInt(remainingWords) - 1));
        int i = Integer.parseInt(remainingWords) - 1;
        printMarking(i);
    }
    private static void deleteTasks(String remainingWords){
        System.out.println("Noted. I've removed this task:");
        int i = Integer.parseInt(remainingWords) - 1;
        printMarking(i);
        System.out.println("Now you have "+tasksList.size()+" tasks left");
        tasksList.remove(i);


    }

    private static void printIntro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I am Duke your personal life assistant \n" + logo);
        System.out.println("What can I do for you today?");
    }

    private static void processList() {
        if (tasksList.size() == 0) {
            System.out.println("Nothing to do for now!Take a break!");
        } else {
            System.out.println("Here's your plan for a productive day!");
        }
        for (int i = 0; i < tasksList.size(); i++) {
            System.out.println(String.format("%d.[%s] [%s] %s", i + 1,
                    tasksList.get(i).getTypeIcon(), tasksList.get(i).getStatusIcon(), tasksList.get(i).getDescription()));
        }
    }

    static void validateInput(String input[]) throws DukeException.InvalidInputException,
            DukeException.IncompleteInputException {

        if ((!Objects.equals(input[0], "bye"))
                && (!Objects.equals(input[0], "list"))
                && (!Objects.equals(input[0], "todo"))
                && (!Objects.equals(input[0], "event"))
                && (!Objects.equals(input[0], "deadline"))
                && (!Objects.equals(input[0], "mark"))
                && (!Objects.equals(input[0], "unmark"))
                && (!Objects.equals(input[0], "delete"))) {
            throw new DukeException.InvalidInputException("I'm sorry, but I don't know what that means :-(");
        } else if ((input.length == 1)
                && ((!Objects.equals(input[0], "list")
                && (!Objects.equals(input[0], "bye"))))) {
            throw new DukeException.IncompleteInputException("The description of a " + input[0] + " cannot be empty!");

        }


    }


    public static void main(String[] args) {
        printIntro();
        String line;
        Scanner in = new Scanner(System.in);

        while (true) {
            line = in.nextLine();
            String arr[] = line.split(" ", 2);

            try {
                validateInput(arr);
            } catch (DukeException.InvalidInputException e1) {
                System.out.println(e1.getMessage());
                System.out.println("Please input a proper command again");
                continue;

            } catch (DukeException.IncompleteInputException e2) {
                System.out.println(e2.getMessage());
                System.out.println("Please input a proper command again");
                continue;
            }

            switch (arr[0]) {
            case "bye":
                System.out.println("Bye see you again!");
                return;
            case "list":
                processList();
                break;
            case "mark":
                addMark(arr[1]);
                break;
            case "unmark":
                unMark(arr[1]);
                break;
            case "delete" :
                deleteTasks(arr[1]);
                break;
            case "todo":
                addTasks(new Todo(arr[1]));
                break;
            case "deadline":
                String[] parts = arr[1].split("/");
                addTasks(new Deadline(parts[0], parts[1]));
                break;
            case "event":
                String[] words = arr[1].split("/");
                addTasks(new Event(words[0], words[1], words[2]));
                break;
            }
        }
    }

}
