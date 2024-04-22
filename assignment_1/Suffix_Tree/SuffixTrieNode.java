import java.util.*;
import java.util.Hashtable;


public class SuffixTrieNode {
    int numChildren = 0;
    public Hashtable<Character, SuffixTrieNode> children = new Hashtable<>();
    private SuffixTrieData data;


    public SuffixTrieNode() {
        data = new SuffixTrieData();
    }

    public SuffixTrieNode(String str) {
        data = new SuffixTrieData();
        data.addStartIndex(0, 0);
    }

    public SuffixTrieData getData() {
        return data;
    }

    public void setData(SuffixTrieData data) {
        this.data = data;
    }

    public int getNumChildren() {
        return numChildren;
    }

    public void setNumChildren(int numChildren) {
        this.numChildren = numChildren;
    }

    public boolean hasChild(char label) {
        return false;
    }

    public SuffixTrieNode getChild(char label) {
        return null;
    }
    public void addChild(char label, SuffixTrieNode node) {
        this.numChildren++;
    }

    public void addData(int sentencePos, int characterPos) {
        data.addStartIndex(sentencePos, characterPos);
    }

    public String toString() {
        return data.toString();
    }

}
