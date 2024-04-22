import java.util.HashMap;
import java.util.Map;

public class TrieNode {

    private TrieData data = null;
    private boolean terminal = false;
    private int numChildren = 0;

    private Map<Character, TrieNode> children = new HashMap<>();
    /**
     * Lookup a child node of the current node that is associated with a
     * particular character label.
     *
     * @param label The label to search for
     * @return The child node associated with the provided label
     */
    // public TrieNode getChild(char label) {
    //     return null;
    // }
    public TrieNode getChild(char label) {
        if (children.containsKey(label)) {
            return children.get(label);
        }
        return null;
        
    }

    /**
     * Add a child node to the current node, and associate it with the provided
     * label.
     *
     * @param label The character label to associate the new child node with
     * @param node The new child node that is to be attached to the current node
     */
    public void addChild(char label, TrieNode node) {
        // hint: you can use a Map<Character, TrieNode> to store the children
        // of the current node
        children.put(label, node);
        numChildren++;
    }

    /**
     * Add a new data object to the node.
     *
     * @param dataObject The data object to be added to the node.
     */
    public void addData(TrieData dataObject) {
        data = dataObject;
    }

    public void setTerminal(boolean terminal) {
        this.terminal = terminal;
    }

    public boolean isTerminal() {
        return terminal;
    }

    public TrieData getData() {
        return data;
    }

    public int getNumChildren() {
        return numChildren;
    }




    /**
     * The toString() method for the TrieNode that outputs in the format
     *   TrieNode; isTerminal=[true|false], data={toString of data}, #children={number of children}
     * for example,
     *   TrieNode; isTerminal=true, data=3, #children=1
     * @return
     */
    @Override
    public String toString() {
        //return "Not implemented yet";
        return "TrieNode; isTerminal=" + terminal + ", data=" + data + ", #children=" + numChildren;
    }

    /**
     * Returns the data object associated with the node.
     *
     * @return The data object associated with the node
     */

    
}

