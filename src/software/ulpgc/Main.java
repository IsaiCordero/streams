package software.ulpgc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;

@SuppressWarnings("Convert2MethodRef")
public class Main {
    public static void main(String[] args) throws IOException {
        List<Word> words = load().stream()
                .map(text -> new Word(text))
                .toList();

        List<String> fromAtoM = words.stream()
                .map(Word::text)
                .filter(w -> w.matches("^[a-mA-M].*"))
                .collect(Collectors.toList());
        //System.out.println(fromAtoM);
        List<String> fromNtoZ = words.stream()
                .map(Word::text)
                .filter(w -> w.matches("^[n-zN-Z].*"))
                .collect(Collectors.toList());
        //System.out.println(fromNtoZ);

        Map<String,List<String>> groupedByPrefix = words.stream()
                .map(Word::text)
                .collect(Collectors.groupingBy(w -> w.length() >= 3 ? w.substring(0, 3) : w));
        //System.out.println(groupedByPrefix);
        
        List<String> palindrome = words.stream()
                .map(Word::text)
                .filter(w-> isPalindrome(w))
                .collect(Collectors.toList());
        //System.out.println(palindrome);

        long countVowels = words.stream()
                .map(Word::text)
                .flatMapToInt(String::chars)
                .filter(c -> "aeiouAEIOU".indexOf(c) != -1)
                .count();
        //System.out.println(countVowels);
        List<String> startAendZ = words.stream()
                .map(Word::text)
                .filter(w -> w.matches("^[a].*[z]"))
                .collect(Collectors.toList());
        //System.out.println(startAendZ);

        String longestWord = words.stream()
                .map(Word::text)
                .max((w1,w2) -> Integer.compare(w1.length(),w2.length()))
                .orElse("");
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
