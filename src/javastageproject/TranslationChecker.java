
package javastageproject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Class for checking translations against a given list of words.
 * @author Carly Hill 10523162
 */
public class TranslationChecker {
    //fields
    private Set<String> words;
    private File goodTranslations;
    private File badTranslations;
    /**
     * constructor for translationchecker
     * @param woorden file with checking words.
     */
    public TranslationChecker(File woorden){
        ArrayList<String> rules = woorden.getRules();
        words = new HashSet<>();
        //read words from rule, and add new words to the set. 
        for (String rule: rules){
            String[] ruleWords = StringComparator.getWords(rule);
            for(String ruleWord: ruleWords){
                words.add(ruleWord);
            }
        }
        System.out.println("lees woordenlijst");
    }
    
    /**
     * method that checks translations
     * @param translations to be checked
     * @param placeTranslation place of translation that needs to be checked
     */
    public void checkTranslations(File translations, int placeTranslation){
        
        //create arraylists for new files
        ArrayList<String> goodTranslationrules = new ArrayList<>();
        ArrayList<String> badTranslationrules = new ArrayList<>();
        ArrayList<String[]> goodTranslationwords = new ArrayList<>();
        ArrayList<String[]> badTranslationwords = new ArrayList<>();
        
        //get arraylists from given translation file
        ArrayList<String> uncheckedRules = translations.getRules();
        ArrayList<String[]> uncheckedWords = translations.getWords();
        
        //get first rule(descriptions), and put them in both new files
        
        goodTranslationrules.add(uncheckedRules.get(0));
        badTranslationrules.add(uncheckedRules.get(0));
        goodTranslationwords.add(uncheckedWords.get(0));
        badTranslationwords.add(uncheckedWords.get(0));
        
        for(int i = 1; i<uncheckedWords.size() ;i++){
            int found = 0;
            int j = 0;
            //get words
            String[] wordsToCheck = StringComparator.getWords(uncheckedWords.get(i)[placeTranslation]);
            //check if all words are in given checkwords
            while(j < wordsToCheck.length){
                String wordToCheck = wordsToCheck[j];
                for(String word: words){
                    if(wordToCheck.equalsIgnoreCase(word)){
                        found++;
                    }
                }
                j++;
            }
            boolean good = (found > wordsToCheck.length/2);
            if(good){
                goodTranslationrules.add(uncheckedRules.get(i));
                goodTranslationwords.add(uncheckedWords.get(i));
                System.out.println("regel " + i + " toegevoegd aan goede vertalingen.");
            }
            else{
                badTranslationrules.add(uncheckedRules.get(i));
                badTranslationwords.add(uncheckedWords.get(i));
            }
           
        }
        
        goodTranslations = new File("goodTranslation.txt", goodTranslationrules, goodTranslationwords);
        badTranslations = new File("badTranslations.txt", badTranslationrules, badTranslationwords);
        
    }
    
    /**
     * get the good translations
     * @return goodtranslations
     */
    public File getGoodTranslations(){
        return goodTranslations;
    }
    
    /**
     * get the bad translations
     * @return badtranslations
     */
    public File getBadTranslations(){
        return badTranslations;
    }
}
