import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner input = dukeStart();
        ArrayList<Task> list = dukeReadFromFile();
        dukeAddList(input, list);
    }
    public static ArrayList<Task> dukeReadFromFile() {
        //Frome https://www.sghill.net/2014/how-do-i-make-cross-platform-file-paths-in-java/
        String home = System.getProperty("user.dir");
//        System.out.println(home);
        java.nio.file.Path path = java.nio.file.Paths.get(home, "src", "main", "savefile");
//        System.out.println(path);

        return new ArrayList<>();
    }
    // Printing the startup code
    public static Scanner dukeStart() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner input = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        return input;
    }

    // The bulk of the logic (should change name soon)
    public static void dukeAddList(Scanner inputScanner, ArrayList<Task> list) {
        while (true) {
            String nextLine = inputScanner.nextLine();
            if (nextLine.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                dukeCommandList(list);
            } else if (nextLine.split(" ", 0)[0].equals("mark")) {
                dukeCommandMark(nextLine, list);
            } else if (nextLine.split(" ", 0)[0].equals("deadline")) {
                dukeCommandDeadline(nextLine, list);
            } else if (nextLine.split(" ", 0)[0].equals("todo")) {
                dukeCommandToDo(nextLine, list);
            } else if (nextLine.split(" ", 0)[0].equals("delete")) {
                dukeCommandDelete(nextLine, list);
            } else if (nextLine.split(" ", 0)[0].equals("event")) {
                dukeCommandEvent(nextLine, list);
            } else if (nextLine.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                dukeSaveList(list);
                return;
            } else {
                continue;
            }
        }
    }
    public static void dukeSaveList(ArrayList<Task> list) {
        String home = System.getProperty("user.dir");
        java.nio.file.Path path = java.nio.file.Paths.get(home, "src", "main", "savefile");
        boolean directoryExists = java.nio.file.Files.exists(path);
        if (!directoryExists) {
        // https://docs.oracle.com/javase/7/docs/api/java/nio/file/Files.html
            try {
               Files.createFile(path);
            } catch (IOException e) {
                //complete
            }
        }
        for (Task task: list) {
            String taskType = String.valueOf(task.getClass());
            switch (taskType) {
                case ("Task"):
                    break;
                case ("Deadline"):
                    break;
                case ("Event"):
                    break;
                default:
                    break;
            }

            Files.write(path, )
        }
    }


    public static void dukeCommandDelete(String nextLine, List<Task> list) {
        String[] inputArray = nextLine.split(" ", 0);
        if (inputArray.length != 2) {
            return;
        }
        String indexString = inputArray[1];
        int index;
        try {
            index = Integer.parseInt(indexString);
        } catch (NumberFormatException e) {
            //throw some error here
            return;
        }
        if (list.size() < index - 1) {
            //throw some error here
            return;
        }

        list.remove(index);
        System.out.println("Removed task at " + index);
    }

    public static void dukeCommandList (List<Task> list) {
        for (int i = 0; i < list.size(); i++ ) {
            Task task = list.get(i);
            if (task instanceof Event) {
                printEvent((Event) task, i);
            } else if (task instanceof Deadline) {
                printDeadline((Deadline) task, i);
            } else {
                printTodo(task, i);
            }
        }
    }

    // Helper functions for dukeCommandList
    private static void printEvent(Event event, int index) {
        if (event.isDone()) {
            System.out.println(index + ". [E][X] " + event.getTaskName() +
                    " (from: " + event.getStart() + " to: " + event.getDeadline() + ")");
        } else {
            System.out.println(index + ". [E][ ] " + event.getTaskName() +
                    " (from: " + event.getStart() + " to: " + event.getDeadline() + ")");
        }
    }
    private static void printDeadline(Deadline deadline, int index) {
        if (deadline.isDone()) {
            System.out.println(index + ". [D][X] " + deadline.getTaskName() +
                    " (by: " + deadline.getDeadline() + ")");
        } else {
            System.out.println(index + ". [D][ ] " + deadline.getTaskName() +
                    " (by: " + deadline.getDeadline() + ")");
        }
    }

    private static void printTodo(Task task, int index) {
        if (task.isDone()) {
            System.out.println(index + ". [T][X] " + task.getTaskName());
        } else {
            System.out.println(index + ". [T][ ] " + task.getTaskName());
        }
    }

    public static void dukeCommandMark(String nextLine, List<Task> list) {
        String[] inputArray = nextLine.split(" ", 0);
        if (inputArray.length != 2) {
            return;
        }
        int index;
        try {
            index = Integer.parseInt(inputArray[1]);
        } catch (NumberFormatException e) {
            return;
        }
        if (index > list.size() - 1) {
            return;
        }
        Task task = list.get(index);
        if (!task.isDone()) {
            task.doTask();
        }
    }

    public static void dukeCommandEvent(String nextLine, List<Task> list) {
        String lineWithoutCommand;
        try {
            lineWithoutCommand = nextLine.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            return;
        }
        String[] regexOutput = lineWithoutCommand.split("/", 0);
        if (regexOutput.length != 3) {
            return;
        }
        String eventName = regexOutput[0];
        String startDate = regexOutput[1];
        String endDate = regexOutput[2];
        Deadline deadline = new Event(eventName, list.size(), startDate, endDate);
        list.add(deadline);
    }
    public static void dukeCommandDeadline(String nextLine, List<Task> list) {
//        System.out.println(nextLine);
        String lineWithoutCommand;
        try {
            lineWithoutCommand = nextLine.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            return;
        }
        String[] regexOutput = lineWithoutCommand.split("/", 0);
        if (regexOutput.length != 2) {
            return;
        }
        String deadlineName = regexOutput[0];
        String deadlineDate = regexOutput[1];
        Deadline deadline = new Deadline(deadlineName, list.size(), deadlineDate);
        list.add(deadline);
    }
    public static void dukeCommandToDo(String nextLine, List<Task> list) {
        String lineWithoutCommand;
        try {
            lineWithoutCommand = nextLine.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            return;
        }
        Task task = new Task(lineWithoutCommand, list.size());
        list.add(task);

    }





    //Code not needed for Level-1
    public static void dukeEcho(Scanner inputScanner) {
        while (true) {
            String echo = inputScanner.nextLine();
            if (echo.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            }
            System.out.println(echo);
        }
    }
}
