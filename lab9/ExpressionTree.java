/**
 * File: ExpressionTree.java
 *
 * A subclass of LinkedBinaryTree to represent simple arithmetic expressions.
 * @author Prabhat Bhootra
 * @author Takunari Miyazaki
 * @see LinkedBinaryTree
 * @version 1.00 28/03/2018
 */

public class ExpressionTree extends LinkedBinaryTree<String> {

  /** Evaluates the value of the entire expression tree. */
  public int evaluate() throws IllegalStateException {
    return evaluate(root());

    // Complete this blank.

  }

  /** Recursively evaluates the value of the subtree rooted at v. */
  protected int evaluate(Position<String> v) {
    if (v.getElement().equals("+")){
      return evaluate(left(v)) + evaluate(right(v));
    }
    else if (v.getElement().equals("*")){
      return evaluate(left(v)) * evaluate(right(v));
    }
    else if (v.getElement().equals("/")){
      return evaluate(left(v)) / evaluate(right(v));
    }
    else if (v.getElement().equals("-")){
      return evaluate(left(v)) - evaluate(right(v));
    }
    else{
      return new Integer(v.getElement());
    }
   // Complete this blank.
  }

}
