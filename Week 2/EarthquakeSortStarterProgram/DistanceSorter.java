
/**
 * Write a description of DistanceSorter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class DistanceSorter {
    
    public void sortByDistance(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
    
        ArrayList<QuakeEntry> list = parser.read(source);
        Location Durham = new Location(35.9886, -78.9072);
        //sort the earthquakes based on distance
        Collections.sort(list,new DistanceComparator(Durham));
        
        for(QuakeEntry qe : list){
            System.out.println(qe);
        }
    }
}
