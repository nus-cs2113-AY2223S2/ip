package duke.utils;

import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import duke.deadline.Deadline;
import duke.event.Event;
import duke.item.Item;
import duke.remind.Remind;

import duke.exceptions.FileException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class FileAction {
    /**
     * Imports the file and put into the list
     * 
     * @param file File object that retrieves the file location
     */
    public static ArrayList<Item> importItems() throws FileException {
        File file = new File("items.txt");
        if (!file.exists()) {
            System.out.println(Message.WARNING_MISSING_FILE);
            return new ArrayList<Item>();
        }

        try(FileReader fileReader = new FileReader(file)) {
            RuntimeTypeAdapterFactory<Item> itemAdapterFactory = RuntimeTypeAdapterFactory.of(Item.class, "type")
                .registerSubtype(Remind.class)
                .registerSubtype(Deadline.class)
                .registerSubtype(Event.class);

            Gson gson = new GsonBuilder().registerTypeAdapterFactory(itemAdapterFactory).create();
            ArrayList<Item> items = gson.fromJson(fileReader, new TypeToken<ArrayList<Item>>(){}.getType());
            fileReader.close();

            System.out.println(Message.SUCCESS_LOADED_FILE);
            return items;
        } catch (IOException err) {
            throw new FileException(Message.ERROR_FILE_INPUT.toString());
        }
    }

    /**
     * Exports the list into a file
     * 
     */
    public static void exportItems(ArrayList<Item> items) throws FileException {
        try {
            Gson gson = new Gson();
            FileWriter fw = new FileWriter("items.txt");
            String jsonString = gson.toJson(items);
            fw.write(jsonString);
            fw.flush();
            fw.close();
            
        } catch (IOException err) {
            throw new FileException(Message.ERROR_FILE_OUTPUT.toString());
        }
    }
}
