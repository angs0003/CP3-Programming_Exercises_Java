import java.util.Dictionary;
import java.util.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class SuffixTrie {

    private SuffixTrieNode root = new SuffixTrieNode();
   


    /**
     * Insert a String into the suffix trie.  For the assignment the string str
     * is a sentence from the given text file.
     *
     * @param str the sentence to insert
     * @param startPosition the starting index/position of the sentence
     * @return the final node inserted
     */
    public SuffixTrieNode insert(String str, int startPosition) {
        str = str + "$";
        SuffixTrieNode current = root;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (current.children.containsKey(c)) {
                current = current.children.get(c);
            } else {
                SuffixTrieNode newNode = new SuffixTrieNode();
                current.children.put(c, newNode);
                current = newNode;
            }
        }
        return current;
    }

    /**
     * Get the suffix trie node associated with the given (sub)string.
     *
     * @param str the (sub)string to search for
     * @return  the final node in the (sub)string
     */
    public SuffixTrieNode get(String str) {
        SuffixTrieNode current = root;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (current.children.containsKey(c)) {
                current = current.children.get(c);
            } else {
                return null;
            }
        }
        return current;
    }

    /**
     * Helper/Factory method to build a SuffixTrie object from the text in the 
     * given file.  The text file is broken up into sentences and each sentence
     * is inserted into the suffix trie.
     * 
     * It is called in the following way
     * <code>SuffixTrie st = SuffixTrie.readInFromFile("Frank01e.txt");</code>
     *
     * @param fileName
     * @return 
     */
    public static SuffixTrie readInFromFile(String fileName) {
        SuffixTrie d = new SuffixTrie();
        Scanner fileScanner;

        try {
            // use a FileInputStream to ensure correct reading end-of-file
            fileScanner = new Scanner(new FileInputStream(fileName));

            while (fileScanner.hasNextLine()) {
                String nextLine = fileScanner.nextLine();

                // System.out.println("nextLine: " + nextLine); //uncomment if you want to see
                
                SuffixTrieNode data = new SuffixTrieNode(nextLine);
                // System.out.println(data);
                // Insert the data into the dictionaryMap
                String[] parts = nextLine.split(" ");
                for (int i = 0; i < parts.length; i++) {
                    d.insert(parts[i], i);
                }
            }


        } catch (FileNotFoundException ex) {
            System.out.println("could not find the file " + fileName + "in the data directory!");
            return null;
        }

        fileScanner.close();
        return d;
    }
    }
