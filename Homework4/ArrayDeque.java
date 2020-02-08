/* A program to create an ArrayDeque 
 * @author Prabhat Bhootra
 * @version 1.0, 3/3/18
 */
public class ArrayDeque<E> implements Deque<E>{
    public static final int CAPACITY = 1000;
    public E[] data;
    private int f = 0;
    private int sz = 0;
    /* 
     * Creates an ArrayDeque of maximum capacity
     */
    public ArrayDeque(){
        this(CAPACITY);
    }
    /* 
     * Creates an ArrayDeque of given capacity
     * @param capacity the maximum given capacity of the ArrayDeque
     */
    public ArrayDeque(int capacity){
        data = (E[]) new Object[capacity];
    }
    /* 
     * returns the size of the ArrayDeque
     * @return sz the size of the ArrayDeque
     */
    public int size(){
        return sz;
    }
    /* 
     * returns whether or not the ArrayDeque is empty
     * @return true if the ArrayDeque is empty, false otherwise
     */
    public boolean isEmpty(){
        return (sz == 0);
    }
    /* 
     * returns the first element of the ArrayDeque
     * @return data[f] the first element of the ArrayDeque if not empty, null otherwise
     */
    public E first(){
        if(isEmpty()){
            return null;
        }
        return data[f];
    }
    /* 
     * returns the last element of the ArrayDeque
     * @return data[sz-1] the last element of the ArrayDeque if not empty, null otherwise
     */
    public E last(){
        if(isEmpty()){
            return null;
        }
        return data[sz-1];
    }
    /* 
     * adds an element to the front of the ArrayDeque
     * @param e an element of generic type
     */
    public void addFirst(E e) throws IllegalStateException{
        if (sz == data.length) throw new IllegalStateException("Deque is full. Cannot add element.");
        f = (f-1+data.length)%data.length;
        data[f] = e;
        sz++;
    }
    /* 
     * adds an element to the end of the ArrayDeque
     * @param e an element of generic type
     */
    public void addLast(E e) throws IllegalStateException{
        if (sz == data.length) throw new IllegalStateException("Deque is full. Cannot add element.");
        data[sz%data.length] = e;
        sz++;
    }
    /* 
     * removes an element from the front of the ArrayDeque
     * @return answer the element of generic type that was removed
     */
    public E removeFirst(){
        E answer;
        answer = data[f];
        data[f] = null;
        f++;
        sz--;
        return answer;        
    }
    /* 
     * removes an element from the end of the ArrayDeque
     * @return answer the element of generic type that was removed
     */
    public E removeLast(){
        E answer;
        answer = data[sz-1];
        data[sz-1] = null;
        sz--;
        return answer;
        
    }
}
        
            
        
    