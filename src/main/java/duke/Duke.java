package duke;

import command.Command;
import parser.Parser;
import storage.TaskStorage;
import ui.UI;
import task.TaskList;
import exception.DukeException;
import exception.InvalidIndexException;



public class Duke {
    private final TaskStorage storage;
    private final TaskList tasks;
    private final UI ui;
    private final Parser parser;



    public Duke (String filePath) {
        TaskList tempTasks;
        ui = new UI();
        parser = new Parser();
        storage = new TaskStorage(filePath);
        try {
            tempTasks = new TaskList(storage.load());
        } catch (DukeException ex) {
            ui.printError(ex);
            tempTasks= new TaskList();
        }
        tasks = tempTasks;
    }

    public void run() {
        ui.printGreeting();
        while(parser.isExecuting()) {
            String fullCommand = ui.getCommand();
            try {
                Command cmd = parser.parse(fullCommand);
                cmd.execute(tasks, storage, ui);
            } catch (DukeException e) {
                ui.printError(e);
            } catch (NumberFormatException e) {
                ui.printError(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke ("data/duke.txt").run();
    }
}

    //private static final String LINE = "___________________________________________";
//    private static final String EXIT_STRING = "bye";
//    private static final String LIST_STRING = "list";
//    private static final String MARK_STRING = "mark";
//    private static final String UNMARK_STRING = "unmark";
//    private static final String DELETE_STRING = "delete";
//    private static final String TODO_STRING = "todo";
//    private static final String DEADLINE_STRING = "deadline";
//    private static final String EVENT_STRING = "event";
//    private static boolean isExecuting;
//    public static void printGreeting() {
//        System.out.println("\t" + LINE);
//        System.out.println("\t Hello I'm duke.Duke, your personal chatbot.");
//        System.out.println("\t Is there anything I can do for you");
//        System.out.println("\t" + LINE);
//    }
//
//    public static void printList() {
//        System.out.println("\t" + LINE);
//        System.out.println("\t Here are the tasks for your list: ");
//        int i =1;
//        for (Task t: taskList) {
//            System.out.println("\t " + i++ + "." + t.toString());
//        }
//        System.out.println("\t" + LINE);
//    }
//
//    public static void printTask(Task task) {
//        System.out.println("\t" + LINE);
//        System.out.println("\t Got it. I've added this task:");
//        System.out.println("\t  " + task);
//        System.out.println("\t Now you have " + taskList.size() + " tasks in your list.");
//        System.out.println("\t" + LINE);
//    }
//
//    public static void printBye() {
//        System.out.println("\t" + LINE);
//        System.out.println("\t Bye! Do let me know if you need any further assistance");
//        System.out.println("\t" + LINE);
//    }
//
//    public static void markTaskAndPrint(int taskIndex) throws DukeException {
//        if (taskIndex < 0 || taskList.get(taskIndex) == null) {
//            throw new DukeException("Please ensure that you enter the correct task number");
//        }
//        //only gets executed if no exception is thrown
//        taskList.get(taskIndex).isDone = true;
//        System.out.println("\t" + LINE);
//        System.out.println("\t Nice, I have marked this task as done: ");
//        System.out.println("\t [" + taskList.get(taskIndex).getStatusIcon() + "] " + taskList.get(taskIndex).description);
//        System.out.println("\t" + LINE);
//    }
//
//    public static void unmarkTaskAndPrint(int taskIndex) throws DukeException {
//        if (taskIndex < 0 || taskList.get(taskIndex) == null) {
//            throw new DukeException("Please ensure that you enter the correct task number");
//        }
//        taskList.get(taskIndex).isDone = false;
//        System.out.println("\t" + LINE);
//        System.out.println("\t Ouch, I have unmarked this task: ");
//        System.out.println("\t [" + taskList.get(taskIndex).getStatusIcon() + "] " + taskList.get(taskIndex).description);
//        System.out.println("\t" + LINE);
//    }
//
//    public static void deleteTaskAndPrint(int indexToDelete) throws DukeException {
//        if (indexToDelete < 0 || taskList.get(indexToDelete) == null) {
//            throw new DukeException("Please ensure that you enter the correct task number to remove");
//        }z
//        System.out.println("\t" + LINE);
//        System.out.println("\t Noted. I've removed this task: ");
//        System.out.println("\t " + taskList.get(indexToDelete));
//        System.out.println("\t Now you have " + Integer.toString(taskList.size() -1 )+ " tasks in your list");
//        System.out.println("\t" + LINE);
//        taskList.remove(indexToDelete);
//    }
//
//    public static void handleAddTask(String taskType, String commandInfo) {
//        Task newTask = null;
//        if (taskType.equals(TODO_STRING)) {
//            newTask = new Task(commandInfo);
//        } else if (taskType.equals(DEADLINE_STRING)) {
//            String[] infoArr = commandInfo.split("/by");
//            //infoArr contains descStr and deadlineStr respectively
//            newTask = new Deadline(infoArr[0].trim(), infoArr[1].trim());
//        } else if (taskType.equals(EVENT_STRING)) {
//            String[] infoArr = commandInfo.split("/from|/to");
//            //infoArr contains descStr, fromStr, and toStr respectively
//            newTask = new Event(infoArr[0].trim(), infoArr[1].trim(), infoArr[2].trim());
//        }
//        taskList.add(newTask) ;
//        printTask(newTask);
//    }
//
//    public static void saveTask() throws java.io.IOException {
//        File f = new File("data/duke.txt");
//        FileWriter fw = new FileWriter("data/duke.txt");
//        if (!f.exists()) {
//            System.out.println("File does not exist, creating a new file ./duke/data.txt");
//            f.createNewFile();
//        }
//
//        for (Task t: taskList) {
//            fw.write(t.toString() + System.lineSeparator());
//        }
//
//        fw.close();
//    }
//
//    public static void processLocalInput (String input) throws duke.DukeException {
//        String taskType = "", commandInfo = "";
//        switch (input.charAt(1)) {
//            case 'T' :
//                taskType = TODO_STRING;
//                commandInfo = input.substring(7);
//                break;
//            case 'D' :
//                taskType = DEADLINE_STRING;
//                commandInfo = input.substring(7).replace("(by:","/by").replace(")","");
//                break;
//            case 'E' :
//                taskType = EVENT_STRING;
//                commandInfo = input.substring(7).replace("(from:","/from").replace("to:","/to").replace(")","");
//                break;
//        }
//        handleAddTask(taskType,commandInfo);
//        if (input.charAt(4) == 'X') {
//            markTaskAndPrint(taskList.size()-1);
//        }
//    }
//
//    public static void loadTask()  {
//        File f = new File("data/duke.txt");
//        if (f.exists()) {
//            try {
//                Scanner s = new Scanner(f);
//                while (s.hasNext()) {
//                    String inputStr = s.nextLine();
//                    try {
//                        processLocalInput(inputStr);
//                    } catch (duke.DukeException e) {
//                        System.out.println("Exception : " +e);
//                    }
//                }
//            } catch (FileNotFoundException e) {
//                System.out.println("Exception : Failed to read existing file " + e);
//            }
//        }
//    }
//
//    public static void handleInput(String inputLine) throws DukeException, java.io.IOException {
//        //splits input based on one or more whitespaces into two words
//        String[] inputWords = inputLine.split("\\s+", 2);
//        String command = inputWords[0];
//
//        if (command.equals(EXIT_STRING)) {
//            printBye();
//            isExecuting = false;
//        }
//        else if (command.equals(LIST_STRING)) {
//            printList();
//        }
//        else if (command.equals(MARK_STRING)) {
//            //inputWords[1] is string that no longer contains the command string
//            if (inputWords.length < 2) {
//                throw new DukeException("Please specify which task you wish to mark");
//            } else {
//                int indexToMark = Integer.parseInt(inputWords[1]) - 1; //turn it into 0-based
//                markTaskAndPrint(indexToMark);
//                saveTask();
//            }
//        }
//        else if (command.equals(UNMARK_STRING)) {
//            if (inputWords.length < 2) {
//                throw new DukeException("Please specify which task you wish to unmark");
//            } else {
//                int indexToUnmark = Integer.parseInt(inputWords[1]) - 1;
//                unmarkTaskAndPrint(indexToUnmark);
//                saveTask();
//            }
//        }
//        else if(command.equals(DELETE_STRING)) {
//            if(inputWords.length < 2) {
//                throw new DukeException("Please specify which task you wish to delete");
//            } else {
//                int indexToDelete = Integer.parseInt(inputWords[1])-1;
//                deleteTaskAndPrint(indexToDelete);
//                saveTask();
//            }
//        }
//        //check if command string matches either of the string
//        else if (command.matches("todo|deadline|event")) {
//            if (inputWords.length < 2) {
//                throw new DukeException("Please specify the description to the task that you wish to add");
//            }
//            handleAddTask(command, inputWords[1]);
//            saveTask();
//        } else {
//            throw new DukeException("fsgfsuygu I don't know what that means :(");
//        }
//    }
