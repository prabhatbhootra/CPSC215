/** 
 * A program to create a DequeStack. 
 * @author Prabhat Bhootra
 * @version 1.0, 3/3/18
 */
public class DequeStack<E> implements Stack<E>{
    Deque<E> deque = new ArrayDeque<E>();
    /* 
     * returns the size of the DequeStack
     * @return deque.size() the size of the DequeStack
     */
    public int size(){
        return deque.size();
    }
    /* 
     * returns whether or not the DequeStack is empty
     * @return true if the DequeStack is empty, false otherwise
     */
    public boolean isEmpty(){
        return deque.isEmpty();
    }
    /* 
     * pushes an element to the DequeStack
     * @param e the element of generic type to be pushed to the DequeStack
     */
    public void push(E e){
        deque.addLast(e);
    }
    /* 
     * returns last element of the DequeStack
     * @return deque.last() the last element of the DequeStack of generic type
     */
    public E top(){
        return deque.last();
    }
    /* 
     * pops last element of the DequeStack
     * @return deque.removeLast() the popped element of the DequeStack of generic type
     */
    public E pop(){
        return deque.removeLast();
    }
}
    