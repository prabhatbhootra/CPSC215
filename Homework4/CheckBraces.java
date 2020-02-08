/**
 * This program reads a text file specified in a command-line argument 
 * into a string named inString.  It also has a code segment to echo 
 * print inString.  To execute, assuming an input file is named filename 
 * and exists in the same directory as CheckBraces.class, type
 *
 *   java CheckBraces filename
 * 
 *@author Fumihiro Tamada, Prabhat Bhootra
 *@version 1.1, 03/03/2018
 *@see java.io.InputStreamReader
 */

import java.io.*;

public class CheckBraces {
  
  public static void main(String args[]) {
    String inString = null;
    if (args.length < 1) {
      System.out.println("Usage: java CheckBraces sourcefile");
      return;
    }

    // Read the file named as the command-line argument
    try {
      File f = new File(args[0]);
      InputStreamReader inStream = 
        new InputStreamReader(new FileInputStream(f));
      int length = (int) f.length();
      char input[] = new char[length];
      inStream.read(input);
      inString = new String(input);
    } 
    catch (FileNotFoundException e) {
      System.err.println("Error: File " + args[0] + " not found");
      e.printStackTrace();
    }
    catch (IOException e) {
      System.err.println("Error: I/O exception");
      e.printStackTrace();
    }
 
    // Echo print the file
    for (int k = 0; k < inString.length(); k++)
      System.out.print(inString.charAt(k));
    System.out.println();
    
    //Check the java source file to see if all the opening and closing braces match 
    DequeStack<Character> S = new DequeStack<Character>();
    for (int i = 0;i < inString.length();i++){
      if((inString.charAt(i) == '(') || (inString.charAt(i) == '{') || (inString.charAt(i) == '[')) 
         S.push(inString.charAt(i));
      if((inString.charAt(i) == ')') || (inString.charAt(i) == '}') || (inString.charAt(i) == ']')){ 
         if(S.isEmpty()){//no opening bracket
            System.out.println("Unmatched brace at character"+Integer.toString(i)+  " No opening brace for " +Character.toString(inString.charAt(i)));
            System.exit(1);
          }
          //No corresoponding bracket
          else if( (inString.charAt(i)==')') && (S.top() == '[') ){
             System.out.println("Unmatched brace at character" + Integer.toString(i) + ": Found "+ inString.charAt(i) + " expecting "+ Character.toString(']'));
             System.exit(1);
          }
          else if( (inString.charAt(i)==')') && (S.top() == '{') ){
             System.out.println("Unmatched brace at character" + Integer.toString(i) + ": Found "+ inString.charAt(i) + " expecting "+ Character.toString('}'));
             System.exit(1);
          }
          else if((inString.charAt(i)==']') && (S.top() == '{')){
             System.out.println("Unmatched brace at character" + Integer.toString(i) + ": Found "+ inString.charAt(i) + " expecting "+ Character.toString('}'));
             System.exit(1);
          }
          else if((inString.charAt(i)==']') && (S.top() == '(')){
             System.out.println("Unmatched brace at character" + Integer.toString(i) + ": Found "+ inString.charAt(i) + " expecting "+ Character.toString(')'));
             System.exit(1);
          }
          else if((inString.charAt(i)=='}')&&(S.top() == '[')){
             System.out.println("Unmatched brace at character" + Integer.toString(i) + ": Found "+ inString.charAt(i) + " expecting "+ Character.toString(']'));
             System.exit(1);
          }
          else if((inString.charAt(i)=='}')&&(S.top() == '(')){
             System.out.println("Unmatched brace at character" + Integer.toString(i) + ": Found "+ inString.charAt(i) + " expecting "+ Character.toString(')'));
             System.exit(1);
          }
          //if the bracket match, pop the current bracket
          else 
            S.pop();
         } 
         
     }
     if(!S.isEmpty()){
        System.out.println("Closing bracket not found");
        return;
     }
     else{
        System.out.println("No missing brackets");   
     }
  }

}
