package mom.utils;

import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import mom.exceptions.FileException;
import mom.item.Item;

public class FileAction {

    /**
     * Imports the file and put into the list.
     * 
     * @throws FileException When file is missing or cannot be opened.
     */
    public static ArrayList<Item> importItems() throws FileException {
        File file = new File(Constants.FILE_NAME.toString());
        if (!file.exists()) {
            System.out.println(Message.WARNING_MISSING_FILE);
            return new ArrayList<Item>();
        }

        try(FileReader fileReader = new FileReader(file)) {
            Gson gson = new GsonBuilder().registerTypeAdapterFactory(new ItemAdapterFactory()).create();
            ArrayList<Item> items = gson.fromJson(fileReader, new TypeToken<ArrayList<Item>>(){}.getType());
            fileReader.close();

            System.out.println(Message.SUCCESS_LOADED_FILE);
            return items;
        } catch (IOException err) {
            throw new FileException(Message.ERROR_FILE_INPUT.toString());
        }
    }

    /**
     * Exports the list into a file.
     * 
     * @param items List of items which will be exported into the file.
     * @throws FileException When file cannot be saved.
     */
    public static void exportItems(ArrayList<Item> items) throws FileException {
        try {
            Gson gson = new GsonBuilder().registerTypeAdapterFactory(new ItemAdapterFactory()).create();

            FileWriter fw = new FileWriter(Constants.FILE_NAME.toString());
            String jsonString = gson.toJson(items);
            fw.write(jsonString);
            fw.flush();
            fw.close();
            
        } catch (IOException err) {
            throw new FileException(Message.ERROR_FILE_OUTPUT.toString());
        }
    }
}
