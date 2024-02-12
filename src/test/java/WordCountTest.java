import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordCountTest {

    @Test
    public void testWordCount() throws IOException {
        String filePath = "src/test/resources/test.txt";
        Map<String, Integer> wordCountMap = WordCount.countWords(filePath);

        assertEquals(2, wordCountMap.get("lorem"));
        assertEquals(2, wordCountMap.get("21"));
        assertEquals(1, wordCountMap.get("ipsum"));
        assertEquals(1, wordCountMap.get("1a"));
        assertEquals(1, wordCountMap.get("1"));
        assertEquals(1, wordCountMap.get("2"));
    }

    @Test
    public void testSanitizeString_removesWhitespaces() {
        String input = "test string";
        String expected = "teststring";
        assertEquals(expected, WordCount.sanitizeString(input));

        String input2 = "test ";
        String expected2 = "test";
        assertEquals(expected2, WordCount.sanitizeString(input2));

        String input3 = " test";
        String expected3 = "test";
        assertEquals(expected3, WordCount.sanitizeString(input3));
    }

    @Test
    public void testSanitizeString_removesSpecialCharacters() {
        String input = "test: !@#$%^&*()_+-=[]{};:'\",.<>?/\\|";
        String expected = "test";
        assertEquals(expected, WordCount.sanitizeString(input));
    }

    @Test
    public void testSanitizeString_lowercases() {
        String input = "TEST";
        String expected = "test";
        assertEquals(expected, WordCount.sanitizeString(input));
    }

    @Test
    public void testSanitizeString_doesNotRemoveDigits() {
        String input = "test1";
        String expected = "test1";
        assertEquals(expected, WordCount.sanitizeString(input));
    }
}
