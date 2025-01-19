package software.ulpgc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainWords {
    public static void main(String[] args) throws IOException {
        List<String> words = load().stream()
                .toList();
        List<String> fromAtoM = words.stream()
                .filter(w -> w.matches("^[a-mA-M].*"))
                .toList();
        //System.out.println(fromAtoM);
        List<String> fromNtoZ = words.stream()
                .filter(w -> w.matches("^[n-zN-Z].*"))
                .toList();
        //System.out.println(fromNtoZ);
        Map<String,List<String>> groupedByPrefix = words.stream()
                .filter(w -> w.length() >= 3)
                .collect(Collectors.groupingBy(w -> w.substring(0, 3)));
        //System.out.println(groupedByPrefix);
        List<String> palindromes = words.stream()
                .filter(MainWords::isPalindrome)
                .toList();
        //System.out.println(palindromes);
        long countWords = words.stream()
                .flatMapToInt(String::chars)
                .filter(c -> "aeiouAEIOU".indexOf(c) != -1)
                .count();
        //System.out.println(countWords);
        List<String> startAendsZ = words.stream()
                .filter(w -> w.matches("^[aA].*[zZ]"))
                .toList();
        //System.out.println(startAendsZ);
        String longestWord = words.stream()
                .max((w1,w2) -> Integer.compare(w1.length(),w2.length()))
                .orElse(null);
        //System.out.println(longestWord);
    }

    private static boolean isPalindrome(String word) {
        String reverse = new StringBuilder(word).reverse().toString();
        return word.equalsIgnoreCase(reverse);
    }

    private static List<String> load() throws IOException {
        return Files.readAllLines(new File("words.txt").toPath());
    }
}
