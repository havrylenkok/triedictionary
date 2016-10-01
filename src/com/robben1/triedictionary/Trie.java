package com.robben1.triedictionary;

/**
 * <what class do>
 *
 * @author Kyrylo Havrylenko
 * @see
 */
public class Trie {

    TrieNode root = new TrieNode();

    Trie(String[] dict) {

        root.c = 1;
        System.out.println(root.isRoot());


        TrieNode localRoot = root;
        for (int i = 0; i < dict.length; i++) {
            for (int j = 0; j < dict[i].length(); j++) {
                TrieNode r = localRoot.isAlreadyChild(dict[i].charAt(j));
                if (r == null) {
                    r = new TrieNode();
                    r.c = dict[i].charAt(j);
                    r.parents.add(localRoot);
                    localRoot.children.add(r);
                }
                localRoot = r;
            }
            localRoot = root;
        }
    }
}
