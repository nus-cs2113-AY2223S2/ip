package duke.tasklist;

import duke.parser.Parser;
import duke.storage.FileManager;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.ui.DukeMessages;
import duke.util.DukeException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskData {
    private final ArrayList<Task> tasks;
    private final DukeMessages ui;
    private final Parser parser;
    private final String path;

    public TaskData(DukeMessages ui, Parser parser, String path) {
        this.tasks = new ArrayList<>();
        this.ui = ui;
        this.parser = parser;
        this.path = path;
    }

    public void listOut() {
        int taskCount = tasks.size();
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

    public void rewriteFile(String path) {
        try {
            FileManager.writeFile(path, tasks);
        } catch (IOException e) {
            ui.printWriteFileError();
        }
    }

    public void checkMarkStatus(int index, boolean changeTo, String type) throws DukeException{
        if (tasks.get(index - 1).getIsDone() == changeTo) {
            System.out.println("It is already " + type + "ed! *Shakes head* ");
            throw new DukeException();
        }
    }

    public void echo() {
        int taskCount = tasks.size() - 1;
        System.out.println(tasks.get(taskCount));
        try {
            FileManager.writeTask(path, tasks.get(taskCount));
        } catch (IOException e) {
            ui.printWriteFileError();
        }
    }

    public void handleMark(String checkCmd, String next, boolean isFromCommand) {
        try {
            parser.convertString(next.trim(), tasks.size());
            checkMarkStatus(parser.getNum(), true, checkCmd);
        } catch (DukeException e) {
            return;
        }
        tasks.get(parser.getNum() - 1).setDone(true);
        if (isFromCommand) {
            ui.printMark();
            System.out.println(tasks.get(parser.getNum() - 1));
            rewriteFile(path);
        }
    }

    public void handleUnmark (String checkCmd, String next, boolean isFromCommand) {
        try {
            parser.convertString(next.trim(), tasks.size());
            checkMarkStatus(parser.getNum(), false, checkCmd);
        } catch (DukeException e) {
            return;
        }
        tasks.get(parser.getNum() - 1).setDone(false);
        if (isFromCommand) {
            ui.printUnmark();
            System.out.println(tasks.get(parser.getNum() - 1));
            rewriteFile(path);
        }
    }

    public void handleTodo(String next, boolean isFromCommand) {
        tasks.add(new ToDo(next.stripLeading(), false));
        if (isFromCommand) {
            ui.printTodo();
            echo();
        }
    }

    public Deadline handleDeadline(String next, boolean isFromCommand) throws DukeException {
        String[] deadline = next.split("/by", 2);
        LocalDate localByDate;
        try {
            localByDate = parser.processDate(deadline[1].trim());
            deadline[1] = localByDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException();
        } catch (DukeException f) {
            localByDate = null;
        }
        Deadline task = new Deadline(deadline[0].trim(), false, deadline[1].trim(), localByDate);
        tasks.add(task);
        if (isFromCommand) {
            ui.printDeadline();
            echo();
        }
        return task;
    }

    public Event handleEvent(String next, boolean isFromCommand) throws DukeException {
        String[] eventName = next.split("/from", 2);
        LocalDate localByDate;
        LocalDate localFromDate;
        try {
            String[] eventTime = eventName[1].split("/to", 2);
            try {
                localByDate = parser.processDate(eventTime[0].trim());
                eventTime[0] = localByDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (DukeException e) {
                localByDate = null;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException();
            }
            try {
                localFromDate = parser.processDate(eventTime[1].trim());
                eventTime[1] = localFromDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (DukeException e) {
                localFromDate = null;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException();
            }
            Event task = new Event(eventName[0].trim(), false, eventTime[0].trim(), eventTime[1].trim()
                    , localByDate, localFromDate);
            tasks.add(task);
            if (isFromCommand) {
                ui.printEvent();
                echo();
            }
            return task;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException();
        }
    }

    public void handleDelete(String next, boolean isFromCommand) {
        try {
            parser.convertString(next.trim(), tasks.size());
        } catch (DukeException e) {
            return;
        }
        if (isFromCommand) {
            System.out.println("Roger!" + tasks.get(parser.getNum() - 1) + " removed!");
        }
        tasks.remove(tasks.get(parser.getNum() - 1));
        rewriteFile(path);
    }

}
