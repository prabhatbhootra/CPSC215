import java.util.Iterator;

/**
 * File: Tester.java
 *
 * This is a driver program to test your implementation of the class
 * LinkedBinaryTree.
 *
 * @author Takunari Miyazaki
 * @see Character
 * @see Iterable
 * @see Iterator
 * @see LinkedBinaryTree
 * @see Position
 * @see String
 * @see Tree
 */

public class Tester {

  /**
   * This method constructs an arithmetic expression tree of an infix
   * arithmetic expression s by simply calling the recursive version of the
   * same method.
   */
  public static void preorderAET(LinkedBinaryTree<Character> T, String s) {
    preorderAET(T, T.addRoot(null), s);
  }

  /**
   * This method recursively constructs an arithmetic expression tree of an
   * infix arithmetic expression s starting at a position v.
   */
  public static String preorderAET(LinkedBinaryTree<Character> T,
    Position<Character> v, String s) {
    if (s.length() == 0)
      return "";
    if (s.charAt(0) == ' ')
      return preorderAET(T, v, s.substring(1));  // Skip spaces.
    else if (Character.isDigit(s.charAt(0))) {
      T.set(v, (new Character(s.charAt(0))));
      return s.substring(1);
    }
    else {
      T.set(v, (new Character(s.charAt(0))));
      T.addLeft(v, null);
      T.addRight(v, null);
      String t = preorderAET(T, T.left(v), s.substring(1));
      return preorderAET(T, T.right(v), t);
    }
  }

  /**
   * This method prints all the elements of the tree T in preorder.
   */
  public static <E> void preorderPrint(Tree<E> T) {
    Iterator<E> pos = T.iterator();
    while (pos.hasNext()){
      System.out.print(pos.next());
    }

    // Implement this method.

  }

  /**
   * This method returns the indented parenthetic string representation of
   * the tree T.
   */
  public static <E> String IPSR(Tree<E> T) {
    return IPSR(T, T.root(), 0);
  }

  /**
   * This method recursively constructs the indented parenthetic string
   * representation of the subtree of T rooted at a position p.
   */
  public static <E> String IPSR(Tree<E> T, Position<E> p, int h) {
    String s = p.getElement().toString();
    if (T.isInternal(p)){
      boolean firsttime = true;
      Iterator<Position<E>> i = T.children(p).iterator();
      while (i.hasNext()){
        Position<E> c = i.next();
        if(firsttime){
          s = s + "(" + IPSR(T,c,0);
          firsttime = false;
        }
        else s = s + "," + IPSR(T,c,0);
      }
      s = s + ")";
    }
    // Implement this method.
    return s;
  }

  /**
   * This main method tests the class LinkedBinaryTree using the example
   * from Figure 8.6, p. 318, of our main textbook.
   */
  public static void main(String[] args) {
    LinkedBinaryTree<Character> bt = new LinkedBinaryTree<Character>();
    preorderAET(bt, "- / * + 3 1 3 + - 9 5 2 + * 3 - 7 4 6");
    preorderPrint(bt);
    System.out.println(IPSR(bt));
  }

}
