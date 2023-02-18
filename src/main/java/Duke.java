import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
public class Duke {

    private Ui ui;
    private Storage storage;
    private Parser parser;
    private TaskList taskList;
    private final String FILE_PATH = "data/duke.txt";
    private final String DIR_PATH = "data";
    private final String BYE = "bye";
    public static void main(String[] args) {
        new Duke().run();
    }
    public Duke() {
        ui = new Ui();
        storage = new Storage(DIR_PATH, FILE_PATH);
        try {
            taskList = storage.load();
            ui.showLoadingSuccess();
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public void run() {

        ui.printLogo();

        String fullCommand = ui.readInput();

        while (!fullCommand.equals(BYE)) {
            ui.printLine();

            Command c = Parser.parseInput(fullCommand);
            c.execute(taskList, ui, storage);

            ui.printLine();
            fullCommand = ui.readInput();
        }

        storage.save(taskList, ui);

        ui.showGoodbye();
    }



//    private void saveTasks(ArrayList<Task> tasks, String filePath, int index) throws IOException{
//        FileWriter fw = new FileWriter(filePath);
//
//        for(int i = 0; i<index; i++) {
//            fw.write(tasks.get(i).getTypeOfTask() + " | " + (tasks.get(i).isDone? "1" : "0") + " | " + tasks.get(i).getDetailsToSave() + System.lineSeparator());
//        }
//        fw.close();
//    }

//    private int loadTasks(ArrayList<Task> tasks, String filePath) throws FileNotFoundException {
//        int count = 0;
//        File f = new File(filePath);
//        Scanner s = new Scanner(f); // create a Scanner using the File as the source
//        while (s.hasNext()) {
//            String line = s.nextLine();
//            String[] args = line.split(" \\| ");
//
//            switch (args[0]) {
//                case "T":
//                    try {
//                        count = addTodo(tasks, args[2], count);
//                        if(args[1].equals("1")) {
//                            markTask(tasks, Integer.toString(count), count);
//                        }
//                    } catch (DukeException e) {
//
//                    }
//                    break;
//                case "D":
//                    try {
//                        count = addDeadline(tasks, args[2], count);
//                        if(args[1].equals("1")) {
//                            markTask(tasks, Integer.toString(count), count);
//                        }
//                    } catch (DukeException e) {
//
//                    }
//                    break;
//                case "E":
//                    try {
//                        count = addEvent(tasks, args[2], count);
//                        if(args[1].equals("1")) {
//                            markTask(tasks, Integer.toString(count), count);
//                        }
//                    } catch (DukeException e) {
//
//                    }
//                    break;
//                default:
//                    break;
//            }
//        }
//
//        return count;
//    }

//    private int deleteTask(ArrayList<Task> tasks, String input, int index) throws DukeException {
//        int val = Integer.parseInt(input);
//        val--;
//
//        if(val >= index) {
//            throw new DukeException("Task " + (val + 1) + " does not exist.");
//        }
//
//        System.out.println("     Noted. I've removed this task:");
//        System.out.println(tasks.get(val));
//        System.out.println("     Now you have " + (index - 1) + (index - 1 == 1 ? " task" : " tasks") + " in the list.");
//
//
//        tasks.remove(val);
//        index--;
//
//        return index;
//    }

//    private int addEvent(ArrayList<Task> tasks, String todoTask, int index) throws DukeException {
//
//        String[] taskAndDeadline = todoTask.split(" /from ");
//
//        if(taskAndDeadline.length < 2) {
//            throw new DukeException("The start date/time cannot be empty.");
//        }
//
//        String theTask = taskAndDeadline[0];
//        String dueBy = taskAndDeadline[1];
//        String[] startAndEnd = dueBy.split(" /to ");
//
//        if(startAndEnd.length < 2) {
//            throw new DukeException("The end date/time cannot be empty.");
//        }
//
//        String start = startAndEnd[0];
//        String end = startAndEnd[1];
//        tasks.add(new Event(theTask, start, end));
//        index = index + 1;
//        System.out.println("     Got it. I've added this task:");
//        System.out.println(tasks.get(index - 1));
//        System.out.println("     Now you have "
//                            + index + (index > 1 ? " tasks " : " task ")
//                            + "in the list.");
//
//        return index;
//    }

//    private int addDeadline(ArrayList<Task> tasks, String todoTask, int index) throws DukeException {
//
//        String[] taskAndDeadline = todoTask.split(" /by ");
//
//        if(taskAndDeadline.length < 2) {
//            throw new DukeException("The due date/time cannot be empty.");
//        }
//
//        String theTask = taskAndDeadline[0];
//        String dueBy = taskAndDeadline[1];
//        tasks.add(new Deadline(theTask, dueBy));
//        index = index + 1;
//        System.out.println("     Got it. I've added this task:");
//        System.out.println(tasks.get(index -1));
//        System.out.println("     Now you have "
//                            + index + (index > 1 ? " tasks " : " task ")
//                            + "in the list.");
//        return index;
//    }

//    private int addTodo(ArrayList<Task> tasks, String todoTask, int index) throws DukeException {
//
//        tasks.add(new Todo(todoTask));
//        index = index + 1;
//        System.out.println("     Got it. I've added this task:");
//        System.out.println(tasks.get(index - 1));
//        System.out.println("     Now you have "
//                            + index + (index > 1 ? " tasks " : " task ")
//                            + "in the list.");
//        return index;
//    }

//    private void unmarkTask(ArrayList<Task> tasks, String taskNumber, int index) throws DukeException {
//
//        int ind = Integer.parseInt(taskNumber) - 1;
//
//        if(ind >= index || ind < 0) {
//            throw new DukeException("Task " + (ind + 1) + " does not exist.");
//        }
//
//        tasks.get(ind).unmark();
//        System.out.println("     OK, I've marked this task as not done yet:\n");
//        System.out.println("       "
//                            + "[" + tasks.get(ind).getStatusIcon() + "] "
//                            + tasks.get(ind).getDescription());
//    }

//    private void markTask(ArrayList<Task> tasks, String taskNumber, int index) throws DukeException {
//
//        int ind = Integer.parseInt(taskNumber) - 1;
//
//        if(ind >= index || ind < 0) {
//            throw new DukeException("Task " + (ind + 1) + " does not exist.");
//        }
//
//        tasks.get(ind).mark();
//        System.out.println("     Nice! I've marked this task as done:\n");
//        System.out.println("       "
//                            + "[" + tasks.get(ind).getStatusIcon() + "] "
//                            + tasks.get(ind).getDescription());
//    }

//    private void listTask(ArrayList<Task> tasks, int index) {
//        System.out.println("     Here are the tasks in your list:\n");
//        for (int i = 0; i < index; i = i + 1) {
//            int num = i + 1;
//            System.out.println("     " + num
//                                + ".[" + tasks.get(i).getTypeOfTask()
//                                + "][" + tasks.get(i).getStatusIcon()
//                                + "] " + tasks.get(i).getDescription());
//        }
//    }

}
