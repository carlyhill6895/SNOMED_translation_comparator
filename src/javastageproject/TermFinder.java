
package javastageproject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class for finding best(at this moment defined as closest) translation of English term.
 * @author Carly Hill 10523162
 */
public class TermFinder {
    //fields
    private static final int BEGIN_REGELS = 1;
    private final File umls;
    private final File snomed;
    
    /**
     * constructor
     * @param umlsnl dutch umls terms
     * @param snomedconcepts english terms
     */
    public TermFinder(File umlsnl, File snomedconcepts){
        umls = umlsnl;
        snomed = snomedconcepts;
    }
    
    /**
     * finds closest term for the same code
     * @param pCodeUmls place code in umls file
     * @param pCodeSnomed place code in snomed file
     * @param pTermnl place dutch term
     * @param pConcept place english term
     * @return file with the found terms
     */
    public File findTerms(int pCodeUmls, int pCodeSnomed, int pTermnl, int pConcept){
        File translationUmls;
        
        //get needed information from umls and snomed.
        ArrayList<String[]> snomedWords = snomed.getWords();
        ArrayList<String[]> umlsWords = umls.getWords();
        ArrayList<String> umlsRules = umls.getRules();
        
        //make new arraylists for new file
        ArrayList<String[]> translationWords = new ArrayList<>();
        ArrayList<String> translationRules = new ArrayList<>();
        
        //use stringcomparator to get best translation and put this in translation
        for(int i = BEGIN_REGELS; i<snomedWords.size(); i++){
            String conceptCode = snomedWords.get(i)[pCodeSnomed];
            String[] conceptWords = StringComparator.getWords(snomedWords.get(i)[pConcept]);
            HashMap<Integer, Integer>  conceptPlaces = new HashMap<>();
            
            //get all translations for conceptcode
            for(int j = BEGIN_REGELS; j<umlsRules.size();j++){
                String rule = umlsRules.get(j);
                String[] words = umlsWords.get(j);
                if(rule.contains(conceptCode)){
                    String[] translationdivided = StringComparator.getWords(words[pTermnl]);
                    int comparison = StringComparator.compareLetters(translationdivided, conceptWords);
                    conceptPlaces.put(j,comparison);
                }
            }
            //choose best translation, with lowest value
            int chosen = Integer.MAX_VALUE;
            if(conceptPlaces.size()>0){
                for (HashMap.Entry pair : conceptPlaces.entrySet()) {
                    if((int)pair.getValue()<=chosen){
                        chosen = (int)pair.getKey();
                    }
                }
                translationWords.add(umlsWords.get(chosen));
                translationRules.add(umlsRules.get(chosen));
            }
            
        }
        
        //create file
        translationUmls = new File("googleVertalingBe.txt", translationRules, translationWords);
        return translationUmls;
    }
    
    /**
     * finds rules that contain a given term
     * @param term the term to be searched for
     * @param searchFile the file in which should be searched
     * @return an arraylist with numbers of the lines that contain the term
     */
    public static ArrayList<Integer> findRulesWithTerm(String term, ArrayList<String> searchFile){
        ArrayList<Integer> ruleNumbers = new ArrayList<>();
        for(int i = 0; i<searchFile.size();i++){
            if(searchFile.get(i).contains(term)){
                ruleNumbers.add(i);
            }
        }
        return ruleNumbers;
    }
}
