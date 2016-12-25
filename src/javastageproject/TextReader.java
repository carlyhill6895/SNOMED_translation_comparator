
package javastageproject;
import java.io.*;
import java.util.ArrayList;
/**
 * Class for reading lines from a txt file.
 * @author Carly Hill, 10523162
 */
public class TextReader {

    /**
     * Method to read a file into an arraylist.
     * @param fileName file to be read
     * @return the lines in an arrayList
     */
    public static ArrayList<String> readLines(String fileName){
        //list for the text
        ArrayList<String> text = new ArrayList<>();
        //try reading the file
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while(line != null){
                text.add(line);
                line = reader.readLine();
            }
        } 
        //catch exceptions: return null if exception was caught
        catch (FileNotFoundException ex) {
            System.err.print("File wasn't found....");
            return null;
        } 
        catch (IOException ex) {
            System.err.print("File could not be read....");
            return null;
        }
        return text;
    }
    /**
     * Reads file from directory
     * @param fileName the name of the file (best encoded in UTF-8)
     * @param splitter the string part used to split collumns like a , or ;
     * @return a list with all the lines of the file.
     */
    public static File readFile(String fileName, String splitter){
        //list for the text
        ArrayList<String> text = new ArrayList<>();
        //try reading the file
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while(line != null){
                text.add(line);
                line = reader.readLine();
            }
        } 
        //catch exceptions
        catch (FileNotFoundException ex) {
            System.err.print("File wasn't found....");
            return null;
        } 
        catch (IOException ex) {
            System.err.print("File could not be read....");
            return null;
        }
        
        //make and fille arraylist with the different collumns
        ArrayList<String[]> words = new ArrayList<>();
        for(String rule: text){
            words.add(splitString(rule, splitter));
        }
        //make the file to return
        File bestand = new File(fileName, text, words);
        return bestand;
    }
    
    private static String[] splitString(String toSplit, String splitter){
        //spliting string
        String[] splitted = null;
        if(toSplit.contains(splitter)){
            toSplit = toSplit.trim();
            splitted = toSplit.split(splitter);
        }
        //trim splitted string
        if(splitted != null){
            for(int i = 0; i<splitted.length; i++){
                splitted[i] = splitted[i].trim();
            }
        }
        return splitted;
    }
}
