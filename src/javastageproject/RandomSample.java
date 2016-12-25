
package javastageproject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

/**
 * class for getting a random sample of a given size
 * @author Carly Hill 10523162
 */
public class RandomSample {

    //fields
    private static int size;
    private static HashSet<Integer> randomset;
    private static int[] randomNumbers;
    
    /**
     * constructor
     * @param randomsize size of random file/array to be made
     */
    public RandomSample(int randomsize) {
        size = randomsize;
    }

    /**
     * generates a random(non-repeating) file from a given file
     * @param nonrandom given file
     * @param randomSize size of the new random file
     * @return the random file
     */
    public static File getRandomFile(File nonrandom, int randomSize){
        //initialize file and needed arraylists
        File randomFile;
        ArrayList<String> rulesNonRandom = nonrandom.getRules();
        ArrayList<String> rulesRandom = new ArrayList<>();
        ArrayList<String[]> wordsNonRandom = nonrandom.getWords();
        ArrayList<String[]> wordsRandom = new ArrayList<>();
        
        int fileSize = rulesNonRandom.size();
        Random random = new Random();
        
        //create set of different random numbers with given size and bound
        HashSet<Integer> randomNums = new HashSet<>();
        while (randomNums.size()<randomSize) {            
            randomNums.add(random.nextInt(fileSize));
        }
        
        //loop over random numbers and fill random file with rules corresponding to those numbers
        for (Integer randomNumber : randomNums) {
            rulesRandom.add(rulesNonRandom.get(randomNumber));
            wordsRandom.add(wordsNonRandom.get(randomNumber));
        }
        
        //create file from arraylists
        randomFile = new File("randomFile.txt", rulesRandom, wordsRandom);
        return randomFile;
    }
    
    /**
     * generates a list of random numbers with size as given in constructor, and a bound
     * ! Call this method before getRandomStrings !
     * @param bound highest number the random number can be (mostly filesize)
     */
    public void generateNumbers(int bound){
        randomNumbers = new int[size];
        Random random = new Random();
        
        //create set of different random numbers
        randomset = new HashSet<>();
        while (randomset.size()<size) {            
            randomset.add(random.nextInt(bound));
        }
        
        //iterate over set to put numbers in array
        int i=0;
        for (Iterator<Integer> iterator = randomset.iterator(); iterator.hasNext();) {
            Integer next = iterator.next();
            randomNumbers[i] = next;
            i++;
        }
        
        System.out.println("Random numbers generated....");
    }
    
    /**
     * make array of strings with previously made random numbers
     * ! Make sure you have called generateNumbers with the right bound !
     * @param strings arraylist from which array will be made
     * @return array with random strings
     */
    public String[] getRandomStrings(ArrayList<String> strings){
        String[] randomStrings = new String[size];
        
        //fill array with the strings corresponding to the random rulenumbers
        for (int i = 0; i < size; i++) {
            int number = randomNumbers[i];
            randomStrings[i] = strings.get(number);
        }
        
        System.out.println("Random strings generated....");
        return randomStrings;
    }
    
    /**
     * Deletes the randomly selected strings from arraylist 
     * ! Make sure generateNumbers & getRandomStrings have been called before this, otherwise you might lose data!
     * @param strings arraylist from which strings will be deleted
     * @return new arraylist without deleted strings
     */
    public ArrayList<String> deleteRandomStrings(ArrayList<String> strings){
        ArrayList<String> stringsDeleted = new ArrayList<>();
        
        //add strings that should not be deleted to stringsDeleted
        for (int i = 0; i < strings.size(); i++) {
            if(!randomset.contains(i)){
                stringsDeleted.add(strings.get(i));
            }
        }
        
        System.out.println("Deleted strings....");
        return stringsDeleted;
    }
    
    /**
     * gives a training file back
     * ! does the same as deleteRandomStrings but gives Array back!
     * @param strings the arraylist from which training set should be made
     * @return the array without items that were deleted
     */
    public String[] getTrain(ArrayList<String> strings){
        String[] train = new String[strings.size() - size];
        
        //get arraylist without strings from the randomnumbers
        ArrayList<String> deleted = deleteRandomStrings(strings);
        
        //put them in an array
        for (int i = 0; i < train.length; i++) {
                train[i] = deleted.get(i);
        }
        
        System.out.println("Generated training set....");
        return train;
    }
    
    /**
     * set random set to given numbers
     * @param numbers list of numbers to be put in randomset
     */
    public void setRandomset(ArrayList<Integer> numbers){
        randomset = new HashSet<>();
        for(int number: numbers){
            randomset.add(number);
        }
    }
    
    /**
     * set randomnumbers with given list
     * @param numbers list with random numbers
     */
    public void setRandomNumbers(ArrayList<Integer> numbers){
        randomNumbers = new int[size];
        for(int i = 0; i<size;i++){
            randomNumbers[i] = numbers.get(i);
        }
    }
}
