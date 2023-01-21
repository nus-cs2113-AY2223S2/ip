public class SeparationLine {
    private int length;
    public SeparationLine(int length){
        setLength(length);
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void drawSeparationLine(){
        Indentation indentation = new Indentation();
        indentation.printIndentation(4);
        String s = Character.toString(0x2500);
        for (int i=0; i<this.length; i+=1){
            System.out.print(s);
        }
        System.out.print("\n");
    }
}
