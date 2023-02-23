package FileIO;

import TaskItems.Todos;

import java.io.*;
import java.util.ArrayList;

public class DukeFile {
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
    private static Todos parseTodoString(String todoString) {
        String[] fields = todoString.split(";");
        String type = fields[0];
        String description = fields[1];
        boolean isMarked = Boolean.parseBoolean(fields[2]);
        Todos todo = new Todos(description, isMarked, type);
        return todo;
    }
}
