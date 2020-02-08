/**
 * File: Tester.java
 *
 * This is a simple test driver application to test your BinarySearchTreeMap 
 * class. It should perform the following operations.
 * 1. Insert all strings contained in the array S into the binary search 
 *    tree bst using the strings as keys and arbitrary integers as values.
 * 2. Print all entries as legible key-value pairs using the method 
 *    entrySet().
 * 3. Test the methods get(), put() and remove().
 * 4. Print all values using the method values().
 * 5. Print all keys using the method keys().
 * @author Prabhat Bhootra
 * @version 1.00 04/29/2018
 */

public class Tester {

  public static void main(String args[]) {
    BinarySearchTreeMap<String, Integer> bst = new BinarySearchTreeMap<String, Integer>();
    String S[] = {"gamma", "phi", "beta", "alpha", "delta", "lambda", "epsilon", "zeta" };
    for (int i = 0; i < S.length; i++){
        bst.put(S[i], (i+3)%10);
    }
    System.out.println("Printing all entries");
    System.out.println("");
    Iterable<Entry<String, Integer>> i = bst.entrySet();
    for (Entry<String, Integer> e : i){
      System.out.println(e.getKey() + ", " + e.getValue());
    }
    System.out.println("");
    System.out.println("Testing get for phi:");
    System.out.println(bst.get("phi"));
    System.out.println("");
    System.out.println("Testing put by inserting pi, 3");
    bst.put("pi",3);
    i = bst.entrySet();
    System.out.println("Printing all entries");
    for (Entry<String, Integer> e : i){
      System.out.println(e.getKey() + ", " + e.getValue());
    }
    System.out.println("Testing put by inserting gamma, 11 (existing key)");
    bst.put("gamma",11);
    i = bst.entrySet();
    System.out.println("Printing all entries");
    for (Entry<String, Integer> e : i){
      System.out.println(e.getKey() + ", " + e.getValue());
    }
    System.out.println("");
    System.out.println("Testing remove for pi:");
    bst.remove("pi");
    i = bst.entrySet();
    System.out.println("Printing all entries");
    for (Entry<String, Integer> e : i){
      System.out.println(e.getKey() + ", " + e.getValue());
    }
    System.out.println("");
    System.out.println("Printing all values:");
    System.out.println(bst.values());
    System.out.println("");
    System.out.println("Printing all keys:");
    System.out.println(bst.keySet());

  }
}
