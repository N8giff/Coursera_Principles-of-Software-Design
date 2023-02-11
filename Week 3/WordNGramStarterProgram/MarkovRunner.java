
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWordTwo markovWord = new MarkovWordTwo(); 
        runModel(markovWord, st, 200, 832); 
    } 
    
    /*public void testMarkovWordOne(){
        MarkovWordOne mw = new MarkovWordOne();
        FileResource fr = new FileResource();
        String text = fr.asString();
        //String text = "One two three four five six seven eight nine.";
        
        mw.setRandom(175);
        mw.setTraining(text);
        
        String result = mw.getRandomText(120);
        System.out.println(result);
    }
    
    public void testMarkovWordTwo(){
        MarkovWordTwo mw = new MarkovWordTwo();
        FileResource fr = new FileResource();
        String text = fr.asString();
        //String text = "One two three four five six seven eight nine.";
        
        mw.setRandom(549);
        mw.setTraining(text);
        
        String result = mw.getRandomText(30);
        System.out.println(result);
    }
    
    public void testGetFollows(){
        MarkovWordOne mw = new MarkovWordOne();
        FileResource fr = new FileResource();
        String text = fr.asString();
        //String text = "There is a turtle, a duck, a frog, and a rabbit.";
        //System.out.println("Sample text:" + text + "\n");
        
        mw.setRandom(175);
        mw.setTraining(text);
        
        //Test 1
        System.out.println("TEST #1" + "\n");
        String search = "a";
        ArrayList<String> answer = mw.getFollows(search);
        System.out.println("Searching for: " + search);
        System.out.println("Expected output: turtle, duck, frog, rabbit");
        System.out.println("Actual output: " + answer + "\n");
        
        //Test 2
        System.out.println("TEST #2" + "\n");
        search = "There";
        answer = mw.getFollows(search);
        System.out.println("Searching for: " + search);
        System.out.println("Expected output: is");
        System.out.println("Actual output: " + answer + "\n");
        
        //Test 3
        System.out.println("TEST #3" + "\n");
        search = "candy";
        answer = mw.getFollows(search);
        System.out.println("Searching for: " + search);
        System.out.println("Expected output: []");
        System.out.println("Actual output: " + answer + "\n");
        
        //Test 4
        System.out.println("TEST #4" + "\n");
        search = "his";
        answer = mw.getFollows(search);
        System.out.println("Searching for: " + search);
        //System.out.println("Expected output: []");
        System.out.println("Actual output: " + answer + "\n");
        
        
        System.out.println("//      //      //      //      //      //");
        
    }
    
    */
    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 
    
}
