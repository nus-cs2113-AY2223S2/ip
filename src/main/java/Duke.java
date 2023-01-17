public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Conversation.lines();
        Conversation.greeting();
        Conversation.question();
        //Conversation.gap();
        Conversation.lines();
        Conversation.farewell();
        //Conversation.gap();
        Conversation.lines();
    }
}
