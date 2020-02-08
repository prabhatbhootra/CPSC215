public class ArraySequence<E> implements Sequence<E>{
  public int CAPACITY = 16;
  public int sz = 0;
  public PositionObject<E>[] S;
  public ArraySequence(){
    S = (PositionObject<E>[]) new PositionObject[CAPACITY];
  }
  public Position<E> atIndex(int i) throws IndexOutOfBoundsException{
    if (i<0 || i>S.length-1){
      throw new IndexOutOfBoundsException("Index is out of bounds.");
    }
    
    return S[i];
  }
  public int indexOf(Position<E> p) throws IllegalArgumentException{
    for (int k = 0; k <= sz-1; k++){
      if (S[k] == p){
        return k;
      }
    }
    throw new IllegalArgumentException("Invalid Position.");
  }
  public int size(){
    return sz;
  }
  public boolean isEmpty(){
    return sz == 0;
  }
  public E get(int i) throws IndexOutOfBoundsException{
    if (i<0 || i>S.length-1){
      throw new IndexOutOfBoundsException("Index is out of bounds.");
    }
    return S[i].getElement();
  }
  public E set(int i, E e) throws IndexOutOfBoundsException{
    if (i<0 || i>S.length-1){
      throw new IndexOutOfBoundsException("Index is out of bounds.");
    }
    E temp = (E) S[i];
    S[i].setElement(e);
    return temp;
  }
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
    return temp;
  }
  public Position<E> first(){
    return S[0];
  }
  public Position<E> last(){
    return S[sz-1];
  }
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
  public Position<E> addFirst(E e){
    if (sz == S.length){
      resize(2*S.length);
    }
    for (int c = sz-1; c <= 0; c--){
      S[c+1] = S[c];
    }
    S[0].setElement(e);
    S[0].setPosition(0);
    for (int y = 1; y <= sz; y++){
        S[y].setPosition(y+1);
    }
    sz++;
    return S[0];
  }
  public Position<E> addLast(E e){
    if (sz == S.length){
      resize(2*S.length);
    }
    S[sz] = new PositionObject(sz, e); 
    sz++;
    return S[sz-1];
  }
  public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException{
    if (sz == S.length){
      resize(2*S.length);
    }
    for (int d = 0; d <= sz-1; d++){
      if (S[d] == p){
        for (int z = sz-1; z >= d; z--){
          S[z+1] = S[z];
        }
      }
      S[d].setElement(e);
      S[d].setPosition(d);
      sz++;
      for (int u = d+1; u <= sz-1; u++){
         S[u].setPosition(u+1);
      }
      return S[d];
    }
    throw new IllegalArgumentException("Invalid Position.");
  }
  public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException{
    int index = indexOf(p);
    if (sz == S.length-1){
      resize(2*S.length);
    }
    for (int f = 0; f <= sz-1; f++){
      if (f == index){
        for (int g = sz-1; g > f; g--){
          S[g+1] = S[g];
        }
      }
      S[index + 1] = new PositionObject(index + 1, e);
      //System.out.println(S[f+2].getElement());
      for (int o = index+1; o <= sz-1; o++){
        S[o].setPosition(o+1);
      }
      sz++;
      return S[index + 1];
    }
    throw new IllegalArgumentException("Invalid Position.");
  }
  public E set(Position<E> p, E e) throws IllegalArgumentException{
    for (int h = 0; h <= sz-1; h++){
      if (S[h] == p){
        E tempel = S[h].getElement();
        S[h].setElement(e);
        return tempel;
      }
    }
    throw new IllegalArgumentException("Invalid Position.");
  }
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
  public void resize(int capacity) {
    PositionObject<E>[] temp = (PositionObject<E>[]) new Object[capacity];
    for (int k=0; k < sz; k++){
      temp[k] = S[k];      
    }
    S = temp;
  }

}
