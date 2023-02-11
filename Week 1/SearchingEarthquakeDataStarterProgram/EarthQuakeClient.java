import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for(QuakeEntry qe : quakeData){
            if(qe.getMagnitude() > magMin){
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for(int i = 0; i < quakeData.size(); i++){
            QuakeEntry quake = quakeData.get(i);
            Location loc = quake.getLocation();
            if(loc.distanceTo(from) < distMax){
                answer.add(quake);
            }
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        ArrayList<QuakeEntry> answer = filterByMagnitude(list, 5.0);
        
        for(QuakeEntry qe : answer){
            System.out.println(qe);
        }
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //"http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes" + "\n");
        
        double distance = 1000;
        
        // This location is Durham, NC
        Location city = new Location(35.988, -78.907);
        String cityName = "Durham, NC";
        ArrayList<QuakeEntry> answer1 = filterByDistanceFrom(list, distance*1000, city);
        
        if(answer1.size() == 0){
            System.out.println("There are no quakes less than " + distance + " km from " + cityName);
        }
        else{
            System.out.println("There are " + answer1.size() + " quakes less than " + distance + " km from " + cityName);
        }
        System.out.println("\n");
        for(QuakeEntry qe : answer1){
            System.out.println(qe.getLocation().distanceTo(city)/1000 + "km: " + qe.getInfo());
        }
        System.out.println("//      //      //      //      //");

        // This location is Bridgeport, CA
        city =  new Location(38.17, -118.82);
        cityName = "Bridgeport, CA";
        ArrayList<QuakeEntry> answer2 = filterByDistanceFrom(list, distance*1000, city);
        
        if(answer2.size() == 0){
            System.out.println("There are no quakes less than " + distance + " km from " + cityName);
        }
        else{
            System.out.println("There are " + answer2.size() + " quakes less than " + distance + " km from " + cityName);
        }
        System.out.println("\n");
        for(QuakeEntry qe : answer2){
            System.out.println(qe.getLocation().distanceTo(city)/1000 + "km: " + qe.getInfo());
        }
        System.out.println("//      //      //      //      //");
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for(QuakeEntry qe : quakeData){
            if(qe.getDepth() > minDepth && qe.getDepth() < maxDepth){
                answer.add(qe);
            }
        }
        return answer;
    }
    
    public void quakesOfDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes" + "\n");
        
        double min = -4000.0;
        double max = -2000.0;
        
        ArrayList<QuakeEntry> answer = filterByDepth(list, min, max);
        
        System.out.println("There are " + answer.size() + " quakes between " + min + " and " + max + " meters deep:");
        System.out.println("\n");
        for (QuakeEntry qe : answer) {
            System.out.println(qe);
        }
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for(QuakeEntry qe : quakeData){
            if(where.equalsIgnoreCase("start") && qe.getInfo().startsWith(phrase)){
                answer.add(qe);
            } 
            else if(where.equalsIgnoreCase("end") && qe.getInfo().endsWith(phrase)){
                answer.add(qe);
            }
            else if(where.equalsIgnoreCase("any") && qe.getInfo().contains(phrase)){
                answer.add(qe);
            }
        }
        return answer;
    }
    
    public void quakesByPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes" + "\n");
        
        String phrase = "Can";
        String where = "any";
        
        ArrayList<QuakeEntry> answer  = filterByPhrase(list, where, phrase);
        
        System.out.println("Phrase used: " + phrase);
        System.out.println("Position of phrase: " + where);
        System.out.println("There are " + answer.size() + " quakes that meet these criteria: " + "\n");
        
        for(QuakeEntry qe : answer){
            System.out.println(qe);
        }
    }
    
    
    
    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
