public class PositionObject<E> implements Position<E>{
  public E element;
  public int Index;
  public PositionObject(int i, E el){
    Index = i;
    element = el;
  }
  public int getPosition(){
    return Index;
  }
  public E getElement() throws IllegalStateException{
    return element;
  }
  public void setElement(E elm){
    element = elm;
  }
  public void setPosition(int i){
    Index = i;
  }
}
