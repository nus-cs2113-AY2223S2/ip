import parser.FileParser;

public class Main {
    private static final Duke duke = new Duke();
    public static void main(Strinlg[] args) {
        FileParser parser = FileParser.getInstance();
        parser.readFromFile();
        duke.run();
    }
}
