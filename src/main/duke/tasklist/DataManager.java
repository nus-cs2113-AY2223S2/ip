package duke.tasklist;

import duke.parser.Parser;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;
import duke.util.DukeException;
import duke.ui.DukeMessages;
import duke.storage.FileManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class DataManager {

    protected final ArrayList<Task> tasks = new ArrayList<>();
    protected String path;
    protected int taskCount = 0;
    protected final DukeMessages ui;
    private final Parser parser;

    public DataManager(String path, DukeMessages ui, Parser parser) {
        this.path = path;
        this.ui = ui;
        this.parser = parser;
    }

    public void rewriteFile(String path) throws DukeException {
        try {
            FileManager.writeFile(path, tasks);
        } catch (IOException e) {
            ui.printWriteFileError();
            throw new DukeException();
        }
    }

    public void listOut() {
        if (taskCount == 0) {
            ui.printEmpty();
            return;
        } else {
            ui.printList();
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
            ui.printWriteFileError();
        }
    }

    public void handleMark(String checkCmd, String next, boolean isFromCommand) throws DukeException {
        try {
            parser.convertString(next.trim(), taskCount);
            checkMarkStatus(parser.getNum(), true, checkCmd);
        } catch (DukeException e) {
            throw new DukeException();
        }
        tasks.get(parser.getNum() - 1).setDone(true);
        if (isFromCommand) {
            ui.printMark();
            System.out.println(tasks.get(parser.getNum() - 1));
            try {
                rewriteFile(path);
            } catch (DukeException e) {
                throw new DukeException();
            }
        }
    }

    public void handleUnmark (String checkCmd, String next, boolean isFromCommand) throws DukeException {
        try {
            parser.convertString(next.trim(), taskCount);
            checkMarkStatus(parser.getNum(), false, checkCmd);
        } catch (DukeException e) {
            throw new DukeException();
        }
        tasks.get(parser.getNum() - 1).setDone(false);
        if (isFromCommand) {
            ui.printUnmark();
            System.out.println(tasks.get(parser.getNum() - 1));
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
            ui.printTodo();
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
            ui.printError();
            throw new DukeException();
        }
        if (isFromCommand) {
            ui.printDeadline();
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
            ui.printError();
            throw new DukeException();
        }
        if (isFromCommand) {
            ui.printEvent();
            echo();
        }
        ++taskCount;
    }

    public void handleDelete(String next, boolean isFromCommand) throws DukeException {
        try {
            parser.convertString(next.trim(), taskCount);
        } catch (DukeException e) {
            throw new DukeException();
        }

        if (isFromCommand) {
            System.out.println("Roger!" + tasks.get(parser.getNum() - 1) + " removed!");
        }
        try {
            tasks.remove(tasks.get(parser.getNum() - 1));
            rewriteFile(path);
        } catch (DukeException e) {
            throw new DukeException();
        }
        --taskCount;
    }

    public void command(String checkCmd, String next) {
        boolean isFromCommand = true;
        switch (checkCmd) {
        case "list":
            listOut();
            break;
//            case "help":
//                String next = in.nextLine();
//                printHelp(next.trim());
//                break;
        case "mark":
            try {
                handleMark(checkCmd, next, isFromCommand);
            } catch (DukeException e) {
                break;
            }
            break;
        case "unmark":
            try {
                handleUnmark(checkCmd, next, isFromCommand);
            } catch (DukeException e) {
                break;
            }
            break;
        case "todo":
            handleTodo(next, isFromCommand);
            break;
        case "deadline":
            try {
                handleDeadline(next, isFromCommand);
            } catch (DukeException e) {
                break;
            }
            break;
        case "event":
            try {
                handleEvent(next, isFromCommand);
            } catch (DukeException e) {
                break;
            }
            break;
        case "delete":
            try {
                handleDelete(next, isFromCommand);
            } catch (DukeException e) {
                break;
            }
            break;
        case "find":
            break;
        }
    }

    public void initialize() throws DukeException{
        try {
            FileManager.readFile(path);
        } catch (FileNotFoundException e) {
            ui.printFileNotFoundError();
            FileManager.createFile(path);
            throw new DukeException();
        }
        try {
            FileManager.handleFile(this);
        } catch (DukeException e) {
            ui.printReadFileError();
            throw new DukeException();
        }
    }

    public void run() {
        ui.printDiv();
        ui.printHi();
        ui.printQuery();
        ui.printDiv();
    }
}
