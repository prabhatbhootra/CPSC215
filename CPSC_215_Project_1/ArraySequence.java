/**
 * An array sequence for a text editor
 *
 * @author Prabhat Bhootra
 * @author Michael Goodrich
 *
 * @see IllegalArgumentException
 * @see IndexOutOfBoundsException
 * @version 1.00 03/27/2018
 */

public class ArraySequence<E> implements Sequence<E>{
  public int CAPACITY = 16;
  public int sz = 0;
  public PositionObject<E>[] S;
  public ArraySequence(){
    S = (PositionObject<E>[]) new PositionObject[CAPACITY];
  }
  /**
   * Returns the position containing the element at the given index.
   * @param  i the index of the element
   * @return the position of the element at the specified index
   * @throws IndexOutOfBoundsException if the index is negative or greater
   *         than size()-1
   */
  public Position<E> atIndex(int i) throws IndexOutOfBoundsException{
    if (i<0 || i>S.length-1){
      throw new IndexOutOfBoundsException("Index is out of bounds.");
    }

    return S[i];
  }
  /**
   * Returns the index of the element stored at the given Position.
   * @param  p the Position of the element
   * @return the index of the element at the specified Position
   * @throws IllegalArgumentException if p is not a valid position for this
   *         list
   */
  public int indexOf(Position<E> p) throws IllegalArgumentException{
    for (int k = 0; k <= sz-1; k++){
      if (S[k] == p){
        return k;
      }
    }
    throw new IllegalArgumentException("Invalid Position.");
  }
  /**
   * Returns the number of elements in the list.
   * @return number of elements in the list
   */
  public int size(){
    return sz;
  }
  /**
   * Tests whether the list is empty.
   * @return true if the list is empty and false otherwise
   */
  public boolean isEmpty(){
    return sz == 0;
  }
  /**
   * Returns (but does not remove) the element at index i.
   * @param  i the index of the element to return
   * @return the element at the specified index
   * @throws IndexOutOfBoundsException if the index is negative or greater
   *         than size()-1
   */
  public E get(int i) throws IndexOutOfBoundsException{
    if (i<0 || i>S.length-1){
      throw new IndexOutOfBoundsException("Index is out of bounds.");
    }
    return S[i].getElement();
  }
  /**
   * Replaces the element at the specified index, and returns the element
   * previously stored.
   * @param  i the index of the element to replace
   * @param  e the new element to be stored
   * @return the previously stored element
   * @throws IndexOutOfBoundsException if the index is negative or greater
   *         than size()-1
   */
  public E set(int i, E e) throws IndexOutOfBoundsException{
    if (i<0 || i>S.length-1){
      throw new IndexOutOfBoundsException("Index is out of bounds.");
    }
    E temp = (E) S[i];
    S[i].setElement(e);
    return temp;
  }
  /**
   * Inserts the given element at the specified index of the list, shifting
   * all subsequent elements in the list one position further to make room.
   * @param  i the index at which the new element should be stored
   * @param  e the new element to be stored
   * @throws IndexOutOfBoundsException if the index is negative or greater
   *         than size()
   */
  public void add(int i, E e) throws IndexOutOfBoundsException{
    if (i<0 || i>S.length-1){
      throw new IndexOutOfBoundsException("Index is out of bounds.");
    }
    if (isEmpty()){
      S[i] = new PositionObject(i, e);
      sz++;
      return;
    }
    if (sz == S.length){
      resize(2*S.length);
    }
    for (int j = sz-1; j >= i; j--){
      S[j+1] = S[j];
    }
    S[i].setPosition(i);
    S[i].setElement(e);
    for (int s = i+1; s <= sz; s++){
        S[s].setPosition(s+1);
    }
    sz++;
  }
  /**
   * Removes and returns the element at the given index, shifting all
   * subsequent elements in the list one position closer to the front.
   * @param  i the index of the element to be removed
   * @return the element that had be stored at the given index
   * @throws IndexOutOfBoundsException if the index is negative or greater
   *         than size()-1
   */
  public E remove(int i) throws IndexOutOfBoundsException{
    if (i<0 || i>S.length-1){
      throw new IndexOutOfBoundsException("Index is out of bounds.");
    }
    E temp = (E) S[i];
    for (int k=i; k <= sz-1; k++){
      S[k] = S[k+1];
    }
    for(int q=i; q <= sz; q++){
        S[q].setPosition(q);
    }
    sz--;
    return temp;
  }
  /**
   * Returns the first Position in the list.
   * @return the first Position in the list (or null, if empty)
   */
  public Position<E> first(){
    return S[0];
  }
  /**
   * Returns the last Position in the list.
   * @return the last Position in the list (or null, if empty)
   */
  public Position<E> last(){
    return S[sz-1];
  }
  /**
   * Returns the Position immediately before Position p.
   * @param  p a Position of the list
   * @return the Position of the preceding element (or null, if p is first)
   * @throws IllegalArgumentException if p is not a valid position for this
   *         list
   */
  public Position<E> before(Position<E> p) throws IllegalArgumentException{
    for (int a = 0; a <= sz-1; a++){
      if (S[a] == p){
          if (a == 0){
              return null;
          }
          else{
              return S[a-1];
          }
      }
    }
    throw new IllegalArgumentException("Invalid Position.");
  }
  /**
   * Returns the Position immediately after Position p.
   * @param  p a Position of the list
   * @return the Position of the following element (or null, if p is last)
   * @throws IllegalArgumentException if p is not a valid position for this
   *         list
   */
  public Position<E> after(Position<E> p) throws IllegalArgumentException{
    for (int b = 0; b <= sz-1; b++){
      if (S[b] == p){
        if (b == sz-1){
          return null;
        }
        else{
          return S[b+1];
        }
      }
    }
    throw new IllegalArgumentException("Invalid Position.");
  }
  /**
   * Inserts an element at the front of the list.
   * @param  e the new element
   * @return the Position representing the location of the new element
   */
  public Position<E> addFirst(E e){
    if (sz == S.length){
      resize(2*S.length);
    }
    for (int c = sz-1; c <= 0; c--){
      S[c+1] = S[c];
    }
    S[0] = new PositionObject(0, e);
    for (int y = 1; y <= sz; y++){
        S[y].setPosition(y+1);
    }
    sz++;
    return S[0];
  }
  /**
   * Inserts an element at the back of the list.
   * @param  e the new element
   * @return the Position representing the location of the new element
   */
  public Position<E> addLast(E e){
    if (sz == S.length){
      resize(2*S.length);
    }
    S[sz] = new PositionObject(sz, e);
    sz++;
    return S[sz-1];
  }
  /**
   * Inserts an element immediately before the given Position.
   * @param  p the Position before which the insertion takes place
   * @param  e the new element
   * @return the Position representing the location of the new element
   * @throws IllegalArgumentException if p is not a valid position for this
   *         list
   */
  public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException{
    if (sz == S.length-1){
      resize(2*S.length);
    }
    for (int d = 0; d <= sz-1; d++){
      if (d == indexOf(p)){
        for (int z = sz-1; z >= d; z--){
          S[z+1] = S[z];
        }
      }
    S[d] = new PositionObject(d, e);
    sz++;
    return S[d];
    }
    throw new IllegalArgumentException("Invalid Position.");
  }
  /**
   * Inserts an element immediately after the given Position.
   * @param  p the Position after which the insertion takes place
   * @param  e the new element
   * @return the Position representing the location of the new element
   * @throws IllegalArgumentException if p is not a valid position for this
   *         list
   */
  public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException{
    int index = indexOf(p);
    if (sz-1 == S.length-1){
      resize(2*S.length);
    }
    for (int g = sz-1; g >= index+1; g--){
      S[g+1] = new PositionObject(g+1, S[g].getElement());
    }
    S[index+1] = new PositionObject(index+1, e);
    sz++;
    return S[index + 1];
  }
  /**
   * Replaces the element stored at the given Position and returns the
   * replaced element.
   * @param  p the Position of the element to be replaced
   * @param  e the new element
   * @return the replaced element
   * @throws IllegalArgumentException if p is not a valid position for this
   *         list
   */
  public E set(Position<E> p, E e) throws IllegalArgumentException{
    if (sz-1 == S.length-1){
      resize(2*S.length);
    }
    for (int h = 0; h <= sz-1; h++){
      if (S[h] == p){
        E tempel = S[h].getElement();
        S[h].setElement(e);
        return tempel;
      }
    }
    throw new IllegalArgumentException("Invalid Position.");
  }
   /**
   * Removes the element stored at the given Position and returns it.
   * The given position is invalidated as a result.
   * @param  p the Position of the element to be removed
   * @return the removed element
   * @throws IllegalArgumentException if p is not a valid position for this
   *         list
   */
  public E remove(Position<E> p) throws IllegalArgumentException{
    for (int l = 0; l <= sz-1; l++){
      if (S[l] == p){
        E tempelement = S[l].getElement();
        for (int m = l; m <= sz-1; m++){
          S[m] = S[m+1];
        }
        sz--;
        for (int n = l; n <= sz-1; n++){
            S[n].setPosition(n);
        }
        return tempelement;
      }
    }
    throw new IllegalArgumentException("Invalid Position.");
  }
  /**
   * Resizes array to double the size when full
   * @param  capacity the extended size of the array
   */
  public void resize(int capacity) {
    PositionObject<E>[] temp = (PositionObject<E>[]) new PositionObject[capacity];    //new Object[capacity]
    for (int k=0; k < sz; k++){
      temp[k] = S[k];
    }
    S = temp;
  }
  public int length(){
    return S.length;
  }

}
