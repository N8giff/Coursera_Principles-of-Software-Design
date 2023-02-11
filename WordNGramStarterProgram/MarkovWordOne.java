
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private String[] testText;
    private Random myRandom;
    
    public MarkovWordOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-1); 
        String key = myText[index];
        sb.append(key);
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key);
            //System.out.println("Key: " + key + "\nNext: " + follows);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = next;
        }
        return sb.toString().trim();
    }
    
    private int indexOf(String[] words, String target, int start){
        for(int k = start; k < words.length; k++){
            if(words[k].equals(target)){
                return k;
            }
        }
        return -1;
    }
    
    private ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while(pos < myText.length){
            int start = indexOf(myText,key, pos);
            if(start == -1){
                break;
            }
            if(start + key.length() >= myText.length -1){
                break;
            }
            String next = myText[start+1];
            follows.add(next);
            pos = start + 1;
        }
        return follows;
    }

    public void testIndextOf(){
        String text = "this is just a test yes this is a simple test";
        testText = text.split("\\s+");
        
        System.out.println("Sample text:" + text + "\n");
        
        //Test 1
        System.out.println("TEST #1" + "\n");
        String search = "this";
        int pos = 0;
        int answer = indexOf(testText, search, pos);
        System.out.println("Searching for: " + search);
        System.out.println("Starting at position: " + pos);
        System.out.println("Expected output: 0");
        System.out.println("Actual output: " + answer);
        
        //Test 2
        System.out.println("TEST #2" + "\n");
        search = "this";
        pos = 3;
        answer = indexOf(testText, search, pos);
        System.out.println("Searching for: " + search);
        System.out.println("Starting at position: " + pos);
        System.out.println("Expected output: 6");
        System.out.println("Actual output: " + answer);
        
        //Test 3
        System.out.println("TEST #3" + "\n");
        search = "frog";
        pos = 0;
        answer = indexOf(testText, search, pos);
        System.out.println("Searching for: " + search);
        System.out.println("Starting at position: " + pos);
        System.out.println("Expected output: -1");
        System.out.println("Actual output: " + answer);
        
        //Test 4
        System.out.println("TEST #4" + "\n");
        search = "frog";
        pos = 5;
        answer = indexOf(testText, search, pos);
        System.out.println("Searching for: " + search);
        System.out.println("Starting at position: " + pos);
        System.out.println("Expected output: -1");
        System.out.println("Actual output: " + answer);
        
        //Test 5
        System.out.println("TEST #5" + "\n");
        search = "simple";
        pos = 2;
        answer = indexOf(testText, search, pos);
        System.out.println("Searching for: " + search);
        System.out.println("Starting at position: " + pos);
        System.out.println("Expected output: 9");
        System.out.println("Actual output: " + answer);
        
        //Test 6
        System.out.println("TEST #6" + "\n");
        search = "test";
        pos = 5;
        answer = indexOf(testText, search, pos);
        System.out.println("Searching for: " + search);
        System.out.println("Starting at position: " + pos);
        System.out.println("Expected output: 10");
        System.out.println("Actual output: " + answer);
        
        System.out.println("//      //      //      //      //      //");
        
    }
    
    
}
