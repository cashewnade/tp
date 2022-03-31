package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

public class StringUtilTest {

    //---------------- Tests for isNonZeroUnsignedInteger --------------------------------------

    @Test
    public void isNonZeroUnsignedInteger() {

        // EP: empty strings
        assertFalse(StringUtil.isNonZeroUnsignedInteger("")); // Boundary value
        assertFalse(StringUtil.isNonZeroUnsignedInteger("  "));

        // EP: not a number
        assertFalse(StringUtil.isNonZeroUnsignedInteger("a"));
        assertFalse(StringUtil.isNonZeroUnsignedInteger("aaa"));

        // EP: zero
        assertFalse(StringUtil.isNonZeroUnsignedInteger("0"));

        // EP: zero as prefix
        assertTrue(StringUtil.isNonZeroUnsignedInteger("01"));

        // EP: signed numbers
        assertFalse(StringUtil.isNonZeroUnsignedInteger("-1"));
        assertFalse(StringUtil.isNonZeroUnsignedInteger("+1"));

        // EP: numbers with white space
        assertFalse(StringUtil.isNonZeroUnsignedInteger(" 10 ")); // Leading/trailing spaces
        assertFalse(StringUtil.isNonZeroUnsignedInteger("1 0")); // Spaces in the middle

        // EP: number larger than Integer.MAX_VALUE
        assertFalse(StringUtil.isNonZeroUnsignedInteger(Long.toString(Integer.MAX_VALUE + 1)));

        // EP: valid numbers, should return true
        assertTrue(StringUtil.isNonZeroUnsignedInteger("1")); // Boundary value
        assertTrue(StringUtil.isNonZeroUnsignedInteger("10"));
    }

    //---------------- Tests for isUnsignedInteger --------------------------------------
    @Test
    public void isUnsignedInteger() {

        // EP: empty strings
        assertFalse(StringUtil.isUnsignedInteger("")); // Boundary value
        assertFalse(StringUtil.isUnsignedInteger("  "));

        // EP: not a number
        assertFalse(StringUtil.isUnsignedInteger("a"));
        assertFalse(StringUtil.isUnsignedInteger("aaa"));

        // EP: zero
        assertTrue(StringUtil.isUnsignedInteger("0"));

        // EP: zero as prefix
        assertTrue(StringUtil.isUnsignedInteger("01"));

        // EP: signed numbers
        assertFalse(StringUtil.isUnsignedInteger("-1"));
        assertFalse(StringUtil.isUnsignedInteger("+1"));

        // EP: numbers with white space
        assertFalse(StringUtil.isUnsignedInteger(" 10 ")); // Leading/trailing spaces
        assertFalse(StringUtil.isUnsignedInteger("1 0")); // Spaces in the middle

        // EP: number larger than Integer.MAX_VALUE
        assertFalse(StringUtil.isUnsignedInteger(Long.toString(Integer.MAX_VALUE + 1)));

        // EP: valid numbers, should return true
        assertTrue(StringUtil.isUnsignedInteger("1")); // Boundary value
        assertTrue(StringUtil.isUnsignedInteger("10"));
    }

    //---------------- Tests for isValidDescriptionLength --------------------------------------

    /*
     * Invalid equivalence partitions: null, Strings more than 280
     * Valid equivalence partitions: String length of 0 to 280
     */
    @Test
    public void isValidDescriptionLength() {
        assertThrows(NullPointerException.class, ()-> StringUtil.isValidDescriptionLength(null));

        //EP: 280 characters
        assertTrue(StringUtil.isValidDescriptionLength("aaaaaaaaaaaaaaaa  "
                + "aaaaaaaaaaaaaaaa "
                + "aaaaaaaaaaaaaaaa "
                + "aaaaaaaaaaaaaaaa "
                + "aaaaaaaaaaaaaaaa "
                + "aaaaaaaaaaaaaaaa "
                + "aaaaaaaaaaaaaaaa "
                + "aaaaaaaaaaaaaaaa "
                + "aaaaaaaaaaaaaaaa "
                + "aaaaaaaaaaaaaaaa "
                + "aaaaaaaaaaaaaaaa "
                + "aaaaaaaaaaaaaaaa "
                + "aaaaaaaaaaaaaaaa "
                + "aaaaaaaaaaaaaaaa "
                + "aaaaaaaaaaaaaaaa "
                + "aaaaaaaaaaaaaaaa "
                + "a good."));

        //EP: 281 characters
        assertFalse(StringUtil.isValidDescriptionLength("aaaaaaaaaaaaaaaa  "
                + "delete a lot aaa "
                + "aaaaaaaaaaaaaaaa "
                + "aaaaaaaaaaaaaaaa "
                + "aaaaaaaaaaaaaaaa "
                + "aaaaaaaaaaaaaaaa "
                + "aaaaaaaaaaaaaaaa "
                + "aaaaaaaaaaaaaaaa "
                + "aaaaaaaaaaaaaaaa "
                + "aaaaaaaaaaaaaaaa "
                + "aaaaaaaaaaaaaaaa "
                + "aaaaaaaaaaaaaaaa "
                + "aaaaaaaaaaaaaaaa "
                + "aaaaaaaaaaaaaaaa "
                + "aaaaaaaaaaaaaaaa "
                + "aaaaaaaaaaaaaaaa "
                + "aaaaaaaa"));

        //EP: 279 characters
        assertTrue(StringUtil.isValidDescriptionLength("aaaaaaaaaaaaaaaa  "
                + "aaaaaaaaaaaaaaaa "
                + "aaaaaaaaaaaaaaaa "
                + "aaaaaaaaaaaaaaaa "
                + "aaaaaaaaaaaaaaaa "
                + "aaaaaaaaaaaaaaaa "
                + "aaaaaaaaaaaaaaaa "
                + "aaaaaaaaaaaaaaaa "
                + "aaaaaaaaaaaaaaaa "
                + "aaaaaaaaaaaaaaaa "
                + "aaaaaaaaaaaaaaaa "
                + "aaaaaaaaaaaaaaaa "
                + "aaaaaaaaaaaaaaaa "
                + "aaaaaaaaaaaaaaaa "
                + "aaaaaaaaaaaaaaaa "
                + "aaaaaaaaaaaaaaaa "
                + "aaaaaa"));

        //EP: 0 characters, valid because we don't have a lower bound
        assertTrue(StringUtil.isValidDescriptionLength(""));

    }

    //---------------- Tests for containsWordIgnoreCase --------------------------------------

    /*
     * Invalid equivalence partitions for word: null, empty, multiple words
     * Invalid equivalence partitions for sentence: null
     * The four test cases below test one invalid input at a time.
     */

    @Test
    public void containsWordIgnoreCase_nullWord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.containsWordIgnoreCase("typical sentence", null));
    }

    @Test
    public void containsWordIgnoreCase_emptyWord_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, "Word parameter cannot be empty", ()
            -> StringUtil.containsWordIgnoreCase("typical sentence", "  "));
    }

    @Test
    public void containsWordIgnoreCase_multipleWords_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, "Word parameter should be a single word", ()
            -> StringUtil.containsWordIgnoreCase("typical sentence", "aaa BBB"));
    }

    @Test
    public void containsWordIgnoreCase_nullSentence_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.containsWordIgnoreCase(null, "abc"));
    }

    /*
     * Valid equivalence partitions for word:
     *   - any word
     *   - word containing symbols/numbers
     *   - word with leading/trailing spaces
     *
     * Valid equivalence partitions for sentence:
     *   - empty string
     *   - one word
     *   - multiple words
     *   - sentence with extra spaces
     *
     * Possible scenarios returning true:
     *   - matches first word in sentence
     *   - last word in sentence
     *   - middle word in sentence
     *   - matches multiple words
     *
     * Possible scenarios returning false:
     *   - query word matches part of a sentence word
     *   - sentence word matches part of the query word
     *
     * The test method below tries to verify all above with a reasonably low number of test cases.
     */

    @Test
    public void containsWordIgnoreCase_validInputs_correctResult() {

        // Empty sentence
        assertFalse(StringUtil.containsWordIgnoreCase("", "abc")); // Boundary case
        assertFalse(StringUtil.containsWordIgnoreCase("    ", "123"));

        // Matches a partial word only
        assertFalse(StringUtil.containsWordIgnoreCase("aaa bbb ccc", "bb")); // Sentence word bigger than query word
        assertFalse(StringUtil.containsWordIgnoreCase("aaa bbb ccc", "bbbb")); // Query word bigger than sentence word

        // Matches word in the sentence, different upper/lower case letters
        assertTrue(StringUtil.containsWordIgnoreCase("aaa bBb ccc", "Bbb")); // First word (boundary case)
        assertTrue(StringUtil.containsWordIgnoreCase("aaa bBb ccc@1", "CCc@1")); // Last word (boundary case)
        assertTrue(StringUtil.containsWordIgnoreCase("  AAA   bBb   ccc  ", "aaa")); // Sentence has extra spaces
        assertTrue(StringUtil.containsWordIgnoreCase("Aaa", "aaa")); // Only one word in sentence (boundary case)
        assertTrue(StringUtil.containsWordIgnoreCase("aaa bbb ccc", "  ccc  ")); // Leading/trailing spaces

        // Matches multiple words in sentence
        assertTrue(StringUtil.containsWordIgnoreCase("AAA bBb ccc  bbb", "bbB"));
    }

    //---------------- Tests for getDetails --------------------------------------

    /*
     * Equivalence Partitions: null, valid throwable object
     */

    @Test
    public void getDetails_exceptionGiven() {
        assertTrue(StringUtil.getDetails(new FileNotFoundException("file not found"))
            .contains("java.io.FileNotFoundException: file not found"));
    }

    @Test
    public void getDetails_nullGiven_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.getDetails(null));
    }

}
