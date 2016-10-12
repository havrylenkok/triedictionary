package com.robben1.triedictionary;

import java.util.ArrayList;
import java.util.List;

/**
 * Trie data structure
 *
 * @author Kyrylo Havrylenko
 * @see
 */
public class TrieNode {

    private static final int ALPHABET_SIZE = 26;
    private static final int BUFFER_SIZE = 1024;
    private TrieNode[] children;
    private TrieNode parent; // used to back track nodes to form the word
    private char character;
    private boolean isWord;
    private boolean isLeaf;

    /**
     * Constructor by default
     */
    public TrieNode() {
        this.setChildren(new TrieNode[ALPHABET_SIZE]);
        this.setWord(false); // if there no character then it's a leaf, not a word (end of loop)
        this.setLeaf(true);
    }

    /**
     * Adds a word to trie
     *
     * @param word String
     */
    public void add(String word) {
        int limit = word.length(),
                letter, index;
        TrieNode t = this;

        word = word.toLowerCase();
        for (int i = 0; i < limit; i++) {
            letter = word.charAt(i);
            index = letter - 'a';
            if (!isLetter(letter)) continue;
            if (t.children[index] == null) {
                t.children[index] = new TrieNode();
                t.children[index].character = (char) letter;
            }
            t.children[index].setLeaf(false); // it's a word if there is character
            t.children[index].parent = t;
            t = t.children[index];
        }
        t.setWord(true);
    }

    /**
     * Adds an array of words to trie
     *
     * @param words String array
     */
    public void addAll(String[] words) {
        for (String s : words)
            add(s);
    }

    /**
     * Checks if symbos is alphabetic (a letter)
     *
     * @param letter int code of character
     *
     * @return true if symbol is a letter, false otherwise
     */
    private boolean isLetter(int letter) {
        return Character.isAlphabetic(letter);
    }

    /**
     * Finds all words via recursive backtracking from root of trie
     *
     * @return String List of words
     */
    public List<String> findAndSaveWords() {
        List<String> words = new ArrayList<>();
        TrieNode t = this;
        if (t.isWord())
            words.add(t.getWord());
        if (!t.isLeaf()) {
            for (int i = 0; i < t.children.length; i++) {
                if (t.children[i] != null)
                    words.addAll(children[i].findAndSaveWords());
            }
        }
        return words;
    }

    /**
     * Backtracks trie-nodes to form a word for a current node
     *
     * @return String word from trie for This
     */
    public String getWord() {
        TrieNode t = this;
        char[] buffer = new char[BUFFER_SIZE];
        StringBuilder res = new StringBuilder();

        int i = 0;
        while (!t.isLeaf()) {
            buffer[i] = t.character;
            t = t.parent;
            i++;
        }
        res.append(buffer);
        return res.reverse().toString().trim();

    }

    /**
     * Checks if word is in trie
     *
     * @param s String word
     *
     * @return true if word is in trie, false otherwise
     */
    public boolean contains(String s) {
        TrieNode t = this;
        int limit = s.length();
        for (int i = 0; i < limit; i++) {
            int letter = s.charAt(i);
            int index = letter - 'a';
            if (t.children[index] == null)
                return false;
            t = t.children[index];
        }
        return t.isWord();
    }

    /**
     * Checks if trie is empty
     *
     * @param t Root of trie (can be used to check other tries)
     *
     * @return true if is empty, false otherwise
     */
    public boolean isEmpty(TrieNode t) {
        return t.children == null;
    }

    /**
     * Getter
     *
     * @return
     */
    public TrieNode[] getChildren() {
        return children;
    }

    /**
     * Setter
     *
     * @param children
     */
    public void setChildren(TrieNode[] children) {
        this.children = children;
    }

    /**
     * Getter
     *
     * @param index
     *
     * @return
     */
    public TrieNode getNode(int index) {
        TrieNode t = this;
        return t.children[index];
    }

    /**
     * Finds all words for current node
     *
     * @return String List of words
     */
    public List<String> getWords() {
        TrieNode t = this; // tail
        List<String> words = new ArrayList<>();

        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if (t.children[i] != null)
                words.addAll(t.getChildren()[i].findAndSaveWords());
        }
        return words;
    }

    /**
     * Getter
     *
     * @return
     */
    public TrieNode getParent() {
        return parent;
    }

    /**
     * Setter
     *
     * @param parent
     */
    public void setParent(TrieNode parent) {
        this.parent = parent;
    }

    /**
     * Getter
     *
     * @return
     */
    public char getCharacter() {
        return character;
    }

    /**
     * Setter
     *
     * @param character
     */
    public void setCharacter(char character) {
        this.character = character;
    }

    /**
     * Getter
     *
     * @return
     */
    public boolean isWord() {
        return isWord;
    }

    /**
     * Setter
     *
     * @param word
     */
    public void setWord(boolean word) {
        isWord = word;
    }

    /**
     * Getter
     *
     * @return
     */
    public boolean isLeaf() {
        return isLeaf;
    }

    /**
     * Setter
     *
     * @param leaf
     */
    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }
}
