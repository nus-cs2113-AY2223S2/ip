import parser.FileParser;

public class Main {
    // private static final Duke duke = new Duke();
    public static void main(String[] args) {
        FileParser parser = FileParser.getInstance();
        parser.readFromFile();
        // duke.run();
    }
}
