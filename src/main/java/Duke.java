import java.io.IOException;

public class Duke {
    protected static Storage storage;
    protected static Parser parser;
    protected static Ui ui;

    public Duke() {
        storage = new Storage();
        parser = new Parser();
        ui = new Ui();
    }

    public static void main(String[] args) throws IOException {
        Ui.printWelcome();

        try {
            Storage.findData();
        } catch (IOException err) {
            System.out.println("☹ OOPS!!! Currently creating file as it does not exist");
            String path = "data";
            String file = "data/duke.txt";
            FileOperations.makeFile(file, path);
        }

        Parser.cmdToExcecute();
        // add save to file code here
        Storage.saveData();
        // exit
        Ui.printExit();
    }
}