public class Task {
    protected String description;
    protected String type;
    protected boolean isDone;
    private static String line = "__________________________________________________________";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = "0";
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getType() {
        return (type);
    }

    public String toString() {
        return ("[" + type + "][" + getStatusIcon() + "] " + description);
    }

    public void markAsDone(String action) {
        if (action.equals("unmark")) {
            this.isDone = false;
            //System.out.println("Ok, I've marked this task as not done yet:");
        } else {
            this.isDone = true;
            //System.out.println("Nice! I've marked this task as done:");
        }
        //System.out.println("  [" + getStatusIcon() + "] " + description);
//        toString();
//        System.out.println(line);
    }

//    public void getTask(String text) throws NullPointerException {
//        System.out.println(text);
//        if (!text.isEmpty()) {
//            System.out.println("Trying to read...");
//            this.isDone = (text.substring(4, 5).equals("X")) ? true : false; //4th char in string
//            this.description = text.substring(7);
//            this.type = text.substring(1, 2); //2nd char in string
//            if (this.type == "T") {
//                Task t = new //Todo(description);
//            } else if (this.type == "E") {
//                Task t = new Event;
//            }
//
//        } else {
//            throw new NullPointerException();
//        }
//    }
}

