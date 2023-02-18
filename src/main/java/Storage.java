import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String dirPath;
    private String filePath;

    public Storage(String dirPath, String filePath) {
        this.dirPath = dirPath;
        this.filePath = filePath;
    }

    public void save(TaskList taskList, Ui ui) {
        try {
            saveTasks(taskList.tasks, filePath);
        } catch (IOException e) {
            System.out.println("Error saving tasks to storage.");
        }
    }

    private void saveTasks(ArrayList<Task> tasks, String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);

        for(int i = 0; i<tasks.size(); i++) {
            fw.write(tasks.get(i).getTypeOfTask() + " | " + (tasks.get(i).isDone? "1" : "0") + " | " + tasks.get(i).getDetailsToSave() + System.lineSeparator());
        }
        fw.close();
    }

    public TaskList load() throws DukeException {
        TaskList taskList = new TaskList();
        taskList.isSilent = true;
        try {
            taskList = loadTasks(taskList, filePath);
        } catch (FileNotFoundException e) {
            taskList.isSilent = false;
            try {
                File dir = new File(dirPath);
                dir.mkdir();
                File myObj = new File(filePath);
                myObj.createNewFile();

            } catch (IOException e2) {
                System.out.println(e2.getMessage());
                throw new DukeException("");
            }

        }
        taskList.isSilent = false;
        return taskList;
    }

    private TaskList loadTasks(TaskList taskList , String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] args = line.split(" \\| ");

            switch (args[0]) {
                case "T":
                    try {
                        taskList.addTodo(args[2]);
                        if(args[1].equals("1")) {
                            taskList.markTask(taskList.tasks.size());
                        }
                    } catch (DukeException e) {
                        // will not fail
                    }
                    break;
                case "D":
                    try {
                        taskList.addDeadline(args[2]);
                        if(args[1].equals("1")) {
                            taskList.markTask(taskList.tasks.size());
                        }
                    } catch (DukeException e) {
                        // will not fail
                    }
                    break;
                case "E":
                    try {
                       taskList.addEvent(args[2]);
                        if(args[1].equals("1")) {
                            taskList.markTask(taskList.tasks.size());
                        }
                    } catch (DukeException e) {
                        // will not fail
                    }
                    break;
                default:
                    break;
            }
        }

        return taskList;
    }

//    private void addEvent(ArrayList<Task> tasks, String todoTask, int index) throws DukeException {
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
//
//    }
//
//    private void addDeadline(ArrayList<Task> tasks, String todoTask, int index) throws DukeException {
//
//        String[] taskAndDeadline = todoTask.split(" /by ");
//
//        String theTask = taskAndDeadline[0];
//        String dueBy = taskAndDeadline[1];
//        tasks.add(new Deadline(theTask, dueBy));
//
//    }
//
//    private void addTodo(ArrayList<Task> tasks, String todoTask, int index) throws DukeException {
//
//        tasks.add(new Todo(todoTask));
//
//    }
//
//    private void markTask(ArrayList<Task> tasks, int index) throws DukeException {
//
//        tasks.get(index).mark();
//
//    }
}
