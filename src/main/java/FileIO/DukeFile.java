package FileIO;

import TaskItems.Todos;

import java.io.*;
import java.util.ArrayList;

public class DukeFile {

    /**
     * writes the attributes of the Todo, Event or deadline class into the Data.txt file with each attribute separated by a semi-colon
     * writes each new object on the new line
     * @param Tasks - an ArrayList of type Todos that contains all the list of todos, events and deadlines.
     */
    public static void WriteToFile(ArrayList<Todos> Tasks) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Data.txt"));
            for(Todos Task : Tasks) {
                writer.write(Task.item+";"+Task.type+";" + Task.isMarked + "\n");
            }
            writer.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * takes each line from the Data.txt file and parses them into their relevant classes and appends to teh ArrayList
     * @param fileName - the name of the file which stores the Data
     * @return an ArrayLIst of type Todos that contains all the events, deadlines and events
     * @throws IOException
     */
    public static ArrayList<Todos> loadListFromFile(String fileName) throws IOException {
        ArrayList<Todos> todoList = new ArrayList<>();
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] container = line.split(";");
            Todos todo = new Todos(container[0], Boolean.parseBoolean(container[2]), container[1]);
            todoList.add(todo);
        }
        bufferedReader.close();
        return todoList;
    }

    /**
     * creates a new file to store the ArrayList items so that they can be reloaded when program runs again.
     * @return an arraylist of type todos that contains the events, deadlines and todos.
     * @throws IOException
     */
    public static ArrayList<Todos> CreateFile() throws IOException {
        File file = new File("Data.txt");
        ArrayList<Todos> todoItems;
        if (file.exists()) {
            todoItems = DukeFile.loadListFromFile("Data.txt");
            return todoItems;
        }
        return new ArrayList<Todos>();
    }
}
