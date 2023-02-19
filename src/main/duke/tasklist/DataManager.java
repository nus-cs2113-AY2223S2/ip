package duke.tasklist;

import duke.parser.Parser;
import duke.storage.FileManager;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import duke.util.DukeException;
import duke.ui.DukeMessages;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.LinkedHashSet;

public class DataManager {

    private final DateData dates;
    private final FindData find;
    private final Parser parser;
    protected String path;
    private final DukeMessages ui;
    private final TaskData tasks;


    public DataManager(String path, DukeMessages ui, Parser parser) {
        this.dates = new DateData();
        this.find = new FindData();
        this.parser = parser;
        this.path = path;
        this.ui = ui;
        this.tasks = new TaskData(ui, parser, path);
    }

    public void command(String parsedCommand, String next) {
        boolean isFromCommand = true;
        try {
            switch (parsedCommand) {
            case "list":
                tasks.listOut();
                break;
            case "mark":
                tasks.handleMark(parsedCommand, next, isFromCommand);
                break;
            case "unmark":
                tasks.handleUnmark(parsedCommand, next, isFromCommand);
                break;
            case "todo":
                ToDo todo = tasks.handleTodo(next, isFromCommand);
                find.addTask(todo, tasks.getTaskCount());
                break;
            case "deadline":
                Deadline deadline = tasks.handleDeadline(next, isFromCommand);
                dates.addDeadline(deadline, tasks.getTaskCount());
                find.addTask(deadline, tasks.getTaskCount());
                break;
            case "event":
                Event event = tasks.handleEvent(next, isFromCommand);
                dates.addEvent(event, tasks.getTaskCount());
                find.addTask(event, tasks.getTaskCount());
                break;
            case "delete":
                tasks.handleDelete(next, isFromCommand);
                dates.handleDelete(parser.getNum());
                find.handleDelete(parser.getNum());
                break;
            case "date":
                LocalDate date = parser.processDate(next);
                LinkedHashSet<Integer> list = dates.findDate(date);
                tasks.printFromList(list);
                break;
            case "find":
                list = find.findKeyword(next);
                tasks.printFromList(list);
                break;
            }
        } catch (DukeException e) {
            ui.printError();
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
            FileManager.handleFile(this.tasks, this.dates, this.find);
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
