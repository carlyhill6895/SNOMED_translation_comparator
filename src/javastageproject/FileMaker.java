
package javastageproject;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Class that makes a txt(UTF-8 encoded) file in the folder of this application
 * @author Carly Hill 10523162
 */
public class FileMaker {

    /**
     * makes a real file from the given file
     * @param theFile input file
     */
    public static void makeFile(File theFile){
        ArrayList<String> rules = theFile.getRules();
        //try printing the file
        try( BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(theFile.getName()), "UTF-8"))){
            for(String rule: rules){
                out.write(rule);
                out.newLine();
            }
            
        } 
        //catch exception
        catch (IOException ex) {
            Logger.getLogger(FileMaker.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("There was an error printing the file....");
        }
    }
    
    /**
     * make txt file from array
     * @param rules Sting array to be printed in file
     * @param name filename
     */
    public static void makeTxt(String[] rules, String name){
        //try printing the file
        try( BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(name), "UTF-8"))){
            for(String rule: rules){
                out.write(rule);
                out.newLine();
            }
            
        } 
        //catch exception
        catch (IOException ex) {
            Logger.getLogger(FileMaker.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("There was an error printing the file....");
        }
    }
}
