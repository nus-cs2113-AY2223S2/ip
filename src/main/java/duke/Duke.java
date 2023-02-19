package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

public class Duke {
    static boolean isFileEdited = false;
    static boolean toPrint = true;
    static int taskCount = 0;

    public static void extractData(File fileName) throws FileNotFoundException {
        Scanner s = new Scanner(fileName);
        int count = 1;
        while (s.hasNext()) {
            toPrint = false;
            String currentLine = s.nextLine();
            String userCommand = currentLine.substring(4);
            handleUserCommand(userCommand);
            String markStatus = currentLine.substring(0, 4);
            if (markStatus.equals("[X] ")) {
                TaskList.doCommandMark(count);
            } else if (!markStatus.equals("[ ] ")) {
                // print task wrong format
                Ui.printIncorrectTaskFormat();
            }
            count++;
        }
        toPrint = true;
    }

    public static void doEditFile(java.nio.file.Path path) throws IOException {
        File fileName = new java.io.File(path.toUri());
        FileWriter savedFile = new FileWriter(fileName, false);
        for (int index = 0; index < taskCount; index++) {
            savedFile.write(TaskList.tasks.get(index).returnCommand());
            savedFile.write(System.getProperty("line.separator"));
        }
        savedFile.write(System.getProperty("line.separator"));
        savedFile.close();
    }

    public static void doLoadFile(java.nio.file.Path path) {
        try {
            File fileName = new java.io.File(path.toUri());
            if (fileName.createNewFile()) {
                // print file created
                Ui.printFileCreated(true);
            } else {
                Scanner s = new Scanner(fileName);
                if (!s.hasNext()) {
                    //print empty file
                    Ui.printEmptyFile();
                } else {
                    //print here are your tasks
                    System.out.println(Ui.LINE);
                    System.out.println("\tHere are your stored tasks!");

                    int index = 1;
                    while (s.hasNext()) {
                        System.out.println("\t" + index + ". " + s.nextLine());
                        index++;
                    }
                    System.out.println(Ui.LINE);
                }
                extractData(fileName);
            }
        } catch (IOException e) {
            // print file creation error
            Ui.printFileCreated(false);
        } catch (IndexOutOfBoundsException e) {
            // print task saved incorrect format
            Ui.printIncorrectTaskFormat();
        }
    }

    public static void handleUserCommand(String userCommand) {
        String[] extractFirstWord = userCommand.split(" ", 2);
        String firstWord = extractFirstWord[0];
        switch (firstWord) {
        case Ui.COMMAND_MARK:
            try {
                int taskNum = Integer.parseInt(extractFirstWord[1]);
                TaskList.doCommandMark(taskNum);
            } catch (IndexOutOfBoundsException | NumberFormatException out_mark_a) {
                Ui.printInvalidNumber("mark");
            }
            break;
        case Ui.COMMAND_UNMARK:
            try {
                int taskNum = Integer.parseInt(extractFirstWord[1]);
                TaskList.doCommandUnmark(taskNum);
            } catch (IndexOutOfBoundsException | NumberFormatException out_unmark_a) {
                Ui.printInvalidNumber("unmark");
            }
            break;
        case Ui.COMMAND_LIST:
            TaskList.doCommandList();
            break;
        case Ui.COMMAND_BYE:
            Ui.doCommandBye();
            break;
        case Ui.COMMAND_DELETE:
            try {
                int taskNum = Integer.parseInt(extractFirstWord[1]);
                TaskList.doCommandDelete(taskNum);
            } catch (IndexOutOfBoundsException | NumberFormatException out_delete_a) {
                Ui.printInvalidNumber("delete");
            }
            break;
        case Ui.COMMAND_TODO:
            try {
                String taskName = (extractFirstWord[1]);
                TaskList.doCommandTodo(taskName);
            } catch (IndexOutOfBoundsException out_todo_a) {
                Ui.printEmptyCommand("todo");
            }
            break;
        case Ui.COMMAND_DEADLINE:
            try {
                int index = extractFirstWord[1].indexOf("/by");
                String taskName = extractFirstWord[1].substring(0, index);
                String taskDeadline = extractFirstWord[1].substring(index + 4);
                TaskList.doCommandDeadline(taskName, taskDeadline);
            } catch (StringIndexOutOfBoundsException out_deadline_a) {
                Ui.printInvalidFormat("deadline");
            } catch (IndexOutOfBoundsException out_deadline_a) {
                Ui.printEmptyCommand("deadline");
            }
            break;
        case Ui.COMMAND_EVENT:
            try {
                int indexOfEventDetailsPartOne = extractFirstWord[1].indexOf("/from");
                int indexOfEventDetailsPartTwo = extractFirstWord[1].indexOf("/to");
                String eventName = extractFirstWord[1].substring(0, indexOfEventDetailsPartOne);
                String eventDetailsPartOne = extractFirstWord[1].substring(indexOfEventDetailsPartOne + 6, indexOfEventDetailsPartTwo - 1);
                String eventDetailsPartTwo = extractFirstWord[1].substring(indexOfEventDetailsPartTwo + 4);
                TaskList.doCommandEvent(eventName, eventDetailsPartOne, eventDetailsPartTwo);
            } catch (StringIndexOutOfBoundsException out_event_a) {
                Ui.printInvalidFormat("event");
            } catch (IndexOutOfBoundsException out_event_a) {
                Ui.printEmptyCommand("event");
            }
            break;
        default:
            if (toPrint) {
                System.out.println(Ui.LINE);
                System.out.println("\t☹ Error! Please input a valid command!");
                System.out.println(Ui.LINE);
            }
        }
    }

    public static void main(String[] args) {
        String home = System.getProperty("user.home");
        java.nio.file.Path path = java.nio.file.Paths.get(home, "duke.txt");
        doLoadFile(path);
        Ui.doCommandGreet();
        Scanner in = new Scanner(System.in);
        String userCommand;
        do {
            userCommand = in.nextLine();
            handleUserCommand(userCommand);
        } while (!userCommand.equals(Ui.COMMAND_BYE));
        try {
            if (isFileEdited) {
                doEditFile(path);
            }
        } catch (IOException e) {
            System.out.println("☹ Error! Failed to create file.");
        }
    }
}
