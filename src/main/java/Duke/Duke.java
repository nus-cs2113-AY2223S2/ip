package Duke;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasklist;
    private Parser parser;
    private final int PARSERINDEX = 1;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage();
        tasklist = new TaskList(filePath);
        parser = new Parser();
    }

    public void run() {
        ui.greet();
        while (parser.getIsRunning()) {
            String commandToBeParsed = ui.readCommand();
            String commandParsed = parser.parseCommand(commandToBeParsed);
            if (commandParsed.startsWith("B")) { // bye from user, terminate program
                parser.setIsRunning(false);
            } else if (commandParsed.startsWith("L")) { // print list
                tasklist.printCurrentList();
            } else if (commandParsed.startsWith("F")) { // find tasks in list
                tasklist.printTasksFound(commandParsed.substring(PARSERINDEX));
            } else if (commandParsed.startsWith("M")) { // mark task
                tasklist.mark(commandParsed.substring(PARSERINDEX));
            } else if (commandParsed.startsWith("U")) { // unmark task
                tasklist.unmark(commandParsed.substring(PARSERINDEX));
            } else if (commandParsed.startsWith("D")) { // delete task
                tasklist.delete(commandParsed.substring(PARSERINDEX));
            } else if (commandParsed.startsWith("XIOB")) { // index out of bound error
                ui.printErrorMessage("Index out of bound error. Please try again Sire");
            } else if (commandParsed.startsWith("XNC")) { // null command error
                ui.printErrorMessage("Sire, I am not trained to understand gibberish.");
            } else { // task keyed in by users
                tasklist.addTask(commandParsed.substring(PARSERINDEX));
            }
        }
        ui.bye();
    }

    public static void main(String[] args) {
        new Duke("dukeData.txt").run();
    }

//    public static void addList(ArrayList<Task> list) {
//        String line;
//        Scanner in = new Scanner(System.in);
//        line = in.nextLine();
//        while (!line.equals("bye")) { // condition to shut down program
//            if (line.equals("list")) { // users wants to know all text so far
//                Ui.printCurrentList(list);
//            } else if (line.startsWith("mark")) {
//                TaskList.mark(line, list);
//                Storage.dukeDataStorage(Storage.arraylistToStringConverter(list));
//            } else if (line.startsWith("unmark")) {
//                TaskList.unmark(line, list);
//                Storage.dukeDataStorage(Storage.arraylistToStringConverter(list));
//            } else if (line.startsWith("delete")) {
//                TaskList.delete(line, list);
//                Storage.dukeDataStorage(Storage.arraylistToStringConverter(list));
//            } else {// new tasks keyed in by user
//                try {
//                    Task newTask = new Task(line);
//                    if (line.startsWith("todo")) {
//                        newTask = new ToDos(line);
//                        list.add(newTask);
//                    } else if (line.startsWith("deadline")) {
//                        newTask = new Deadlines(line);
//                        list.add(newTask);
//                    } else if (line.startsWith("event")) {
//                        newTask = new Events(line);
//                        list.add(newTask);
//                    } else {
//                        throw new NullCommandException();
//                    }
//                    Storage.dukeDataStorage(Storage.arraylistToStringConverter(list));
//                } catch (EmptyToDoException e) {
//                    System.out.println("Sire, you have yet to tell me what is it you want to do.");
//                } catch (EmptyDeadlineException e) {
//                    System.out.println("Sire, what is it that is due your specified time?");
//                } catch (EmptyEventsException e) {
//                    System.out.println("Sire, your event is unclear. Please specify.");
//                } catch (StringIndexOutOfBoundsException e) {
//                    System.out.println("Don't hold back Sire. I am here to serve.");
//                } catch (NullCommandException e) {
//                    System.out.println("Sire, I am not trained to understand gibberish.");
//                }
//            }
//            line = in.nextLine(); // read in next line of text
//        }
//    }
}
