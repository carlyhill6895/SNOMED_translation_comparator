
package javastageproject;

import java.util.ArrayList;

/**
 * represents a file
 * @author Carly Hill 10523162
 */
public class File {
    
    //fields
    private final ArrayList<String> rules;
    private final ArrayList<String[]> words;
    private String fileName;
    
    /**
     * constructor
     * @param name filename
     * @param theRules the rules of the file
     * @param theWords the words of the file
     */
    public File(String name, ArrayList<String> theRules, ArrayList<String[]> theWords){
        rules = theRules;
        words = theWords;
        fileName = name;
    }
    
    /**
     * getter for filename
     * @return filename
     */
    public String getName(){
        return fileName;
    }
    
    /**
     * getter for rules
     * @return the rules
     */
    public ArrayList<String> getRules(){
        return rules;
    }
    
    /**
     * getter for words
     * @return the words
     */
    public ArrayList<String[]> getWords(){
        return words;
    }
    
    /**
     * getter for the attributes
     * @return the first splitted rule
     */
    public String[] getAttributes(){
        return words.get(0);
    }
    
    /**
     * gives the size of the file
     * @return the size of the file (number of rules)
     */
    public int getSize(){
        return rules.size();
    }
    
    /**
     * gives the file a new name
     * @param newName the new name for the file
     */
    public void setName(String newName){
        fileName = newName;
    }
    
    
}


