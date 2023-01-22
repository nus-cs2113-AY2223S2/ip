public class Duke {
    public static void main(String[] args) {

        TaskListener.greet();

        TaskListener listener = new TaskListener();
        listener.listen();
    }
}
