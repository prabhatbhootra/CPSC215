import java.io.*;
public class MyTextEditor implements SimpleTextEditor{
  public int cursor;
  public ArraySequence<String> arrSeq;
  public MyTextEditor(String filename){
      arrSeq = new ArraySequence<String>();
      File f  = new File(filename);
      try{
        FileReader fr = new FileReader(f);
        BufferedReader br  = new BufferedReader(fr);
        String line = "";
        while ((line = br.readLine()) != null){
          arrSeq.addLast(line);
        }
        fr.close();
      }
      catch (FileNotFoundException exception){
        System.out.println("File not found");
      }
      catch (IOException exception){
        System.out.println("File could not be read");
      }
      cursor = -1;
  }
  public boolean isEmpty(){
      if (arrSeq.isEmpty() == true && cursor == -1){
          return true;
      }
      else{
          return false;
      }
  }
  public int size(){
      return arrSeq.size();
  }
  public boolean isCursorAtLastLine(){
      if (arrSeq.isEmpty() == true || cursor == arrSeq.indexOf(arrSeq.last()) + 1){
          return true;
      }
      else{
          return false;
      }
  }
  public void cursorDown() throws IndexOutOfBoundsException{
      if (cursor == arrSeq.indexOf(arrSeq.last()) + 1){
          throw new IndexOutOfBoundsException("Already at last line of text.");
      }
      cursor = arrSeq.indexOf(arrSeq.after(arrSeq.atIndex(cursor-1))) + 1;
  }
  public void cursorUp() throws IndexOutOfBoundsException{
      if (cursor == arrSeq.indexOf(arrSeq.first()) + 1){
          throw new IndexOutOfBoundsException("Already at first line of text.");
      }
      cursor = arrSeq.indexOf(arrSeq.before(arrSeq.atIndex(cursor-1))) + 1;
  }
  public void moveCursorToLine(int i) throws IndexOutOfBoundsException{
      if (i<0 || i>=arrSeq.size()){
        throw new IndexOutOfBoundsException("Invalid line");
      }
      cursor = arrSeq.indexOf(arrSeq.atIndex(i-1)) + 1;
  }
  public int cursorLineNum(){
      return cursor;
  }
  public void insertAfterCursor(String s){
      if (isEmpty()){
          cursor = 1;
          arrSeq.add(cursor-1, s);
      }
      else{
        cursor = arrSeq.indexOf(arrSeq.addAfter(arrSeq.atIndex(cursor-1), s)) + 1;
      }
  }
  public void insertBeforeCursor(String s){
      cursor = arrSeq.indexOf(arrSeq.addBefore(arrSeq.atIndex(cursor-1), s)) + 1;
  }
  public void replaceAtCursor(String s){
      arrSeq.set(cursor, s);
  }
  public void removeAtCursor(){
      if (cursor == arrSeq.indexOf(arrSeq.last()) + 1){
          arrSeq.remove(arrSeq.atIndex(cursor-1));
          cursor = arrSeq.indexOf(arrSeq.last()) + 1;
      }
      else{
          arrSeq.remove(arrSeq.atIndex(cursor-1));
          cursor = arrSeq.indexOf(arrSeq.after(arrSeq.atIndex(cursor-1))) + 1;
      }
  }
  public void print(){
      int printcursor = 1;
      while (printcursor <= arrSeq.indexOf(arrSeq.last()) + 1){
          System.out.println(arrSeq.atIndex(printcursor-1).getElement());
          printcursor++;
      }
  } 
}
