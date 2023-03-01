import util.Parser;
import util.Storage;
import util.TaskList;
/**
 * Executes the whole Duke Project. Also creates an amazingly cute Pikachu
 * */


public class Duke {
    private static final String dukeGreetUser = "PIKAPIKAPI HELLOO\n"
            + "Please let Pikapi write you a lists";
    private Storage storage;
    private TaskList tasks;

    /**
     * Creates a new tasklist and loads the data from listData.txt (if any)
     */
    public Duke() {
        storage = new Storage();
        tasks = new TaskList();
        storage.loadData(tasks.accessTaskList());
    }

    /**
     * The main function for the whole Duke project
     * */
    public static void main(String[] args) {
        new Duke().run();
    }


    /**
     * Starts taking in user input and starts the Duke Application
     */
    public void run() {
        System.out.println(dukeGreetUser);
        Parser addList = new Parser();
        addList.parseData(tasks);
    }

}
