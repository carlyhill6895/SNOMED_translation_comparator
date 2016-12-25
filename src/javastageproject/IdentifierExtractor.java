
package javastageproject;

import java.util.ArrayList;

/**
 * Class for extracting SNOMED procedures from UMLS file
 * @author Carly Hill 10523162
 */
public class IdentifierExtractor {
    
    private final File codes;
    private final File umls;
    
    /**
     * constructor
     * @param extractionCodes file containing extractioncodes
     * @param umlsCodes file containing UMLS codes.
     */
    public IdentifierExtractor(File extractionCodes, File umlsCodes){
        codes = extractionCodes;
        umls = umlsCodes;
    }
    
    /**
     * method for extracting codes from file.
     * @param positionCode The position(column, counting from 0) of the extraction code
     * @param positionUmls Position of the SNOMED code(column, counting from 0)
     * @return file with UMLS findings for SNOMED procedures
     */
    public File extract(int positionCode, int positionUmls){
        File extraction;
        
        //getting needed words and rules for extraction
        ArrayList<String[]> codeWords = codes.getWords();
        ArrayList<String[]> umlsWords = umls.getWords();
        ArrayList<String> umlsRules = umls.getRules();
        
        //making new arraylists for the new file
        ArrayList<String[]> extractionWords = new ArrayList<>();
        ArrayList<String> extractionRules = new ArrayList<>();
        System.out.println("beginnen met zoeken...");
        for(int i = 1; i<codeWords.size() ;i++){
            //get code from codewords
            String code = codeWords.get(i)[positionCode];
            //find code and add to extraction
            for(int j = 1; j<umlsWords.size();j++){
                String umlsCode = umlsWords.get(j)[positionUmls];
                if(code.equals(umlsCode)){
                    extractionWords.add(umlsWords.get(j));
                    extractionRules.add(umlsRules.get(j));
                    break;
                }
                
            }
        }
        //make file
        extraction = new File("proceduresExtraction.txt", extractionRules, extractionWords);
        return extraction;
    }
}
