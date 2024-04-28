import java.util.*;
import java.io.*;


// class SuffixTrieNode {

//     final static int MAX_CHAR = 256; // Maximum characters allowed

//     // Use a map instead of an array to handle dynamic growth
//     Map<Character, SuffixTrieNode> children = new HashMap<>();
//     List<Integer> indexes;

//     SuffixTrieNode() {
//         indexes = new LinkedList<>();
//     }

//     void insertSuffix(String s, int index, int sentenceNum) {
//         indexes.add(index);
//         if (s.length() > 0) {
//             char cIndex = s.charAt(0);
//             cIndex = Character.toLowerCase(cIndex); // Trim and convert to lowercase
            
//             // System.out.println(cIndex);
//             if (cIndex == '.' || cIndex == '?' || cIndex == '!' || String.valueOf(cIndex).equals(".")) {
//                 sentenceNum++;
//                 index = 0;
//                 // System.out.println("Sentence Number: " + sentenceNum);
//             }
            
//             if (!children.containsKey(cIndex)) {
//                 children.put(cIndex, new SuffixTrieNode());
//             }
//             children.get(cIndex).insertSuffix(s.substring(1), index + 1, sentenceNum);
           
//         }
//     }

//     List<Integer> search(String s) {
//         if (s.length() == 0) return indexes;
//         if (children.containsKey(s.charAt(0))) {
//             return children.get(s.charAt(0)).search(s.substring(1));
//         } else {
//             return null;
//         }
//     }
// }

// class SuffixTree {

//     SuffixTrieNode root = new SuffixTrieNode();

//     SuffixTree(String txt, int sentenceNum) {
//         for (int i = 0; i < txt.length(); i++) {
//             root.insertSuffix(txt.substring(i), i, sentenceNum);
//         }
//     }

//     void searchTree(String pat) {
//         List<Integer> result = root.search(pat);
//         System.out.println("Pattern: " + result);
//         if (result == null) {
//             System.out.println("Pattern not found");
//         } else {
//             int patLen = pat.length();
//             for (Integer i : result) {
//                 System.out.println(i);
//                 System.out.println("Pattern found at position " + (i - patLen) + " in sentence " + (i / patLen));
//             }
//         }
//     }

//     public static void main(String args[]) {
//         // String txt = "I arrived here yesterday, and my first task is to assure my dear sister of my welfare and increasing confidence in the success of my undertaking.";
//         // SuffixTree S = new SuffixTree(txt);

//         // System.out.println("Search for ', the'");
//         // S.searchTree(", the");

//         // System.out.println("\nSearch for 'and'");
//         // S.searchTree("and");

//         // System.out.println("\nSearch for 'si'");
//         // S.searchTree("si");

//         // System.out.println("\nSearch for 'missi'");
//         // S.searchTree("missi");
//         Scanner in = new Scanner(System.in);
//         String fileName = in.nextLine();
//         Queue<String> ss = new ArrayDeque<>();
//         String suffix = in.nextLine();

//         while (!suffix.equals(""))
//         {
//             ss.offer(suffix);
//             suffix = in.nextLine();
//         }

//         in.close();

        

//         String[] txt = readInFromFile(fileName);
//         String combined = "";
//         int sentenceNum = 0;
//         for (String sentence : txt) {
//             // trie.insert(sentence, sentenceNum);
//             if (sentence.equals("")) {
//                 continue;
//             }
//             combined += sentence + " ";
//             // root.insertSuffix(sentence.trim(), sentenceNum);
//         }

//         if (combined.contains(".") || combined.contains("?") || combined.contains("!")) {
//             sentenceNum = 1;
//         }

//         SuffixTree S = new SuffixTree(combined, sentenceNum);
        
     

//         while (!ss.isEmpty()) {
//             String s = ss.poll();
//             System.out.println("Search for '" + s + "'");
//             S.searchTree(s.toLowerCase());
//         //     SuffixTrieNode sn = st.get(s);
//         //     System.out.println("[" + s + "]: " + sn);
//         }
//             //Original Testing Data
//             // String txt2 = "I am already far north of London, and as I walk in the streets of Petersburgh, I feel a cold northern breeze play upon my cheeks, which braces my nerves and fills me with delight.";
//             // SuffixTree S2 = new SuffixTree(combined, sentenceNum);

//             // System.out.println("Search for 'will'");
//             // S.searchTree("will");

//             // System.out.println("\nSearch for 'You'");
//             // S.searchTree("You");
//         }

          
//         public static String[] readInFromFile(String fileName) {
//         // SuffixTrie trie = new SuffixTrie();
//         Scanner fileScanner;

//         try {
//             // use a FileInputStream to ensure correct reading end-of-file
//             fileScanner = new Scanner(new FileInputStream(fileName.trim()));
//             // int sentenceNum = 0;
//             while (fileScanner.hasNextLine()) {
//                 String line = fileScanner.nextLine();
//                 if (line.equals("")) {
//                     continue;
//                 }
//                 String[] parts = line.split("\\s+");
//                 // String[] sentences = line.split("[.!?]");
//                 // String[] test = line.split("\n");
//                 fileScanner.close();
//                 return parts;

//         } 
//     } catch (FileNotFoundException ex) {
//             System.out.println("could not find the file " + fileName + " in the data directory!");
//             return null;
//         }
//         return null;

//         // fileScanner.close();
//         // return trie;
//     }
// }


/////////////////////////////////////////////////////////////////
// 2nd try, not working as expected
// import java.util.*;

// class SuffixTrieNode {

//     final static int MAX_CHAR = 256; // Maximum characters allowed

//     Map<Character, SuffixTrieNode> children = new HashMap<>();
//     List<Integer> indexes;

//     SuffixTrieNode() {
//         indexes = new LinkedList<>();
//     }

//     void insertSuffix(String s, int index) {
//         indexes.add(index);
//         if (s.length() > 0) {
//             char cIndex = s.charAt(0);
//             if (!children.containsKey(cIndex)) {
//                 children.put(cIndex, new SuffixTrieNode());
//             }
//             children.get(cIndex).insertSuffix(s.substring(1), index + 1);
//         }
//     }

//     List<Integer> search(String s) {
//         if (s.length() == 0) return indexes;
//         if (children.containsKey(s.charAt(0))) {
//             return children.get(s.charAt(0)).search(s.substring(1));
//         } else {
//             return null;
//         }
//     }
// }

// class SuffixTree {

//     SuffixTrieNode root = new SuffixTrieNode();

//     SuffixTree(String txt) {
//         String[] sentences = txt.split("[.!?]");
//         int position = 0;
//         for (String sentence : sentences) {

//             root.insertSuffix(sentence.trim(), position);
//             position += sentence.length() + 1; // Add 1 for the punctuation mark
//         }
//     }

//     void searchTree(String pat) {
//         List<Integer> result = root.search(pat);
//         if (result == null) {
//             System.out.println("Pattern not found");
//         } else {
//             for (Integer i : result) {
//                 System.out.println("Pattern found at position " + i);
//             }
//         }
//     }

//     public static void main(String args[]) {
//         // String txt = "You will rejoice to hear that no disaster has accompanied the commencement of an enterprise which you have regarded with such evil forebodings. I arrived here yesterday, and my first task is to assure my dear sister of my welfare and increasing confidence in the success of my undertaking.\n"
//                 // + "\n"
//                 // + "I am already far north of London, and as I walk in the streets of Petersburgh, I feel a cold northern breeze play upon my cheeks, which braces my nerves and fills me with delight. Do you understand this feeling? This breeze, which has travelled from the regions towards which I am advancing, gives me a foretaste of those icy climes. Inspirited by this wind of promise, my daydreams become more fervent and vivid. I try in vain to be persuaded that the pole is the seat of frost and desolation; it ever presents itself to my imagination as the region of beauty and delight. There, Margaret, the sun is for ever visible, its broad disk just skirting the horizon and diffusing a perpetual splendour. There—for with your leave, my sister, I will put some trust in preceding navigators—there snow and frost are banished; and, sailing over a calm sea, we may be wafted to a land surpassing in wonders and in beauty every region hitherto discovered on the habitable globe. Its productions and features may be without example, as the phenomena of the heavenly bodies undoubtedly are in those undiscovered solitudes. What may not be expected in a country of eternal light? I may there discover the wondrous power which attracts the needle and may regulate a thousand celestial observations that require only this voyage to render their seeming eccentricities consistent for ever. I shall satiate my ardent curiosity with the sight of a part of the world never before visited, and may tread a land never before imprinted by the foot of man. These are my enticements, and they are sufficient to conquer all fear of danger or death and to induce me to commence this laborious voyage with the joy a child feels when he embarks in a little boat, with his holiday mates, on an expedition of discovery up his native river. But supposing all these conjectures to be false, you cannot contest the inestimable benefit which I shall confer on all mankind, to the last generation, by discovering a passage near the pole to those countries, to reach which at present so many months are requisite; or by ascertaining the secret of the magnet, which, if at all possible, can only be effected by an undertaking such as mine.";
        
//         String txt = "mississippi";
//         SuffixTree S = new SuffixTree(txt);

//         System.out.println("Search for 'i'");
//         S.searchTree("i");

//         System.out.println("\nSearch for 'is'");
//         S.searchTree("is");

//         System.out.println("\nSearch for 'si'");
//         S.searchTree("si");

//         System.out.println("\nSearch for 'missi'");
//         S.searchTree("missi");
//     }
// }



//////////////////////////////////////////////////////////////

class SuffixTrieNode {

    final static int MAX_CHAR = 256; // Maximum characters allowed

    // Use a map instead of an array to handle dynamic growth
    Map<Character, SuffixTrieNode> children = new HashMap<>();
    List<AbstractMap.SimpleEntry<Integer, Integer>> indexes = new ArrayList<>();

    // SuffixTrieNode() {
    //     indexes = new LinkedList<>();
    // }

    void insertSuffix(String s, int index, int sentenceNum) {
        indexes.add(new AbstractMap.SimpleEntry<>(index, sentenceNum));
        if (s.length() > 0) {
            char cIndex = s.charAt(0);
            cIndex = Character.toLowerCase(cIndex); // Trim and convert to lowercase
            
            // System.out.println(cIndex);
            if (cIndex == '.' || cIndex == '?' || cIndex == '!' || String.valueOf(cIndex).equals(".")) {
                sentenceNum++;
                index = -1;
            }
            // indexes.add(new AbstractMap.SimpleEntry<>(index, sentenceNum));
            if (!children.containsKey(cIndex)) {
                children.put(cIndex, new SuffixTrieNode());
            }
            
            children.get(cIndex).insertSuffix(s.substring(1), index + 1, sentenceNum);
           
        }
    }

    List<AbstractMap.SimpleEntry<Integer, Integer>> search(String s) {
        if (s.length() == 0) return indexes;
        if (children.containsKey(s.charAt(0))) {
            return children.get(s.charAt(0)).search(s.substring(1));
        } else {
            return null;
        }
    }
}

class SuffixTree {

    static SuffixTrieNode root = new SuffixTrieNode();

    SuffixTree(String txt, int sentenceNum) {
        String[] sentences = txt.split("[.!?]");
        for (String sentence : sentences) {
            for (int i = 0; i < sentence.length(); i++) {
                root.insertSuffix(sentence.substring(i), i, sentenceNum);
            }
            sentenceNum++;
        }
        // for (int i = 0; i < txt.length(); i++) {
        //     root.insertSuffix(txt.substring(i), i, sentenceNum);
        // }
    }

    static void printTree(SuffixTrieNode node, String prefix) {
        System.out.println(prefix + ": " + node.indexes);
        for (Map.Entry<Character, SuffixTrieNode> entry : node.children.entrySet()) {
            printTree(entry.getValue(), prefix + entry.getKey());
        }
    }

    void searchTree(String pat) {
        List<AbstractMap.SimpleEntry<Integer, Integer>> results = root.search(pat);
        // System.out.println("Pattern: " + results);
        if (results != null) {
            for (AbstractMap.SimpleEntry<Integer, Integer> result : results) {
                int position = result.getKey() - pat.length();
                System.out.println("Substring found at index " + position + " in sentence " + result.getValue());
            
            } 
            }
            else {
                System.out.println("Pattern not found");
            }
            // int patLen = pat.length();
            // for (Integer i : results) {
            //     System.out.println(i);
            //     System.out.println("Pattern found at position " + (i - patLen) + " in sentence " + (i / patLen));
            // }
        
    }

    public static void main(String args[]) {
       
        Scanner in = new Scanner(System.in);
        String fileName = in.nextLine();
        Queue<String> ss = new ArrayDeque<>();
        String suffix = in.nextLine();

        while (!suffix.equals(""))
        {
            ss.offer(suffix);
            suffix = in.nextLine();
        }

        in.close();

        String[] txt = readInFromFile(fileName);
        String combined = "";
        int sentenceNum = 0;
        for (String sentence : txt) {
            // trie.insert(sentence, sentenceNum);
            if (sentence.equals("")) {
                continue;
            }
            combined += sentence + " ";
            // root.insertSuffix(sentence.trim(), sentenceNum);
        }

        // if (combined.contains(".") || combined.contains("?") || combined.contains("!")) {
        //     sentenceNum = 1;
        // }

        SuffixTree S = new SuffixTree(combined, sentenceNum);
        printTree(root, "");
        
     

        while (!ss.isEmpty()) {
            String s = ss.poll();
            System.out.println("Search for '" + s + "'");
            S.searchTree(s.toLowerCase());
        //     SuffixTrieNode sn = st.get(s);
        //     System.out.println("[" + s + "]: " + sn);
        }
            //Original Testing Data
            // String txt2 = "I am already far north of London, and as I walk in the streets of Petersburgh, I feel a cold northern breeze play upon my cheeks, which braces my nerves and fills me with delight.";
            // SuffixTree S2 = new SuffixTree(combined, sentenceNum);

            // System.out.println("Search for 'will'");
            // S.searchTree("will");

            // System.out.println("\nSearch for 'You'");
            // S.searchTree("You");
        }

          
        public static String[] readInFromFile(String fileName) {
        // SuffixTrie trie = new SuffixTrie();
        Scanner fileScanner;

        try {
            // use a FileInputStream to ensure correct reading end-of-file
            fileScanner = new Scanner(new FileInputStream(fileName.trim()));
            // int sentenceNum = 0;
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (line.equals("")) {
                    continue;
                }
                String[] parts = line.split("\\s+");
                // String[] sentences = line.split("[.!?]");
                // String[] test = line.split("\n");
                fileScanner.close();
                return parts;

        } 
    } catch (FileNotFoundException ex) {
            System.out.println("could not find the file " + fileName + " in the data directory!");
            return null;
        }
        return null;

        // fileScanner.close();
        // return trie;
    }
}
