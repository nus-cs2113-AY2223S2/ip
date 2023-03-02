import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;


public class Buddy {
    public static int taskCount = 0;
    public static String divider = "________________________________________________________________________________";

    public static void main(String[] args) throws IOException {
        TaskList taskList = new TaskList();
        try {
            Storage.loadFile(taskList);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            Storage.createFile();

        }

        String greeting = "Hello there! I'm Buddy\n"
                + "How may I assist you?";
        String listOfCommands = "Here are the commands you can use: todo, deadline, event,  list, mark, unmark, bye";
        String exitMessage = "Hope I was of help to you! Have a great day and see you again, Buddy :)";


        System.out.println(divider);
        System.out.println(greeting);
        System.out.println(listOfCommands);
        System.out.println(divider);


        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        Parser processAllCommands = new Parser();

        while (! processAllCommands.isExit(input)) {
            processAllCommands.executeLine(taskList, input);


            /*int index = 1;
            if (input.equals("list")) {
                Command list = new ListCommand();
                list.executeCommand(taskList, input);

            } else if (input.startsWith("mark")) {
                Command mark = new MarkTaskCommand();
                mark.executeCommand(taskList, input);
                /*try {
                    Task currentTask = taskList.get(taskNumber - 1);
                    currentTask.setDone(true);
                    System.out.println(divider);
                    System.out.println("Great work on completing this task! Marked as done! :)");
                    System.out.println(currentTask);
                    System.out.println(divider);
                } catch (IndexOutOfBoundsException a) {
                    System.out.println("That is not a valid task to mark! Please check your list again and input a valid task");

                }

            } else if (input.startsWith("unmark")) {
                Command unmark = new UnmarkTaskCommand();
                unmark.executeCommand(taskList, input);
                /*
                try {
                    Task currentTask = taskList.get(taskNumber - 1);

                    currentTask.setDone(false);
                    System.out.println(divider);
                    System.out.println("Remember to come back to this task! Marked as undone!");
                    System.out.println(currentTask);
                    System.out.println(divider);
                } catch (IndexOutOfBoundsException a) {
                    System.out.println("That is not a valid task to unmark! Please check your list again and input a valid task");

                }
            } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event") || input.startsWith("delete") || input.startsWith("find")) {

                if (input.startsWith("todo")) {
                    Command addTodo = new AddTodoCommand();
                    addTodo.executeCommand(taskList, input);

                } else if (input.startsWith("deadline")) {
                    Command addDeadline = new AddDeadlineCommand();
                    addDeadline.executeCommand(taskList, input);

                } else if (input.startsWith("event")) {
                    Command addEvent = new AddEventCommand();
                    addEvent.executeCommand(taskList, input);

                } else if (input.startsWith("delete")) {
                    Command delete = new DeleteTaskCommand();
                    delete.executeCommand(taskList, input);

                } else if (input.startsWith("find")){
                    Command find = new FindTaskCommand();
                    find.executeCommand(taskList, input);

                }*/

            input = in.nextLine();

        }
        try {
            Storage.updateFile(taskList);

        } catch (IOException e) {
            System.out.println("Error occurred");
        }

        System.out.println(divider);
        System.out.println(exitMessage);
        System.out.println(divider);

    }
}
