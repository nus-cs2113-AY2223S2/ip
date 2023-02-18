public class Todos extends Task{
    Todos(String description) {
        super(description);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + getStatusAndDescription());
    }

    /**
     * Do not have to worry about
     * 1. Lines.
     * 2. Number of variables in the list.
     */
//    public void printAddedTodo() {
//        System.out.println("\tGot it. I've added this task:");
//        System.out.println("\t\t" + getStatusAndDescription());
//    }

    @Override
    public String getStatusAndDescription() {
        return "[T]" + super.getStatusAndDescription();
    }
}

//random comment