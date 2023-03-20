/*
 * Duke is an instance of the bot which has several subclasses,
 * storage for file management, ui for communicating with user for input/output, tasklist for managing tasks and task-related commands
 */
public class Duke {

    private String fileName = "duke.txt";
    public Storage storage;
    private UI ui;
    public TaskList tasks;

    public Duke() {
        ui = new UI();
        storage = new Storage(fileName);
        this.tasks = new TaskList();
        storage.load(tasks);
        ui.greet(tasks);
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
