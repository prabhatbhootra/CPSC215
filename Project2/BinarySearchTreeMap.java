import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * File: BinarySearchTreeMap.java
 *
 * Realization of a map by means of a binary search tree
 *
 * @author Takunari Miyazaki
 * @author Prabhat Bhootra
 * @see ArrayList
 * @see Comparator
 * @see DefaultComparator
 * @see Entry
 * @see IllegalArgumentException
 * @see Iterator
 * @see LinkedBinaryTree
 * @see List
 * @see Map
 * @see Position
 * @version 1.00 04/29/2018
 */

public class BinarySearchTreeMap<K,V> extends LinkedBinaryTree<Entry<K,V>> 
  implements Map<K,V> {

  protected Comparator<K> C; // comparator
  protected Position<Entry<K,V>> actionPos; // a node variable 
  protected int numEntries = 0; // number of entries

  /** Creates a BinarySearchTreeMap with a default comparator. */
  public BinarySearchTreeMap()  { 
    C = new DefaultComparator<K>(); 
    addRoot(null);
  }

  public BinarySearchTreeMap(Comparator<K> c)  { 
    C = c; 
    addRoot(null);
  }
  
  /** Nested class for location-aware binary search tree entries */
  protected static class BSTEntry<K,V> implements Entry<K,V> {
    protected K key;
    protected V value;
    protected Position<Entry<K,V>> pos;
    BSTEntry() { /* default constructor */ }
    BSTEntry(K k, V v, Position<Entry<K,V>> p) { 
      key = k; value = v; pos = p;
    }
    /** Extracts the key of an entry 
     *  @return the key at the entry of the tree
     */
    public K getKey() { return key; }
    /** Extracts the value of an entry 
     *  @return the value at the entry of the tree
     */
    public V getValue() { return value; }
    /** Extracts the position of an entry 
     *  @return the position at the entry of the tree
     */
    public Position<Entry<K,V>> position() { return pos; }
  }

  /** Extracts the key of the entry at a given node of the tree. 
   * @param position the position whose key should be extracted
   * @return the key at the given position of the tree
   */
  protected K key(Position<Entry<K,V>> position)  {
    return position.getElement().getKey();
  }

  /** Extracts the value of the entry at a given node of the tree. 
   * @param position the position whose value should be extracted
   * @return the value of the given position of the tree
   */  
  protected V value(Position<Entry<K,V>> position)  { 
    return position.getElement().getValue(); 
  }

  /** Extracts the entry at a given node of the tree. 
   * @param position the position whose entry should be extracted
   * @return the entry at the given position of the tree
   */  
  protected Entry<K,V> entry(Position<Entry<K,V>> position)  { 
    return position.getElement();
  }

  /** Replaces an entry with a new entry (and reset the entry's location) 
   * @param pos the position whose entry should be replaced
   * @param ent the entry whose new position is pos
   * @return the value of the previous entry
   */
  protected V replaceEntry(Position <Entry<K,V>> pos, Entry<K,V> ent) {
    ((BSTEntry<K,V>) ent).pos = pos;
    return set(pos, ent).getValue();
  }

  /** Checks whether a given key is valid. 
   * @param key the key that is to be checked
   * @throws IllegalArgumentException if the key is null
   */  
  protected void checkKey(K key) throws IllegalArgumentException {
    if(key == null) // just a simple test for now
      throw new IllegalArgumentException("null key");
  }

  /** Checks whether a given entry is valid. 
   * @param ent the entry to be checked
   * @throws IllegalArgumentException if the entry is invalid
   */
  protected void checkEntry(Entry<K,V> ent) throws IllegalArgumentException {
    if (ent == null || !(ent instanceof BSTEntry))
      throw new IllegalArgumentException("invalid entry");
  }

  /** Auxiliary method for inserting an entry at an external node 
   * @param v the external node where the entry should be inserted
   * @param e the entry to be inserted at the external node
   * @return e the current entry of the external node
   */
  protected Entry<K,V> insertAtExternal(Position<Entry<K,V>> v, Entry<K,V> e) {
      Node<Entry<K,V>> node = validate(v);
      set(node, e);
      addLeft(node, null);
      addRight(node, null);
      numEntries++;
      return e;
    // Complete this blank.
  }

  /** Auxiliary method for removing an external node and its parent 
   * @param v the external node to be removed
   */
  protected void removeExternal(Position<Entry<K,V>> v) {
      if(isExternal(v)){
          Node<Entry<K,V>> s = (Node<Entry<K,V>>) sibling(v);
          Node<Entry<K,V>> p = (Node<Entry<K,V>>) parent(v);
          p.setElement(s.getElement());
          p.setLeft(s.getLeft());
          p.setRight(s.getRight());
          p.setParent((Node<Entry<K,V>>) parent((Node<Entry<K,V>>) parent(v)));
          remove(v);
      }
  }

  /** Auxiliary method used by get, put, and remove. 
   * @param key the key to be searched within the tree
   * @param pos the position to be searched from within the tree
   * @return the position where the key was found, otherwise the external node if key was not found
   */
  protected Position<Entry<K,V>> treeSearch(K key, Position<Entry<K,V>> pos) {
    if (isExternal(pos)) return pos; // key not found; return external node
    else {
      K curKey = key(pos);
      int comp = C.compare(key, curKey);
      if (comp < 0) 
 return treeSearch(key, left(pos)); // search left subtree
      else if (comp > 0)
 return treeSearch(key, right(pos)); // search right subtree
      return pos; // return internal node where key is found
    }
  }

  // methods of the map ADT

  /** Returns the number of entries in the map. 
   * @return numEntries the number of entries in the map
   */
  public int size() { return numEntries; }

  /** Tests whether the map is empty. 
   * @return true if the map iss empty, false otherwise
   */
  public boolean isEmpty() { return size() == 0; }

  /** Returns a value whose associated key is k. 
   * @param key the key whose value is to be extracted
   * @return the value of associated with the key if it exists, null otherwise
   * @throws IllegalArgumentException if the key is invalid
   */
  public V get(K key) throws IllegalArgumentException {
    checkKey(key); // may throw an InvalidKeyException
    Position<Entry<K,V>> curPos = treeSearch(key, root());
    actionPos = curPos; // node where the search ended
    if (isInternal(curPos)) return value(curPos);
    return null;
  }

  /** Inserts an entry with a given key k and value v into the map, returning 
   *  the old value whose associated key is k if it exists. 
   * @param k the key to be inserted into the map
   * @param x the associated value to be inserted into the map
   * @return old value of the key, if it already existed, or null if it previously didn't
   * @throws IllegalArgumentException if key is invalid
   */
  public V put(K k, V x) throws IllegalArgumentException {
    checkKey(k); // may throw an IllegalArgumentException
    Position<Entry<K,V>> insPos = treeSearch(k, root());
    BSTEntry<K,V> e = new BSTEntry<K,V>(k, x, insPos);
    actionPos = insPos; // node where the entry is being inserted
    if (isExternal(insPos)) { // we need a new node, key is new
      insertAtExternal(insPos, e).getValue();
      return null;
    }
    return replaceEntry(insPos, e); // key already exists
  }

  /** Removes from the map the entry whose key is k, returning the value of 
   *  the removed entry. 
   * @param k the key to be removed
   * @return the value of the removed entry
   * @throws IllegalArgumentException if the given key is invalid
   */
  public V remove(K k) throws IllegalArgumentException  {
    checkKey(k); // may throw an IllegalArgumentException
    Position<Entry<K,V>> remPos = treeSearch(k, root());
    if (isExternal(remPos)) return null; // key not found
    Entry<K,V> toReturn = entry(remPos); // old entry 
    if (isExternal(left(remPos))) remPos = left(remPos); // left case
    else if (isExternal(right(remPos))) remPos = right(remPos); // right case
    else { // entry is at a node with internal children
      Position<Entry<K,V>> swapPos = remPos; // find node for moving entry
      remPos = left(swapPos);
      do remPos = right(remPos);
      while (isInternal(remPos));
      replaceEntry(swapPos, (Entry<K,V>) parent(remPos).getElement());
    }
    actionPos = sibling(remPos); // sibling of the leaf to be removed
    removeExternal(remPos);
    return toReturn.getValue();
  }

  /** Returns an iterable collection of the keys of all entries stored in the 
   *  map. 
   * @return an iterable collection of the keys in the map
   */
  public Iterable<K> keySet() {
    List<K> keyList = new ArrayList<K>();
    for (Entry<K,V> e : inorderElements()) {
      if (e != null){
       keyList.add(e.getKey()); 
      }     
    }
    return keyList;
    
    // Complete this blank.

  }

  /** Returns an iterable collection of the values of all entries stored in 
   *  the map. 
   *  @return an iterable collection of the values in the map
   */
  public Iterable<V> values() {
    List<V> valueList = new ArrayList<V>();
    for (Entry<K,V> e : inorderElements()) {
      if (e != null){
       valueList.add(e.getValue()); 
      }     
    }
    return valueList;
    // Complete this blank.

  }

  /** Returns an iterable collection of all entries stored in the map. 
   * @return an iterable collection of the entries in the map
   */
  public Iterable<Entry<K,V>> entrySet() {
    List<Entry<K,V>> entryList = new ArrayList<Entry<K,V>>();
    for (Entry<K,V> e : inorderElements()) {
      if (e != null){
       entryList.add(e); 
      }     
    }
    return entryList;
    // Complete this blank.

  }

}
