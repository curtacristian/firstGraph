package firstGraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.TreeSet;



public class Graph {
	private List<Vertex> vertices;
	private List<Edge> edges;
	private ListIterator<Edge> iterator;
	private ListIterator<Vertex> iterator2;
	
	public Graph(){
		this.vertices=new ArrayList<Vertex>();
		this.edges=new ArrayList<Edge>();
	}
	
	public Graph(List<Vertex> v,List<Edge> e){
		this.vertices=v;
		this.edges=e;
	}
	
	public List<Edge> getEdges(){
		return this.edges;
	}
	
	public List<Vertex> getVertices(){
		return this.vertices;
	}
	
	public int getVerticesSize(){
		return this.vertices.size();
	}
	
	public void setEdges(List<Edge> e){
		this.edges=e;
	}
	
	public void setVertices(List<Vertex> v){
		this.vertices=v;
	}
	
	public int numberOfVertices(){
		return this.vertices.size();
	}
	
	public void initVertices(int verticesNo){
		for(int i = 0; i < verticesNo; i++)
			this.vertices.add(new Vertex(i));
	}
	
	public void addEdge(Edge e){
		Vertex start=findVertexByID(e.getStart());
		Vertex end=findVertexByID(e.getEnd());
		if(start!=null&&end!=null){
			start.addOutbound(e);
			end.addInbound(e);
		}
		this.edges.add(e);
	}
	
	public Edge getEdge(int id){
		for(Edge e:this.getEdges()){
			if(e.getID()==id)
				return e;
		}
		return null;
	}
	
	public void RemoveEdge(int id){
		int rank=0;
		for(Edge e:this.getEdges()){
			if(e.getID()==id){
				this.edges.remove(rank);
				return;
			}
			rank++;
		}
	}
	
	
	
	
	public int[] getEndEdges(int id){
		int [] ends=new int[2];
		ends[0]=0;
		ends[1]=0;
		for(Edge currentEdge:edges){
			if(currentEdge.getID()==id){
				ends[0]=currentEdge.getStart();
				ends[1]=currentEdge.getEnd();
			}
		}
		return ends;
	}
	
	public int existsEdge(int v1,int v2){
		List<Edge> currentEdge=this.vertices.get(v1).getOutbound();
		for(int i=0;i<currentEdge.size();i++){
				if(currentEdge.get(i).getEnd()==v2)
					return currentEdge.get(i).getID();
		}
		return -1;
	}
	
	public Vertex findVertexByID(int id){
		for(Vertex currentVertex:vertices){
			if(currentVertex.getID()==id){
				return currentVertex;
			}
		}
		return null;
	}
	
	public Edge findEdgeByID(int id){
		for(Edge currentEdge:edges){
			if(currentEdge.getID()==id){
				return currentEdge;
			}
		}
		return null;
	}
	
	
	public int[] getDegreeVertex(int id){
		int [] Degree=new int [2];
		if(findVertexByID(id)!=null){
			Vertex v=new Vertex();
			v=findVertexByID(id);
			Degree[0]=v.getInArity();
			Degree[1]=v.getOutArity();
		}
		return Degree;
	}
	
	public int getNumberOfVertices(){
		return this.vertices.size();
	}

	public int [] getInboundEdges(int id){
		Edge e=new Edge();
		int [] array=new int[findVertexByID(id).getInArity()];
		int i=0;
		for( iterator=this.edges.listIterator() ; iterator.hasNext();){
				e=iterator.next();
				if(e.getEnd()==id){
					array[i]=e.getID();
					i++;
				}
		}
		return array;
			
			
	}
	
	public int [] getOutboundEdges(int id){
		Edge e=new Edge();
		int [] array=new int[findVertexByID(id).getOutArity()];
		int i=0;
		for( iterator=this.edges.listIterator() ; iterator.hasNext();){
				e=iterator.next();
				if(e.getStart()==id){
					array[i]=e.getID();
					i++;
				}
		}
		return array;
			
			
	}
	
	public int getEdgeCost(int id1,int id2){
		Edge e=new Edge();
		for( iterator=this.edges.listIterator() ; iterator.hasNext();){
			e=iterator.next();
			if(e.getStart()==id1&&e.getEnd()==id2){
				return e.getCost();
			}
		}
		return 0;
		
	}
	
	
	public void emptyViz(){
		Vertex v=new Vertex();
		for(iterator2 =this.vertices.listIterator();iterator2.hasNext();){
			v=iterator2.next();
			v.setVis(false);
		}
		
	}
	
	public List<Edge> orderedEdges(){
	
		List<Edge> order=new ArrayList<Edge>();
		order=this.edges;
		Collections.sort(order);
		return order;
	
	}
	
	public void BFS(int id,int id2){
		emptyViz();
		BFSearch bf=new BFSearch(this);
		bf.bfs(this.findVertexByID(id),this.findVertexByID(id2));
		bf.pathTo(id2);
	}
	
	public void Dijkstra(int id,int id2){
		emptyViz();
		Dijkstra d=new Dijkstra(this);
		d.reverse(this.findVertexByID(id),this.findVertexByID(id2));
		d.pathTo(id2);
	}
	public TreeSet<Edge> Kruskal(){
		Kruskal k=new Kruskal(this);
		return k.getEdges();
	}
	
	public void Hamiltonian(){
		Hamilton H=new Hamilton(this);
		H.searchCycle();
	}
	
	
}
