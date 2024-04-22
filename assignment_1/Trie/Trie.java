import java.util.*;
import java.io.*;

public class Trie {

    private TrieNode root = new TrieNode();

    /**
     * Inserts a string into the trie and returns the last node that was
     * inserted.
     *
     * @param str The string to insert into the trie
     * @param data	The data associated with the string
     * @return The last node that was inserted into the trie
     */
    public TrieNode insert(String str, TrieData data) {
        // hint you can use str.toCharArray() to get the char[] of characters
        // insert a string into the trie and associate the string with a data object
        str = str.toLowerCase();
        char[] charArray = str.toCharArray();
        TrieNode currentNode = root;
        for (int i = 0; i < charArray.length; i++) {
            TrieNode child = currentNode.getChild(charArray[i]);
            if (child == null) {
                child = new TrieNode();
                currentNode.addChild(charArray[i], child);
            }
            currentNode = child;
        }
        currentNode.addData(data);
        currentNode.setTerminal(true);
        return currentNode;
    }

    /**
     * Search for a particular prefix in the trie, and return the final node in
     * the path from root to the end of the string, i.e. the node corresponding
     * to the final character. getNode() differs from get() in that getNode()
     * searches for any prefix starting from the root, and returns the node
     * corresponding to the final character of the prefix, whereas get() will
     * search for a whole word only and will return null if it finds the pattern
     * in the trie, but not as a whole word.  A "whole word" is a path in the
     * trie that has an ending node that is a terminal node.
     *
     * @param str The string to search for
     * @return the final node in the path from root to the end of the prefix, or
     * null if prefix is not found
     */
    public TrieNode getNode(String str) {
        // hint you can use str.toCharArray() to get the char[] of characters
        str = str.toLowerCase();
        char[] charArray = str.toCharArray();
        TrieNode currentNode = root;
        for (int i = 0; i < charArray.length; i++) {
            TrieNode child = currentNode.getChild(charArray[i]);
            if (child == null) {
                return null;
            }
            // System.out.println("child: " + child);
            currentNode = child;
        }
        return currentNode;
    }

    /**
     * Searches for a word in the trie, and returns the final node in the search
     * sequence from the root, i.e. the node corresponding to the final
     * character in the word.
     *
     * getNode() differs from get() in that getNode() searches for any prefix
     * starting from the root, and returns the node corresponding to the final
     * character of the prefix, whereas get() will search for a whole word only
     * and will return null if it finds the pattern in the trie, but not as a
     * whole word. A "whole word" is a path in the
     * trie that has an ending node that is a terminal node.
     *
     * @param str The word to search for
     * @return The node corresponding to the final character in the word, or
     * null if word is not found
     */
    public TrieNode get(String str) {
        // hint you can use str.toCharArray() to get the char[] of characters
        str = str.toLowerCase();
        char[] charArray = str.toCharArray();
        TrieNode currentNode = root;
        for (int i = 0; i < charArray.length; i++) {
            TrieNode child = currentNode.getChild(charArray[i]);
            if (child == null) {
                return null;
            }
            currentNode = child;
        }
        
        if (currentNode.isTerminal()) {
            return currentNode;
        }
        return null;
    

        // hint use getNode to find the end node and then check to see if it is
        // not null and a terminal



    }

    /**
     * Retrieve from the trie an alphabetically sorted list of all words
     * beginning with a particular prefix.
     *
     * @param prefix The prefix with which all words start.
     * @return The list of words beginning with the prefix, or an empty list if
     * the prefix was not found.
     */
    public List<String> getAlphabeticalListWithPrefix(String prefix) {
        // hint you can use str.toCharArray() to get the char[] of characters
        List<String> words = new ArrayList<>();
        TrieNode node = getNode(prefix);
        if (node == null) {
            return words;
        }
        StringBuilder sb = new StringBuilder(prefix);
        getAlphabeticalListWithPrefixHelper(node, sb, words);
        return words;
    }

    /**
     * NOTE: TO BE IMPLEMENTED IN ASSIGNMENT 1 Finds the most frequently
     * occurring word represented in the trie (according to the dictionary file)
     * that begins with the provided prefix.
     *
     * @param prefix The prefix to search for
     * @return The most frequent word that starts with prefix
     */
    

    public String getMostFrequentWordWithPrefix(String prefix) {
    TrieNode node = root;
    for (char c : prefix.toCharArray()) {
        TrieNode next = node.getChild(c);
        if (next == null) {
            return prefix; // prefix not found
        }
        node = next;
    }

    // Perform Depth First Search (DFS) to find the most frequent word
    return getMostFrequentWordDFS(node, prefix, new WordFrequency("", 0));
}

private String getMostFrequentWordDFS(TrieNode node, String word, WordFrequency maxFreq) {
    
    if (node.isTerminal() && node.getData().getFrequency() > maxFreq.frequency) {
        maxFreq.word = word;
        maxFreq.frequency = node.getData().getFrequency();
    }

    for (char c = 'a'; c <= 'z'; c++) {
        TrieNode child = node.getChild(c);
        if (child != null) {
            getMostFrequentWordDFS(child, word + c, maxFreq);
        }
    }

    return maxFreq.word;
}

private static class WordFrequency {
    String word;
    int frequency;

    WordFrequency(String word, int frequency) {
        this.word = word;
        this.frequency = frequency;
    }
}



    /**
     * NOTE: TO BE IMPLEMENTED IN ASSIGNMENT 1 Reads in a dictionary from file
     * and places all words into the trie.
     *
     * @param fileName the file to read from
     * @return the trie containing all the words
     */
    
    public static Trie readInDictionary(String fileName) {
        Trie trie = new Trie();
        Scanner fileScanner;

        try {
            // use a FileInputStream to ensure correct reading end-of-file
            fileScanner = new Scanner(new FileInputStream(fileName));

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split("\\s+");
                int frequency = Integer.parseInt(parts[2]);
                String word = parts[1];
                //System.out.println("word: " + word + " " + frequency);
                TrieData data = new TrieData(frequency);
                trie.insert(word, data);

        } 
    } catch (FileNotFoundException ex) {
            System.out.println("could not find the file " + fileName + " in the data directory!");
            return null;
        }

        fileScanner.close();
        return trie;
    }


    public TrieNode getRoot() {
        return root;
    }

    public void setRoot(TrieNode root) {
        this.root = root;
    }

    private void getAlphabeticalListWithPrefixHelper(TrieNode node, StringBuilder sb, List<String> words) {
        if (node.isTerminal()) {
            words.add(sb.toString());
        }
        for (char c = 'a'; c <= 'z'; c++) {
            TrieNode child = node.getChild(c);
            if (child != null) {
                sb.append(c);
                getAlphabeticalListWithPrefixHelper(child, sb, words);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

   
    

}
