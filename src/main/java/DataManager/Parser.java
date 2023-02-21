package DataManager;
import Exception.DukeException;
import UI.Ui;
import task.*;


public class Parser {
    public static Command parseUserInput(String input) throws Exception {
        String inst = input.split("\\s+")[0];
        Command action = new Command(input);
        if (inst.equalsIgnoreCase("list")) {
            Ui.printList();
        } else if (inst.equalsIgnoreCase("mark")) {
            markTask(input, action);
        } else if (inst.equalsIgnoreCase("unmark")) {
            unmarkTask(input, action);
        } else if (inst.equalsIgnoreCase("todo")) {
            todoTask(input, action);
        } else if (inst.equalsIgnoreCase("deadline")) {
            deadlineTask(input, action);
        } else if (inst.equalsIgnoreCase("event")) {
            eventTask(input, action);
        } else if (inst.equalsIgnoreCase("delete")) {
            deleteTask(input, action);
        } else if (inst.equalsIgnoreCase("find")) {
            findTask(input, action);
        } else if (inst.equalsIgnoreCase("bye")) {
            action.setExit(true);
        } else {
            throw new DukeException(Ui.UNRECOGNISED_INPUT);
        }
        updateFileData(action);
        return action;
    }

    private static void markTask(String input, Command action) throws Exception {
        try {
            int index = Integer.parseInt(input.split("\\s+")[1]) - 1;
            action.markTask(index);
        } catch (Exception e) {
            throw new Exception(Ui.UNRECOGNISED_ITEM_INDEX);
        }
    }

    private static void unmarkTask(String input, Command action) throws Exception {
        try {
            int index = Integer.parseInt(input.split("\\s+")[1]) - 1;
            action.unmarkTask(index);
        } catch (Exception e) {
            throw new Exception(Ui.UNRECOGNISED_ITEM_INDEX);
        }
    }

    private static void todoTask(String input, Command action) throws DukeException {
        String[] tokens = input.split("\\s+", 2);
        if(tokens.length < 2) {
            throw new DukeException(Ui.EMPTY_DESCRIPTION);
        }
        String task = tokens[1];
        action.createTodoTask(task);
    }

    private static void deadlineTask(String input, Command action) throws DukeException {
        String[] tokens = input.split("\\s+", 2);
        String[] instruction = tokens[1].split("/");
        if(tokens.length < 2) {
            throw new DukeException(Ui.EMPTY_DESCRIPTION);
        }
        if(instruction.length < 2) {
            throw new DukeException(Ui.WRONG_INPUTS_GIVEN);
        }
        String task = instruction[0];
        String deadline = instruction[1].split("\\s+", 2)[1];
        action.createDeadlineTask(task, deadline);
    }

    private static void eventTask(String input, Command action) throws DukeException {
        String[] tokens = input.split("\\s+", 2);
        String[] instruction = tokens[1].split("/");
        if(tokens.length < 2) {
            throw new DukeException(Ui.EMPTY_DESCRIPTION);
        }
        if(instruction.length < 3) {
            throw new DukeException(Ui.WRONG_INPUTS_GIVEN);
        }
        String task = instruction[0];
        String from = instruction[1];
        String to = instruction[2];
        String dateFrom = from.split("\\s+", 2)[1];
        String dateTo = to.substring(to.lastIndexOf("to") + 2);
        action.createEventTask(task, dateFrom, dateTo);
    }

    private static void deleteTask(String input, Command action) throws Exception {
        String[] tokens = input.split("\\s+", 2);
        if(tokens.length < 2) {
            throw new DukeException(Ui.WRONG_INPUTS_GIVEN);
        }
        try {
            int index = Integer.parseInt(tokens[1]) - 1;
            action.deleteTask(index);
        } catch (Exception e) {
            throw new Exception(Ui.UNRECOGNISED_ITEM_INDEX);
        }
    }

    public static void findTask(String input, Command action) throws Exception {
        String[] tokens = input.split("\\s+");
        if(tokens.length < 2) {
            throw new Exception(Ui.FIND_ITEM_EMPTY);
        }
        String keyword = tokens[1];
        action.findItems(keyword);
    }

    public static void updateFileData(Command action) throws DukeException {
        Storage.createFileWriterObject();
        for (Task t : TaskList.list) {
            String typeOfTask = t.getType();
            String status = t.getStatus();
            String task = t.getTask();
            String taskDescription;
            switch (typeOfTask) {
            case "T":
                taskDescription = String.format("%s-%s-%s", typeOfTask, status, task);
                break;
            case "D":
                Deadline deadlineTask = (Deadline) t;
                String deadline = deadlineTask.getDeadline();
                taskDescription = String.format("%s-%s-%s-%s", typeOfTask, status, task, deadline);
                break;
            case "E":
                Event eventTask = (Event) t;
                String from = eventTask.getFrom();
                String to = eventTask.getTo();
                taskDescription = String.format("%s-%s-%s-from %s-to%s", typeOfTask, status, task, from, to);
                break;
            default:
                throw new DukeException(Ui.UNRECOGNISED_TASKTYPE);
            }
            action.updateFileData(taskDescription);
        }
        Storage.closeFileWriterObject();
    }

    public static void parseExistingTodo(String existingData) {
        String[] tokens = existingData.split("-");
        String status = tokens[1];
        String task = tokens[2];
        TaskList.addTodo(task, status);
    }

    public static void parseExistingDeadline(String existingData) {
        String[] tokens = existingData.split("-");
        String status = tokens[1].trim();
        String task = tokens[2].trim();
        String deadline = tokens[3].trim();
        TaskList.addDeadline(task, deadline, status);
    }

    public static void parseExistingEvent(String existingData) {
        String[] tokens = existingData.split("-");
        String status = tokens[1];
        String task = tokens[2];
        String from;
        String to;
        if(tokens[3].contains(" ")) {
            from = tokens[3].split("\\s+", 2)[1];
        } else {
            int idxFrom = tokens[3].indexOf("from") + 4;
            from = tokens[3].substring(idxFrom);
        }
        if(tokens[4].contains(" ")) {
            to = tokens[4].split("\\s+", 2)[1];
        } else {
            int idxTo = tokens[4].indexOf("to") + 2;
            to = tokens[4].substring(idxTo);
        }
        TaskList.addEvent(task, from, to, status);
    }
}
