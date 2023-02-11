
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class Tester {
    public void testGetFollows(){
        MarkovOne mo = new MarkovOne();
        FileResource fr = new FileResource();
        String text = fr.asString();
        mo.setTraining(text);
        
        ArrayList<String> follows = mo.getFollows("o");
        System.out.println("//      //      //      //      //");
        System.out.println("Size of follows: " + follows.size());
        //System.out.println("Following strings: " + follows); 
        
        follows = mo.getFollows("he");
        System.out.println("//      //      //      //      //");
        System.out.println("Size of follows: " + follows.size());
    }
}
