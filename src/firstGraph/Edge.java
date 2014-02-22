package firstGraph;

public class Edge implements Comparable<Edge>{
	private int id;
	private int start;
	private int end;
	private int cost;
	
	public Edge(){
		this.id=0;
		this.start=0;
		this.end=0;
		this.cost=0;
	}
	
	public Edge(int id,int s,int e,int c){
		this.id=id;
		this.start=s;
		this.end=e;
		this.cost=c;
	}
	
	public void setData(int id,int s,int e,int c){
		this.id=id;
		this.start=s;
		this.end=e;
		this.cost=c;
	}
	
	public int getEnd(){
		return this.end;
	}
	
	public int getStart(){
		return this.start;
	}
	
	public int getID(){
		return this.id;
	}
	
	public void setCost(int cost){
		this.cost=cost;
	}
	
	public int getCost(){
		return this.cost;
	}
	
	@Override
	public int compareTo(Edge edge){
    	//== is not compared so that duplicate values are not eliminated. 
    	return (this.getCost() < edge.getCost()) ? -1: 1;
    }
	
	

	
	
}
