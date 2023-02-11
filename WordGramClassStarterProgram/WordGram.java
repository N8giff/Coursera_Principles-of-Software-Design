
public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        StringBuilder sb = new StringBuilder();
        for(int k = 0; k < myWords.length; k++){
            ret+= myWords[k] + " ";
        }
        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        if(this.length() != other.length()) {
            return false;
        }
        
        for(int k = 0; k < myWords.length; k++){
            if(! myWords[k].equals(other.wordAt(k))){
                return false;
            }
        }
        return true;
    }

    public WordGram shiftAdd(String word) { 
        WordGram out = new WordGram(myWords, 0, myWords.length);
        String[] shifted = new String[out.length()];
        for(int k = 0; k < out.length()-1; k++){
            shifted[k] = out.wordAt(k+1);
        }
        shifted[shifted.length-1] = word;
        out = new WordGram(shifted, 0, shifted.length);
        return out;
    }
    
    public int hashCode(){
        if(myHash == 0){
            myHash = this.toString().hashCode();
        }
        return myHash;
    }
}