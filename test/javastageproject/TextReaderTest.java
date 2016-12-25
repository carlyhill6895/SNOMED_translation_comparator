/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javastageproject;

import static junit.framework.Assert.assertEquals;
import org.junit.Assert;
import org.junit.Test;

/**
 * test class for the text reader
 * @author Carly Hill 10523162
 */
public class TextReaderTest {
    public TextReaderTest() {
    }
    
    /**
     * Test of readFile method, of class TextReader.
     */
    @Test
    public void testReadFile() {
        System.out.println("readFile");
        String fileName = "try.txt";
        String splitter = ",";
        TextReader reader = new TextReader();
        File result = reader.readFile(fileName, splitter);
        String[] attributen = {"Terminologiestelsel","Engelse term", "Nederlandse term"};
        Assert.assertArrayEquals(attributen, result.getAttributes());
        assertEquals(3, result.getRules().size());
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
