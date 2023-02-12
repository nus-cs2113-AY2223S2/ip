package duke.save;

import duke.tasks.TaskList;
import duke.tasks.Task;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;

import java.io.*;

public class Storage {

    private String filepath;

    public Storage(String filepath){
        this.filepath = filepath;
    }

    public void initFile() throws IOException {
        File newFile = new File(filepath);

        if (!newFile.exists()) {
            newFile.mkdirs();
            newFile.createNewFile();
        }
    }

    public void loadFile(TaskList list) {
        try {
            FileReader reader = new FileReader(filepath);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] array = line.split("\\,", -1);
                Task newTask;
                if (array[0].equals("[T]")) {
                    ToDo newTodo = new ToDo(array[2]);
                    newTask = newTodo;
                    if (array[1].equals("X")){
                        newTodo.setDone(true);
                    }
                } else if (array[0].equals("[D]")) {
                    Deadline newDeadline = new Deadline(array[2], array[3]);
                    newTask = newDeadline;
                    if (array[1].equals("X")){
                        newDeadline.setDone(true);
                    }
                } else {
                    Event newEvent = new Event(array[2], array[3], array[4]);
                    newTask = newEvent;
                    if (array[1].equals("X")){
                        newEvent.setDone(true);
                    }
                }

                list.loadTask(Task.getIndexCount() - 1, newTask);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Oops, something went wrong!");
        } catch (Exception e){
            System.out.println("Your file is corrupted! Please delete the file");
        }
    }

    public void updateFile(TaskList taskList) throws IOException {
        FileWriter overwriteFile = new FileWriter(filepath);
        for (int i = 0; i < Task.getIndexCount(); i++) {
            Task temp = taskList.get(i);
            String type = temp.getType();
            String done = temp.getIsDone() ? "X" : " ";
            if (type.equals("[T]")) {
                ToDo newTemp = (ToDo) temp;
                overwriteFile.write(type + "," + done + "," + temp.getTaskName() + "\n");
            } else if (type.equals("[D]")){
                Deadline newTemp = (Deadline) temp;
                overwriteFile.write(type + "," + done + "," + newTemp.getTaskName() + "," + newTemp.getDeadline() + "\n");
            } else {
                Event newTemp = (Event) temp;
                overwriteFile.write(type + "," + done + "," + newTemp.getTaskName() + "," + newTemp.getStart() +
                        "," + newTemp.getEnd() + "\n");
            }
        }
        overwriteFile.close();
    }
}

