
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*;

public class MarkovRunner {
    public void runMarkovZero() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int seed = 1024;
        
        MarkovZero markov = new MarkovZero();
        markov.setTraining(st);
        markov.setRandom(seed);
        for(int k=0; k < 3; k++){
            String text = markov.getRandomText(200);
            printOut(text);
        }
    }
    
    public void runMarkovOne() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int seed = 365;
        
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        markov.setRandom(seed);
        for(int k=0; k < 3; k++){
            String text = markov.getRandomText(500);
            printOut(text);
        }
    }
    
    public void runMarkovFour() {
	FileResource fr = new FileResource();
	String st = fr.asString();
	st = st.replace('\n', ' ');
	int seed = 715;
	
	MarkovFour markov = new MarkovFour();
	markov.setTraining(st);
	markov.setRandom(seed);
	for(int k=0; k < 3; k++){
	    String text = markov.getRandomText(500);
	    printOut(text);
	}
    }
    
    public void runMarkovModel(){
        FileResource fr = new FileResource();
	String st = fr.asString();
	st = st.replace('\n', ' ');
	
	int seed = 953;
	int order = 7;
	
	MarkovModel markov = new MarkovModel(order);
	markov.setTraining(st);
	markov.setRandom(seed);
	for(int k=0; k < 3; k++){
	    String text = markov.getRandomText(500);
	    printOut(text);
	}
    }
    
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
