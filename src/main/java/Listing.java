public class Listing {
	private String[] list = new String[100];
	private int inputCount = 0;
	public void addList(String userInput) {
		list[inputCount] = userInput;
		inputCount++;
	}

	public String[] getList() {
		return list;
	}
}
