package com.robben1.triedictionary;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        List<String> list = Files.readAllLines(Paths.get("dict.txt"), StandardCharsets.UTF_8);

        System.out.print("Find words for: ");
        String input = new Scanner(System.in).nextLine();

        long startTime = System.nanoTime();
        list.stream()
            .filter(s -> s.startsWith(input))
            //            .limit(3)
            .collect(Collectors.toList());
        //            .forEach(System.out::println);
        long estTime = TimeUnit.MILLISECONDS.convert(System.nanoTime() - startTime, TimeUnit.NANOSECONDS);
        System.out.println("Time pseudo-func: " + estTime + "ms");

        String[] dict = list.toArray(new String[list.size()]);
        TriePrefix tp = new TriePrefix(dict);
        startTime = System.nanoTime();
        List<String> s = tp.getWordsFor(input);
        estTime = TimeUnit.MILLISECONDS.convert(System.nanoTime() - startTime, TimeUnit.NANOSECONDS);
        System.out.println("Time trie: " + estTime + "ms");

        s.stream().forEach(System.out::println);

    }
}
