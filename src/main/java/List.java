public class List {
    private int numItems = 0;
    private String[] listItems;

    public List() {
        listItems = new String[100];
    }

    public void listDisplay()
    {
        for (int i = 0; i < numItems; i+= 1) {
            System.out.print(" " + (i+1) + ". ");
            System.out.println(listItems[i]);
        }

    }
    public void listAdd(String sentence) {
        listItems[numItems] = sentence;
        numItems += 1;

        System.out.print(" added: ");
        System.out.println(sentence);
    }
}
