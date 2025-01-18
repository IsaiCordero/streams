package software.ulpgc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;

@SuppressWarnings("Convert2MethodRef")
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
                .collect(Collectors.groupingBy(w -> w.substring(0,3)));
        //System.out.println(groupedByPrefix);
        List<String> Palindromes = words.stream()
                .filter(w -> isPalindrome(w))
                .toList();
        //System.out.println(Palindromes);
        long countVowels = words.stream()
                .flatMapToInt(String::chars)
                .count();
        //System.out.println(countVowels);
        List<String> startAendsZ = words.stream()
                .filter(w -> w.matches("^[aA].*[zZ]"))
                .toList();
        //System.out.println(startAendsZ);
        String largestWord = words.stream()
                .max((w1,w2) -> Integer.compare(w1.length(),w2.length()))
                .orElse("");
        //System.out.println(largestWord);

    }

    private static boolean isPalindrome(String word) {
        String reverse = new StringBuilder(word).reverse().toString();
        return word.equalsIgnoreCase(reverse);
    }


    private static List<String> load() throws IOException {
        return Files.readAllLines(new File("words.txt").toPath());
    }
}
