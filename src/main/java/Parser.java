import java.io.IOException;

public class Parser {
    Ui ui;
    Storage storage;
    TaskList taskList;

    public Parser(Ui ui, Storage storage, TaskList taskList) {
        this.ui = ui;
        this.storage = storage;
        this.taskList = taskList;
    }

    public static void run(TaskList tasks, String line) {
        while (!line.equals("bye")) {
            if (line.length() == 0) {
                line = Ui.requestUserInput();
                continue;
            }
            Parser.prepareInput(tasks, line);
            line = Ui.requestUserInput();
        }
        Ui.showFarewellMessage();
    }


    public static void prepareInput(TaskList tasks, String line) {
        String[] wordList = line.split(" ");
        String command = wordList[0];
        try {
            Parser.processInput(line, tasks, wordList, command);
        } catch (DukeException | IOException exception) {
            System.out.println(exception);
        }
    }

    public static void addTask(TaskList tasks, Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list\n", tasks.size());
    }

    private static void doMark(TaskList tasks, String[] wordList) throws DukeException {
        if (wordList.length != 2) {
            throw new DukeException("☹ OOPS!!! Wrong number of arguments for mark");
        }
        try {
            int index = Integer.parseInt(wordList[1]) - 1;
            tasks.get(index).setDone(true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(index));

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    private static void doUnmark(TaskList tasks, String[] wordList) throws DukeException {
        if (wordList.length != 2) {
            throw new DukeException("☹ OOPS!!! Wrong number of arguments for unmark");
        }
        try {
            int index = Integer.parseInt(wordList[1]) - 1;
            tasks.get(index).setDone(false);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tasks.get(index));
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    private static void doEvent(String line, TaskList tasks) throws DukeException {
        if (line.length() <= 6 || line.substring(6).isBlank()) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        try {
            line = line.substring(6);
            String description = line.split(" /from ")[0];
            String eventTime = line.split(" /from ")[1];
            String startTime = eventTime.split(" /to ")[0];
            String endTime = eventTime.split(" /to ")[1];
            Event eventTask = new Event(description, startTime, endTime);
            addTask(tasks, eventTask);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    private static void doTodo(String line, TaskList tasks) throws DukeException {
        if (line.length() <= 5 || line.substring(5).isBlank()) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        String description = line.substring(5);
        Todo todoTask = new Todo(description);
        addTask(tasks, todoTask);
    }

    private static void doDeadline(String line, TaskList tasks) throws DukeException {
        if (line.length() <= 9 || line.substring(9).isBlank()) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        try {
            line = line.substring(9);
            String description = line.split(" /by ")[0];
            String by = line.split(" /by ")[1];
            Deadline deadlineTask = new Deadline(description, by);
            addTask(tasks, deadlineTask);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    private static void printList(TaskList tasks, String[] wordList) throws DukeException {
        if (wordList.length > 1) {
            throw new DukeException("☹ OOPS!!! Too much arguments for list");
        }
        if (tasks.size() > 0) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i += 1) {
                System.out.printf("%d. %s\n", i + 1, tasks.get(i));
            }
        } else {
            System.out.println("There is no tasks");
        }
    }

    private static void doDelete(TaskList tasks, String[] wordList) throws DukeException {
        if (wordList.length != 2) {
            throw new DukeException("☹ OOPS!!! Wrong number of arguments for delete");
        }
        try {
            int index = Integer.parseInt(wordList[1]) - 1;
            if (index + 1 > tasks.size()) {
                throw new DukeException("☹ OOPS!!! index out of bounds");
            } else {
                System.out.println("Noted. I've removed this task:");
                System.out.println(tasks.get(index));
                tasks.remove(index);
                System.out.printf("Now you have %d tasks in the list\n", tasks.size());
            }

        } catch (Exception exception) {
            System.out.println(exception);
        }
        return;
    }

    public static void processInput(String line, TaskList tasks, String[] wordList, String command) throws
            DukeException, IOException {
        switch (command) {
        case "list":
            printList(tasks, wordList);
            break;
        case "unmark":
            doUnmark(tasks, wordList);
            Storage.save(tasks);
            break;
        case "mark":
            doMark(tasks, wordList);
            Storage.save(tasks);
            break;
        case "deadline":
            doDeadline(line, tasks);
            Storage.save(tasks);
            break;
        case "todo":
            doTodo(line, tasks);
            Storage.save(tasks);
            break;
        case "event":
            doEvent(line, tasks);
            Storage.save(tasks);
            break;
        case "delete":
            doDelete(tasks, wordList);
            Storage.save(tasks);
            break;
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
