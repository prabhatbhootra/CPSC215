/** Class for doing encryption and decryption using the Caesar Cipher. 
  * @author Prabhat Bhootra
  */
public class CaesarCipher {

  protected char[] encoder = new char[26];    // Encryption array
  protected char[] decoder = new char[26];    // Decryption array

  /** Constructor that initializes the encryption and decryption arrays 
    * @param rotation the integer by which the positions of the array should be shifted
    */
  public CaesarCipher(int rotation) {
    for (int k=0; k < 26; k++) {
      encoder[k] = (char) ('A' + (k + rotation) % 26);
      decoder[k] = (char) ('a' + (k - rotation + 26) % 26);
    }
  }

  /** Returns String representing encrypted message. 
    * @param plaintext the string to be encrypted
    * @return String(msg) the encrypted message 
    */
  public String encrypt(String plaintext) {
    return transform(plaintext, encoder);       // use encoder array
  }

  /** Returns decrypted message given encrypted secret. 
    * @param ciphertext the string to be decrypted
    * @return String(msg) the decrypted message
    */
  public String decrypt(String ciphertext) {
    return transform(ciphertext, decoder);        // use decoder array
  }

  /** Returns transformation of original String using given code. 
    * @param original the string to be encrypted or decrypted
    * @param code the array that the string has to be transformed with
    * @return String(msg) the encrypted or decrypted message
    */

  private String transform(String original, char[] code) {
    char[] msg = original.toCharArray();
    for (int k=0; k < msg.length; k++)
      if (Character.isUpperCase(msg[k])) {    // we have a letter to change
        int j = msg[k] - 'A' + 3;                 // will be value from 0 to 25
        msg[k] = code[j];                     // replace the character
      }
    for (int k=0; k < msg.length; k++)
      if (Character.isLowerCase(msg[k])) {    // we have a letter to change
        int j = msg[k] - 'a';                 // will be value from 0 to 25
        msg[k] = code[j];                     // replace the character
      }
    return new String(msg);
  }

  /** Simple main method for testing the Caesar cipher */
  public static void main(String[] args) {
    CaesarCipher cipher = new CaesarCipher(3);
    System.out.println("Encryption code = " + new String(cipher.encoder));
    System.out.println("Decryption code = " + new String(cipher.decoder));
    String message = "the eagle is in play; meet at joe's.";
    String coded = cipher.encrypt(message);
    System.out.println("Secret:  " + coded);
    String answer = cipher.decrypt(coded);
    System.out.println("Message: " + answer); // should be plaintext again
  }
}
