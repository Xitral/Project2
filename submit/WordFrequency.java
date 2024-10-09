/**
 * WordFrequency class to be used in a hash table.
 * 
 * @author Zander Polk
 */
public class WordFrequency {
    private String word;
    private int count;

    /**
     * Constructor to create a new WordFrequency instance.
     * 
     * @param w The word to be assigned to this instance
     */
    public WordFrequency(String w) {
        this.word = w.toLowerCase();
        this.count = 1;
    }

    /**
     * Gets the word assigned to this instance.
     * 
     * @return the word
     */
    public String getWord() {
        return word;
    }

    /**
     * Gets the count associated with this instance.
     * 
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * Adds +1 to the count of this given instance.
     */
    public void increment() {
        count += 1;
    }

    /**
     * equals() - compares two WordFrequency
     * objects checking to see if they are the same.
     * Equality is defined by string matching ignoring case.
     * 
     * @param other object to compare against
     * @return true if this and other are equal; otherwise, false
     */
    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other instanceof String) {
            return getWord().equalsIgnoreCase((String) other);
        }
        if (other instanceof WordFrequency) {
            return getWord().equalsIgnoreCase(((WordFrequency) other).getWord());
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return word.toLowerCase().hashCode();         // case-insensitivity
    }
}
