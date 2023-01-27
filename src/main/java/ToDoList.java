import java.util.Vector;

public class ToDoList {
    private static Vector<String> list = new Vector<String>(10,1);

    public static void addItem (String in) {
        list.addElement(in);
        System.out.println("added: " + in);
    }

    public static void viewList () {
        for (int i = 0; i < list.size(); ++i) {
            System.out.println(i+1 + ". " + list.elementAt(i));
        }
    }

}
