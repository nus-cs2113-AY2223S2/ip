package duke.save;

import duke.tasks.*;

import java.io.*;

public class FileOperation {

    private static File saveFile;

    public static void initFile() throws IOException {
        File newFile = new File("./taskList.csv");

        if (!saveFile.exists()){
            saveFile.createNewFile();
        }

        saveFile = newFile;
    }

    public static void loadFile(){
        try {
            FileReader reader = new FileReader("./tasklist.csv");
            BufferedReader bufferedReader = new BufferedReader(reader);
            TaskList list = new TaskList();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] array = line.split("\\,", -1);
                Task newTask;
                if (array[0].equals("T")){
                    ToDo newTodo = new ToDo(array[1]);
                    newTask = newTodo;
                } else if (array[0].equals("D")){
                    Deadline newDeadline = new Deadline(array[1], array[2]);
                    newTask = newDeadline;
                } else {
                    Event newEvent = new Event(array[1], array[2], array[3]);
                    newTask = newEvent;
                }

                list.addTask(Task.getIndexCount(), newTask);
            }
            reader.close();
        } catch (IOException e){
            System.out.println("Oops, something went wrong!");
        }
    }

    public static void updateFile(TaskList taskList) throws IOException {
        FileWriter overwriteFile = new FileWriter("./taskList.csv");
        for (int i = 0; i < Task.getIndexCount(); i++){
            Task temp = taskList.getTask(i);
        }
    }
}
