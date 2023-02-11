
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class LargestQuakes {
    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        /*
         * for(QuakeEntry qe : list){
            System.out.println(qe);
        }
        */
        System.out.println("read data for "+list.size());
        
        int howMany = 50;
        ArrayList<QuakeEntry> answer = getLargest(list,howMany);
        
        System.out.println("The top " + howMany + " quakes with the largest magnitudes: " + "\n" );
        for(QuakeEntry qe : answer){
            System.out.println(qe);
        }
        
    }
    
    public int indexOfLargest(ArrayList<QuakeEntry> data){
        double largestMag = 0.0;
        int largestInt = 0; 
        
        for (int i = 0 ; i < data.size(); i++){
            QuakeEntry currEntry = data.get(i);
            if(currEntry.getMagnitude() > largestMag){
                largestInt = i;
                largestMag = currEntry.getMagnitude();
            }
        }
    return largestInt;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = quakeData;;
        
        for (int i = 0; i < howMany; i++){
            int maxPos = indexOfLargest(copy);
            answer.add(copy.get(maxPos)); 
            copy.remove(copy.get(maxPos));
        }
        return answer;
    }
}
