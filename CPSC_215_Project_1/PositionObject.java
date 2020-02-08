/**
 * A class to define PositionObjects
 * @author Prabhat Bhootra
 * @version 1.00 03/27/2018
 */
public class PositionObject<E> implements Position<E>{
  public E element;
  public int Index;
  public PositionObject(int i, E el){
    Index = i;
    element = el;
  }
  /**
   * Returns the position of the position object
   * @return the index of the position object
   */
  public int getPosition(){
    return Index;
  }
  /**
   * Returns the element stored at this position.
   * @return the stored element
   */
  public E getElement() throws IllegalStateException{
    return element;
  }
  /**
   * Sets the element of the position object
   * @param elm the element to be set
   */
  public void setElement(E elm){
    element = elm;
  }
  /**
   * Sets the position of the position object
   * @param i the position to be set
   */
  public void setPosition(int i){
    Index = i;
  }
}
