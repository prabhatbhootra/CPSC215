/**
 * An interface for a sequence, a data structure supporting all operations of
 * the ADTs list and positional list.
 *
 * @author Roberto Tamassia
 * @author Michael Goodrich
 *
 * @see IllegalArgumentException
 * @see IndexOutOfBoundsException
 * @see List
 * @see PositionalList
 */

public interface Sequence<E> extends List<E>, PositionalList<E> {

  /**
   * Returns the position containing the element at the given index.
   * @param  i the index of the element
   * @return the position of the element at the specified index
   * @throws IndexOutOfBoundsException if the index is negative or greater
   *         than size()-1
   */
  Position<E> atIndex(int i) throws IndexOutOfBoundsException;

  /**
   * Returns the index of the element stored at the given Position.
   * @param  p the Position of the element
   * @return the index of the element at the specified Position
   * @throws IllegalArgumentException if p is not a valid position for this
   *         list
   */
  int indexOf(Position<E> p) throws IllegalArgumentException;

}
