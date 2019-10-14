package acp.p2;

/**
 *
 * @author Christian
 */
public class SpellChecker {
    public String workingDir = System.getProperty("user.dir");//stores uses working director
    
    String dictonaryFileName = "Words.txt";
    //all of our letters we will be sending everything to lowercase
    char[] letters = "abcdefghijklmnopqrstuvwxyz".toCharArray();
}
