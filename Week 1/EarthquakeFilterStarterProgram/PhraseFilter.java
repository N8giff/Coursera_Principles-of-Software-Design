
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter{
    private String wh; 
    private String ph;
    
    public PhraseFilter(String where, String phrase) { 
        wh = where;
        ph = phrase;
    } 

    public boolean satisfies(QuakeEntry qe) { 
        if (wh.equals("start") && qe.getInfo().startsWith(ph)) {
            return true;
        }
        else if (wh.equals("end") && qe.getInfo().endsWith(ph)) {
            return true;
        }
        else if (wh.equals("any") && qe.getInfo().contains(ph)) {
            return true;
        }
        return false;
    } 
    
    public String getName() {
        return "Phrase";
    }
}
