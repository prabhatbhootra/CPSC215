/**
 * This is a simple text editor interface, which assumes a text editor
 * is built for lines of text. Each line of text is to be viewed as a
 * separate string. In addition, this editor has a cursor associated
 * with some line in the text. Initially, the cursor is set to the the
 * line -1 just before the first line in the text. The methods of the
 * text editor are designed to update and print the text file using the
 * cursor.
 *
 * @author Takunari Miyazaki
 * @version 1.3, 03/04/2018
 * @see IndexOutOfBoundsException
 * @see String
 */

public interface SimpleTextEditor {

  /**
   * Returns true if the text is completely empty (and cursor is at line -1).
   * @return true if the text is empty and false otherwise
   */
  boolean isEmpty();

  /**
   * Returns the current number of lines of text.
   * @return the current number of lines
   */
  int size();

  /**
   * Returns true if the cursor is at the last line in the text or the text
   * is empty.
   * @return true if the cursor is at the last line and false otherwise.
   */
  boolean isCursorAtLastLine();

  /**
   * Sets the cursor to be the text line after its current position.
   * @throws IndexOutOfBoundsException if the current line is size()-1
   */
  void cursorDown() throws IndexOutOfBoundsException;

  /**
   * Sets the cursor to be the text line before its current position.
   * @throws IndexOutOfBoundsException if the current line is 0
   */
  void cursorUp() throws IndexOutOfBoundsException;

  /**
   * Sets the cursor to be the line ranked i (the first line is line 0).
   * @param  i the target line number
   * @throws IndexOutOfBoundsException if the index is negative or greater
   *         than size()-1
   */
  void moveCursorToLine(int i) throws IndexOutOfBoundsException;

  /**
   * Returns the line number (rank) of the current cursor line.
   */
  int cursorLineNum();

  /**
   * Inserts a string s in the line after the current cursor, moving the
   * cursor to the line inserted.
   * @param  s the string to be inserted
   */
  void insertAfterCursor(String s);

  /**
   * Inserts a string s in the line before the current cursor, moving the
   * cursor to the line inserted.
   * @param  s the string to be inserted
   */
  void insertBeforeCursor(String s);

  /**
   * Replaces the string at the current cursor with the String s, keeping
   * the cursor at this line.
   * @param  s the string to be inserted
   */
  void replaceAtCursor(String s);

  /**
   * Removes the entire line at the current cursor, setting the cursor to now
   * be the position of the next line, unless the cursor was the last line,
   * in which case the cursor should move to the new last line.
   */
  void removeAtCursor();

  /**
   * Prints the entire text to the console.
   */
  void print();

}
