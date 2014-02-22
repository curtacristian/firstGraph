package firstGraph;
import java.util.*;

public class Vertex {
	private int id;
	private List<Edge> inbound;
	private List<Edge> outbound;
	private boolean visited=false;
	
	public Vertex(){
		this.id=0;
		this.inbound=new ArrayList<Edge>(10);
		this.outbound=new ArrayList<Edge>(10);
	}
	
	public Vertex(int id){
		this.id=id;
		this.inbound=new ArrayList<Edge>(10);
		this.outbound=new ArrayList<Edge>(10);
	}

	public boolean getVis(){
		return this.visited;
	}
	
	
	public void setVis(boolean b){
		this.visited=b;
	}
	
	public int getID(){
		return this.id;
	}
	
	public int getInArity(){
		return this.inbound.size();
	}
	
	public int getOutArity(){
		return this.outbound.size();
	}
	
	public List<Edge> getInbound(){
		return inbound;
	}
	
	public List<Edge> getOutbound(){
		return outbound;
	}
	
	public Vertex getVertex(int id){
		if(id==this.getID())
			return this;
		return null;
	}
	
	public void setInbound(List<Edge> in){
		this.inbound=in;
	}
	
	public void setOutbound(List<Edge> out){
		this.outbound=out;
	}
	
	public boolean existsInbound(Edge E){
		if(this.inbound!=null)
			if(this.inbound.contains(E))
				return true;
		return false;
	}
	
	public boolean existsOutbound(Edge E){
		if(this.outbound!=null)
			if(this.outbound.contains(E))
				return true;
		return false;
	}
	
	public boolean addOutbound(Edge E){
		if(!existsOutbound(E)){
			this.outbound.add(E);
			return true;
		}
		return false;
		
	}
	
	public boolean addInbound(Edge E){
		if(!existsInbound(E)){	
			this.inbound.add(E);
			return true;
		}
		return false;
	}
	
	public boolean isequal(Vertex v){
		return this.getID()==v.getID();
			
	}
	
	
	
	
	
	
	
	
	
}
