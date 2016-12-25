package javastageproject;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Class that gives functionality for removing untranslated terms from files
 *
 * @author Carly Hill 10523162
 */
public class WrongTranslationRemover {

    //fields
    private ArrayList<String> dutchRules;
    private ArrayList<String[]> dutchTerms;
    private ArrayList<String> englishRules;
    private ArrayList<String[]> englishTerms;

    /**
     * constructor
     * @param translation the translation file that has to be checked
     */
    public WrongTranslationRemover(File translation) {
        dutchRules = translation.getRules();
        dutchTerms = translation.getWords();
        englishRules = new ArrayList<>();
        englishTerms = new ArrayList<>();
    }

    /**
     * Removes translations with given suffix
     * @param searchPlace place in translation to search for suffix
     * @param suffix the suffix to search for
     * @return the amount of found translations
     */
    public int removeSuffix(int searchPlace, String suffix) {
        int found = 0;
        
        //chech for every translation on searhPlace if it contains a word with given suffix
        for (int i = 0; i < dutchTerms.size(); i++) {
            String[] dutchTerm = dutchTerms.get(i);
            String[] termWords = StringComparator.getWords(dutchTerm[searchPlace]);
            for (String term : termWords) {
                if (term.endsWith(suffix)) {
                    englishTerms.add(dutchTerm);
                    englishRules.add(dutchRules.get(i));
                    dutchRules.remove(i);
                    dutchTerms.remove(i);
                    found++;
                    i--;
                    break;
                }
            }

        }
        return found;
    }

    /**
     * Method that searches for 'untranslated' words in translation and asks user if they should be deleted
     * @param termPlace place of the term that was translated( English Snomed Term)
     * @param searchPlace place of the translation
     * @param searchFrom i to begin with
     * @return the amount of deleted translations.
     */
    public int removeTranslations(int termPlace, int searchPlace, int searchFrom) {
        int found = 0;
        boolean stop = false;
        //check if term contains word of translation
        for (int i = searchFrom; i < dutchTerms.size(); i++) {
            String dutchTerm = dutchTerms.get(i)[searchPlace];
            String englishTerm = dutchTerms.get(i)[termPlace];
            String[] dutchWords = StringComparator.getWords(dutchTerm);
            for (String word : dutchWords) {
                if (englishTerm.contains(word)) {
                    //if english term contains word: ask user if the translation is wrong -> then delete translation
                    String message = "Is this a good translation? \n" + dutchTerm;
                    int chosen = JOptionPane.showConfirmDialog(null, message, "Please rate this translation",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    //only deleting after confirmation, otherwise mistakes are easily made
                    if (chosen == JOptionPane.YES_OPTION) {
                        int chosen2 = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this translation?\n" + dutchTerm, "Please confirm",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (chosen2 == JOptionPane.YES_OPTION) {
                            englishTerms.add(dutchTerms.get(i));
                            englishRules.add(dutchRules.get(i));
                            dutchRules.remove(i);
                            dutchTerms.remove(i);
                            found++;
                        }
                    }
                    else if(chosen == JOptionPane.CANCEL_OPTION || chosen == JOptionPane.CLOSED_OPTION){
                        stop = true;
                    }
                    break;
                }
            }
            if(stop){
                JOptionPane.showMessageDialog(null, "You have stopped at i=" + i, "stopped",
                                JOptionPane.INFORMATION_MESSAGE);
                break;
            }
        }
        return found;
    }

    /**
     * returns file with good translations
     * @return good dutch translations
     */
    public File getDutchTranslations() {
        File dutchTranslations = new File("dutchtranslations.txt", dutchRules, dutchTerms);
        return dutchTranslations;
    }

    /**
     * Returns file with wrong translations
     * @return English translations
     */
    public File getWrongTranslations() {
        File wrongTranslations = new File("enlishtranslations.txt", englishRules, englishTerms);
        return wrongTranslations;
    }
}
