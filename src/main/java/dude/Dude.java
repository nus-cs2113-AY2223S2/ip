package dude;

import dude.commands.Io;
import dude.commands.SaveData;

public class Dude {

    public static void main(String[] args) {
        Io.printGreeting();
        SaveData.loadSaved();
        Io.readInput();
        SaveData.writeToFile();
        Io.printBye();
    }
}
