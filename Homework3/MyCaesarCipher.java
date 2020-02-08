/*Class for creating a cipher with a keyword and a position
 * @author Prabhat Bhootra
 */
public class MyCaesarCipher extends CaesarCipher {
  public int position;               // the position at which the keyword has to be placed
  /* Constructor to create default MyCaesarCipher */
  public MyCaesarCipher(){
      this("", 24);
  }
  /*Constructor to create a cipher with given keyword and position
   * @param s the string to be placed in the encoder
   * @param n the position at which the string should be placed in the encoder
   */
  public MyCaesarCipher(String s, int n) throws KeywordException{
      super(26-n);
      boolean InvalidInt = false;
      boolean keywordLong = false;
      boolean NotUpperCaseOrDistinct = true;
      for(int a = 0; a < s.length(); a++){
          for(int b = a+1; b < s.length(); b++){
              if(Character.isLowerCase(s.charAt(b)) || s.charAt(a) == s.charAt(b)){
                  NotUpperCaseOrDistinct = false;
              }
          }
      }
      if (NotUpperCaseOrDistinct == false){
          throw new KeywordException("Please use uppercase and distinct keyword.");
      }
      if(s.length() > 26){
          keywordLong = true;
      }
      if (keywordLong == true){
          throw new KeywordException("Please make sure keyword is shorter than or equal to 26 characters");
      }  
      if (n < 1 || n > 26){
          InvalidInt = true;
      }
      if (InvalidInt == true){
          throw new IntegerKeyException("Please make sure the key is greater than or equal to one and less than or equal to 26");
      }
      position = n;     
      int j = n-1;
      for(int i = 0; i < s.length(); i++){
          encoder[j] = s.charAt(i);
          j = j+1;
      }
      boolean StringContains = false;
      char c = 'A';
      while(j < 26 || (j%25) <= n-1){
          if (j < 26){
            for(int i = 0; i < s.length(); i++){
                if((char) c == (char) s.charAt(i)){
                    StringContains = true;                 
                }
            }
            if (StringContains == false){
                encoder[j] = c;
                j = j + 1;
                c = (char)((int)c + 1);
            }
            else{
                c = (char)((int)c + 1);
          }
          }
          else{
            if (j > 25){
                encoder[(j%25)-1] = c;
                j = j + 1;
                c = (char)((int)c + 1);
          }
          }
          StringContains = false;
      }
      int var=0;
      char d = 'a';
      for(int i = 0; i < 26; i++){
        for(int t = 0; t < 26; t++){
          if(encoder[t] == 'A'+i){  
            decoder[i] = (char)((int)'a' + t);
            var = t;
            break;
          }
          var++;
          continue;
        }
      } 
  }
  /** Returns String representing encrypted message. 
    * @param plaintext the string to be encrypted
    * @return String(msg) the encrypted message 
    */
  public String myencrypt(String plaintext) {
    return mytransform(plaintext, encoder);       // use encoder array
  }
  /** Returns decrypted message given encrypted secret. 
    * @param ciphertext the string to be decrypted
    * @return String(msg) the decrypted message
    */
  public String mydecrypt(String ciphertext) {
    return mytransform(ciphertext, decoder);        // use decoder array
  }
  /** Returns transformation of original String using given code. 
    * @param original the string to be encrypted or decrypted
    * @param code the array that the string has to be transformed with
    * @return String(msg) the encrypted or decrypted message
    */
  private String mytransform(String original, char[] code) {
    char[] msg = original.toCharArray();
    for (int k=0; k < msg.length; k++){
      if (Character.isUpperCase(msg[k])) {    // we have a letter to change
        int j = msg[k] - 'A';                 // will be value from 0 to 25
        msg[k] = code[j];   
                          // replace the character
      }
      else if (Character.isLowerCase(msg[k])) {    // we have a letter to change
        int j = msg[k] - 'a';                 // will be value from 0 to 25
        msg[k] = code[j];                     // replace the character
      }
     }

 
    return new String(msg);
  }
  /** Simple main method for testing the Caesar cipher */
  public static void main(String[] args) {
    MyCaesarCipher cipher1 = new MyCaesarCipher("BLUE", 5);
    System.out.println(new String(cipher1.encoder));
    System.out.println("Encryption code = " + new String(cipher1.encoder));
    System.out.println("Decryption code = " + new String(cipher1.decoder));
    String message = "the eagle is in play; meet at joe's.";
    String coded = cipher1.myencrypt(message);
    System.out.println("Secret:  " + coded);
    String answer = cipher1.mydecrypt(coded);
    System.out.println("Message: " + answer);
    
    MyCaesarCipher cipher2 = new MyCaesarCipher("SPACE", 3);
    System.out.println(new String(cipher1.encoder));
    System.out.println("Encryption code = " + new String(cipher2.encoder));
    System.out.println("Decryption code = " + new String(cipher2.decoder));
    String message1 = "the eagle is in play; meet at joe's.";
    String coded1 = cipher2.myencrypt(message1);
    System.out.println("Secret:  " + coded1);
    String answer1 = cipher2.mydecrypt(coded1);
    System.out.println("Message: " + answer1);
    
    MyCaesarCipher cipher3 = new MyCaesarCipher("KEY", 7);
    System.out.println(new String(cipher3.encoder));
    System.out.println("Encryption code = " + new String(cipher3.encoder));
    System.out.println("Decryption code = " + new String(cipher3.decoder));
    String message2 = "the eagle is in play; meet at joe's.";
    String coded2 = cipher3.myencrypt(message2);
    System.out.println("Secret:  " + coded2);
    String answer2 = cipher3.mydecrypt(coded2);
    System.out.println("Message: " + answer2);
    
    MyCaesarCipher cipher4 = new MyCaesarCipher("LONG", 9);
    System.out.println(new String(cipher4.encoder));
    System.out.println("Encryption code = " + new String(cipher4.encoder));
    System.out.println("Decryption code = " + new String(cipher4.decoder));
    String message3 = "the eagle is in play; meet at joe's.";
    String coded3 = cipher4.myencrypt(message3);
    System.out.println("Secret:  " + coded3);
    String answer3 = cipher4.mydecrypt(coded3);
    System.out.println("Message: " + answer3);
    
    MyCaesarCipher cipher5 = new MyCaesarCipher("BLANKS", 6);
    System.out.println(new String(cipher5.encoder));
    System.out.println("Encryption code = " + new String(cipher5.encoder));
    System.out.println("Decryption code = " + new String(cipher5.decoder));
    String message4 = "the eagle is in play; meet at joe's.";
    String coded4 = cipher5.myencrypt(message4);
    System.out.println("Secret:  " + coded4);
    String answer4 = cipher5.mydecrypt(coded4);
    System.out.println("Message: " + answer4);
    
    // Invalid tests
   
    MyCaesarCipher cipher6 = new MyCaesarCipher("BLUE", 27);
    System.out.println(new String(cipher6.encoder));
    System.out.println("Encryption code = " + new String(cipher6.encoder));
    System.out.println("Decryption code = " + new String(cipher6.decoder));
    String message6 = "the eagle is in play; meet at joe's.";
    String coded6 = cipher6.myencrypt(message6);
    System.out.println("Secret:  " + coded6);
    String answer6 = cipher1.mydecrypt(coded6);
    System.out.println("Message: " + answer6);

    MyCaesarCipher cipher7 = new MyCaesarCipher("SPACE", -1);
    System.out.println(new String(cipher7.encoder));
    System.out.println("Encryption code = " + new String(cipher7.encoder));
    System.out.println("Decryption code = " + new String(cipher7.decoder));
    String message7 = "the eagle is in play; meet at joe's.";
    String coded7 = cipher7.myencrypt(message7);
    System.out.println("Secret:  " + coded7);
    String answer7 = cipher7.mydecrypt(coded7);
    System.out.println("Message: " + answer7);

    MyCaesarCipher cipher8 = new MyCaesarCipher("YELLOW", 7);
    System.out.println(new String(cipher8.encoder));
    System.out.println("Encryption code = " + new String(cipher8.encoder));
    System.out.println("Decryption code = " + new String(cipher8.decoder));
    String message8 = "the eagle is in play; meet at joe's.";
    String coded8 = cipher8.myencrypt(message8);
    System.out.println("Secret:  " + coded8);
    String answer8 = cipher8.mydecrypt(coded8);
    System.out.println("Message: " + answer8);

    MyCaesarCipher cipher9 = new MyCaesarCipher("ABCDEFGHIJKLMNOPQRSTUVWXYZ/[;", 9);
    System.out.println(new String(cipher9.encoder));
    System.out.println("Encryption code = " + new String(cipher9.encoder));
    System.out.println("Decryption code = " + new String(cipher9.decoder));
    String message9 = "the eagle is in play; meet at joe's.";
    String coded9 = cipher9.myencrypt(message9);
    System.out.println("Secret:  " + coded9);
    String answer9 = cipher9.mydecrypt(coded9);
    System.out.println("Message: " + answer9);
  
    MyCaesarCipher cipher10 = new MyCaesarCipher("Blanks", 6);
    System.out.println(new String(cipher10.encoder));
    System.out.println("Encryption code = " + new String(cipher10.encoder));
    System.out.println("Decryption code = " + new String(cipher10.decoder));
    String message10 = "the eagle is in play; meet at joe's.";
    String coded10 = cipher10.myencrypt(message10);
    System.out.println("Secret:  " + coded10);
    String answer10 = cipher10.mydecrypt(coded10);
    System.out.println("Message: " + answer10);
  }
}             
         
        
