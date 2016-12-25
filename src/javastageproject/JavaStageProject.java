
package javastageproject;

import java.util.ArrayList;
import java.util.Collections;

/**
 * main class for project
 * @author Carly Hill 10523162
 */
public class JavaStageProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //TextReader reader = new TextReader();
       /* File bestand = reader.readFile("try.txt", ",");
        ArrayList<String> lines = bestand.getRules();
        System.out.println("\nReader reads a file\n");
        for(String line: lines){
            System.out.println(line);
        }
        
        File vertaling = reader.readFile("tryTranslate.txt", ";");
        StringComparator vergelijker = new StringComparator(vertaling);
        vergelijker.compareFiles(5, 3, 2);
        File gelijken = vergelijker.getEquals();
        File ongelijken = vergelijker.getDifferents();
        
        ArrayList<String> equalLines = gelijken.getRules();
        ArrayList<String> differentLines = ongelijken.getRules();
        System.out.println("\nEquals file made by comparator\n");
        for(String line: equalLines){
            System.out.println(line);
        }
        
        System.out.println("\ndifferents file made by comparator\n");
        for(String line: differentLines){
            System.out.println(line);
        }
        
        FileMaker.makeFile(gelijken);
        FileMaker.makeFile(ongelijken);*/
        
        //read train files
        /*ArrayList<String> umlsnl = TextReader.readLines("UMLS_ALL_DUT.txt");
        ArrayList<String> umlsen = TextReader.readLines("UMLS_ALL_EN.txt");
        
        ArrayList<Integer> randomProcedures = TermFinder.findRulesWithTerm("(procedure)", umlsen);
        Collections.shuffle(randomProcedures);
        //make development files
        RandomSample  sample = new RandomSample(5000);
        
        ArrayList<Integer> devProcedures = new ArrayList<>();
        for(int i = 0;i<5000;i++){
            devProcedures.add(randomProcedures.get(i));
        }
        
        sample.setRandomset(devProcedures);
        sample.setRandomNumbers(devProcedures);
        
        String[] umlsdevnl = sample.getRandomStrings(umlsnl);
        String[] umlsdeven = sample.getRandomStrings(umlsen);
        FileMaker.makeTxt(umlsdevnl, "umls_PROC_devnl.txt");
        FileMaker.makeTxt(umlsdeven, "umls_PROC_deven.txt");
        ArrayList<String> deletednl = sample.deleteRandomStrings(umlsnl);
        ArrayList<String> deleteden = sample.deleteRandomStrings(umlsen);
        
        //make test files
        randomProcedures = TermFinder.findRulesWithTerm("(procedure)", deleteden);
        Collections.shuffle(randomProcedures);
        ArrayList<Integer> testProcedures = new ArrayList<>();
        for(int i = 0;i<5000;i++){
            testProcedures.add(randomProcedures.get(i));
        }
        
        sample.setRandomset(testProcedures);
        sample.setRandomNumbers(testProcedures);
        
        String[] umlstestnl = sample.getRandomStrings(deletednl);
        String[] umlstesten = sample.getRandomStrings(deleteden);
        FileMaker.makeTxt(umlstestnl, "umls_PROC_testnl.txt");
        FileMaker.makeTxt(umlstesten, "umls_PROC_testen.txt");
        
        //make train files
        String[] trainnl = sample.getTrain(deletednl);
        String[] trainen = sample.getTrain(deleteden);
        FileMaker.makeTxt(trainnl, "umls_PROC_trainnl.txt");
        FileMaker.makeTxt(trainen, "umls_PROC_trainen.txt");*/
        
        //gelijke en ongelijke bestanden maken voor procedure vertalingen
       /* File vertaling = TextReader.readFile("mwvertaling.txt",";");
        StringComparator vergelijker = new StringComparator(vertaling);
        //vergelijken thot&matecat
        vergelijker.compareFiles(1,3,4);
        File gelijken = vergelijker.getEquals();
        File ongelijken = vergelijker.getDifferents();
        gelijken.setName("gelijkenthotmatecat.txt");
        ongelijken.setName("ongelijkenthotmatecat.txt");
        FileMaker.makeFile(gelijken);
        FileMaker.makeFile(ongelijken);
        File gelijkenGooglethot = TextReader.readFile("gelijkengooglethot.txt",";");
        File gelijkenThotMatecat = TextReader.readFile("eerstegelijkevertalingen.txt", ";");
        System.out.println("Bestanden ingelezen...");
        IdentifierExtractor extractor = new IdentifierExtractor(gelijkenGooglethot, gelijkenThotMatecat);
        File allegtOnGelijkeVertalingen = extractor.extract(0, 0);
        allegtOnGelijkeVertalingen.setName("gelijkevertalingen.txt");
        FileMaker.makeFile(allegtOnGelijkeVertalingen);
        
        /*File gelijkevertalingen = TextReader.readFile("echtalleongelijkevertalingen.txt", ";");
        WrongTranslationRemover remover = new WrongTranslationRemover(gelijkevertalingen);
        int removedmy = remover.removeSuffix(2, "my");
        System.out.println("aantal verwijderde termen met my : " + removedmy);
        int removedsty = remover.removeSuffix(2,"sty");
        System.out.println("aantal verwijderde termen met sty : " + removedsty);
        int removedion = remover.removeSuffix(2,"ion");
        System.out.println("aantal verwijderde termen met ion : " + removedion);
        int removedphy = remover.removeSuffix(2,"phy");
        System.out.println("aantal verwijderde termen met phy : " + removedphy);
        int removedexy = remover.removeSuffix(2,"exy");
        System.out.println("aantal verwijderde termen met exy : " + removedexy);
        int removedtry = remover.removeSuffix(2,"try");
        System.out.println("aantal verwijderde termen met try : " + removedtry);
        int removedsuture = remover.removeSuffix(2,"suture");
        System.out.println("aantal verwijderde termen met suture: " + removedsuture);
        int removedlysis = remover.removeSuffix(2, "lysis");
        System.out.println("aantal verwijderde termen met lysis: " + removedlysis);
        int removedcal = remover.removeSuffix(2, "cal");
        System.out.println("aantal verwijderde termen met cal: " + removedcal);
        int removedscopy = remover.removeSuffix(2,"scopy");
        System.out.println("aantal verwijderde termen met scopy: " + removedscopy);
        int removednal = remover.removeSuffix(2, "nal");
        System.out.println("aantal verwijderde termen met nal: " + removednal);
        //3925
        remover.removeTranslations(1,2,3925);
        File uitkomst = remover.getWrongTranslations();
        uitkomst.setName("verkeerdeallesongelijkeGooglevertalingen3.txt");
        FileMaker.makeFile(uitkomst);
        File goedeVertalingen = remover.getDutchTranslations();
        goedeVertalingen.setName("goedeallesongelijkeGooglevertalingen3.txt");
        FileMaker.makeFile(goedeVertalingen);*/
        
        /*File snomedbe = TextReader.readFile("snomedbe.txt", ";");
        File snomed = TextReader.readFile("alleumls.txt", ";");
        IdentifierExtractor extractor = new IdentifierExtractor(snomedbe, snomed);
        File besnomedtermen = extractor.extract(0,0);
        besnomedtermen.setName("umlssnomedbe.txt");
        FileMaker.makeFile(besnomedtermen);*/
        
       /* File snomedProcedures = TextReader.readFile("proceduresExtraction_tok.txt", ";");
        File googleChecked = TextReader.readFile("goedeThotmwbVertalingen.txt", ";");
        IdentifierExtractor extractor = new IdentifierExtractor(googleChecked, snomedProcedures);
        File extraction = extractor.extract(0,0);
        extraction.setName("snomedbeThotcheckedExtraction.txt");
        FileMaker.makeFile(extraction);
        IdentifierExtractor ex = new IdentifierExtractor(extraction, googleChecked);
        File extr = ex.extract(0,0);
        extr.setName("thotsnomedBc.txt");
        FileMaker.makeFile(extr);*/
        
        //vergelijken snomed be met google
       /* File vergelijkFile = TextReader.readFile("btsbVergelijking.txt", ";");
        StringComparator comp = new StringComparator(vergelijkFile);
        comp.compareFiles(1, 1, 3);
        File gelijken = comp.getEquals();
        gelijken.setName("gtsbvergelijkinggelijken.txt");
        FileMaker.makeFile(gelijken);
        File ongelijken = comp.getDifferents();
        ongelijken.setName("gtsbvergelijkingongelijken.txt");
        FileMaker.makeFile(ongelijken);
        
       /* File beVertaling = TextReader.readFile("gelijkenthotbe.txt", ";");
        File gelijkenVertaling = TextReader.readFile("gelijkenthotmwbe.txt", ";");
        IdentifierExtractor extractor = new IdentifierExtractor(beVertaling, gelijkenVertaling);
        File googlebevertaling = extractor.extract(0, 0);
        googlebevertaling.setName("ttmwbgelijkenhetzelfde.txt");
        FileMaker.makeFile(googlebevertaling);*/
        
        /*File googlebe = TextReader.readFile("snomedbe_thotmw_vergelijking.txt",";");
        StringComparator comparator = new StringComparator(googlebe);
        comparator.compareFiles(2, 1, 3);
        File gelijkenmatecatBe = comparator.getEquals();
        File ongelijkenmatecatBe = comparator.getDifferents();
        gelijkenmatecatBe.setName("gelijkenthotmwbe.txt");
        ongelijkenmatecatBe.setName("ongelijkenthotmwbe.txt");
        FileMaker.makeFile(gelijkenmatecatBe);
        FileMaker.makeFile(ongelijkenmatecatBe);*/
        
        /*File vertalingen = TextReader.readFile("goodmtgextract.txt", ";");
        StringComparator vergelijker = new StringComparator(vertalingen);
        vergelijker.compareFiles(1, 2, 4);
        File equalsg = vergelijker.getEquals();
        File differentsg = vergelijker.getDifferents();
        vergelijker.compareFiles(1, 3, 4);
        File equalsm = vergelijker.getEquals();
        File differentsm = vergelijker.getDifferents();
        equalsg.setName("googlethotmwbchekedgelijk.txt");
        equalsm.setName("matecatthotmwbcheckedgelijk.txt");
        differentsg.setName("googlethotmwbchekedongelijk.txt");
        differentsm.setName("matecatthotmmwbcheckedongelijk.txt");
        FileMaker.makeFile(equalsg);
        FileMaker.makeFile(equalsm);
        FileMaker.makeFile(differentsg);
        FileMaker.makeFile(differentsm);
        vergelijker.compareFiles(1, 2, 3);
        File equalsgm = vergelijker.getEquals();
        File differentsgm = vergelijker.getDifferents();
        equalsgm.setName("googlematecatcheckedgelijk.txt");
        differentsgm.setName("googlematecatcheckedongelijk.txt");
        FileMaker.makeFile(equalsgm);
        FileMaker.makeFile(differentsgm);
        IdentifierExtractor extractor1 = new IdentifierExtractor(differentsg, differentsgm);
        File differentsfirst = extractor1.extract(0,0);
        IdentifierExtractor extractor2 = new IdentifierExtractor(differentsfirst, differentsm);
        File equals = extractor2.extract(0, 0);
        equals.setName("gmthotmwballesongelijkchecked.txt");
        FileMaker.makeFile(equals);
        
       /* File nederlandseWoorden = TextReader.readFile("nederlandsewoordenlijst.txt", ";");
        File test = TextReader.readFile("googleMatecatThotmwbVertalingen.txt", ";");
        TranslationChecker checker = new TranslationChecker(nederlandseWoorden);
        checker.checkTranslations(test, 4);
        File good = checker.getGoodTranslations();
        File bad = checker.getBadTranslations();
        good.setName("goedeThotmwbVertalingen.txt");
        bad.setName("slechteThotmwbVertalingen.txt");
        FileMaker.makeFile(good);
        FileMaker.makeFile(bad);*/
        
        /*File matecatgoed = TextReader.readFile("goodmtextract.txt", ";");
        File thotgoed = TextReader.readFile("goedeGoogleVertalingen.txt", ";");
        IdentifierExtractor ex = new IdentifierExtractor(matecatgoed, thotgoed);
        File mtextract = ex.extract(0, 0);
        mtextract.setName("goodmtgextract.txt");
        FileMaker.makeFile(mtextract);*/
        
        //File gelijken = TextReader.readFile("gmthotmwballesgelijkchecked.txt", ";");
        File ongelijken = TextReader.readFile("gmthotmwballesongelijkchecked.txt", ";");
        //File tweegelijken = TextReader.readFile("tweeGelijk.txt", ";");
        //RandomTranslationChecker.checkRandomTranslations(gelijken, 100, 2);*/
        /*IdentifierExtractor ex = new IdentifierExtractor(gelijken, slechtetweegelijken);
        File gelijkenIntweegelijken = ex.extract(0, 0);
        gelijkenIntweegelijken.setName("gelijkenintweegelijken.txt");
        FileMaker.makeFile(gelijkenIntweegelijken);
        File tweegelijk = RandomTranslationChecker.getgoodRandomFile(slechtetweegelijken, gelijken);
        FileMaker.makeFile(tweegelijk);*/
        //String[] gelijkSample = RandomTranslationChecker.getRandomLines(gelijken, 50);
        String[] ongelijkSample = RandomTranslationChecker.getRandomLines(ongelijken, 50);
        //String[] tweegelijkSample = RandomTranslationChecker.getRandomLines(tweegelijken, 50);
        
        //FileMaker.makeTxt(gelijkSample, "gelijkensample3_50.txt");
        FileMaker.makeTxt(ongelijkSample, "ongelijkensample3_100.txt");
        //FileMaker.makeTxt(tweegelijkSample, "tweegelijkSample3_50.txt");
    }
    
    
}
