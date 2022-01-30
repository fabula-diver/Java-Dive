public class Edge 
{
  int u;
  int v;

  public Edge(int u, int v) 
  {
    this.u = u;
    this.v = v;
  }

  public boolean equals(Object o)
  {
    // directed Edges (strictly u with u, v with v and can't be swapped around like undirected edges)
    return u == ((Edge)o).u && v == ((Edge)o).v;
  }
}