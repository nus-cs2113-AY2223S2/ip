package com.ethanyidong.bunny;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * Handles loading and saving from a save file
 */
public class BunnyStorage {
    private boolean saveEnabled;
    private final Path savePath;
    private BufferedWriter saveWriter = null;

    /**
     * @param saveEnabled whether saving is enabled.
     *                    If set to <code>false</code>, other methods will do nothing.
     * @param savePath    the path to the save file
     */
    public BunnyStorage(boolean saveEnabled, Path savePath) {
        this.saveEnabled = saveEnabled;
        this.savePath = savePath;
    }

    /**
     * Loads previously entered commands from a save file
     *
     * @param bunny the session to load the save into
     */
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
        } catch (Exception e) {
            System.out.println(bunny.getUI().LOAD_ERROR_MESSAGE);
        } finally {
            bunny.setIsSuppressed(false);
        }
    }

    /**
     * Disables this class from writing to the save file.
     */
    public void disableSave() {
        this.saveEnabled = false;
    }

    /**
     * Opens the save file for writing. Must be called before calling <code>save()</code>.
     *
     * @throws IOException if there is an error opening the file at savePath for writing
     */
    public void beginSave(BunnySession bunny) {
        if (!saveEnabled) {
            return;
        }
        try {
            this.saveWriter = Files.newBufferedWriter(
                    this.savePath,
                    StandardCharsets.UTF_8,
                    new StandardOpenOption[]{StandardOpenOption.APPEND, StandardOpenOption.CREATE});
        } catch (Exception e) {
            this.saveEnabled = false;
            System.out.println(bunny.getUI().SAVE_ERROR_MESSAGE);
        }
    }

    /**
     * Saves a command to the save file
     *
     * @param saveData the command to save to the saveFile
     * @throws IOException if the save file is not opened using <code>beginSave()</code>, or if other errors occured
     *                     while writing to the save file
     */
    public void save(BunnySession bunny, String saveData) {
        if (!saveEnabled) {
            return;
        }
        try {
            this.saveWriter.write(saveData + "\n");
        } catch (Exception e) {
            this.saveEnabled = false;
            System.out.println(bunny.getUI().SAVE_ERROR_MESSAGE);
        }
    }

    /**
     * Closes the save file
     *
     * @throws IOException if errors occured while flushing the buffer to the save file
     */
    public void endSave(BunnySession bunny) {
        if (!saveEnabled) {
            return;
        }
        try {
            this.saveWriter.close();
        } catch (Exception e) {
            this.saveEnabled = false;
            System.out.println(bunny.getUI().SAVE_ERROR_MESSAGE);
        }
    }
}
