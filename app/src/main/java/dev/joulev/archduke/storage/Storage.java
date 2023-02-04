package dev.joulev.archduke.storage;

import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;

import dev.joulev.archduke.tasks.Task;

public class Storage {
    private static final String FILE = "../.archduke/tasks.json";
    private static final Gson gson = new Gson();

    private static String readFile(String path) throws IOException {
        String content = Files.readString(Paths.get(path));
        System.out.println(content);
        return content;
    }

    public static ArrayList<Task> readSavedTasks() {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            String savedTaskJson = readFile(FILE);
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
}
