public class ListManager {
    private String[] itemList;
    private int listSize;

    /**
     * Constructor for list assuming items in the list is <=100
     */
    public ListManager(){
        this.listSize = 0;
        this.itemList = new String[100];
    }

    /**
     * Adds item to the list to keep track
     * @param item: user item to remember
     */
    public void addToList(String item){
        itemList[this.listSize] = item;
        listSize+=1;
    }

    /**
     * prints all items stored in the list
     */
    public void printList(){
        for(int i=1;i<this.listSize+1;i++){
            System.out.println(i + ". " + itemList[i-1]);
        }
    }
}
