
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
    
    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from){
        int maxIdx = from;
        for (int i=from+1; i< quakeData.size(); i++) {
            if (quakeData.get(i).getDepth() > quakeData.get(maxIdx).getDepth()) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }
    
    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
       int passes = 0;
       for (int i=0; i< in.size(); i++) {
            int maxIdx = getLargestDepth(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmax = in.get(maxIdx);
            in.set(i,qmax);
            in.set(maxIdx,qi);
            passes = passes + 1;
        }
        System.out.println("NUMBER OF PASSES: " + passes);
    }
    
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }
    
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted){
        
        for (int a = 0; a < numSorted; a++) {
            if (quakeData.get(a+1).getMagnitude() < quakeData.get(a).getMagnitude()) {
                QuakeEntry bigger = quakeData.get(a);
                QuakeEntry smaller = quakeData.get(a+1);
                quakeData.set(a,smaller);
                quakeData.set(a+1, bigger);
            }
        }
        
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in){
        System.out.println("QUAKES BEFORE SORT");
        for(QuakeEntry qe : in){
        System.out.println(qe);
        }
        System.out.println("\n");
        int passNum = 0;
        for(int i = in.size()-1; i > 0; i--){
            passNum = passNum +1;
            
            onePassBubbleSort(in,i);
            
            System.out.println("PRINTING QUAKES AFTER PASS "+ passNum + "\n");
            
            for(QuakeEntry qe : in){
                System.out.println(qe);
            }
            System.out.println("\n");
        }
        System.out.println("QUAKES SORTED" + "\n");
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes){
        for (int i=0; i< quakes.size()-1; i++) {
            double a = quakes.get(i).getMagnitude();
            double b = quakes.get(i+1).getMagnitude();
            
            if(a > b){
                return false;
            }
        }
        return true;
    }
    
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in){
       
        //System.out.println("QUAKES BEFORE SORT");
        //for(QuakeEntry qe : in){
        //    System.out.println(qe);
        //}
        //System.out.println("\n");
        int passNum = 0;
            for(int i = in.size()-1; i > 0; i--){
                passNum = passNum +1;
                onePassBubbleSort(in,i);
                //for(QuakeEntry qe : in){
                //    System.out.println(qe);
                //}
                //System.out.println("\n");
                if (checkInSortedOrder(in)) {
                    break;
                }
            }
        System.out.println("PRINTING QUAKES AFTER PASS "+ passNum + "\n");
        
    }
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in){
        int passNum = 0;
        for (int i=0; i< in.size(); i++) {
            passNum = passNum +1;
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            if (checkInSortedOrder(in)) {
                break;
            }
        } 
        
        System.out.println("PRINTING QUAKES AFTER PASS "+ passNum + "\n");
    }
    
    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/earthquakeDataSampleSix2.atom";
        //String source = "data/nov20quakedata.atom";
        String source = "data/earthQuakeDataWeekDec6sample2.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes" +"\n");   
        //sortByLargestDepth(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        //sortByMagnitudeWithCheck(list);
        
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
        
    }
}
