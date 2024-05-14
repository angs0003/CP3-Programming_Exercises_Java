import org.jdom2.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.output.Format.TextMode;
// import org.jdom2.output.LineSeparator;
// import org.jdom2.JDOMException;


/**
 *
 * @author lewi0146
 */
public class GraphShortestPathDriver {

    /**
     *
     * The program must accept one line of input on the standard input
     * that contains the name of the file to load in graphml format, a space,
     * and then the index of the starting vertex (starting from 0). For example
     *
     * data/graphs/random_v10_e10_w50.graphml 0
     *
     * @param args the command line arguments: <filename> <starting vertex>
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, JDOMException {

        //String filename = "data/graphs/graphSpecExample.graphml";
        //String filename = "data/graphs/graphTutorialExample.graphml";
        String filename = "data/graphs/graph10.graphml";

        int sourceVertex = 0;

        if (args.length > 0) {
            filename = args[0];
            if (args.length > 1) {
                sourceVertex = Integer.parseInt(args[1]);
            }
        }

        boolean printInfo = false;
        boolean printNoPath = true;

        Scanner s = new Scanner(System.in);
        String line = s.nextLine();

        if (!line.isEmpty()) {
            String[] params = line.split(" ");
            if (params.length > 0) {
                filename = params[0];
            }
            if (params.length > 1) {
                sourceVertex = Integer.parseInt(params[1]);
            }
            if (params.length > 2) {
                printInfo = params[2].equals("true");
            }
            if (params.length > 3) {
                printNoPath = params[3].equals("true");
            }
        }

        //System.out.print("Building graph from file: " +filename +"...");
        Graph g = GraphBuilder.buildFromGraphML(filename);
        //System.out.println("done.");

        if (printInfo) {
            System.out.println(g);
        }
        
        // perform dijktra's shortest path from sourceVertex
        int[] shortestDistances = Dijkstra.shortestPaths(g, sourceVertex);

        for (int i = 0; i < shortestDistances.length; i++) {
            if (shortestDistances[i] == 2147483647) {
                System.out.println("Shortest distance from vertex " + sourceVertex + " to vertex " + i + ": " + "NO PATH");
            } else {
                System.out.println("Shortest distance from vertex " + sourceVertex + " to vertex " + i + ": " + shortestDistances[i]);
            }
        }



    }

}
