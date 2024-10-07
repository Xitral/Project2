import org.junit.*;
import static org.junit.Assert.*;

/**
 * Test class for the WordFrequency class.
 * This class contains comprehensive test cases covering all methods
 * and edge cases of the WordFrequency class.
 */
public class WordFrequencyTest {
    private WordFrequency wordFreq;

    /**
     * Setup method, runs before each test method.
     * Initializes a new WordFrequency instance.
     */
    @Before
    public void setup() {
        wordFreq = new WordFrequency("Hello");
    }

    /**
     * Test the constructor and getWord method.
     */
    @Test
    public void testConstructorAndGetWord() {
        assertEquals("Word should be 'hello'", "hello", wordFreq.getWord());
    }

    /**
     * Test the getCount method after initialization.
     */
    @Test
    public void testGetCountInitial() {
        assertEquals("Initial count should be 1", 1, wordFreq.getCount());
    }

    /**
     * Test the increment method.
     */
    @Test
    public void testIncrement() {
        wordFreq.increment();
        assertEquals("Count should be 2 after one increment", 2, wordFreq.getCount());
        wordFreq.increment();
        assertEquals("Count should be 3 after two increments", 3, wordFreq.getCount());
    }

    /**
     * Test the equals method with case insensitivity.
     */
    @Test
    public void testEqualsCaseInsensitive() {
        WordFrequency other = new WordFrequency("HELLO");
        assertTrue("WordFrequency objects with same word should be equal", wordFreq.equals(other));
    }

    /**
     * Test the equals method with null.
     */
    @Test
    public void testEqualsWithNull() {
        assertFalse("WordFrequency object should not be equal to null", wordFreq.equals(null));
    }

    /**
     * Test equals method with a different type.
     */
    @Test
    public void testEqualsWithDifferentType() {
        assertFalse("Should not be equal to an object of different type",
                wordFreq.equals(new Object()));
    }


    /**
     * Test initializing WordFrequency with empty string.
     */
    @Test
    public void testConstructorWithEmptyString() {
        WordFrequency wfEmpty = new WordFrequency("");
        assertEquals("Word should be empty string", "", wfEmpty.getWord());
    }

    /**
     * Test equals reflexive property.
     */
    @Test
    public void testEqualsReflexive() {
        assertTrue("Equals should be reflexive", wordFreq.equals(wordFreq));
    }

    /**
     * Test equals when given empty string.
     */
    @Test
    public void testEqualsWithEmptyString() {
        WordFrequency empty1 = new WordFrequency("");
        WordFrequency empty2 = new WordFrequency("");

        assertTrue("Empty strings should be equal", empty1.equals(empty2));
        assertTrue("Empty string should equal itself", empty1.equals(empty1));
    }

    /**
     * Test equals with case insensitivity.
     */
    @Test
    public void testHashCodeConsistency() {
        WordFrequency wf1 = new WordFrequency("hello");
        WordFrequency wf2 = new WordFrequency("HELLO");

        assertEquals("Equal objects must have the same hashCode",
                wf1.hashCode(), wf2.hashCode());
    }

    /**
     * Test not equals with different words.
     */
    @Test
    public void testHashCodeWithDifferentWords() {
        WordFrequency wf1 = new WordFrequency("hello");
        WordFrequency wf2 = new WordFrequency("world");

        assertNotEquals("Different words should have different hashCodes",
                wf1.hashCode(), wf2.hashCode());
    }

    /**
     * Test the equals method with different words.
     */
    @Test
    public void testEqualsWithDifferentWords() {
        WordFrequency wf1 = new WordFrequency("hello");
        WordFrequency wf2 = new WordFrequency("world");

        assertFalse("WordFrequency objects with different words should not be equal",
                wf1.equals(wf2));
    }


    /**
     * Test equals with a String.
     */
    @Test
    public void testEqualsWithString() {
        WordFrequency wf = new WordFrequency("hello");
        String str = "String";

        assertFalse("WordFrequency should not be equal to a List object", wf.equals(str));
    }

    /**
     * Test equals with an Integer.
     */
    @Test
    public void testEqualsWithInteger() {
        WordFrequency wf = new WordFrequency("hello");
        Integer integer = 3;

        // This should return false as 'list' is not a WordFrequency or String
        assertFalse("WordFrequency should not be equal to a List object", wf.equals(integer));
    }

    /**
     * Test increment after creating a new WordFrequency.
     */
    @Test
    public void testIncrementAfterCreation() {
        WordFrequency wf = new WordFrequency("test");
        wf.increment();
        assertEquals("Count will be 2 after one increment", 2, wf.getCount());
    }

    /**
     * Test incrementing count to a large number.
     */
    @Test
    public void testIncrementToLargeNumber() {
        for (int i = 0; i < 1000; i++) {
            wordFreq.increment();
        }
        assertEquals("Count should be 1001 after 1000 increments", 1001, wordFreq.getCount());
    }

    /**
     * Test getWord returns correct word.
     */
    @Test
    public void testGetWord() {
        assertEquals("getWord should return 'hello'", "hello", wordFreq.getWord());
    }

    /**
     * Test equals method for consistency.
     */
    @Test
    public void testEqualsConsistency() {
        WordFrequency other = new WordFrequency("hello");
        assertTrue("Equals should be consistent", wordFreq.equals(other));
        assertTrue("Equals should be consistent", wordFreq.equals(other));
    }

    /**
     * Test equals method with reflexivity.
     */
    @Test
    public void testEqualsWithItself() {
        assertTrue("Equals should return true comparing to itself", wordFreq.equals(wordFreq));
    }

}
