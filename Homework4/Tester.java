/** 
 * A test program for the class ArrayDeque. 
 * @author Prabhat Bhootra
 * @version 1.0, 3/3/18
 * @see java.util.Arrays
 */
import java.util.Arrays;

public class Tester {

  public static void main(String args[]) {

    Deque<Integer> deque = new ArrayDeque<Integer>(10);
    
    // Create an Integer object and insert it at the front of the deque.
    // Use the constructor Integer() for this.

    for (int k = 0; k < 10; k++) {
        Integer val = new Integer(k);
        deque.addFirst(val);
    }
    // Remove an integer from the front of the deque and print its value. 

    while (!deque.isEmpty()) {
        System.out.println(deque.removeFirst());
      
    }
    // Create an integer object and insert it at the rear of the deque.
    for (int k = 0; k < 5; k++) {
        Integer intgr = new Integer(k);
        deque.addLast(intgr);     
    }
    // Remove an integer from the rear of the deque and print its value.
    while (!deque.isEmpty()) {
        System.out.println(deque.removeLast());      
    }
    
    // Put 0, 1 into the deque and repeat addFirst(i) and removeLast() 20 
    // times for i = 2, 3, ..., 21.
    deque.addFirst(0);
   
    deque.addFirst(1);
    
    for (int i = 2; i < 22; i++){
        deque.addFirst(i);
        deque.removeLast();
    }

    // Design an algorithm to test the methods first() and last().
    deque.addFirst(100);
    System.out.println(deque.first());
    deque.addLast(101);
    System.out.println(deque.last());
 

    
    // Design an algorithm to test if the deque throws exceptions 
    // appropriately.
    int index = 0;
    while (index < 10){
        deque.addLast(index);
    }

    

  }

}