package firstGraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSearch {
	
	private Graph g;
	
	private int parents[];
	private int distTo[];
	private boolean touch;
	
	public BFSearch(Graph g){
		this.g=g;
		this.parents=new int[g.getNumberOfVertices()];
		for(int i=0;i<g.getNumberOfVertices();i++){
			this.parents[i]=-1;
		}
		this.distTo=new int[g.getNumberOfVertices()];
		for(int i=0;i<g.getNumberOfVertices();i++){
			this.distTo[i]=32767;
		}
	}
	
	public void bfs(Vertex v,Vertex v2){
		Queue <Vertex >q = new LinkedList<Vertex>();
		q.add(v);
		int i;
		Vertex adj;
		Edge aux;
		v.setVis(true);
		while(!q.isEmpty()){
			Vertex n = (Vertex)q.poll();
			List<Edge> l=new ArrayList<Edge>();
			l=n.getInbound();
			for(i=0;i<l.size();i++){
				aux=l.get(i);
				adj=this.g.findVertexByID(aux.getStart());
				if(!adj.getVis()){
					parents[adj.getID()]=n.getID();
					adj.setVis(true);
					q.add(adj);
				}
				
			}
		}
		if(v2.getVis()) touch=true;
		
		
	}
	public void pathTo(int id){
		int x=id;
		String msg="";
		if(touch){
			while(x!=-1){
				x=parents[x];
				msg+=Integer.toString(x)+" ";
			}
		}
		
		if(msg.equals("")){
			System.out.print("Target cannot be reached!");
			return;
		}	
		System.out.printf(" " +msg);
	}
	
	

}
