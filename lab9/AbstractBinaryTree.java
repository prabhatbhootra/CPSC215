import java.util.List;
import java.util.ArrayList;

/**
 * An abstract base class providing some functionality of the BinaryTree 
 * interface. The following five methods remain abstract, and must be 
 * implemented by a concrete subclass: size, root, parent, left, right.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
public abstract class AbstractBinaryTree<E> 
  extends AbstractTree<E> implements BinaryTree<E> {

  /**
   * Returns the Position of p's sibling (or null if no sibling exists).
   *
   * @param  p a valid Position within the tree
   * @return the Position of the sibling (or null if no sibling exists)
   * @throws IllegalArgumentException if p is not a valid Position
   */
  public Position<E> sibling(Position<E> p) {
    Position<E> parent = parent(p);
    if (parent == null) return null;    // p must be the root
    if (p == left(parent))              // p is a left child
      return right(parent);             // (right child might be null)
    else                                // p is a right child
      return left(parent);              // (left child might be null)
  }

  /**
   * Returns the number of children of Position p.
   *
   * @param  p a valid Position within the tree
   * @return number of children of Position p
   * @throws IllegalArgumentException if p is not a valid Position
   */
  @Override
  public int numChildren(Position<E> p) {
    int count=0;
    if (left(p) != null)
      count++;
    if (right(p) != null)
      count++;
    return count;
  }

  /**
   * Returns an iterable collection of the Positions representing p's 
   * children.
   *
   * @param  p a valid Position within the tree
   * @return iterable collection of the Positions of p's children
   * @throws IllegalArgumentException if p is not a valid Position
   */
  public Iterable<Position<E>> children(Position<E> p) {
    List<Position<E>> snapshot = new ArrayList<>(2);
    if (left(p) != null)
      snapshot.add(left(p));
    if (right(p) != null)
      snapshot.add(right(p));
    return snapshot;
  }

}
