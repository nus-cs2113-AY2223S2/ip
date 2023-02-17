public class Duke {
    public Storage storage;
    public Parser parser;
    public Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();
    }
    public void run() {
        ui.greetUser();
        storage.load();
        parser.handleCommands();
        storage.saveData();
        ui.sayBye();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}

