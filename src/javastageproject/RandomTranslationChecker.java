
package javastageproject;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 * class for checking translations, and dividing them in good or bad translations
 * @author Carly Hill 10523162
 */
public class RandomTranslationChecker {
    
    /**
     * method for checking translations
     * @param translations the translations to be checked
     * @param randomSize size of the random sample we want to take
     * @param placeTranslation place of the translation in the file.
     */
    public static void checkRandomTranslations(File translations, int randomSize, int placeTranslation){
        
        //get translations
        ArrayList<String> rules = translations.getRules();
        ArrayList<String[]> words = translations.getWords();
        
        //make lists for random sample
        ArrayList<String> linesRandom = new ArrayList<>();
        ArrayList<String[]> wordsRandom = new ArrayList<>();
        
        //make lists for files with good and bad translations
        ArrayList<String> linesGood = new ArrayList<>();
        ArrayList<String[]> wordsGood = new ArrayList<>();
        
        ArrayList<String> linesBad = new ArrayList<>();
        ArrayList<String[]> wordsBad = new ArrayList<>();
        
        Random rand = new Random();
        
        int size = rules.size();
        
        //make random lists
        for(int i = 0; i < randomSize; i++){
            int place = rand.nextInt(size);
            linesRandom.add(rules.get(place));
            wordsRandom.add(words.get(place));
            size--;
            //make sure the chosen rule can't be chosen again
            rules.remove(place);
            words.remove(place);
        }
        
        //go over random list and ask if translation is good or not
        for(int i = 0; i < linesRandom.size(); i++){
            String dutchTerm = wordsRandom.get(i)[placeTranslation];
            String englishTerm = wordsRandom.get(i)[1];
            String message = "Is this : \n" + dutchTerm +  "\n a good translation for: \n" + englishTerm;
            int chosen = JOptionPane.showConfirmDialog(null, message, "Please rate this translation",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(chosen == JOptionPane.YES_OPTION){
                //if translation is good: add translation to the good translations file
                linesGood.add(linesRandom.get(i));
                wordsGood.add(wordsRandom.get(i));
            }
            else{
                //otherwise add to the bad translations file
                linesBad.add(linesRandom.get(i));
                wordsBad.add(wordsRandom.get(i));
            }
        }
        
        //make good and bad translations files
        File goodones = new File("goodTranslations.txt", linesGood, wordsGood);
        File badones = new File("badTranslations.txt", linesBad, wordsBad);
        
        FileMaker.makeFile(badones);
        FileMaker.makeFile(goodones);
    }
    
    public static String[] getRandomLines(File translations, int randomSize){
        String[] randomLines = new String[randomSize];
        
        ArrayList<String> rules = translations.getRules();
        Random rand = new Random();
        
        int size = rules.size();
        
        //make random lists
        for(int i = 0; i < randomSize; i++){
            int place = rand.nextInt(size);
            randomLines[i] = rules.get(place);
            size--;
            //make sure the chosen rule can't be chosen again
            rules.remove(place);
        }
        return randomLines;
    }
    
    public static File getgoodRandomFile(File twoEquals, File allEqual){
        ArrayList<String> twoEqualsLines = twoEquals.getRules();
        ArrayList<String[]> twoEqualswords = twoEquals.getWords();
        
        ArrayList<String[]> allEqualWords = allEqual.getWords();
        for(int i = 0; i<allEqualWords.size(); i++){
            String code = allEqualWords.get(i)[0];
            for(int j = 0; j<twoEqualswords.size();j++){
                String code2 = twoEqualswords.get(j)[0];
                if(code2.equals(code)){
                    System.out.println("removed...");
                    twoEqualswords.remove(j);
                    twoEqualsLines.remove(j);
                }
            }
        }
        
        
        
        File goodTwoEquals = new File("tweeGelijk.txt", twoEqualsLines, twoEqualswords);
       
        return goodTwoEquals;
    }
}
