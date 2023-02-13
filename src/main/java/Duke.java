import duke.util.DataManager;

public class Duke {
    //    static private final HashMap<String, String> helpOutputs = new HashMap<>();

//    public static void generateHelp() {
//        helpOutputs.put("list", "");
//        helpOutputs.put("todo", "");
//        helpOutputs.put("deadline", "");
//        helpOutputs.put("event", "");
//        helpOutputs.put("mark", "");
//        helpOutputs.put("unmark", "");
//        helpOutputs.put("help", "");
//    }

//    public static void printHelp(String str) {
//        for (String i:helpOutputs.keySet()) {
//            System.out.println(i + ": " + helpOutputs.get(i));
//        }
//    }

    public static void main(String[] args) {
//        generateHelp();
        String path = "data\\tasks.txt";
        DataManager dm = new DataManager(path);
        dm.run();
    }
}