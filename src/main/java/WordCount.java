import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WordCount {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java WordCount <file_path>");
            System.exit(1);
        }

        String filePath = args[0];
        try {
            Map<String, Integer> wordCountMap = countWords(filePath);
            wordCountMap.entrySet().stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public static Map<String, Integer> countWords(String filePath) throws IOException {
        Map<String, Integer> wordCountMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    word = sanitizeString(word);
                    wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
                }
            }
        }
        return wordCountMap;
    }

    public static String sanitizeString(String input) {
        String alphaNumeric = input.replaceAll("[^a-zA-Z0-9]", "");
        return alphaNumeric.toLowerCase();
    }
}
