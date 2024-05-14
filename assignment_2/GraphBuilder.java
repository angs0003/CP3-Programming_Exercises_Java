import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;


/**
 *
 * @author lewi0146
 */
public class GraphBuilder {

    public static Graph buildFromGraphML(String file) throws JDOMException, IOException {

        int nNodes = 0;
        // the SAXBuilder is the easiest way to create the JDOM2 objects.
        SAXBuilder jdomBuilder = new SAXBuilder();

        // jdomDocument is the JDOM2 Object
        Document jdomDocument = jdomBuilder.build(file);

        // The root element is the root of the document. we print its name
        System.out.println(jdomDocument.getRootElement().getName());

        Element graphxml = jdomDocument.getRootElement();

        Namespace ns = graphxml.getNamespace(); // Namespace.getNamespace("http://foo.com");

        Element graph = graphxml.getChild("graph", ns);
        //System.out.println(graph.getName() + ": " + graph.getChildren().size());

        List<Element> nodes = graph.getChildren("node", ns);
        List<Element> edges = graph.getChildren("edge", ns);

        System.out.println("number of nodes: " + nodes.size());

        Graph g = new MyGraph(nodes.size()); //new Graph(nodes.size());


        //System.out.println("number of edges: " + edges.size());
        int max = 0;
        int min = 10000;
        for (Element e : edges) {
            //System.out.println("e: " + e.getName());
            // List<Attribute> at = e.getAttributes();
            // //System.out.println(at);
            // int nS = e.getAttribute("source").getIntValue();
            // int nD = e.getAttribute("target").getIntValue();
            // int nV = Integer.parseInt(e.getChild("data", ns).getText());

            // // here you could add an edge to your graph from source to target with the weight of nV

            // if (nV > max) max = nV;
            // if (nV < min) min = nV;
            int source = Integer.parseInt(e.getAttributeValue("source"));
            int target = Integer.parseInt(e.getAttributeValue("target"));
            int weight = Integer.parseInt(e.getChild("data", ns).getText());
            g.addEdge(source, target, weight);
        }
        //System.out.println("max weight: "+max);
        //System.out.println("min weight: "+min);
        return g;
    }

}

