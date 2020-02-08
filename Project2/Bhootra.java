import java.util.Scanner;

/**
 * File: Bhootra.java
 *
 * This is an implementation of an interactive telephone directory of trinity 
 * students that uses the ADT Map as its underlying construct. It performs the following
 * functions:
 * 1. Search an entry in the directory; in particular, given a name as input from the user, output the name and his/her telephone number if his/her entry is in the directory.
 * 2. Add a new entry for a given name and telephone number to the directory.
 * 3. Remove an entry from the directory; in particular, given a name as input from the user, remove his/her entry from the directory.
 * 4. List all entries (as legible name-number pairs) from the directory in the alphabetical order by last names.
 * 5. List all names from the directory.
 * 6. List all telephone numbers from the directory.
 * 7. Terminate the session.
 * @author Prabhat Bhootra
 * @version 1.00 04/29/2018
 */

public class Bhootra{
	public static void main(String[] args) {
		BinarySearchTreeMap<String, Integer> b = new BinarySearchTreeMap();
		Scanner in = new Scanner(System.in);
		int choice = 0;
        do {
            do {
            	System.out.println("Please select a valid choice:");
            	System.out.println("(1) Search an entry in the directory:");
            	System.out.println("(2) Add a new entry in the directory:");
            	System.out.println("(3) Remove an entry from the directory:");
            	System.out.println("(4) List all entries in the directory:");
            	System.out.println("(5) List all names in the directory:");
            	System.out.println("(6) List all telephone numbers in the directory:");
            	System.out.println("(7) Terminate the session in the directory:");
                choice = in.nextInt(); 
                break;
            } while((choice >= 1) && (choice <= 7));

            if (choice == 1){
            	try{
            		Scanner Input = new Scanner(System.in);
            	    System.out.println("Enter name to be searched: ");
                    String name = Input.nextLine();
                    System.out.println("Name: " + name + " Phone Number: " + b.get(name));
            	}
            	catch (Exception e){
            		System.out.println("Error. Please try again.");
            	}                
            }
            else if (choice == 2){
            	try{
            		System.out.println("Enter details for new entry to be added:");
            	    Scanner Input = new Scanner(System.in);
            	    System.out.println("Enter name to be added: ");
                    String name = Input.nextLine();
                    Scanner num = new Scanner(System.in);
                    System.out.println("Enter number to be added: ");
                    int number = num.nextInt();
                    b.put(name, number);
            	}
            	catch (Exception e){
            		System.out.println("Error. Please try again.");
            	}       	
            }
            else if (choice == 3){
            	try{
            		Scanner Input = new Scanner(System.in);
            		System.out.println("Enter name of entry to be removed: ");
            		String name = Input.nextLine();
            		System.out.println("The entry " + name + ", " + b.remove(name) + " has been removed");
            	}
            	catch (Exception e){
            		System.out.println("Error. Please try again.");
            	}
            }
            else if (choice == 4){
            	try{                                                              //Alphabetical order of last names
            		Iterable<Entry<String, Integer>> i = b.entrySet();
            		for (Entry<String,Integer> e : i){
            			System.out.println(e.getKey() + ", " + e.getValue());
            		}
            	}
            	catch (Exception e){
            		System.out.println("Error. Please try again.");
            	}
            }
            else if (choice == 5){
            	try{
            		System.out.println(b.keySet());
            	}
            	catch (Exception e){
            		System.out.println("Error. Please try again.");
            	}
            }
            else if (choice == 6){
            	try{
            		System.out.println(b.values());
            	}
            	catch (Exception e){
            		System.out.println("Error. Please try again.");
            	}
            }
            else{
            	System.out.println("");
            }
        } while (choice != 7);
    }
}