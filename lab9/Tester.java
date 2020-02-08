/**
 * File: Tester.java
 *
 * A driver program to test the classes LinkedBinaryTree and ExpressionTree.
 * @author Prabhat Bhootra
 * @author Takunari Miyazaki
 * @see ExpressionTree
 * @see LinkedBinaryTree
 * @see NumberFormatException
 * @see Position
 * @see String
 * @see StringTokenizer
 * @version 1.00, 28/03/2018
 */

import java.util.*;

public class Tester {

  /** Inserts the prefix expression s into a tree T starting at the root. */
  public static void preorderBuild(LinkedBinaryTree<String> T, String s) {
    StringTokenizer st = new StringTokenizer(s);
    preorderBuild(T, T.addRoot(null), st);
  }

  /** Recursively inserts the prefix expression s */
  protected static void preorderBuild(LinkedBinaryTree<String> T,
    Position<String> v, StringTokenizer st) {
    if (st.hasMoreTokens()) {
      String s = st.nextToken();
      try {
        Integer.parseInt(s);
        T.set(v, s);
      }
      catch (NumberFormatException e) {
        T.set(v, s);
        preorderBuild(T, T.addLeft(v, null), st);
        preorderBuild(T, T.addRight(v, null), st);
      }
    }
  }

  /** The main() method. */
  public static void main(String args[]) {

    LinkedBinaryTree<String> bt1 = new LinkedBinaryTree<String>();
    System.out.println("The first example.");
    System.out.println("Elements in preorder: " + 
                       bt1.preorderElements().toString());
    System.out.println("Inserting * as the root.");
    Position<String> p = bt1.addRoot("*");
    System.out.println("Elements in preorder: " + 
                       bt1.preorderElements().toString());
    System.out.println("Inserting 2 as the left child of *.");
    bt1.addLeft(p, "2");
    System.out.println("Elements in preorder: " + 
                       bt1.preorderElements().toString());
    System.out.println("Inserting + as the right child of *.");
    p = bt1.addRight(p, "+");
    System.out.println("Elements in preorder: " + 
                       bt1.preorderElements().toString());
    System.out.println("Inserting 6 as the left child of +.");
    bt1.addLeft(p, "6");
    System.out.println("Elements in preorder: " + 
                       bt1.preorderElements().toString());
    System.out.println("Inserting 5 as the right child of +.");
    bt1.addRight(p, "5");
    System.out.println("Elements in preorder: " + 
                       bt1.preorderElements().toString());

    LinkedBinaryTree<String> bt2 = new LinkedBinaryTree<String>();
    String exp1 = "- / * + 3 1 3 + - 9 5 2 + * 3 - 7 4 6";
    System.out.println();
    System.out.println("The second example.");
    System.out.println("Inserting in preorder: " + exp1);
    preorderBuild(bt2, exp1);
    System.out.println("Elements in preorder: " + 
                       bt2.preorderElements().toString());
    System.out.println("Elements in postorder: " +
                       bt2.postorderElements().toString());
    
    ExpressionTree et1 = new ExpressionTree();
    preorderBuild(et1, "- / * + 3 1 3 + - 9 5 2 + * 3 - 7 4 6");
    System.out.println("First Expression Tree: " + et1.evaluate());

    ExpressionTree et2 = new ExpressionTree();
    preorderBuild(et2, "+ - 496 * 28 6 8128");
    System.out.println("Second Expression Tree: " + et2.evaluate());
    
    // Add a code segment to test the class ExpressionTree using the 
    // expression "- / * + 3 1 3 + - 9 5 2 + * 3 - 7 4 6".

    // Add a code segment to test the class ExpressionTree using the 
    // expression "+ - 496 * 28 6 8128".

  }

}
