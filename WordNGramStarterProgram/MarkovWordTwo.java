
/**
 * Write a description of MarkovWordTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MarkovWordTwo implements IMarkovModel{
    private String[] myText;
    private String[] testText;
    private Random myRandom;
    
    public MarkovWordTwo() {
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
        int index = myRandom.nextInt(myText.length-2); 
        String key1 = myText[index];
        String key2 = myText[index+1];
        sb.append(key1);
        sb.append(" ");
        sb.append(key2);
        sb.append(" ");
        
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key1, key2);
            //System.out.println("Key1: " + key1 + "Key2: " + key2 + "\nNext: " + follows);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key1 = key2;
            key2 = next;
        }
        return sb.toString().trim();
    }
    
    private int indexOf(String[] words, String target1, String target2, int start){
        for(int k = start; k < words.length-1; k++){
            if(words[k].equals(target1) && words[k+1].equals(target2)){
                return k;
            }
        }
        return -1;
    }
    
    public ArrayList<String> getFollows(String key1, String key2) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while(pos < myText.length){
            int start = indexOf(myText,key1, key2, pos);
            if(start == -1){
                break;
            }
            if(start + 2 >= myText.length){
                break;
            }
            String next = myText[start+2];
            follows.add(next);
            pos = start + 2;
        }
        return follows;
    }

    public void testIndextOf(){
        String text = "this is just a test yes this is a simple test";
        testText = text.split("\\s+");
        
        System.out.println("Sample text:" + text + "\n");
        
        //Test 1
        System.out.println("TEST #1" + "\n");
        String search1 = "this";
        String search2 = "is";
        int pos = 0;
        int answer = indexOf(testText, search1, search2, pos);
        System.out.println("Searching for: " + search1 + " and " + search2);
        System.out.println("Starting at position: " + pos);
        System.out.println("Expected output: 0");
        System.out.println("Actual output: " + answer);
        
        //Test 2
        System.out.println("TEST #2" + "\n");
        search1 = "this";
        search2 = "is";
        pos = 2;
        answer = indexOf(testText, search1, search2, pos);
        System.out.println("Searching for: " + search1 + " and " + search2);
        System.out.println("Starting at position: " + pos);
        System.out.println("Expected output: 6");
        System.out.println("Actual output: " + answer);
        
        //Test 3
        System.out.println("TEST #3" + "\n");
        search1 = "bay";
        search2 = "spoon";
        pos = 2;
        answer = indexOf(testText, search1, search2, pos);
        System.out.println("Searching for: " + search1 + " and " + search2);
        System.out.println("Starting at position: " + pos);
        System.out.println("Expected output: -1");
        System.out.println("Actual output: " + answer);
        
    }
}
