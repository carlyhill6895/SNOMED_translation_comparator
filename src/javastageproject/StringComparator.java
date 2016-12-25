
package javastageproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * This class implements the functionality of comparing a string. 
 * @author Carly Hill 10523162
 */
public class StringComparator {
    //fields
    private final File file;
    private File equals;
    private File differents;
    
    // constants
    private static final int MAX_LENGTH_DIFFERENCE = 2;
    private static final int MAX_LETTER_DIFFERENCE = 4;
    private static final int BEGIN_POINT = 1;
    /**
     * constructor 
     * @param theFile file that has to be checked
     */
    public StringComparator(File theFile){
        file = theFile;
    }
    
    /**
     * Generates equals and differents by using compare method
     * @param factor the threshold for boolean to be false
     * @param placeGoogle place of column google translation
     * @param placeUmls place of column UMLS translation
     */
    public void compareFiles(int factor, int placeGoogle, int placeUmls){
        
        //get words from file
        ArrayList<String[]> words = file.getWords();
        ArrayList<String> rules = file.getRules();
        
        //get arraylists to fill for making files
        ArrayList<String> rulesEqual = new ArrayList<>();
        ArrayList<String[]> wordsEqual = new ArrayList<>();
        ArrayList<String> rulesDifferent = new ArrayList<>();
        ArrayList<String[]> wordsDifferent = new ArrayList<>();
        
        //add attributes  to new file
        rulesEqual.add(rules.get(0));
        rulesDifferent.add(rules.get(0));
        wordsEqual.add(words.get(0));
        wordsDifferent.add(words.get(0));
        
        //start at 1 because first line are the attributes. put them in equal if they are considered to be equal, 
        //else put them in different
        for(int i = BEGIN_POINT; i<words.size(); i++){
            if(compare(words.get(i)[placeGoogle], words.get(i)[placeUmls], factor)){
                rulesEqual.add(rules.get(i));
                wordsEqual.add(words.get(i));
            }
            else{
                rulesDifferent.add(rules.get(i));
                wordsDifferent.add(words.get(i));
            }
                
        }
        
        //making the files
        equals = new File("equalTranslations.txt", rulesEqual, wordsEqual);
        differents = new File("differentTranslations.txt", rulesDifferent, wordsDifferent);
        
    }
    
    /**
     * Method to compare two strings
     * @param first first string for comparison
     * @param second second string for comparison
     * @param factor threshold for boolean to be false
     * @return true if they are to be considered the same, false if not. 
     */
    public static boolean compare(String first, String second, int factor){
        
        //instantiate needed variables
        int comparison = 0;
        String[] firstWords = getWords(first);
        String[] secondWords = getWords(second);
        int lengthDifference = Math.abs(first.length() - second.length());
        
        //comparing length and amount of words
        if(lengthDifference >MAX_LENGTH_DIFFERENCE){
            comparison+= lengthDifference/MAX_LENGTH_DIFFERENCE;
        }
        int wordDifference = Math.abs(firstWords.length - secondWords.length);
        if(wordDifference>0){
            comparison+= wordDifference;
        }
        
        //compare words
        comparison+=compareWords(firstWords, secondWords);
        //compare letters
        comparison += compareLetters(firstWords, secondWords);
        
        return comparison<factor;
    }
    
    /**
     * compares the letters in the two terms
     * @param firstWords array of words from term
     * @param secondWords array of words from term
     * @return the amount of different letters/normalization factor
     */
    public static int compareLetters(String[] firstWords, String[] secondWords){
        //making maps for counting letters
        int comparison = 0;
        HashMap<Character, Integer> aantalLetters1 = new HashMap<>();
        HashMap<Character, Integer> aantalLetters2 = new HashMap<>();
        
        //count letters firstWords
        for (String firstWord : firstWords) {
            char[] word = firstWord.toCharArray();
            for(char letter: word){
                if(!aantalLetters1.containsKey(letter)){
                    aantalLetters1.put(letter, 1);
                }
                else{
                    aantalLetters1.put(letter, aantalLetters1.get(letter)+1);
                }
            }
        }
        //count letters secondWords
        for (String secondWord : secondWords) {
            char[] word = secondWord.toCharArray();
            for(char letter: word){
                if(!aantalLetters2.containsKey(letter)){
                    aantalLetters2.put(letter, 1);
                }
                else{
                    aantalLetters2.put(letter, aantalLetters2.get(letter)+1);
                }
            }
        }
        
        //compare the counted letters
        Set<Character> firstLetters = aantalLetters1.keySet();
        for(char letter: firstLetters){
            if(aantalLetters2.containsKey(letter)){
                int difference = Math.abs(aantalLetters1.get(letter)-aantalLetters2.get(letter));
                if(difference>0){
                    comparison+=difference;
                }
            }
            else{
                comparison++;
            }
        }
        
        //add 1 to comparison if letters from secondletters are not in firstletters.
        Set<Character> secondLetters = aantalLetters2.keySet();
        for(char letter: secondLetters){
            if(!firstLetters.contains(letter)){
                comparison++;
            }
        }
        return comparison/MAX_LETTER_DIFFERENCE;
    }
    
    /**
     * getter for the equals file: Make sure compareFiles has been done first!
     * @return  equals file
     */
    public File getEquals(){
        return equals;
    }
    
    /**
     * getter for the difference file: Make sure compareFiles has been done first!
     * @return differents file
     */
    public File getDifferents(){
        return differents;
    }
    
    private static int compareWords(String[] firstWords, String[] secondWords){
        int comparison = 0;
        int amount = Math.min(firstWords.length, secondWords.length);
        
        //compare words
        for(int i = 0; i<amount; i++){
            if(!firstWords[i].equalsIgnoreCase(secondWords[i])){
                comparison++;
            }
        }
        return comparison;    
    }
    
    /**
     * get the single words from a string
     * @param text string to divide
     * @return string [] with different words.
     */
    public static String[] getWords (String text){
        //remove interpunction
        text = text.replaceAll("[,.;]", "");
        //remove big whitespace
        //text = text.replaceAll("\\s+", " ");
        //split string on space
        String[] words = text.split("\\s+");
        return words;
    }
}
