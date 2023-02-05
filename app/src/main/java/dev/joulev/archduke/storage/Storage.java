package dev.joulev.archduke.storage;

import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dev.joulev.archduke.exceptions.ArchdukeException;
import dev.joulev.archduke.exceptions.OtherException;
import dev.joulev.archduke.tasks.Task;

public class Storage {
    private static final String FILE = "../.archduke/tasks.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private static String readFromFile(String path) throws IOException {
        return Files.readString(Paths.get(path));
    }

    private static void writeToFile(String path, String content) throws IOException {
        Files.createDirectories(Paths.get(path).getParent());
        Files.writeString(Paths.get(path), content, StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);
    }

    public static ArrayList<Task> readSavedTasks() {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            String savedTaskJson = readFromFile(FILE);
            SavedTask[] savedTasks = gson.fromJson(savedTaskJson, SavedTask[].class);
            for (SavedTask savedTask : savedTasks) {
                Task task = savedTask.convertToTask();
                tasks.add(task);
            }
            return tasks;
        } catch (Exception e) {
            // Whether the file is not found, is corrupted or for whatsoever reason, we
            // consider it as non-existent and return an empty list.
            return new ArrayList<>();
        }
    }

    public static void saveTasks(ArrayList<Task> tasks) throws ArchdukeException {
        SavedTask[] savedTasks = new SavedTask[tasks.size()];
        for (int i = 0; i < tasks.size(); i++) {
            savedTasks[i] = tasks.get(i).toSavedTask();
        }
        String savedTaskJson = gson.toJson(savedTasks);
        try {
            writeToFile(FILE, savedTaskJson);
        } catch (Exception e) {
            System.out.println(e);
            throw new OtherException(
                    "Failed to save task list to file. Your changes won't be saved.");
        }
    }
}
