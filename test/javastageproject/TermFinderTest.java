/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javastageproject;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Carly
 */
public class TermFinderTest {
    
    public TermFinderTest() {
    }


    /**
     * Test of findRulesWithTerm method, of class TermFinder.
     */
    @Test
    public void testFindRulesWithTerm() {
        System.out.println("findRulesWithTerm");
        String term = "(procedure)";
        ArrayList<String> searchFile = TextReader.readLines("UMLS_All_EN.txt");
        ArrayList<Integer> result = TermFinder.findRulesWithTerm(term, searchFile);
        assertEquals(17553, result.size());
    }
    
}
