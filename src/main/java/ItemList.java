public class ItemList {

    public static String[] items = new String[100];
    public static int item_count = 0;

    public static void addItem(String item){
        items[item_count] = item;
        item_count++;
        System.out.println("________________________________");
        System.out.println("added: " + item);
        System.out.println("________________________________");
    }

    public static void listItems(){
        System.out.println("________________________________");
        for (int idx = 0; idx < item_count; idx++){
            Integer count_wrapper = idx + 1;
            System.out.println(count_wrapper.toString() + ". " + items[idx]);
        }
        System.out.println("________________________________");
    }
}
