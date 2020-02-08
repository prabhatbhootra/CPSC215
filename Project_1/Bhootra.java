public class Bhootra{   
  public static void main (String args[]){
    MyTextEditor m1 = new MyTextEditor("initial.txt");
    m1.print();
    System.out.println("");
    if(!m1.isCursorAtLastLine()){
      m1.moveCursorToLine(4);
    }
    //System.out.println("");
    //m1.cursorDown();
    m1.insertAfterCursor("");
    System.out.println(m1.cursorLineNum());
    m1.cursorDown();

    m1.replaceAtCursor("where animals talk, where magical things happen, the world of wicked deans");
    //m1.cursorDown();                //1
    //m1.cursorDown();                 //2
    //m1.insertAfterCursor("");        //3
    m1.print();
  }
}
            
        
