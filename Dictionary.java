
import java.io.*;
import java.util.*;

/**
 *
 * @author lewi0146
 */
public class Dictionary {

    private TreeMap<String, DictionaryData> dictionaryMap;

    public Dictionary() {
        dictionaryMap = new TreeMap<String, DictionaryData>();
    }

    /**
     * Extends this dictionary by adding the new word
     * identified with <code>word</code> and the data <code>data</code>.
     * 
     * @param word the word to add to the <code>Dictionary</code>.
     * @param data the data about the word s
     */

    // Creating ignorecase method for cp3
    public static <K> boolean containsKeyIgnoreCase(Map<String, K> map, String key) {
        for (String k : map.keySet()) {
            if (k.equalsIgnoreCase(key)) {
                return true;
            }
        }
        return false;
    }

    // Creating ignorecase method for returning frequency for cp3
    public static <K> K getIgnoreCase(Map<String, K> map, String key) {
        for (String k : map.keySet()) {
            if (k.equalsIgnoreCase(key)) {

                return map.get(k);
            }
        }
        return null;
    }

    public void insert(String word, DictionaryData data) {
        DictionaryData frequency = new DictionaryData(data.toString().split(" ")[3]);
        dictionaryMap.put(word, frequency);
        // System.out.println("inserting " + word + " with frequency " + frequency + "
        // into the dictionary");

    }

    /**
     * Removes the word identified by <code>word</code> from this Dictionary.
     * 
     * @param word the word to remove to the <code>Dictionary</code>.
     * @return
     */
    public DictionaryData remove(String word) {
        // System.out.println("remove() not implemented yet");

        return dictionaryMap.remove(word);
    }

    /**
     * Look up the dictionary entry for a particular word
     *
     * @param word the particular word to look up.
     * @return the data associated with the word identified by <code>word</code>.
     */
    public DictionaryData lookup(String word) {
        return getIgnoreCase(dictionaryMap, word);
    }

    /**
     * Check to see whether a word is in the dictionary or not (returns true/false)
     * 
     * @param word the word to check
     * @return <code>true</code> if in this <code>Dictionary</code>,
     *         <code>false</code> otherwise.
     */
    public boolean contains(String word) {

        return containsKeyIgnoreCase(dictionaryMap, word);
        // return dictionaryMap.containsKey(word.toLowerCase());
    }

    /**
     * Builder method for the <code>Dictionary</code> class that builds
     * a dictionary from the given file name.
     * It is expected that each entry is on a seperate line in the form
     * 
     * <pre>
     *     862 buddy 2743
     * </pre>
     * 
     * where 862 is the rank, buddy is the word, and 2743 is the frequency.
     *
     * @param fileName the file to load the dictionary data from
     * @return the created <code>Dictionary</code> or <code>null</code> on error.
     */

    public static Dictionary readInDictionary(String fileName) {
        // Dictionary d = null; //cp1
        Dictionary d = new Dictionary();// cp2
        Scanner fileScanner;

        try {
            // use a FileInputStream to ensure correct reading end-of-file
            fileScanner = new Scanner(new FileInputStream("data" + File.separator + fileName));

            while (fileScanner.hasNextLine()) {
                String nextLine = fileScanner.nextLine();
                // System.out.println("nextLine: " + nextLine); //uncomment if you want to see
                // what is read in
                DictionaryData data = new DictionaryData(nextLine);
                // System.out.println(data);
                // Insert the data into the dictionaryMap
                String[] parts = nextLine.split(" ");
                String words = parts[1];
                // TODO: call insert() here to insert the data object into the dictionary!
                d.insert(words, data);
            }

        } catch (FileNotFoundException ex) {
            System.out.println("could not find the file " + fileName + "in the data directory!");
            return null;
        }

        fileScanner.close();
        return d;
    }

    /**
     * Read in a file and list all the words not found in this dictionary.
     * 
     * @param fileName the file to read and check.
     * @return List of words not found in this <code>Dictionary</code>.
     */
    public List<String> spellCheck(String fileName) {
        Scanner fileScanner;
        List<String> notFound = new ArrayList<String>();

        try {
            // use a FileInputStream to ensure correct reading end-of-file
            fileScanner = new Scanner(new FileInputStream("data" + File.separator + fileName));

            while (fileScanner.hasNextLine()) {
                String nextLine = fileScanner.nextLine();
                // System.out.println("nextLine: " + nextLine); //uncomment if you want to see
                // what is read in

                if (nextLine != null && !nextLine.isEmpty()) {
                    for (String word : nextLine.split(" ")) {
                        if (!containsKeyIgnoreCase(dictionaryMap, word)) {
                            notFound.add(word);
                        }
                    }
                }

            }
        }

        catch (FileNotFoundException ex) {
            System.out.println("could not find the file " + fileName + "in the data directory!");
            return null;
        }

        fileScanner.close();
        return notFound;
    }

    // Sort by frequency
    public static List<Map.Entry<String, DictionaryData>> sortByValue(Map<String, DictionaryData> dictionaryMap) {
        List<Map.Entry<String, DictionaryData>> entryList = new LinkedList<Map.Entry<String, DictionaryData>>(
                dictionaryMap.entrySet());
            // Check if values in entryList are integers and delete if not
        List<Map.Entry<String, DictionaryData>> toRemove = new ArrayList<>();

        for (Map.Entry<String, DictionaryData> entry : entryList) {
            try {
                Integer.parseInt(entry.getValue().toString());
            } catch (NumberFormatException e) {
           
                toRemove.add(entry);
            }
        }

        // Remove the entries with NumberFormatException from entryList
        entryList.removeAll(toRemove);
        


            // Sort the list based on the values
        Collections.sort(entryList, new Comparator<Map.Entry<String, DictionaryData>>() {
            

            @Override
            public int compare(Map.Entry<String, DictionaryData> o1, Map.Entry<String, DictionaryData> o2) {
                int frequency1 = 0;
                int frequency2 = 0;
                for (Map.Entry<String, DictionaryData> entry : entryList) {
                    try {
                        frequency1 = Integer.parseInt(entry.getValue().toString());
                    } catch (NumberFormatException e) {
                     
                        dictionaryMap.remove(entry.getKey());
                        
                    }
                }
                try {
                    frequency1 = Integer.parseInt(o1.getValue().toString());
                    // frequency2 = Integer.parseInt(o2.getValue().toString());
                } catch (NumberFormatException e) {
            
                    dictionaryMap.remove(o1.getKey());
                    // dictionaryMap.remove(o2.getKey());
                    
                }
                try {
                    frequency2 = Integer.parseInt(o2.getValue().toString());
                } catch (NumberFormatException e) {
                
                    dictionaryMap.remove(o2.getKey());
                    
                } 
                    if (frequency1 == frequency2) {
                        return o2.getKey().compareTo(o1.getKey());
                    } else {
                        return frequency1 - frequency2;
                    }      
        // I need to find better sorting algorithm, this is even slower than O(logn)
            }
        });
        return entryList;
    }

    /**
     * Creates a list of the words in this dictionary by alphabetical order.
     * 
     * @return the list of alphabetical sorted dictionary words.
     */
    public List<DictionaryData> alphabeticalList() {
        List<Map.Entry<String, DictionaryData>> list = new LinkedList<Map.Entry<String, DictionaryData>>(
                dictionaryMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, DictionaryData>>() {
            @Override
            public int compare(Map.Entry<String, DictionaryData> o1, Map.Entry<String, DictionaryData> o2) {
                String key1 = o1.getKey();
                String key2 = o2.getKey();

                // Check if both keys are lowercase or uppercase
                boolean isLower1 = Character.isLowerCase(key1.charAt(0));
                boolean isLower2 = Character.isLowerCase(key2.charAt(0));

                // If both keys are lowercase or uppercase, compare them normally
                if (isLower1 == isLower2) {
                    return key1.compareTo(key2);
                }

                // If key1 is lowercase and key2 is uppercase, key1 should come first
                if (isLower1 && !isLower2) {
                    return -1;
                }

                // If key1 is uppercase and key2 is lowercase, key2 should come first
                return 1;
            }
        });
        int i = 0;
        for (Map.Entry<String, DictionaryData> entry : list) {
            if (i < 20 ) {
                System.out.println(entry.getKey() + ": frequency = " + entry.getValue());
                i++;
            } else {
                break;
            }
            // System.out.println(entry.getKey() + ": frequency = " + entry.getValue());
        }

        return null;
    }

    /**
     * Creates a list of the words in this dictionary by ascending order of
     * frequency (those of the same frequency should be
     * then ordered in reverse alphabetical order).
     * @return the list of frequency sorted dictionary words.
     */
    public List<DictionaryData> frequencyOrderedList() {
        List<Map.Entry<String, DictionaryData>> list = sortByValue(dictionaryMap);
        int i = 0;
        for (Map.Entry<String, DictionaryData> entry : list) {
            if (i < 20) {
                System.out.println(entry.getKey() + ": frequency = " + entry.getValue());
                i++;
            } else {
                break;
            }
        }
        return null;
    }

}
