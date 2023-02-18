package dev.joulev.archduke.storage;

import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dev.joulev.archduke.exceptions.ArchdukeException;
import dev.joulev.archduke.exceptions.OtherException;
import dev.joulev.archduke.io.Output;
import dev.joulev.archduke.tasks.Task;

/**
 * This class provides static methods to handle the storage of tasks in the hard
 * disk.
 */
public class Storage {
    /** The path to the JSON file storing the task list. */
    private static final String FILE = ".archduke/tasks.json";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Helper method to read the content of a file as string.
     */
    private static String readFromFile(String path) throws IOException {
        return Files.readString(Paths.get(path));
    }

    /**
     * Helper method to write a string to a file. If the file does not exist, it
     * will be created. If the file exists, it will be overwritten.
     */
    private static void writeToFile(String path, String content) throws IOException {
        Files.createDirectories(Paths.get(path).getParent());
        Files.writeString(Paths.get(path), content, StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);
    }

    /**
     * Reads the saved tasks from the storage file. If reading fails for any reason,
     * an empty list will be returned.
     * 
     * @return The list of saved tasks, already converted to {@link Task} objects.
     */
    public static ArrayList<Task> readSavedTasks() throws ArchdukeException {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            String savedTaskJson = readFromFile(FILE);
            SavedTask[] savedTasks = GSON.fromJson(savedTaskJson, SavedTask[].class);
            for (SavedTask savedTask : savedTasks) {
                Task task = savedTask.convertToTask();
                tasks.add(task);
            }
            return tasks;
        } catch (NoSuchFileException e) {
            // If the file is not found, we consider it as non-existent and return an empty
            // list.
            return new ArrayList<>();
        } catch (Exception e) {
            // If the file is corrupted or for whatsoever reason, we consider it as
            // non-existent and return an empty list. We also need to print an error
            // message.
            Output.printError(
                    "Stored file (%s) is corrupted. If you continue, it will be wiped. If you want to keep the data, please modify that file manually using an editor of your choice, or rename it to something else.",
                    FILE);
            return new ArrayList<>();
        }
    }

    /**
     * Saves the task list to the storage file.
     * 
     * @param tasks The {@link Task} list to be saved.
     * @throws ArchdukeException If saving fails for any reason.
     */
    public static void saveTasks(ArrayList<Task> tasks) throws ArchdukeException {
        SavedTask[] savedTasks = new SavedTask[tasks.size()];
        for (int i = 0; i < tasks.size(); i++) {
            savedTasks[i] = tasks.get(i).toSavedTask();
        }
        String savedTaskJson = GSON.toJson(savedTasks);
        try {
            writeToFile(FILE, savedTaskJson);
        } catch (Exception e) {
            throw new OtherException(
                    "Failed to save task list to file. Your changes won't be saved.");
        }
    }
}
