import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * File: Sorting.java
 *
 * We compare the performances of sorting algorithms by counting the numbers 
 * of comparisons performed.
 *
 * @author Takunari Miyazaki
 *
 * @see ArrayList
 * @see Integer
 * @see Iterator
 * @see List
 */

public class Sorting {

  /** This method prints the elements of a list L. */
  public static <E> void printList(List<E> L) {
    Iterator<E> i = L.iterator();
    while (i.hasNext())
      System.out.print(i.next().toString() + " ");
    System.out.println();
  }

  /** This method swaps two elements of a list L at indices i and j. */
  public static <E> void swap(List<E> L, int i, int j) {
    E e = L.get(i);
    L.set(i, L.get(j));
    L.set(j, e);
  }

  /** This method heap-sorts (in place) the elements of a list L. */
  public static <E> void heapsort(List<E> L) {

    int n = L.size();

    // The first phase builds a heap, one at a time, of heapsize.
    for (int heapsize = 0; heapsize < n; heapsize++) {
      int i = heapsize;
      while (i > 0) {
        int p = (i - 1)/2;
        if (((Comparable) L.get(i)).compareTo(L.get(p)) > 0) {
          swap(L, i, p);
          i = p;
        }
        else
          break;
      }
    }
    int i = 0;
    int right = 0;
    int left = 0;
    int biggerchild = 0;
    for (int heapsize = n; heapsize > 0; heapsize--){
        i = 0;
        swap(L, 0, heapsize-1);
        left = (2*i) + 1;
        right = (2*i) + 2;
        while (right < heapsize -1){
            left = (2*i) + 1;
            right = (2*i) + 2;
            biggerchild = left;
            if (((Comparable) L.get(right)).compareTo(L.get(left)) > 0) {
                biggerchild = right;
            }
            if (((Comparable) L.get(i)).compareTo(L.get(biggerchild)) < 0){
                swap(L, 0, biggerchild);
                i = biggerchild;
                //i = smallerchild;
            }
            else{
                break;
            }
            //i++;
            
        }
    }
            
    // Complete the second phase below.

  }
  
  public static <E> long selectionsort(List<E> L){
      int n = L.size();
      long selectcount = 0;
      for (int i = 0; i < n-1; i++){
          int minIndex =  i;
          for (int j = i+1; j < n; j++){           
              if (((Comparable) L.get(j)).compareTo(L.get(minIndex)) < 0){               
                  minIndex = j;           
              }
              selectcount++;
          }
          if (minIndex != i){
              swap(L, i, minIndex);
          }
      }
      return selectcount;
  }
  
  public static <E> long bubblesort(List<E> L){
      long bubblecount = 0;
      int n = L.size();
      for (int i = 1; i < n; i++){
          for (int j = 0; j < n-1; j++){
              if (((Comparable) L.get(j)).compareTo(L.get(j+1)) > 0){
                  swap(L, j, j+1); 
              }
              bubblecount++;
              
          }
      }
      return bubblecount;
  }
          
          

  /** This method compares the performaces of sorting algorithms. */
  public static void main(String args[]) {

    java.util.Random r = new java.util.Random();

    // Instantiate a list of 20 random integers between 0 and 19.
    List<Integer> L20 = new ArrayList<Integer>();
    for (int i = 0; i < 20; i++)
      L20.add(i, new Integer(Math.abs(r.nextInt()) % 20));

    // Test heap sort.
    System.out.println("Testing heap sort...");
    System.out.print("Input:  ");
    printList(L20);
    heapsort(L20);
    System.out.print("Output: ");
    printList(L20);
    
    List<Integer> L21 = new ArrayList<Integer>();
    for (int i = 0; i < 20; i++)
      L21.add(i, new Integer(Math.abs(r.nextInt()) % 20));
    
    System.out.println("Testing selection sort...");
    System.out.print("Input:  ");
    printList(L21);
    selectionsort(L21);
    System.out.print("Output: ");
    printList(L21);
    
    List<Integer> L22 = new ArrayList<Integer>();
    for (int i = 0; i < 20; i++)
      L22.add(i, new Integer(Math.abs(r.nextInt()) % 20));
    
    System.out.println("Testing bubble sort...");
    System.out.print("Input:  ");
    printList(L22);
    bubblesort(L22);
    System.out.print("Output: ");
    printList(L22);
    System.out.println("");
    
    List<Integer> L23 = new ArrayList<Integer>();
    for (int i = 0; i < 10; i++)
      L23.add(i, new Integer(Math.abs(r.nextInt()) % 20));
    List<Integer> L24 = L23;
    List<Integer> L25 = L23;
    System.out.println("For n = 10, bubble sort performs " + bubblesort(L23) + " comparisons.");
    System.out.println("For n = 10, selection sort performs " + selectionsort(L24) + " comparisons.");
    System.out.println("For n = 10, heap sort performs " + " comparisons.");
    System.out.println("");
    
    List<Integer> L26 = new ArrayList<Integer>();
    for (int i = 0; i < 100; i++)
      L26.add(i, new Integer(Math.abs(r.nextInt()) % 20));
    List<Integer> L27 = L26;
    List<Integer> L28 = L26;
    System.out.println("For n = 100, bubble sort performs " + bubblesort(L26) + " comparisons.");
    System.out.println("For n = 100, selection sort performs " + selectionsort(L27) + " comparisons.");
    System.out.println("For n = 100, heap sort performs " + " comparisons.");
    System.out.println("");
    
    List<Integer> L29 = new ArrayList<Integer>();
    for (int i = 0; i < 1000; i++)
      L29.add(i, new Integer(Math.abs(r.nextInt()) % 20));
    List<Integer> L30 = L29;
    List<Integer> L31 = L29;
    System.out.println("For n = 1000, bubble sort performs " + bubblesort(L29) + " comparisons.");
    System.out.println("For n = 1000, selection sort performs " + selectionsort(L30) + " comparisons.");
    System.out.println("For n = 1000, heap sort performs " + " comparisons.");
    System.out.println("");
    
    List<Integer> L32 = new ArrayList<Integer>();
    for (int i = 0; i < 10000; i++)
      L32.add(i, new Integer(Math.abs(r.nextInt()) % 20));
    List<Integer> L33 = L32;
    List<Integer> L34 = L32;
    System.out.println("For n = 10000, bubble sort performs " + bubblesort(L32) + " comparisons.");
    System.out.println("For n = 10000, selection sort performs " + selectionsort(L33) + " comparisons.");
    System.out.println("For n = 10000, heap sort performs " + " comparisons.");
    System.out.println("");
    
    /*
    List<Integer> L35 = new ArrayList<Integer>();
    for (int i = 0; i < 100000; i++)
      L35.add(i, new Integer(Math.abs(r.nextInt()) % 20));
    List<Integer> L36 = L35;
    List<Integer> L37 = L35;
    System.out.println("For n = 100000, bubble sort performs " + bubblesort(L35) + " comparisons.");
    System.out.println("For n = 100000, selection sort performs " + selectionsort(L36) + " comparisons.");
    System.out.println("For n = 100000, heap sort performs " + " comparisons.");
    System.out.println("");
    */
  }

}
