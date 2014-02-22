package firstGraph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Dijkstra {
	private Graph g;
	private int [] dist;
	private int [] parents;
	private boolean touch=false;
	
	public Dijkstra(Graph g){
		this.g=g;
		this.parents=new int[g.getNumberOfVertices()];
		for(int i=0;i<g.getNumberOfVertices();i++){
			this.parents[i]=-1;
		}
		this.dist=new int[g.getNumberOfVertices()];
		for(int i=0;i<g.getNumberOfVertices();i++){
			this.dist[i]=32767;
		}
	}

	public void reverse(Vertex source,Vertex dest){
		dist[source.getID()]=0;
		HashSet <Vertex>q = new HashSet<Vertex>();
		q.add(source);
		int i,alt=0;
		Vertex adj;
		Edge aux;
		while(!q.isEmpty()){
			Vertex current = getMin(q);
			q.remove(getMin(q));
			current.setVis(true);
			List<Edge> l=new ArrayList<Edge>();
			l=current.getInbound();
			for(i=0;i<l.size();i++){
				aux=l.get(i);
				alt=this.dist[current.getID()]+aux.getCost();
				adj=this.g.findVertexByID(aux.getStart());
				if(alt<dist[adj.getID()]){
					parents[adj.getID()]=current.getID();
					dist[adj.getID()]=alt;
				}
				if(!adj.getVis()){
					q.add(adj);
				}
			}
		}
		if(dest.getVis()==true){
			touch=true;
		}
	}
	
	public Vertex getMin(HashSet<Vertex> q){
		int min=32765,aux2=-1;
		Vertex aux=new Vertex();
		for(int i=0;i<dist.length;i++){
			if(min>dist[i]){
				if(this.g.findVertexByID(i).getVis()==false){
				min=dist[i];
				aux2=i;
				}
			}
		}
		aux=this.g.findVertexByID(aux2);
		return aux;
		
	}
		
		
	public void pathTo(int id){
		int x=id;
		int cost=0;
		String msg="";
		if(touch){
			while(x!=-1){
				msg+=Integer.toString(x)+" ";
				if(parents[x]!=-1){
					cost+=this.g.getEdgeCost(x, parents[x]);
				}
				x=parents[x];
			}
		}
		else{
			System.out.print("Target cannot be reached!");
			return;
		}
		System.out.print("Parents:\n");
		for(int i=0;i<dist.length;i++){
			System.out.printf("%d:%d\n", i,parents[i]);
		}
		System.out.printf("Walk:\n"+msg);
		System.out.printf("\nTotal cost is %d",cost);
	}
	
	
	
}
