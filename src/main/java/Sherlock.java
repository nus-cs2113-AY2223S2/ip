public class Sherlock {
    public static void main(String[] args) {
        TaskListener.greet();

        TaskListener taskListener = new TaskListener();
        taskListener.listen();
    }
}
