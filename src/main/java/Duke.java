import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static final int MAX_TASKS = 101;
    private static Database database = null;

    public static void main(String[] args) {
        greetingMessage();
        try {
            database = new Database();
            readAndRespond(database);
        } catch (DukeException e) {
            System.out.println("--FATAL ERROR--");
        } catch (IOException e) {
            System.out.println("Unable to create/find database!");
        }
        exitMessage();
    }

    private static void readAndRespond(Database database) throws DukeException {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            String[] words = input.split(" ");
            if (input.equals("list")) {
                printList(database.listOfTasks);
            } else if (input.isBlank()) {
                throw new DukeException();
            } else if (isValidUnmark(input, words)) {
                unmarkTask(database.listOfTasks, words);
            } else if (isValidMark(input, words)) {
                markTask(database.listOfTasks, words);
            } else if (isValidTodo(words)) {
                printAddedTodoMessage(input, database.listOfTasks);
                try {
                    database.appendToFile(input.substring(5) +  " --- T |[ ]| " + "\n");
                    System.out.println("Successfully appended ToDo!");
                } catch (IOException e) {
                    System.out.println("Unable to add ToDo!!");
                }
            } else if (isValidDeadline(words)) {
                printAddedDeadlineMessage(input, database.listOfTasks);
                try {
                    database.appendToFile(input.substring(9) +  " --- D |[ ]| " + "\n");
                    System.out.println("Successfully appended deadline!");
                } catch (IOException e) {
                    System.out.println("Unable to add deadline!!");
                }
            } else if (isValidEvent(words)) {
                printAddedEventMessage(input, database.listOfTasks);
                try {
                    database.appendToFile(input.substring(6) +  " --- E |[ ]| " + "\n");
                    System.out.println("Successfully appended event!");
                } catch (IOException e) {
                    System.out.println("Unable to add event!!");
                }
            } else if (IsValidDelete(input, words)) {
                removeTask(database.listOfTasks, words);
            } else {
                printAddedTaskMessage(input, database.listOfTasks);
            }
            input = sc.nextLine();
        }
    }

    private static void removeTask(ArrayList<Task> listOfTasks, String[] words) {
        int number = Integer.parseInt(words[1]);
        if (number <= 0 || number >= MAX_TASKS || words.length == 1 || !isInt(words[1]) || listOfTasks.size() < number) {
            System.out.println("Please delete only valid tasks! List out your tasks if you are unsure!!");
        } else {
            listOfTasks.get(number - 1).deleteTask();
            listOfTasks.remove(number - 1);
            --Task.taskCount;
            String addedString = "";
            for (Task curr : listOfTasks) {
                String type = null;
                if (curr instanceof ToDo) {
                    type = "T";
                } else if (curr instanceof Event) {
                    type = "E";
                } else if (curr instanceof Deadline) {
                    type = "D";
                }
                String currStr = curr.description + " --- " + type + " |" + curr.getStatusIcon() + "|\n";
                addedString += currStr;
            }
            try {
                System.out.println("Strings to be added: \n" + addedString);
                Database.writeToFile(addedString);
            } catch (IOException e) {
                System.out.println("Unable to delete properly!");;
            }
        }
    }
    private static boolean IsValidDelete(String input, String[] words) {
        return input.startsWith("delete") && words.length == 2 && isInt(words[1]);
    }

    private static boolean isValidMark(String input, String[] words) {
        return input.contains("mark") && words.length == 2 && isInt(words[1]);
    }

    private static boolean isValidUnmark(String input, String[] words) {
        return input.contains("unmark") && words.length == 2 && isInt(words[1]);
    }

    private static boolean isValidEvent(String[] words) {
        return words[0].equals("event") && words.length != 1;
    }

    private static boolean isValidDeadline(String[] words) {
        return words[0].equals("deadline") && words.length != 1;
    }

    private static boolean isValidTodo(String[] words) {
        return words[0].equals("todo") && words.length != 1;
    }

    private static void greetingMessage() {
        String logo = "Hello! I'm Duke\n" + "What can I do for you?\n";
        System.out.println(logo);
    }

    private static void exitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void printAddedTaskMessage(String input, ArrayList<Task> listOfTasks) {
        Task newTask = new Task(input);
        listOfTasks.add(newTask);
        System.out.println(newTask.addedMessage());
    }

    private static void printAddedEventMessage(String input, ArrayList<Task> listOfTasks) {
        int idxOfSlash = input.indexOf('/');
        Event newEvent = new Event(input.substring(6, idxOfSlash),
                input.substring(idxOfSlash + 6, input.indexOf('/', idxOfSlash + 1)),
                input.substring(input.indexOf('/', idxOfSlash + 1) + 4));
        listOfTasks.add(newEvent);
        System.out.println(newEvent.addedMessage());
    }

    private static void printAddedDeadlineMessage(String input, ArrayList<Task> listOfTasks) {
        int idxOfSlash = input.indexOf('/');
        Deadline newDeadline = new Deadline(input.substring(9, idxOfSlash), input.substring(idxOfSlash + 4));
        listOfTasks.add(newDeadline);
        System.out.println(newDeadline.addedMessage());
    }

    private static void printAddedTodoMessage(String input, ArrayList<Task> listOfTasks) {
        ToDo newTodo = new ToDo(input.substring(5));
        listOfTasks.add(newTodo);
        System.out.println(newTodo.addedMessage());
    }

    /**
     * Determines if the string is an integer
     *
     * @param str Str is the string we are checking
     * @return True if str is an integer, false otherwise
     * @catch NumberFormatException If str cannot be converted into integer
     */
    private static boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    private static void markTask(ArrayList<Task> listOfTasks, String[] words) {
        int number = Integer.parseInt(words[1]);
        if (number <= 0 || number >= MAX_TASKS || listOfTasks.size() < number) {
            System.out.println("Please mark only valid tasks! List out your tasks if you are unsure!!");
        } else {
            listOfTasks.get(number - 1).markAsDone();
            System.out.println("  " + listOfTasks.get(number - 1).getStatusIcon() + " " + listOfTasks.get(number - 1).description);
            String addedString = "";
            for (Task curr : listOfTasks) {
                String type = null;
                if (curr instanceof ToDo) {
                    type = "T";
                } else if (curr instanceof Event) {
                    type = "E";
                } else if (curr instanceof Deadline) {
                    type = "D";
                }
                String currStr = curr.description + " --- " + type + " |" + curr.getStatusIcon() + "|\n";
                addedString += currStr;
            }
            try {
                Database.writeToFile(addedString);
            } catch (IOException e) {
                System.out.println("Unable to delete properly!");;
            }
        }
    }

    private static void unmarkTask(ArrayList<Task> listOfTasks, String[] words) throws DukeException {
        int number = Integer.parseInt(words[1]);
        if (number <= 0 || number >= MAX_TASKS  || words.length == 1 || !isInt(words[1]) || listOfTasks.size() < number) {
            System.out.println("Please unmark only valid tasks! List out your tasks if you are unsure!!");
        } else {
            listOfTasks.get(number - 1).unmarkDone();
            System.out.println(" " + listOfTasks.get(number - 1).getStatusIcon() + " " + listOfTasks.get(number - 1).description);
            String addedString = "";
            for (Task curr : listOfTasks) {
                String type = null;
                if (curr instanceof ToDo) {
                    type = "T";
                } else if (curr instanceof Event) {
                    type = "E";
                } else if (curr instanceof Deadline) {
                    type = "D";
                }
                String currStr = curr.description + " --- " + type + " |" + curr.getStatusIcon() + "|\n";
                addedString += currStr;
            }
            try {
                Database.writeToFile(addedString);
            } catch (IOException e) {
                System.out.println("Unable to delete properly!");;
            }
        }
    }

    public static void printList(ArrayList<Task> listOfTasks) {
        System.out.println(" Here are the tasks in your list");
        if (listOfTasks.size() == 0) {
            System.out.println(" Oops! It looks like your list is empty!!");
            return;
        }
        int index = 0;
        for (Task x : listOfTasks) {
            String type = null;
            if (x instanceof ToDo) {
                type = "T";
            } else if (x instanceof Event) {
                type = "E";
            } else if (x instanceof Deadline) {
                type = "D";
            }
            // first prints are correct with status message
            // but after stopping and re-running then the problem comes in
            System.out.println(" " + (index + 1) + "." + x.statusMessage());
            ++index;
        }
    }

}
