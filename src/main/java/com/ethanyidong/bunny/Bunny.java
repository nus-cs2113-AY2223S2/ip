package com.ethanyidong.bunny;

import com.ethanyidong.bunny.arg.InvalidCommandException;
import com.ethanyidong.bunny.cmd.ExecutableCommand;
import com.ethanyidong.bunny.cmd.TokenizedCommand;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class Bunny {
    public static final String DISABLE_SAVE = "nosave";
    public static void main(String[] args) {
        final boolean saveEnabled = args.length < 1 || !args[0].equals(DISABLE_SAVE);
        Scanner in = new Scanner(System.in);
        BunnySession bunny = new BunnySession();

        String home = System.getProperty("user.home");
        Path saveFilePath = Paths.get(home, "bunny.aof");
        boolean saveFileExists = Files.exists(saveFilePath);

        if(saveFileExists && saveEnabled) {
            bunny.setIsSuppressed(true);
            try {
                BufferedReader reader = Files.newBufferedReader(saveFilePath, Charset.forName("UTF-8"));
                String saveFileLine = reader.readLine();
                while(saveFileLine != null) {
                    runCommandString(bunny, saveFileLine);
                    saveFileLine = reader.readLine();
                }
                reader.close();
            } catch (Exception _ex){
                System.out.println("Error reading save file! Continuing...");
            } finally {
                bunny.setIsSuppressed(false);
            }
        }

        bunny.printMessage("Hello! I'm Bunny.\nWhat can I do for you?");

        try {
            BufferedWriter saveFileWriter = null;
            if (saveEnabled) {
                saveFileWriter = Files.newBufferedWriter(
                        saveFilePath,
                        Charset.forName("UTF-8"),
                        new StandardOpenOption[]{StandardOpenOption.APPEND, StandardOpenOption.CREATE});
            }
            while (!bunny.isQuit()) {
                String input = in.nextLine();
                if (saveEnabled) {
                    saveFileWriter.write(input + "\n");
                }
                runCommandString(bunny, input);
            }
            bunny.printMessage("Bye. Hope to see you again soon!");
            if (saveEnabled) {
                saveFileWriter.close();
            }
        } catch (Exception _ex) {
            System.out.println("Error writing save file! Quitting...");
        }
    }

    private static void runCommandString(BunnySession bunny, String commandString) {
        TokenizedCommand inputCommand = new TokenizedCommand(commandString);
        ExecutableCommand executableCommand;
        try {
            executableCommand = ExecutableCommand.validateAndParse(bunny, inputCommand);
        } catch (InvalidCommandException ice) {
            bunny.printMessage(ice.toString());
            return;
        }
        executableCommand.execute(bunny);
    }
}
