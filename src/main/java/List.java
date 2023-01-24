public class List {
    private String[] list;
    private int listLength;
    
    public List() {
        list = new String[100];
        listLength = 0;
    }
    
    public void addList(String text) {
        list[listLength] = text;
        listLength++;
    }
    
    public void printList() {
        for (int i = 0; i < listLength; i++) {
            System.out.println((i+1) + ". " + list[i]);
        }
    }
}
