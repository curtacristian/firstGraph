package firstGraph;

import java.util.Arrays;
import java.util.List;

public class Hamilton {
	private Graph g;
	private int [] path;
	private int pathCount;
	
	Hamilton(Graph x){
		this.g=x;
		this.path=new int[this.g.numberOfVertices()];
	}
	
	
	public void searchCycle(){
		Arrays.fill(path, -1);
		String s=Arrays.toString(path);
		System.out.printf("\nInitial path:%s\n",s);
		try{
			path[0]=0;
			pathCount=1;
			cycleUtil(0);
			System.out.println("\nNo solution!");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			printSolution();
		}
		
	}
	
	public void cycleUtil(int pos)throws Exception{
		
		if(this.g.existsEdge(pos,0)!=-1&&this.pathCount==this.path.length) throw new Exception("Solution found!");
		if(this.pathCount==this.path.length) return;
		for(int v=0;v<this.path.length;v++){
			if(this.g.existsEdge(pos, v)!=-1){
				path[pathCount++]=v;
				
				Edge e=this.g.getEdge(this.g.existsEdge(pos, v));
				this.g.RemoveEdge(e.getID());
				
				if(!isSafe(v)) cycleUtil(v);
				this.g.addEdge(e);
				path[--pathCount]=-1;
				
			}
		}
		
		
	}
	
	public boolean isSafe(int v){
		for(int i=0;i<this.pathCount-1;i++){
			if(path[i]==v){
				return true;
			}
		}
		return false;
		
	}
	
	public void printSolution(){
		System.out.print("Solution exists!");
		String s=Arrays.toString(path);
		System.out.printf("\nFinal path:%s\n\n",s);
	}
	
	
}
