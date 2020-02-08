import java.io.*;
/**
 * An class to define the text editor
 *
 * @author Prabhat Bhootra
 * @see IndexOutOfBoundsException
 * @version 1.00 03/27/2018
 */

public class MyTextEditor implements SimpleTextEditor{
  public int cursor;
  public ArraySequence<String> arrSeq;
  public MyTextEditor(String filename){
      arrSeq = new ArraySequence<String>();
      File f  = new File(filename);
      try{
        FileReader fr = new FileReader(f);
        BufferedReader br  = new BufferedReader(fr);
        String line =  "";
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
  /**
   * Returns true if the text is completely empty (and cursor is at line -1).
   * @return true if the text is empty and false otherwise
   */
  public boolean isEmpty(){
      if (arrSeq.isEmpty() == true && cursor == -1){
          return true;
      }
      else{
          return false;
      }
  }
  /**
   * Returns the current number of lines of text.
   * @return the current number of lines
   */
  public int size(){
      return arrSeq.size();
  }
  /**
   * Returns true if the cursor is at the last line in the text or the text
   * is empty.
   * @return true if the cursor is at the last line and false otherwise.
   */
  public boolean isCursorAtLastLine(){
      if (arrSeq.isEmpty() == true || cursor == arrSeq.indexOf(arrSeq.last()) + 1){
          return true;
      }
      else{
          return false;
      }
  }
  /**
   * Sets the cursor to be the text line after its current position.
   * @throws IndexOutOfBoundsException if the current line is size()-1
   */
  public void cursorDown() throws IndexOutOfBoundsException{
      if (cursor == arrSeq.indexOf(arrSeq.last()) + 1){
          throw new IndexOutOfBoundsException("Already at last line of text.");
      }
      cursor = arrSeq.indexOf(arrSeq.after(arrSeq.atIndex(cursor-1))) + 1;
  }
  /**
   * Sets the cursor to be the text line before its current position.
   * @throws IndexOutOfBoundsException if the current line is 0
   */
  public void cursorUp() throws IndexOutOfBoundsException{
      if (cursor == arrSeq.indexOf(arrSeq.first()) + 1){
          throw new IndexOutOfBoundsException("Already at first line of text.");
      }
      cursor = arrSeq.indexOf(arrSeq.before(arrSeq.atIndex(cursor-1))) + 1;
  }
  /**
   * Sets the cursor to be the line ranked i (the first line is line 0).
   * @param  i the target line number
   * @throws IndexOutOfBoundsException if the index is negative or greater
   *         than size()-1
   */
  public void moveCursorToLine(int i) throws IndexOutOfBoundsException{
      if (i<0 || i>=arrSeq.size()){
        throw new IndexOutOfBoundsException("Invalid line");
      }
      cursor = arrSeq.indexOf(arrSeq.atIndex(i-1)) + 1;
  }
  /**
   * Returns the line number (rank) of the current cursor line.
   */
  public int cursorLineNum(){
      return cursor;
  }
  /**
   * Inserts a string s in the line after the current cursor, moving the
   * cursor to the line inserted.
   * @param  s the string to be inserted
   */
  public void insertAfterCursor(String s){
      if (isEmpty()){
          cursor = 1;
          arrSeq.add(cursor-1, s);
      }
      else{
        cursor = arrSeq.indexOf(arrSeq.addAfter(arrSeq.atIndex(cursor-1), s)) + 1;
      }
  }
  /**
   * Inserts a string s in the line before the current cursor, moving the
   * cursor to the line inserted.
   * @param  s the string to be inserted
   */
  public void insertBeforeCursor(String s){
      cursor = arrSeq.indexOf(arrSeq.addBefore(arrSeq.atIndex(cursor-1), s)) + 1;
      System.out.println(cursor);
  }
  /**
   * Replaces the string at the current cursor with the String s, keeping
   * the cursor at this line.
   * @param  s the string to be inserted
   */
  public void replaceAtCursor(String s){
    if (arrSeq.size()-1 == arrSeq.length()-1){
      arrSeq.resize(100);
    }
    arrSeq.set(cursor-1, s);
    if (arrSeq.size()-1 == arrSeq.length()-1){
      arrSeq.resize(100);
    }
  }
  /**
   * Removes the entire line at the current cursor, setting the cursor to now
   * be the position of the next line, unless the cursor was the last line,
   * in which case the cursor should move to the new last line.
   */
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
  /**
   * Prints the entire text to the console.
   */
  public void print(){
      int printcursor = 0;
      while (printcursor <= arrSeq.indexOf(arrSeq.last())){
          System.out.println(arrSeq.atIndex(printcursor).getElement());
          printcursor++;
      }
  }
}
