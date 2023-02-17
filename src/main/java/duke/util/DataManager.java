package duke.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;

public class DataManager {
    protected final ArrayList<Task> tasks = new ArrayList<>();
    protected String path;
    protected int taskCount = 0;

    public void setPath(String path) {
        this.path = path;
    }

    public DataManager(String path) {
        setPath(path);
    }

    public void rewriteFile(String path) throws DukeException {
        try {
            FileManager.writeFile(path, tasks);
        } catch (IOException e) {
            DukeMessages.printWriteFileError();
            throw new DukeException();
        }
    }

    public void listOut() {
        if (taskCount == 0) {
            DukeMessages.printEmpty();
            return;
        } else {
            DukeMessages.printList();
        }
        for (int i = 0; i < taskCount; ++i) {
            System.out.println(i + 1 + "." + tasks.get(i));
        }
        if (taskCount == 1) {
            System.out.println("Looks like you have " + taskCount + " thing on your list!");
        } else {
            System.out.println("Looks like you have " + taskCount + " things on your list!");
        }
    }

    public int convertString(String str) throws DukeException {
        int num;
        try {
            num = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            DukeMessages.printNotNumber();
            throw new DukeException();
        }
        if (num < 1 || num > taskCount) {
            DukeMessages.printNotInList();
            throw new DukeException();
        }
        return num;
    }

    public void checkMarkStatus(int index, boolean changeTo, String type) throws DukeException{
        if (tasks.get(index - 1).getIsDone() == changeTo) {
            System.out.println("It is already " + type + "ed! *Shakes head* ");
            throw new DukeException();
        }
    }

    public void echo() {
        System.out.println(tasks.get(taskCount));
        try {
            FileManager.writeTask(path, tasks.get(taskCount));
        } catch (IOException e) {
            DukeMessages.printWriteFileError();
        }
    }

    public void handleMark(String checkCmd, String next, boolean isFromCommand) throws DukeException {
        int num;
        try {
            num = convertString(next.trim());
            checkMarkStatus(num, true, checkCmd);
        } catch (DukeException e) {
            throw new DukeException();
        }
        tasks.get(num - 1).setDone(true);
        if (isFromCommand) {
            DukeMessages.printMark();
            System.out.println(tasks.get(num - 1));
            try {
                rewriteFile(path);
            } catch (DukeException e) {
                throw new DukeException();
            }
        }
    }

    public void handleUnmark (String checkCmd, String next, boolean isFromCommand) throws DukeException {
        int num;
        try {
            num = convertString(next.trim());
            checkMarkStatus(num, false, checkCmd);
        } catch (DukeException e) {
            throw new DukeException();
        }
        tasks.get(num - 1).setDone(false);
        if (isFromCommand) {
            DukeMessages.printUnmark();
            System.out.println(tasks.get(num - 1));
            try {
                rewriteFile(path);
            } catch (DukeException e) {
                throw new DukeException();
            }
        }
    }

    public void handleTodo(String next, boolean isFromCommand) {
        tasks.add(new ToDo(next.stripLeading(), false));
        if (isFromCommand) {
            DukeMessages.printTodo();
            echo();
        }
        ++taskCount;
    }

    public void handleDeadline(String next, boolean isFromCommand) throws DukeException {
        try {
            String[] deadline = next.split("/by", 2);
            tasks.add(new Deadline(deadline[0].trim(), false,
                    deadline[1].trim()));
        } catch (ArrayIndexOutOfBoundsException e) {
            DukeMessages.printError();
            throw new DukeException();
        }
        if (isFromCommand) {
            DukeMessages.printDeadline();
            echo();
        }
        ++taskCount;
    }

    public void handleEvent(String next, boolean isFromCommand) throws DukeException {
        try {
            String[] eventName = next.split("/from", 2);
            String[] eventTime = eventName[1].split("/to", 2);
            tasks.add(new Event(eventName[0].trim(), false,
                    eventTime[0].trim(), eventTime[1].trim()));
        } catch (ArrayIndexOutOfBoundsException e) {
            DukeMessages.printError();
            throw new DukeException();
        }
        if (isFromCommand) {
            DukeMessages.printEvent();
            echo();
        }
        ++taskCount;
    }

    public void handleDelete(String next, boolean isFromCommand) throws DukeException {
        int num;
        try {
            num = convertString(next.trim());
        } catch (DukeException e) {
            throw new DukeException();
        }
        if (isFromCommand) {
            System.out.println("Roger!" + tasks.get(num - 1) + " removed!");
            try {
                rewriteFile(path);
            } catch (DukeException e) {
                throw new DukeException();
            }
        }
        tasks.remove(tasks.get(num - 1));
        --taskCount;
    }

    public void command() {
        Scanner in = new Scanner(System.in);
        boolean isFromCommand = true;
        do {
            DukeMessages.printPrompt();
            String cmd = in.next();
            String checkCmd = cmd.toLowerCase();
            System.out.print("\n");
            if (checkCmd.equals("bye")) {
                return;
            }
            DukeMessages.printDiv();
            switch (checkCmd) {
            case "list":
                listOut();
                break;
//            case "help":
//                String next = in.nextLine();
//                printHelp(next.trim());
//                break;
            case "mark":
                String next = in.nextLine();
                try {
                    handleMark(checkCmd, next, isFromCommand);
                } catch (DukeException e) {
                    break;
                }
                break;
            case "unmark":
                next = in.nextLine();
                try {
                    handleUnmark(checkCmd, next, isFromCommand);
                } catch (DukeException e) {
                    break;
                }
                break;
            case "todo":
                next = in.nextLine();
                handleTodo(next, isFromCommand);
                break;
            case "deadline":
                next = in.nextLine();
                try {
                    handleDeadline(next, isFromCommand);
                } catch (DukeException e) {
                    break;
                }
                break;
            case "event":
                next = in.nextLine();
                try {
                    handleEvent(next, isFromCommand);
                } catch (DukeException e) {
                    break;
                }
                break;
            case "delete":
                next = in.nextLine();
                try {
                    handleDelete(next, isFromCommand);
                } catch (DukeException e) {
                    break;
                }
                break;
            default:
                DukeMessages.printError();
                in.nextLine();
            }
            DukeMessages.printDiv();
        } while (true);
    }

    public void run() {
        try {
            FileManager.readFile(path);
        } catch (FileNotFoundException e) {
            DukeMessages.printFileNotFoundError();
            FileManager.createFile(path);
        }
        try {
            FileManager.handleFile(this);
        } catch (DukeException e) {
            DukeMessages.printReadFileError();
        }
        DukeMessages.printDiv();
        DukeMessages.printHi();
        DukeMessages.printQuery();
        DukeMessages.printDiv();
        command();
        DukeMessages.printBye();
    }
}
