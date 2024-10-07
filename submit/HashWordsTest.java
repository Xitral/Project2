import org.junit.*;
import static org.junit.Assert.*;

/**
 * Test class for the HashWords class.
 * This class contains comprehensive test cases covering all public methods
 * and various edge cases of the HashWords class.
 */
public class HashWordsTest {
    private HashWords hashWords;

    /**
     * Setup method, runs before each test method.
     * Initializes a new HashWords instance with an initial size of 10.
     */
    @Before
    public void setup() {
        hashWords = new HashWords(10);
    }

    /**
     * Test the constructor and size() method.
     */
    @Test
    public void testConstructorAndSize() {
        assertEquals("Initial size of the table should be 10", 10, hashWords.size());
    }

    /**
     * Test adding a single word and checking its frequency.
     */
    @Test
    public void testAddWordAndFrequency() {
        hashWords.addWord("hello");
        assertEquals("Frequency of 'hello' should be 1", 1, hashWords.frequency("hello"));
    }

    /**
     * Test case insensitivity in adding and checking words.
     */
    @Test
    public void testCaseInsensitivity() {
        hashWords.addWord("Hello");
        hashWords.addWord("hello");
        hashWords.addWord("HELLO");
        assertEquals("Frequency of 'hello' should be 3", 3, hashWords.frequency("hello"));
    }

    /**
     * Test special characters.
     */
    @Test
    public void testSpecialCharactersInWords() {
        hashWords.addWord("hello!");
        hashWords.addWord("hel@world");
        hashWords.addWord("hello-world");

        assertEquals("Frequency of 'hello!' should be 1",
                1, hashWords.frequency("hello!"));
        assertEquals("Frequency of 'hel@world' should be 1",
                1, hashWords.frequency("hel@world"));
        assertEquals("Frequency of 'hello-world' should be 1",
                1, hashWords.frequency("hello-world"));
    }
    
    /**
     * Test empty string.
     */
    @Test
    public void testAddingEmptyString() {
        hashWords.addWord("");
        
        assertEquals("Frequency of empty string should be 1", 1, hashWords.frequency(""));
        assertEquals("Number of unique words should be 1", 1, hashWords.numUniqueWordsInTable());
    }

    
    /**
     * Test the contains() method.
     */
    @Test
    public void testContains() {
        hashWords.addWord("test");
        assertTrue("Contains 'test' should be true", hashWords.contains("test"));
        assertFalse("Contains 'missing' should be false", hashWords.contains("missing"));
    }

    /**
     * Test numUniqueWordsInTable() and totalNumOfWords() methods.
     */
    @Test
    public void testWordCounts() {
        hashWords.addWord("apple");
        hashWords.addWord("cherry");
        hashWords.addWord("apple");
        hashWords.addWord("orange");
        hashWords.addWord("cherry");
        assertEquals("Number of unique words should be 3", 3, hashWords.numUniqueWordsInTable());
        assertEquals("Total number of words should be 5", 5, hashWords.totalNumOfWords());
    }

    /**
     * Test the mostCommonWord() method.
     */
    @Test
    public void testMostCommonWord() {
        hashWords.addWord("cat");
        hashWords.addWord("bird");
        hashWords.addWord("dog");
        hashWords.addWord("dog");
        hashWords.addWord("bird");
        hashWords.addWord("dog");
        assertEquals("Most common word should be 'dog'", "dog", hashWords.mostCommonWord());
    }

    /**
     * Test the termFrequency() method.
     */
    @Test
    public void testTermFrequency() {
        hashWords.addWord("abc");
        hashWords.addWord("abc");
        hashWords.addWord("bca");
        double expected = 2.0 / 3.0;
        double actual = hashWords.termFrequency("abc");
        assertEquals("Term frequency of 'abc' should be 0.666...", expected, actual, 0.0001);
    }

    /**
     * Test the term frequency on a missing word.
     */
    @Test
    public void testTermFrequencyForMissingWord() {
        HashWords hashWords = new HashWords(10);
        hashWords.addWord("hello");
        hashWords.addWord("world");

        double termFrequency = hashWords.termFrequency("missing");

        assertEquals("Term frequency for a missing word should be 0.0", 0.0, termFrequency, 0.0001);
    }

    /**
     * Test rehash.
     */
    @Test
    public void testTotalWordsCountAfterRehash() {
        HashWords hashWords = new HashWords(3);

        hashWords.addWord("12");
        hashWords.addWord("32");
        hashWords.addWord("54");

        hashWords.addWord("123");

        assertEquals("Total number of words will be 4", 4, hashWords.totalNumOfWords());
    }


    /**
     * Test the term frequency when no words have been added.
     */
    @Test
    public void testTermFrequencyWhenNoWordsAdded() {
        HashWords hashWords = new HashWords(10);
        double termFrequency = hashWords.termFrequency("");

        assertEquals(
                "Term frequency should be 0.0 when no words are added",
                0.0,
                termFrequency,
                0.0001);
    }

    /**
     * Test table resizing and rehashing behavior.
     */
    @Test
    public void testGrowAndRehash() {
        // Initial size is 10; adding more than 10 unique words should trigger resizing.
        for (int i = 0; i < 15; i++) {
            hashWords.addWord("" + i);
        }
        assertEquals("Number of unique words should be 15", 15, hashWords.numUniqueWordsInTable());
    }

    /**
     * Test the hashKey() method for consistency.
     */
    @Test
    public void testHashKeyConsistency() {
        int hash1 = hashWords.hashKey("Test");
        int hash2 = hashWords.hashKey("test");
        assertEquals("Hash keys should be the same for 'Test' and 'test'", hash1, hash2);
    }

    /**
     * Test linear probing collision resolution.
     */
    @Test
    public void testLinearProbing() {
        hashWords.addWord("abc");
        hashWords.addWord("bac"); // same ASCII sum
        hashWords.addWord("cab"); // same ASCII sum

        assertTrue("Contains 'abc' should be true", hashWords.contains("abc"));
        assertTrue("Contains 'bac' should be true", hashWords.contains("bac"));
        assertTrue("Contains 'cab' should be true", hashWords.contains("cab"));
        assertEquals("Number of unique words should be 3", 3, hashWords.numUniqueWordsInTable());
    }

    /**
     * Test linear probing collision resolution with different method.
     */
    @Test
    public void testCollisionHandlingWithLinearProbing() {
        hashWords.addWord("abc");
        hashWords.addWord("bca");  // same ASCII sum
        hashWords.addWord("cab");  // same ASCII sum

        assertEquals("Frequency of 'abc' should be 1", 1, hashWords.frequency("abc"));
        assertEquals("Frequency of 'bca' should be 1", 1, hashWords.frequency("bca"));
        assertEquals("Frequency of 'cab' should be 1", 1, hashWords.frequency("cab"));
        assertEquals("Number of unique words should be 3", 3, hashWords.numUniqueWordsInTable());
    }


    /**
     * Test adding large number of words to ensure performance.
     */
    @Test
    public void testAddingLargeNumberOfWords() {
        for (int i = 0; i < 10000; i++) {
            hashWords.addWord("word" + i);
        }
        assertEquals("Number of unique words should be 10000",
                10000, hashWords.numUniqueWordsInTable());
        assertEquals("Total number of words should be 10000",
                10000, hashWords.totalNumOfWords());
    }

    /**
     * Test adding only a few items, allowing for nulls.
     */
    @Test
    public void testGrowAndRehashWithNullEntries() {
        HashWords hashWords = new HashWords(5);

        // Add some words but not enough to fill the table
        hashWords.addWord("cat");
        hashWords.addWord("dog");

        // Force a rehash by adding more words
        hashWords.addWord("fish");
        hashWords.addWord("bird");

        // After rehashing make sure the original words are present
        assertTrue("Hash table should still contain 'cat' after rehashing",
                hashWords.contains("cat"));
        assertTrue("Hash table should still contain 'dog' after rehashing",
                hashWords.contains("dog"));
        assertTrue("Hash table should still contain 'fish' after rehashing",
                hashWords.contains("fish"));
        assertTrue("Hash table should still contain 'bird' after rehashing",
                hashWords.contains("bird"));
    }

}
