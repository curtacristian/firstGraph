package firstGraph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

public class Ui {
	
	private String filename;
	private Graph FileGraph;
	
	public Ui(String f){
		this.filename=f;
		this.FileGraph=new Graph();
	};
	
	
	public void readFromFile(){
			BufferedReader br=null;;
			try {
				br = new BufferedReader(new FileReader(filename));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String line;
			String[] tokens;
			int lineNo = 0,edgeNo,edgeID,sourceVertex,targetVertex,EdgeCost,verticeNo;
			try {
				while((line = br.readLine()) != null){
					if(lineNo == 0){
						tokens = line.split(" ");
							// initialize the number of vertices 
						verticeNo=Integer.parseInt(tokens[0]);
						this.FileGraph.initVertices(verticeNo);
						edgeNo = Integer.parseInt(tokens[1]);
						
					}
					else{
							tokens = line.split(" ");
							edgeID = Integer.valueOf(tokens[0]).intValue();
							sourceVertex = Integer.valueOf(tokens[1]).intValue();
							targetVertex = Integer.valueOf(tokens[2]).intValue();
							EdgeCost=Integer.valueOf(tokens[3]).intValue();
							
							Edge edge=new Edge(edgeID,sourceVertex,targetVertex,EdgeCost);
							this.FileGraph.addEdge(edge);
							
						}
						
					lineNo++;
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	
	public void printMenu(){
		System.out.println("Welcome to Graph");
		System.out.println("1.Number of Vertices");
		System.out.println("2.Edge between 2 vertices");
		System.out.println("3.In degree and out degree of a vertex");
		System.out.println("4.Get inbound");
		System.out.println("5.Get outbound");
		System.out.println("6.Get endpoints of an edge");
		System.out.println("7.Set edge cost");
		System.out.println("8.Reverse BFS");
		System.out.println("9.Reverse Dijkstra");
		System.out.println("10.Kruskal");
		System.out.println("11.Hamiltonian Cycle");
		System.out.println("12.Exit app");
	}
	
	
	public void runUI(){
		readFromFile();
		int option=0,id1=0,id2=0,i;
		int [] array;
		boolean b=true;
		Scanner input=new Scanner(System.in);
		while(b){
			printMenu();
			System.out.print("Gibe option:");
			option=input.nextInt();
			switch(option){
			case 1:
				System.out.printf("Number of vertices is %d\n", this.FileGraph.getNumberOfVertices());
				break;
			case 2:
				System.out.print("Vertice 1:");
				if(input.hasNextInt()){
					id1=input.nextInt();
				}	
				System.out.print("Vertice 2:");
				if(input.hasNextInt()){
					id2=input.nextInt();
				}
				id1=this.FileGraph.existsEdge(id1,id2);
				System.out.printf("Edge id:%d\n",id1);
				break;
			case 3:
				System.out.print("Vertice :");
				if(input.hasNextInt()){
					id1=input.nextInt();
				}
				array=this.FileGraph.getDegreeVertex(id1);
				System.out.printf("In degree:%d\n", array[0]);
				System.out.printf("Out degree:%d\n", array[1]);
				break;
			case 4:
				System.out.print("Vertice :");
				if(input.hasNextInt()){
					id1=input.nextInt();
				}
				array=this.FileGraph.getInboundEdges(id1);
				System.out.print("Inbound edges(by id):");
				for(i=0;i<array.length;i++){
					System.out.printf("%d ",array[i]);
				}
				break;
			case 5:
				System.out.print("Vertice :");
				if(input.hasNextInt()){
					id1=input.nextInt();
				}
				array=this.FileGraph.getOutboundEdges(id1);
				System.out.print("Outbound edges(by id):");
				for(i=0;i<array.length;i++){
					System.out.printf("%d ",array[i]);
				}
				System.out.print("\n");
				break;
			case 6:
				System.out.print("Give Edge id :");
				if(input.hasNextInt()){
					id1=input.nextInt();
				}
				array=this.FileGraph.getEndEdges(id1);
				System.out.printf("Start:%d\n", array[0]);
				System.out.printf("End:%d\n", array[1]);
				break;
			case 7:
				System.out.print("Edge :");
				if(input.hasNextInt()){
					id1=input.nextInt();
				}
				System.out.print("New Cost :");
				if(input.hasNextInt()){
					id2=input.nextInt();
				}
				this.FileGraph.findEdgeByID(id1).setCost(id2);
				break;
			case 8:
				System.out.print("Ending in:");
				if(input.hasNextInt()){
					id1=input.nextInt();
				}
				System.out.print("From:");
				if(input.hasNextInt()){
					id2=input.nextInt();
				}
				this.FileGraph.BFS(id1,id2);
				System.out.print("\n");
				break;
			case 9:
				System.out.print("Ending in:");
				if(input.hasNextInt()){
					id1=input.nextInt();
				}
				System.out.print("From:");
				if(input.hasNextInt()){
					id2=input.nextInt();
				}
				this.FileGraph.Dijkstra(id1,id2);
				System.out.print("\n");
				break;
			case 10:
				System.out.println("Printing minimum spanning tree");
				TreeSet<Edge> edges=this.FileGraph.Kruskal();
				int total=0;
				for(Edge e: edges){
					System.out.printf("%d->%d ",e.getStart(),e.getEnd());
					total+=e.getCost();
				}
				System.out.printf("\nTotal cost:%d\n",total);
				break;
			case 11:
				System.out.println("Searching Hamiltonian cycle ! ! !");
				this.FileGraph.Hamiltonian();
				break;
			case 12:
				b=false;
				break;
			default:
				System.out.println("Wrong choice.Try again!");
				break;
			}
		
		
		
		
	}
	input.close();
	}
}
	
			
