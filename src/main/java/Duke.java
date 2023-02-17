public class Duke {
    protected Storage storage;
    protected Parser parser;
    protected Ui ui;

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

