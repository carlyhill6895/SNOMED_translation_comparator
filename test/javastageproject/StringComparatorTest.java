
package javastageproject;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Carly Hill 10523162
 */
public class StringComparatorTest {
    
    public StringComparatorTest() {
    }

    /**
     * Test of compare method, of class StringComparator.
     */
    @Test
    public void testCompare() {
        System.out.println("compare");
        
        String first = "Hoi ik ben Carly";
        String second = "Hoi ik ben Carly";
        

        assertEquals(true, StringComparator.compare(first, second, 5));
        
        String firstInequal = "SNOMED CT";
        String secondInequal = "UMLS metathesaurus";
        assertEquals(false, StringComparator.compare(firstInequal, secondInequal, 5));
        
        String firstEqual = "Procedure";
        String secondEqual = "procedure";
        assertEquals(true, StringComparator.compare(firstEqual, secondEqual, 5));
        
    }

    /**
     * Test of compareLetters method, of class StringComparator.
     */
    @Test
    public void testCompareLetters() {
        System.out.println("compareLetters");
        String[] firstWords = {"Hoi", "ik", "ben", "Carly"};
        String[] secondWords = {"Hoi", "ik", "ben", "Carly"};
        assertEquals(0, StringComparator.compareLetters(firstWords, secondWords));
      
    }
    
    @Test
    public void testCompareWords(){
        System.out.println("compareWords");
        String text = "Hoi ik ben Carly";
        String[] words = {"Hoi", "ik", "ben", "Carly"};
        String[] result = StringComparator.getWords(text);
        Assert.assertArrayEquals(words, result);
    }
    
    
}
