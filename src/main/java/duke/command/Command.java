package duke.command;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

/**
 * Represent a command given to <code>Duke</code> assistant.
 */
public class Command {

    private String line;
    private String command;
    private String cmdContent;
    private int cmdIdx;

    /**
     * Set the command line.
     * @param line Input command line, e.g. "list", "mark 1" or "deadline Return books /by 2023/03/31 18:00".
     */
    public void setLine(String line) {
        this.line = line;
    }

    /**
     * Get the command line.
     * @return Command line, e.g. "list", "mark 1" or "deadline Return books /by 2023/03/31 18:00".
     */
    public String getLine() {
        return line;
    }

    /**
     * Set the command.
     * @param command Input command, e.g. "list", "mark", "unmark", "delete", "todo", "deadline", "event".
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * Get the command.
     * @return Command, e.g. "list", "mark", "unmark", "delete", "todo", "deadline", "event".
     */
    public String getCommand() {
        return command;
    }

    /**
     * Set the content of the command, i.e. the part beside the command part <code>command</code> in command line <code>line</code>.
     * @param cmdContent Content of the command.
     */
    public void setCmdContent(String cmdContent) {
        this.cmdContent = cmdContent;
    }

    /**
     * Get the content of the command, i.e. the part beside the command part <code>command</code> in command line <code>line</code>.
     * @return Content of the command.
     */
    public String getCmdContent() {
        return cmdContent;
    }

    /**
     * Set the index of the space behind <code>command</code>, 
     * or index of end of command line if <code>cmdContent</code> is null.
     * @param cmdIdx index of the space behind <code>command</code>,
     * or index of end of command line if <code>cmdContent</code> is null.
     */
    public void setCmdIdx(int cmdIdx) {
        this.cmdIdx = cmdIdx;
    }

    /**
     * Get the index of the space behind <code>command</code>, 
     * or index of end of command line if <code>cmdContent</code> is null.
     * @return index of the space behind <code>command</code>,
     * or index of end of command line if <code>cmdContent</code> is null.
     */
    public int getCmdIdx() {
        return cmdIdx;
    }

    /**
     * Judge whether the command represents exit(<code>command.equals("bye")</code>).
     * @return <code>true</code> if the command represents exit and <code>false</code> if it does not.
     */
    public boolean isExit() {
        return (command.equals("bye"));
    }

    /**
     * Function interface for functions that accept a variable of type <code>T</code>, do not 
     * have return value, and throw <code>DukeException</code>.
     */
    @FunctionalInterface
    public interface Consumer<T> {
        void accept(T t) throws DukeException;
    }

    /**
     * Execute the command.
     * @param todoList List of tasks that executing function may refer to and/or modify.
     * @throws DukeException If execution function throws <code>DukeException</code>.
     */
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

    /**
     * Show all tasks in <code>todoList</code>.
     * @param todoList The <code>todoList</code> storing all tasks.
     * @throws DukeException If it throws <code>DukeException</code>.
     */
    public void list(TodoList todoList) throws DukeException {
        todoList.showList();
    }

    /**
     * Mark the work as done.
     * @param todoList The <code>todoList</code> storing all tasks.
     * @throws DukeException If it throws <code>DukeException</code>.
     */
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

    /**
     * Mark the work as not done.
     * @param todoList The <code>todoList</code> storing all tasks.
     * @throws DukeException If it throws <code>DukeException</code>.
     */
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

    /**
     * Add a <code>Todo</code> task to the end of <code>todoList</code>.
     * @param todoList The <code>todoList</code> storing all tasks.
     * @throws DukeException If it throws <code>DukeException</code>.
     */
    public void todo(TodoList todoList) throws DukeException {
        Todo todo = Todo.toTodo(cmdContent);
        todoList.addItem(todo);
    }

    /**
     * Add a <code>Deadline</code> task to the end of <code>todoList</code>.
     * @param todoList The <code>todoList</code> storing all tasks.
     * @throws DukeException If it throws <code>DukeException</code>.
     */
    public void deadline(TodoList todoList) throws DukeException {
        Deadline deadline = Deadline.toDeadline(cmdContent);
        todoList.addItem(deadline);
    }

    /**
     * Add an <code>Event</code> task to the end of <code>todoList</code>.
     * @param todoList The <code>todoList</code> storing all tasks.
     * @throws DukeException If it throws <code>DukeException</code>.
     */
    public void event(TodoList todoList) throws DukeException {
        Event event = Event.toEvent(cmdContent);
        todoList.addItem(event);
    }

    /**
     * Add a task from <code>todoList</code> by the number it appears in the list.
     * @param todoList The <code>todoList</code> storing all tasks.
     * @throws DukeException If it throws <code>DukeException</code>.
     */
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

    /**
     * Find a task from <code>todoList</code>. <br>
     * 1) If "/by" is in the command, it will find all tasks ending before the time after "/by", which should be 
     * in format of "yyyy/MM/dd" or "yyyy/MM/dd HH:mm". <br>
     * 2) If "/by" is not in the command, it will find all tasks that have <code>cmdContent</code> in its description.
     * @param todoList The <code>todoList</code> storing all tasks.
     * @throws DukeException If it throws <code>DukeException</code>.
     */
    public void find(TodoList todoList) throws DukeException {
        int byIdx = line.indexOf("/by");
        if(byIdx != -1) {
            String timeBeforeStr = line.substring(byIdx + "/by ".length());
            LocalDateTime timeBefore;
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
            todoList.findDesc(cmdContent);
        }
    }

    /**
     * An unknown command. It will throw a <code>DukeException</code>.
     * @param todoList The <code>todoList</code> storing all tasks.
     * @throws DukeException <code>DukeException</code> indicating that the command is of unkown type.
     */
    public void unknown(TodoList todoList) throws DukeException {
        throw new DukeException("I'm sorry, but I don't know what that means :-(");
    }

}
