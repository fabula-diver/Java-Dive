import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.net.URL;

class ConnectedGraph 
{
  public static void main(String[] args) throws Exception
  {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter a URL: "); // input prompt http://liveexample.pearsoncmg.com/test/GraphSample1.txt
    URL url = new URL(input.nextLine());
    Scanner infile = new Scanner(url.openStream()); // open an input file from an URL
    input.close();
    
    // Read the first line in the file (number of vertices)
    String s = infile.nextLine();
    int numberOfVertices = Integer.parseInt(s); // convert a string into int
    System.out.println("The number of vertices is " + numberOfVertices);
    
    List<Edge> list = new ArrayList<>();
    // continue to read the next lines and put the data into the list
    while(infile.hasNext()) 
    {
      s = infile.nextLine(); // grab the next line
      // a line format is like: 1 2 3			-> (1,2) (1,3)
      // split the string line into string tokens: " " as the delimiter
      String[] tokens = s.split("[\\s+]"); // + because there may be more than one whitespace
      
      // u value is always the first number in the line, so it's outside of the loop
      int startingVertex = Integer.parseInt(tokens[0].trim());
      // now put the rest into the list
      for (int i = 1; i < tokens.length; i++) 
      {
        int adjacentVertex = Integer.parseInt(tokens[i].trim());
        Edge edge = new Edge(startingVertex, adjacentVertex);
        list.add(edge);
        // list.add(new Edge(startingVertex, adjacentVertex));
      } // end for
    } // end while
    Graph<Integer> graph = new UnweightedGraph<>(list, numberOfVertices);
    
    graph.printEdges();
    UnweightedGraph<Integer>.SearchTree tree = graph.dfs(0);
    if (tree.getNumberOfVerticesFound() == numberOfVertices)
      System.out.println("The graph is connected");
    else
      System.out.println("The graph is not connected");
  } // end main
} // end ConnectedGraph
