package duke.command;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

// import java.util.function.Consumer;

import duke.DukeException;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

public class Command {

    private String line;
    private String command;
    private String cmdContent;
    private int cmdIdx;

    public void setLine(String line) {
        this.line = line;
    }

    public String getLine() {
        return line;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public void setCmdContent(String cmdContent) {
        this.cmdContent = cmdContent;
    }

    public String getCmdContent() {
        return cmdContent;
    }

    public void setCmdIdx(int cmdIdx) {
        this.cmdIdx = cmdIdx;
    }

    public int getCmdIdx() {
        return cmdIdx;
    }

    public boolean isExit() {
        return (command.equals("bye"));
    }

    @FunctionalInterface
    public interface Consumer<T> {
        void accept(T t) throws DukeException;
    }

    public void execute(TodoList todoList) throws DukeException {
        Consumer<TodoList> commandFunction = 
            line.equals("list")        ? this::list :
            command.equals("mark")     ? this::mark : 
            command.equals("unmark")   ? this::unmark :
            command.equals("todo")     ? this::todo :
            command.equals("deadline") ? this::deadline :
            command.equals("event")    ? this::event :
            command.equals("delete")   ? this::delete :
            command.equals("find")     ? this::find :
                                         this::unknown ;
        commandFunction.accept(todoList);
    }

    public void list(TodoList todoList) throws DukeException {
        todoList.showList();
    }

    public void mark(TodoList todoList) throws DukeException {
        // mark work as done
        int index;
        try {                        
            index = Integer.parseInt(line.substring(cmdIdx + 1, line.length())) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Wrong number format: " + line.substring(cmdIdx+1, line.length()));
        }
        todoList.markItem(index, true);
    }

    public void unmark(TodoList todoList) throws DukeException {
        // mark work as unfinished
        int index;
        try {                        
            index = Integer.parseInt(cmdContent) - 1;
        } catch(NumberFormatException e) {
            throw new DukeException("Wrong number format: " + cmdContent);
        }
        todoList.markItem(index, false);
    }

    public void todo(TodoList todoList) throws DukeException {
        Todo todo = Todo.toTodo(cmdContent);
        todoList.addItem(todo);
    }

    public void deadline(TodoList todoList) throws DukeException {
        Deadline deadline = Deadline.toDeadline(cmdContent);
        todoList.addItem(deadline);
    }

    public void event(TodoList todoList) throws DukeException {
        Event event = Event.toEvent(cmdContent);
        todoList.addItem(event);
    }

    public void delete(TodoList todoList) throws DukeException {
        try {                        
            int index = Integer.parseInt(cmdContent);
            if(index > 0) {
                todoList.deleteItem(--index);
            } else {
                throw new DukeException("Wrong number format: " + cmdContent);
            }
        } catch(NumberFormatException e) {
            throw new DukeException("Wrong number format: " + cmdContent);
        }
    }

    public void find(TodoList todoList) throws DukeException {
        int byIdx = line.indexOf("/by");
        if(byIdx != -1) {
            String timeBeforeStr = line.substring(byIdx + "/by ".length());
            LocalDateTime timeBefore;
            // dateBefore = LocalDateTime.parse(dateBeforeStr, Task.parseFormatter);
            try {
                timeBefore = LocalDateTime.parse(timeBeforeStr, Task.parseFormatter);
            } catch(DateTimeParseException e1) {
                try {
                    LocalDate dateBefore = LocalDate.parse(timeBeforeStr, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                    LocalTime endOfDay = LocalTime.parse("23:59", DateTimeFormatter.ofPattern("HH:mm"));
                    timeBefore = LocalDateTime.of(dateBefore, endOfDay);
                } catch(DateTimeParseException e2) {
                    throw new DukeException("Wrong time format!");
                }
            }
            todoList.findEndTimeBefore(timeBefore);
        } else {
            todoList.findDesc();
        }
    }

    public void unknown(TodoList todoList) throws DukeException {
        throw new DukeException("I'm sorry, but I don't know what that means :-(");
    }

}
