package grandduke;

import grandduke.command.Io;

public class GrandDuke {

    /**
     * Starts GrandDuke
     * 
     * @param args
     *            starting arguments provided
     */
    public static void main(String[] args) {

        Io.printIntro();
        Io.getInputs();
        Io.printExit();

    }
}
