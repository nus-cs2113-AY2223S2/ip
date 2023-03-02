package grandduke;

import grandduke.command.Io;
import grandduke.command.Storage;

public class GrandDuke {

    /**
     * Starts GrandDuke
     * @param args starting arguments provided
     */
    public static void main(String[] args) {

        Io.printIntro();
        Storage.loadData();
        Io.getInputs();
        Storage.saveData();
        Io.printExit();

    }
}
