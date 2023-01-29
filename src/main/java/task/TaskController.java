package task;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import parser.Argument;
import parser.Command;

public class TaskController implements ITaskController  {
    ArrayList<Task> list = new ArrayList<Task>();
    
    public Task addTask(Argument arg) throws EmptyDescriptionException{
        Task newTask = null;
        try {
            switch ((TaskType) arg.getCommand()) {
            case DEADLINE:
                newTask = new Deadline(arg.getVariableArguments().get("description"),
                        arg.getVariableArguments().get("endDate"));
                list.add(newTask);
                break;
            case EVENT:
                newTask = new Event(arg.getVariableArguments().get("description"),
                                        arg.getVariableArguments().get("startDate"),
                                        arg.getVariableArguments().get("endDate"));
                list.add(newTask);
                break;
            case TODO:
                newTask = new ToDo(arg.getVariableArguments().get("description"));
                list.add(newTask);
                break;
            }
        } catch (EmptyDescriptionException e) {
            throw e;
        }
        return newTask;
    }
    public Task addTask(Task newTask) {
        list.add(newTask);
        return newTask;
    }
    public ArrayList<Task> getTasks() throws EmptyTaskListException {
        if (list.isEmpty()) {
            throw new EmptyTaskListException("*** Empty List", new NoSuchElementException());
        }
        return list;
    }

    public String toString() {
        String Text = IntStream.range(0, list.size())
                                .mapToObj(index -> String.format("%d. %s\n", index+1, list.get(index)))
                                .collect(Collectors.joining("\n"));
        return Text;
    }

    @Override
    public String markTask(Argument arg) throws TaskIndexOutOfRangeException {
        if (arg.getIndex() < 0 || arg.getIndex()>list.size()) {
            // Bad index input by user
            throw new TaskIndexOutOfRangeException("Invalid index given to mark!", new IllegalArgumentException());
        }
        String dukeMessage;
        if (arg.getCommand().equals(Command.MARK)) {
            list.get(arg.getIndex()-1).setMark(true);
            dukeMessage = "Nice! I've marked this task as done:";
        }
        else {
            list.get(arg.getIndex()-1).setMark(false);
            dukeMessage = "OK, I've marked this task as not done yet:";
        }
        return String.format("%s\n %s",
                        dukeMessage,
                        list.get(arg.getIndex()-1).toString());
    }

    @Override
    public int getCount() {
        return list.size();
    }
}