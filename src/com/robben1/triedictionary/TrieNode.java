package com.robben1.triedictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <what class do>
 *
 * @author Kyrylo Havrylenko
 * @see
 */
public class TrieNode {

    Character c;
    List<TrieNode> children = new ArrayList<>();
    List<TrieNode> parents = new ArrayList<>();

    TrieNode(List<TrieNode> linksChild, List<TrieNode> linksPar) {
        children = linksChild;
        parents = linksPar;
    }

    TrieNode() {
    }

    public boolean isLastCharacter() {
        return children.isEmpty();
    }

    public boolean isRoot() {
        return parents.isEmpty();
    }

    public TrieNode isAlreadyChild(Character c) {
        return this.children.stream()
                            .filter(child -> child.c == c)
                            .findFirst()
                            .orElse(null);
    }

    void printFirst10() {

        System.out.println("first 10");
    }

    void printWordsFor(String s) {
        if (s.isEmpty()) printFirst10();
        TrieNode res = this.children.stream()
                                    .filter(child -> child.c == s.charAt(0))
                                    .findFirst()
                                    .orElse(null);
        if(res == null) {
            printFirst10();
            return;
        }

        System.out.println(res.c);
        List<TrieNode> children = res.children;

        for (int i = 1; i < s.length(); i++) {
            int finalI = i;
            children = children.stream().filter(child -> child.c == s.charAt(finalI)).collect(Collectors.toList());
            // for each child
            // filter for c == s[i]
            // add res to List
            // go to next level
            children.stream().forEach(child -> System.out.print(child.c + " "));
        }

        for (int i = 0; i < children.size(); i++) {
            TrieNode child = children.get(i);
            while(!child.isLastCharacter()) {
                System.out.println(child.c);
                child = child.children.get(0);
            }
        }
    }
}
