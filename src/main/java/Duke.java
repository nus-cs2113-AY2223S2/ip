public class Duke {

    private static String fileName = "duke.txt";
    private Storage storage;
    private UI ui;
    public TaskList tasks;

    public Duke() {
        ui = new UI();
        ui.greet();
        storage = new Storage(fileName);
        tasks = new TaskList();
        tasks.Tasklist(storage.load());
    }

    // public void addTask(String taskName){
    // Task t = new Task(taskName);
    // taskList.add(t);
    // System.out.printf(
    // "Got it. I've added this task:\n" +
    // String.format("added: %s\n", taskName)

    // );
    // }

}
