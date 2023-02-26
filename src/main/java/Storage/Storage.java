package Storage;

import Tasks.Task;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {

    /**
     * This method is designed to write the contents of the task list to the file
     * at the end of the program before rolexSaysBye() is executed.
     *
     * @param task1: the ArrayList containing the task list
     * @throws IOException: throw exception if there are issues in writing to file
     */
    public static void fileWrite(ArrayList<Task> task1) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("Rolex.txt"));
        for (Task x : task1) {
            writer.write(x.giveTaskName() + ";" + x.giveTaskStatus() + ";" + x.giveTaskSign() + "\n");
        }
        writer.close();
    }


    /**
     * This method is designed to access of the saved file on the local system
     * before taking in user input for adding new tasks.
     *
     * @param fileName: name of the file stored in local system.
     *
     * @return taskList: the ArrayList containing the tasks.
     *
     * @throws IOException: throw exception if there are issues in accessing the file
     */
    public static ArrayList<Task> fileAccess(File fileName) throws IOException {
        ArrayList<Task> taskList = new ArrayList<>();
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String input;
        while((input = bufferedReader.readLine())!=null){
            String[] line = input.split(";");
            Task r = new Task(line[0], Boolean.parseBoolean(line[1]), line[2]);
            taskList.add(r);
        }
        bufferedReader.close();
        return taskList;
    }


    /**
     * This method is designed to create a new file to save the contents of
     * tasks in the local system.
     *
     * @return an ArrayList
     *
     * @throws IOException: throw exception if there are issues in creating the file
     */
    public static ArrayList<Task> createFile() throws IOException {
        File fileName = new File("rolex.txt");
        if(fileName.exists()){
            return fileAccess(fileName);
        }
        return new ArrayList<>();
    }

}
