package com.ethanyidong.bunny;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class BunnyStorage {
    private final boolean saveEnabled;
    private final Path savePath;
    private BufferedWriter saveWriter = null;

    public BunnyStorage(boolean saveEnabled, Path savePath) {
        this.saveEnabled = saveEnabled;
        this.savePath = savePath;
    }

    public void loadSave(BunnySession bunny) {
        if (!saveEnabled) {
            return;
        }

        boolean saveFileExists = Files.exists(this.savePath);
        if (!saveFileExists) {
            return;
        }

        bunny.setIsSuppressed(true);
        try {
            BufferedReader reader = Files.newBufferedReader(this.savePath, StandardCharsets.UTF_8);
            String saveFileLine = reader.readLine();
            while (saveFileLine != null) {
                bunny.runCommandString(saveFileLine);
                saveFileLine = reader.readLine();
            }
            reader.close();
        } catch (Exception _ex) {
            System.out.println("Error reading save file! Continuing...");
        } finally {
            bunny.setIsSuppressed(false);
        }
    }

    public void beginSave() throws IOException {
        if (!saveEnabled) {
            return;
        }
        this.saveWriter = Files.newBufferedWriter(
                this.savePath,
                StandardCharsets.UTF_8,
                new StandardOpenOption[]{StandardOpenOption.APPEND, StandardOpenOption.CREATE});
    }

    public void save(String saveData) throws IOException {
        if (!saveEnabled) {
            return;
        }
        this.saveWriter.write(saveData + "\n");
    }

    public void endSave() throws IOException {
        if (!saveEnabled) {
            return;
        }
        this.saveWriter.close();
    }
}
