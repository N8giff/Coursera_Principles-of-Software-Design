
/**
 * Write a description of MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovWord implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    
    public MarkovWord(int order){
        myOrder = order;
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder); 

        WordGram kGram = new WordGram(myText,index,myOrder);
        sb.append(kGram.toString());
        sb.append(" ");
        
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(kGram);
            
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            kGram = kGram.shiftAdd(next);
        }
        return sb.toString().trim();
    }
    
    public int indexOf(String [] words, WordGram target, int start){
        boolean found = false;
        
        for(int k = start; k < words.length - target.length(); k++){
            if(words[k].equals(target.wordAt(0))){
                found = true;
                for (int j=1;j<target.length();j++) {
                    if (!words[k+j].equals(target.wordAt(j))) {
                        found = false;
                        break;
                    }
                }
            }
            
            if(found == true){
            return k;
            }
        }
        return -1;
    }
    
    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while(pos < myText.length-kGram.length()){
            int start = indexOf(myText,kGram, pos);
            if(start == -1){
                break;
            }
            if(start + kGram.length() >= myText.length -1){
                break;
            }
            String next = myText[start+kGram.length()];
            follows.add(next);
            pos = start + kGram.length();
        }
        return follows;
    }
    
    public void testGetRandomText() {
        myText = "this is just a test yes this is a simple test i repeat this is a test just a test.".split("\\s");
        String randomText = getRandomText(100);
        for (int i=0;i<myText.length;i++) {
            //System.out.println(myText[i]);
        }
        System.out.println(randomText);
    }
    
    public void testGetFollows() {
        myText = "this is just a test yes this is a simple test".split("\\s");
        String[] targetArray = "this".split("\\s");
        WordGram target = new WordGram(targetArray,0,targetArray.length);
        ArrayList<String> follows = getFollows(target);
        System.out.println(follows);
    }
    
    public void testIndexOf() {
        String[] textArray = "this is just a test yes this is a simple test".split("\\s");
        String[] targetArray = "this is".split("\\s");
        WordGram target = new WordGram(targetArray,0,targetArray.length);
        System.out.println(indexOf(textArray,target,0));
        System.out.println(indexOf(textArray,target,5));
        System.out.println(indexOf(textArray,target,8));
    }

    
}
