/**
 * HashWords class to be used for counting and analyzing words in a text.
 * 
 * @author Zander Polk
 */
public class HashWords {
    private WordFrequency[] table;
    private int size;
    private int uniqueWordCount = 0;     // Increment locally for ease of access
    private int totalWordsCount = 0;

    /**
     * Constructor for the class, creates the initial
     * array of WordFrequency objects
     * to be of size initialSize.
     * 
     * @param initialSize the starting size of our table
     */
    public HashWords(int initialSize) {
        this.size = initialSize;
        this.table = new WordFrequency[size];
    }

    /**
     * Returns the size of the table used internally.
     * When first created, this value
     * should be equal to initialSize.
     * 
     * @return the size of table
     */
    public int size() {
        return size;
    }

    /**
     * Computes the key for argument w.
     * This method is called from addWord() as part of the logic
     * to add this word to the hash table.
     * It uses the internal size of the table to compute the index.
     * 
     * @param w the word
     * @return the hashkey value
     */
    public int hashKey(String w) {
        String word = w.toLowerCase();
        int hash = 0;
        for (char c : word.toCharArray()) {
            hash += (int) c; // Sum ASCII values
        }
        return hash % size;
    }

    /**
     * Returns the WordFrequency object associated with a specific String.
     * 
     * @param w the word
     * @return the WordFrequency object
     */
    private WordFrequency getUsingWord(String w) {
        int key = hashKey(w);
        
        while (table[key] != null) {
            if (table[key].getWord().equals(w)) {
                return table[key];
            }
            key = (key + 1) % size;  // Linear probing
        }
        return null;
    }

    /**
     * Returns the count of {@link WordFrequency WordFrequency} if the word (w)
     * exists in the table, returns 0 otherwise.
     * Note that the count is the value stored inside the {@link WordFrequency
     * WordFrequency} object.
     * Just find the word (w) in the table and then {@link WordFrequency#getCount()
     * WordFrequency.getCount()} from that object and return the value.
     * 
     * @param w the word
     * @return the count of (w) if it exists in the table; otherwise, 0
     */
    public int frequency(String w) {
        WordFrequency wf = getUsingWord(w);

        if (wf != null) {
            return wf.getCount();
        } else {
            return 0;
        }
    }

    /**
     * Adds a word to the table. If the word already exists, increase it's frequency
     * using
     * {@link WordFrequency#increment() WordFrequency.increment()}
     * 
     * @param w the word
     */
    public void addWord(String w) {
        String word = w.toLowerCase();
        int key = hashKey(word);
        
        totalWordsCount++;

        while (table[key] != null) {
            if (table[key].getWord().equals(word)) {
                table[key].increment();  // Word found, increment count
                return;
            }
            key = (key + 1) % size;  // Linear probing
        }

        table[key] = new WordFrequency(word);
        uniqueWordCount++;

        if (isFull()) {
            growAndRehash();
        }
    }

    /**
     * Checks if the table's #elements equals it's max size.
     * 
     * @return true if the table is full of unique words; otherwise, false
     */
    private boolean isFull() {
        return uniqueWordCount >= size;
    }

    /**
     * Function for handling the resizing and rehashing of the table.
     */
    private void growAndRehash() {
        WordFrequency[] oldTable = table;

        // Increase the size of the table
        size *= 3;
        table = new WordFrequency[size];

        // Rehash all old entries into the new table
        for (WordFrequency wf : oldTable) {
            reinsertWord(wf);
        }
    }

    /**
     * Function for reinserting a word into the table after we create a new one.
     * 
     * @param wf the WordFrequency object to be reinserted
     */
    private void reinsertWord(WordFrequency wf) {
        int key = hashKey(wf.getWord());

        // Linear probing to resolve collisions
        while (table[key] != null) {
            key = (key + 1) % size;  // Keep probing to find an empty slot
        }

        table[key] = wf;  // Insert the word at the available position
    }



    /**
     * Does this hash table contain this word (w)?
     * 
     * @param w the word
     * @return true if word is in table; otherwise, false
     */
    public boolean contains(String w) {
        return getUsingWord(w.toLowerCase()) != null;
    }

    /**
     * Total number of UNIQUE words is computed on-the-go inside the
     * {@link HashWords#addWord(String) addWord()} function.
     * 
     * @return the sum of all of the UNIQUE words in the table
     */
    public int numUniqueWordsInTable() {
        return uniqueWordCount;
    }

    /**
     * Total number of words is computed on-the-go inside the
     * {@link HashWords#addWord(String) addWord()} function.
     * 
     * @return the sum of all of the word counts in the table
     */
    public int totalNumOfWords() {
        return totalWordsCount;
    }

    /**
     * Finds the most common word in the table.
     * 
     * @return the word with the highest count of appearance in the table
     */
    public String mostCommonWord() {
        String word = null;
        int count = 0;
        for (WordFrequency wf : table) {
            if (wf != null && wf.getCount() > count) {
                count = wf.getCount();
                word = wf.getWord();
            }
        }
        return word;
    }

    /**
     * The measure that indicates how unique a term (or word) is in the particular
     * collection of words represented by this table.
     * It is computed as a ratio of the number of times a particular word appears
     * divided by the total number of words in the document.
     * 
     * @param w the word
     * @return the result of frequency(w) / totalNumOfWords(). If the word w is not
     *         on the table, this method returns 0
     */
    public double termFrequency(String w) {
        int wordFrequency = frequency(w.toLowerCase());

        if (totalWordsCount > 0) {
            return (double) wordFrequency / totalWordsCount;
        } else {
            return 0.0;
        }
    }
}
