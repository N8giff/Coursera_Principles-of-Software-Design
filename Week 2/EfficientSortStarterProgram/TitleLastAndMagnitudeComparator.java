
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{
    
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String[] title1 = q1.getInfo().split("\\W");
        String[] title2 = q2. getInfo().split("\\W");
        if(title1[title1.length-1].compareTo(title2[title2.length-1]) == 0){
            return Double.compare(q1.getMagnitude(),q2.getMagnitude());
        }
        return title1[title1.length-1].compareTo(title2[title2.length-1]);
    }
}
