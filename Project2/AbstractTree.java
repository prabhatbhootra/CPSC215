import java.util.Iterator;
import java.util.List;         // for use as snapshot iterator
import java.util.ArrayList;    // for use as snapshot iterator

/**
 * An abstract base class providing some functionality of the Tree interface.
 * The following three methods remain abstract, and must be implemented by a 
 * concrete subclass: root, parent, children. Other methods implemented in 
 * this class may be overridden to provide a more direct and efficient 
 * implementation.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
public abstract class AbstractTree<E> implements Tree<E> {

  /**
   * Returns true if Position p has one or more children.
   *
   * @param  p a valid Position within the tree
   * @return true if p has at least one child, false otherwise
   * @throws IllegalArgumentException if p is not a valid Position
   */
  public boolean isInternal(Position<E> p) { return numChildren(p) > 0; }

  /**
   * Returns true if Position p does not have any children.
   *
   * @param  p a valid Position within the tree
   * @return true if p has zero children, false otherwise
   * @throws IllegalArgumentException if p is not a valid Position
   */
  public boolean isExternal(Position<E> p) { return numChildren(p) == 0; }

  /**
   * Returns true if Position p represents the root of the tree.
   *
   * @param  p a valid Position within the tree
   * @return true if p is the root of the tree, false otherwise
   */
  public boolean isRoot(Position<E> p) { return p == root(); }

  /**
   * Returns the number of children of Position p.
   *
   * @param  p a valid Position within the tree
   * @return number of children of Position p
   * @throws IllegalArgumentException if p is not a valid Position
   */
  public int numChildren(Position<E> p) {
    int count=0;
    for (Position child : children(p)) count++;
    return count;
  }

  /**
   * Returns the number of nodes in the tree.
   *
   * @return number of nodes in the tree
   */
  public int size() {
    int count=0;
    for (Position p : positions()) count++;
    return count;
  }

  /**
   * Tests whether the tree is empty.
   *
   * @return true if the tree is empty, false otherwise
   */
  public boolean isEmpty() { return size() == 0; }

  //---------- computing depth of nodes and height of (sub)trees ----------

  /** 
   * Returns the number of levels separating Position p from the root.
   *
   * @param  p a valid Position within the tree
   * @throws IllegalArgumentException if p is not a valid Position
   */
  public int depth(Position<E> p) throws IllegalArgumentException {
    if (isRoot(p))
      return 0;
    else
      return 1 + depth(parent(p));
  }

  /** 
   * Returns the height of the tree, using depth().
   */
  private int heightBad() {
    int h = 0;
    for (Position<E> p : positions())
      if (isExternal(p))
        h = Math.max(h, depth(p));
    return h;
  }

  /**
   * Returns the height of the subtree rooted at Position p.
   *
   * @param  p a valid Position within the tree
   * @throws IllegalArgumentException if p is not a valid Position
   */
  public int height(Position<E> p) throws IllegalArgumentException {
    int h = 0;                          // base case if p is external
    for (Position<E> c : children(p))
      h = Math.max(h, 1 + height(c));
    return h;
  }

  //---------- support for various iterations of a tree ----------

  //---------------- nested ElementIterator class ----------------
  /* 
   * This class adapts the iteration produced by positions() to return 
   * elements.
   */
  private class ElementIterator implements Iterator<E> {
    Iterator<Position<E>> posIterator = positions().iterator();
    public boolean hasNext() { return posIterator.hasNext(); }
    public E next() { return posIterator.next().getElement(); }
    public void remove() { posIterator.remove(); }
  }

  /**
   * Returns an iterator of the elements stored in the tree.
   *
   * @return iterator of the tree's elements
   */
  public Iterator<E> iterator() { return new ElementIterator(); }

  /**
   * Returns an iterable collection of the positions of the tree.
   *
   * @return iterable collection of the tree's positions
   */
  public Iterable<Position<E>> positions() { return preorder(); }

  /**
   * Adds positions of the subtree rooted at Position p to the given snapshot 
   * using a preorder traversal
   *
   * @param p Position serving as the root of a subtree
   * @param snapshot  a list to which results are appended
   */
  private void preorderSubtree(Position<E> p, List<Position<E>> snapshot) {
    snapshot.add(p);
    for (Position<E> c : children(p))
      preorderSubtree(c, snapshot);
  }

  /**
   * Returns an iterable collection of positions of the tree, reported in 
   * preorder.
   *
   * @return iterable collection of the tree's positions in preorder
   */
  public Iterable<Position<E>> preorder() {
    List<Position<E>> snapshot = new ArrayList();
    if (!isEmpty())
      preorderSubtree(root(), snapshot);   // fill the snapshot recursively
    return snapshot;
  }

}
