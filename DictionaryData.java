
/**
 *
 * @author lewi0146
 */
public class DictionaryData {
    private String line; //Variable initialize to store input
    /**
     * Creates a new DictionaryData object based upon the the String line that
     * contains the data about the new data item.
     *
     * @param line the data about the new data item
     */
    public DictionaryData(String line)
    {
        // String[] lineArray = line.split(" "); //Splitting the input into an array
        // if (lineArray.length != 3) //Checking if the input is valid
        // {
            // throw new IllegalArgumentException("Invalid input");
        this.line = line; //Setter
        // }else
        // {
            // this.line = lineArray[1] + ": frequency = " + lineArray[2]; //Setter
        // }
        // this.line = lineArray[1] + ": frequency = " + lineArray[2]; //Setter
    }

    /**
     * A string representation of the DataDictionary object. For example
     *
     *     "orange: frequency = 518"
     *
     * @return a string representation of the DataDictionary object
     */
    @Override
    public String toString() {
        String[] lineArray = this.line.split(" "); //Splitting the input into an array
        if (lineArray.length != 3) //Checking if the input is valid
        {
            return this.line;
        }else
        {
            // System.out.println(lineArray[2]);
            return lineArray[1] + ": frequency = " + lineArray[2]; //Setter
        }
    }

}
