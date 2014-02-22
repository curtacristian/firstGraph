package firstGraph;

import java.util.HashSet;
import java.util.TreeSet;
import java.util.Vector;

public class Kruskal {
		
		private Graph g;
		public Kruskal(Graph g){
			this.g=g;
			for(Edge e : g.orderedEdges()){
				insertEdge(e);
			}
		}
		Vector<HashSet<Vertex>> vertexGroups=new Vector<HashSet<Vertex>>();
		TreeSet<Edge> kruskalEdges=new TreeSet<Edge>();
		
		public TreeSet<Edge> getEdges(){
			return kruskalEdges;
		}
		
		HashSet<Vertex> getVertexGroup(Vertex vertex){
			for(HashSet<Vertex> vertexGroup:vertexGroups){
				if(vertexGroup.contains(vertex)){
					return vertexGroup;
				}
			}
			return null;
		}
		
		public void insertEdge(Edge e){
			Vertex a=g.findVertexByID(e.getStart());
			Vertex b=g.findVertexByID(e.getEnd());
			HashSet<Vertex> vertexGroupA=getVertexGroup(a);
			HashSet<Vertex> vertexGroupB=getVertexGroup(b);
			if(vertexGroupA==null){
				kruskalEdges.add(e);
				if(vertexGroupB==null){
					HashSet<Vertex> htNewVertexGroup=new HashSet<Vertex>();
					htNewVertexGroup.add(a);
					htNewVertexGroup.add(b);
					vertexGroups.add(htNewVertexGroup);
					}
				else{
					vertexGroupB.add(a);
				}
			}
			else{
				if(vertexGroupB==null){
					vertexGroupA.add(b);
					kruskalEdges.add(e);
				}
				else if(vertexGroupA!=vertexGroupB){
					vertexGroupA.addAll(vertexGroupB);
					vertexGroups.remove(vertexGroupB);
					kruskalEdges.add(e);
				}
			}
		}
}