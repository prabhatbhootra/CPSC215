/**
 * A main to test the editor
 *
 * @author Prabhat Bhootra
 * @version 1.00 03/27/2018
 */

public class Bhootra{
  public static void main (String args[]){
    MyTextEditor m1 = new MyTextEditor("initial.txt");
    m1.print();
	  System.out.println("");
    if(!m1.isCursorAtLastLine()){
      m1.moveCursorToLine(4);
    }
    m1.insertAfterCursor("");
    m1.cursorDown();
    m1.cursorDown();
    m1.replaceAtCursor("where animals talk, where magical things happen, the world of wicked deans");
    m1.cursorDown();
    m1.cursorDown();
    m1.insertAfterCursor("");
    m1.cursorDown();
    m1.replaceAtCursor("Narnia... where professors are wise, where some of the giants like to");
    m1.cursorDown();
    m1.removeAtCursor();
    m1.cursorUp();
    m1.cursorUp();
    m1.insertAfterCursor("snack on students (and, if carefully cooked, on Marsh-wiggles, too),");
    m1.cursorDown();
    m1.insertAfterCursor("");
    m1.print();
    System.out.println("");
    m1.moveCursorToLine(3);
    m1.replaceAtCursor("Susan, Edmund, and Lucy.");
    m1.insertAfterCursor("");
    m1.cursorDown();
    m1.replaceAtCursor("Narnia... where horses talk and hermits like company,");
    m1.cursorDown();
    m1.replaceAtCursor("where evil men turn into donkeys, where boys and girls go into battle.");
    m1.cursorDown();
    m1.replaceAtCursor("");
    m1.cursorDown();
    m1.replaceAtCursor("Narnia... the land between the lamp-post and the castle of Cair Paravel,");
    m1.cursorDown();
    m1.replaceAtCursor("where animals talk, where magical things happen, the world of wicked deans");
    m1.cursorDown();
    m1.replaceAtCursor("and magic spells, where the very best is brought out of even the worst people,");
    m1.cursorDown();
    m1.replaceAtCursor("where anything can happen (and most often does).");
    m1.cursorDown();
    m1.replaceAtCursor("");
    m1.cursorDown();
    m1.replaceAtCursor("Narnia... where owls are wise, where some of the giants like to");
    m1.cursorDown();
    m1.replaceAtCursor("snack on students (and, if carefully cooked, on Marsh-wiggles, too),");
    m1.cursorDown();
    m1.replaceAtCursor("where a prince is put under an evil spell. ");
    m1.insertAfterCursor("");
    m1.cursorDown();
    m1.replaceAtCursor("Narnia... where dwarfs are loyal and tough and strong---or are they really?");
    m1.print();






  }
}
