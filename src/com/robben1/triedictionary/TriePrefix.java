package com.robben1.triedictionary;

import java.util.ArrayList;
import java.util.List;

/**
 * TrieNode wrapper to simplify usage of trie for input predictions
 *
 * @author Kyrylo Havrylenko
 * @see
 */
public class TriePrefix {
    private TrieNode root;

    /**
     * Constructor by default
     */
    public TriePrefix() {
        root = new TrieNode();
    }

    /**
     * Constructor
     *
     * @param words - array of Strings which represent dictionary with words
     */
    public TriePrefix(String[] words) {
        root = new TrieNode();
        root.addAll(words);
    }

    /**
     * Adds a word to trie
     *
     * @param word String
     */
    public void add(String word) {
        root.add(word);
    }

    /**
     * Finds list of words for prefix (first part of word)
     *
     * @param prefix String
     *
     * @return List of Strings with best candidates for input prediction in alphabet order
     */
    public List<String> getWordsFor(String prefix) {
        TrieNode tail = root;
        int limit = prefix.length();

        // last char of the prefix and tail
        for (int i = 0; i < limit; i++) {
            tail = tail.getNode(prefix.charAt(i) - 'a');
            // if there no words then return empty arrlist
            if (tail == null)
                return new ArrayList<>();
        }
        return tail.getWords();
    }
}
